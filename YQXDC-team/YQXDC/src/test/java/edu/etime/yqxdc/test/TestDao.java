package edu.etime.yqxdc.test;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.etime.yqxdc.dao.CustomerMapper;
import edu.etime.yqxdc.pojo.Customer;

@ContextConfiguration(locations = "classpath:spring-dao.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDao {

	@Autowired
	private CustomerMapper mapper;
	@Test
	public void testCustomer() {
		List<Customer> custom = mapper.selectCustomerByOpenid("omUbF5F4NeJaiWvbnJnoWOW48WQ0");
		System.out.println(custom);
	}
}
