package edu.etime.yqxdc.controllers.wxcontrollers;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.stat.TableStat.Mode;

import edu.etime.yqxdc.pojo.Customer;
import edu.etime.yqxdc.services.interfaces.CustomerService;

@RestController
@RequestMapping("/wx")
public class WXLoginController {

	@Autowired
	private CustomerService service;
	
	@RequestMapping("/login")
//	public void loginn(String openid,String nickname,Customer customer){
//		
//		int rtn = 0;
//		List<Customer> list = service.selectCusByOpenid(openid);
//		System.out.println(list);
//		if(list.size()>0){
//			rtn = 1;
//		}
//		customer.setCusid(UUID.randomUUID().toString());
//		customer.setNickname(nickname);
//		customer.setOpenid(openid);
//		rtn = service.insertCustomer(customer);
//	}
	public void login(HttpServletResponse response,HttpServletRequest request){
		
		String openid = request.getParameter("openid");
		String nickname = request.getParameter("nickname");
		System.out.println("openid:"+openid);
		System.out.println("nickname:"+nickname);
		int rtn = 0;
		List<Customer> list = service.selectCusByOpenid(openid);
		System.out.println(list);
		if(list.size()>0){
			rtn = 1;
		}else{
			Customer cus =new Customer();
			cus.setCusid(UUID.randomUUID().toString());
			cus.setOpenid(openid);
			cus.setNickname(nickname);
			rtn = service.insertCustomer(cus);
		}
		try {
			response.getWriter().println(rtn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	} 
}
