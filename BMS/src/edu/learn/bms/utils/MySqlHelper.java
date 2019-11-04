package edu.learn.bms.utils;
/**
 * MySql��װ
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
	// ����c3p0�����ļ�
	static {
		dataSource = new ComboPooledDataSource();
	}

	/**
	 * ������Ӷ���
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return dataSource == null ? null : dataSource.getConnection();
	}

	/**
	 * sql��ѯ����װ��Bean���͵�list������
	 * 
	 * @param sql
	 * @param clazz  Ҫ��װ��Bean����
	 * @param params ����Ĳ���
	 * @return
	 */
	public static <T> List<T> executeQueryForBeanList(String sql, Class<T> clazz, Object... params) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1.������Ӷ���
			conn = getConnection();
			// 2.���statement
			pstmt = conn.prepareStatement(sql);
			// 3.��Ӳ���
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i + 1, params[i]);
				}
			}
			// 4.ִ��sql
			rs = pstmt.executeQuery();
			// 5.��װ
			List<T> list = new ArrayList<>();
			Field[] fields = clazz.getDeclaredFields();
			while (rs.next()) {
				T t = clazz.getDeclaredConstructor().newInstance();
				for (Field field : fields) {
					// ���ú������Ӱ�ȫ�����ÿ����ӣ�
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
		// 1.������Ӷ���
		conn = getConnection();
		// 2.���statement
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// 3.��Ӳ���
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}
		}
		// 4.ִ��sql
		ResultSet rs = pstmt.executeQuery();
		// 5.��װ
		List<T> list = new ArrayList<>();
		Field[] fields = clazz.getDeclaredFields();
		while (rs.next()) {
			T t = clazz.getDeclaredConstructor().newInstance();
			for (Field field : fields) {
				// ���ú������Ӱ�ȫ�����ÿ����ӣ�
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
	 * sql��ѯ��װ�õ�1��Bean��������������1�������׳�ClassCastException
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
			throw new ClassCastException("��ѯ���Ľ������1��������תΪ" + clazz.getName());
		}

	}

	public static <T> T executeQueryForBean(Connection conn, String sql, Class<T> clazz, Object... params) throws Exception {
		List<T> list = executeQueryForBeanList(conn, sql, clazz, params);
		if (list == null || list.size() == 0)
			return null;
		if (list.size() == 1) {
			return list.get(0);
		} else {
			throw new ClassCastException("��ѯ���Ľ������1��������תΪ" + clazz.getName());
		}
	}

	/**
	 * �õ����е���ֵ
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

		// 1.������Ӷ���
		try {
			conn = getConnection();
			// 2.���statement
			pstmt = conn.prepareStatement(sql);
			// 3.��Ӳ���
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i + 1, params[i]);
				}
			}
			// 4.ִ��sql
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
	 * �õ�ֵ ��������
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
		// 3.��Ӳ���
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}
		}
		// 4.ִ��sql
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
			// 1.������Ӷ���
			conn = getConnection();
			// 2.���statement
			pstmt = conn.prepareStatement(sql);
			// 3.��Ӳ���
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i + 1, params[i]);
				}
			}
			// 4.ִ��sql
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
		// DML���ʧ��
		return 0;
	}

	/**
	 * �ͷ���Դ��conn�ỹ�����ӳ�
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
	 * �����������Ƿ����column��
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
		// 1.������Ӷ���
		// conn = getConnection();
		// conn.setAutoCommit(false);
		// 2.���statement
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// 3.��Ӳ���
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}
		}
		// 4.ִ��sql
		return pstmt.executeUpdate();

	}
}
