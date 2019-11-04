package edu.learn.bms.utils;
/**
 * MySql封装
 * @author 1
 *
 */

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MySqlHelper {
	private static DataSource dataSource;
	// 加载c3p0配置文件
	static {
		dataSource = new ComboPooledDataSource();
	}

	/**
	 * 获得连接对象
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return dataSource == null ? null : dataSource.getConnection();
	}

	/**
	 * sql查询，封装到Bean类型的list集合中
	 * 
	 * @param sql
	 * @param clazz  要封装的Bean类型
	 * @param params 传入的参数
	 * @return
	 */
	public static <T> List<T> executeQueryForBeanList(String sql, Class<T> clazz, Object... params) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1.获得连接对象
			conn = getConnection();
			// 2.获得statement
			pstmt = conn.prepareStatement(sql);
			// 3.添加参数
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i + 1, params[i]);
				}
			}
			// 4.执行sql
			rs = pstmt.executeQuery();
			// 5.封装
			List<T> list = new ArrayList<>();
			Field[] fields = clazz.getDeclaredFields();
			while (rs.next()) {
				T t = clazz.getDeclaredConstructor().newInstance();
				for (Field field : fields) {
					// 设置忽略连接安全（设置可连接）
					if (checkeColumn(rs, field.getName())) {
						field.setAccessible(true);
						field.set(t, rs.getObject(field.getName()));
					}
				}
				list.add(t);
			}

			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return null;
	}

	public static <T> List<T> executeQueryForBeanList(Connection conn, String sql, Class<T> clazz, Object... params) throws Exception {
		// 1.获得连接对象
		conn = getConnection();
		// 2.获得statement
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// 3.添加参数
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}
		}
		// 4.执行sql
		ResultSet rs = pstmt.executeQuery();
		// 5.封装
		List<T> list = new ArrayList<>();
		Field[] fields = clazz.getDeclaredFields();
		while (rs.next()) {
			T t = clazz.getDeclaredConstructor().newInstance();
			for (Field field : fields) {
				// 设置忽略连接安全（设置可连接）
				if (checkeColumn(rs, field.getName())) {
					field.setAccessible(true);
					field.set(t, rs.getObject(field.getName()));
				}
			}
			list.add(t);
		}

		return list;

	}

	public static <T> List<T> executeQueryForBeanList(String sql, Class<T> clazz) {
		return executeQueryForBeanList(sql, clazz, null);
	}

	/**
	 * sql查询封装得到1个Bean对象，如果结果不是1个，会抛出ClassCastException
	 * 
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 */
	public static <T> T executeQueryForBean(String sql, Class<T> clazz, Object... params) {
		List<T> list = executeQueryForBeanList(sql, clazz, params);
		if (list == null || list.size() == 0)
			return null;
		if (list.size() == 1) {
			return list.get(0);
		} else {
			throw new ClassCastException("查询出的结果不是1个，不能转为" + clazz.getName());
		}

	}

	public static <T> T executeQueryForBean(Connection conn, String sql, Class<T> clazz, Object... params) throws Exception {
		List<T> list = executeQueryForBeanList(conn, sql, clazz, params);
		if (list == null || list.size() == 0)
			return null;
		if (list.size() == 1) {
			return list.get(0);
		} else {
			throw new ClassCastException("查询出的结果不是1个，不能转为" + clazz.getName());
		}
	}

	/**
	 * 得到单行单列值
	 * 
	 * @param sql
	 * @param obj
	 * @param params
	 * @return
	 */
	public static <T> T executeQueryForObject(String sql, Class<T> obj, Object... params) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 1.获得连接对象
		try {
			conn = getConnection();
			// 2.获得statement
			pstmt = conn.prepareStatement(sql);
			// 3.添加参数
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i + 1, params[i]);
				}
			}
			// 4.执行sql
			rs = pstmt.executeQuery();
			rs.next();
			Object object = rs.getObject(1);
			return (T) rs.getObject(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			close(rs, pstmt, conn);
		}
		return null;

	}

	/**
	 * 得到值 ，带事务
	 * 
	 * @param conn
	 * @param sql
	 * @param obj
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static <T> T executeQueryForObject(Connection conn, String sql, Class<T> obj, Object... params)
			throws SQLException {
		conn.setAutoCommit(false);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// 3.添加参数
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}
		}
		// 4.执行sql
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		Object object = rs.getObject(1);
		return (T) rs.getObject(1);
	}

	/**
	 * 
	 * @param sql
	 * @return
	 */
	public static int executeUpdate(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// 1.获得连接对象
			conn = getConnection();
			// 2.获得statement
			pstmt = conn.prepareStatement(sql);
			// 3.添加参数
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i + 1, params[i]);
				}
			}
			// 4.执行sql
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
		// DML语句失败
		return 0;
	}

	/**
	 * 释放资源，conn会还给连接池
	 * 
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	private static void close(ResultSet rs, Statement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 检验结果集中是否存在column列
	 * 
	 * @param rs
	 * @param column
	 * @return
	 */
	private static boolean checkeColumn(ResultSet rs, String column) {
		boolean flag = false;

		try {
			rs.findColumn(column);
			flag = true;
		} catch (SQLException e) {
			// e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static int executeUpdate(Connection conn, String sql, Object... params) throws SQLException {
		// 1.获得连接对象
		// conn = getConnection();
		// conn.setAutoCommit(false);
		// 2.获得statement
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// 3.添加参数
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}
		}
		// 4.执行sql
		return pstmt.executeUpdate();

	}
}
