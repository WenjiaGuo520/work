package edu.etime.cms.utils;
/**
 * DBUtils的封装类
 * @author gwj
 */

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DBHelper {
	
	private static DataSource ds;
	
	//加载配置文件,创建连接池
	static {
		Properties prop = new Properties();
		InputStream is = DBHelper.class.getClassLoader().getResourceAsStream("druid.properties");
		
		
		try {
			prop.load(is);
			ds = DruidDataSourceFactory.createDataSource(prop);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
	public static DataSource getDataSource() {
		return ds;
	}
	/**
	 * 	封装DBUtils的query方法,返回list集合
	 * @param <T>
	 * @param sql
	 * @param cls
	 * @param objs
	 * @return
	 */
	public static <T> List<T> queryForList(String sql,Class<T> cls,Object...objs){
		List<T> list = null;
		try {
			QueryRunner qr = new QueryRunner(ds);
			
			if(objs!=null && objs.length>0) {
				list = qr.query(sql, new BeanListHandler<T>(cls),objs);
			}else {
				list = qr.query(sql, new BeanListHandler<T>(cls));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	

	/**
	 * 	查询数据,封装到T类型中返回
	 * @param <T>
	 * @param sql
	 * @param cls
	 * @param objs
	 * @return
	 */
	public static <T> T queryForObject(String sql,Class<T> cls,Object...objs) {
		T t = null;
		try {
			QueryRunner qr = new QueryRunner(ds);
			
			if(objs!=null) {
				t = qr.query(sql, new BeanHandler<T>(cls),objs);
			}else {
				t = qr.query(sql, new BeanHandler<T>(cls));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return t;
	}
	/**
	 * 聚合函数(查询单行单列的聚合函数返回值)
	 * @param sql
	 * @param objs
	 * @return Number类型
	 */
	public static Number queryForNumber(String sql,Object...objs) {
		QueryRunner qr = new QueryRunner(ds);
		Long n = null;
		try {
			if(objs!=null) {
				n = qr.query(sql, new ScalarHandler<Long>(),objs);
			}else {
				n = qr.query(sql, new ScalarHandler<Long>());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}
	public static int update(String sql,Object...objs) {
		int rtn = 0;
		QueryRunner qr = new QueryRunner(ds);
		try {
			
			if(objs!=null) {
				rtn = qr.update(sql, objs);
			}else {
				rtn = qr.update(sql);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rtn;
	}
	
}
