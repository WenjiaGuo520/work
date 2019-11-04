package edu.etime.cms.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import edu.etime.cms.pojo.User;
import edu.etime.cms.utils.DBHelper;

/**
 * 测试DBHelper
 * @author 1
 *
 */
public class DBHelperTest {
	
	@Test
	public void testDs() {
		String sql = "select count(tid) from arttype";
		int c = DBHelper.queryForNumber(sql).intValue();
		System.out.println(c);
	}
	@Test
	public void testUpdate() {
		String sql = "delete from vipuser where uid=?";
		int count = DBHelper.update(sql, 1);
		System.out.println(count);
	}
	
	@Test
	public void testBeanUtils() throws IllegalAccessException, InvocationTargetException {
		Map<String, String[]> map =new HashMap<String, String[]>();
		map.put("cmd", new String[] {"add"});
		map.put("username", new String[] {"zhangsan"});
		User user = new User();
		BeanUtils.populate(user, map);
		System.out.println(user);
		
	}
}
