package edu.etime.cms.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.etime.cms.dto.SysUserDto;
import edu.etime.cms.pojo.PageBean;
import edu.etime.cms.pojo.ReturnBean;
import edu.etime.cms.pojo.User;
import edu.etime.cms.services.impl.UserServiceImpl;
import edu.etime.cms.services.interfaces.UserService;
import edu.etime.cms.utils.PageTools;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/sys/user")
public class UserServlet extends HttpServlet {
	 private UserService service = new UserServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		if("toLogin".equals(cmd)){
			toLogin(req,resp);
		}else if("login".equals(cmd)) {
			login(req,resp);
		}else if("toList".equals(cmd)) {
			list(req,resp);
		}else if("del".equals(cmd)) {
			del(req,resp);
		}else if("add".equals(cmd)) {
			add(req,resp);
		}else if("edit".equals(cmd)) {
			edit(req,resp);
		}else if("check".equals(cmd)) {
			check(req,resp);
		}else if("toEdit".equals(cmd)) {
			toEdit(req,resp);
		}
	}
	/**
	 * 	跳转到修改界面
	 * @param req
	 * @param resp
	 */
	private void toEdit(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, String[]> map = req.getParameterMap();
		SysUserDto user = new SysUserDto();
		try {
			BeanUtils.populate(user, map);
			req.setAttribute("user", user);
			req.getRequestDispatcher("/admin/user/edit.jsp").forward(req, resp);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void check(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		int isExists = service.selectIsExists(username);
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("isExists", isExists>0);
		String json = "";
		try {
			json = mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		resp.getWriter().write(json);
		
	}
	/**
	 * 	修改用户
	 * @param req
	 * @param resp
	 */
	private void edit(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, String[]> map = req.getParameterMap();
		User user = new User();
		try {
			BeanUtils.populate(user, map);
			
			boolean isSuccess = service.editUser(user);
			req.setAttribute("msg", isSuccess?"修改成功":"修改失败!");
			req.getRequestDispatcher("/admin/user/edit.jsp").forward(req, resp);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 添加用户
	 * @param req
	 * @param resp
	 */
	private void add(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, String[]> map = req.getParameterMap();
		User user = new User();
		user.setUserid(UUID.randomUUID().toString());
		try {
			BeanUtils.populate(user, map);
			boolean flag = service.addUser(user);
			req.setAttribute("msg", flag?"添加成功":"添加失败");
			req.getRequestDispatcher("/admin/user/add.jsp").forward(req, resp);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 删除用户,即修改用户状态为不可用
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] userids = req.getParameterValues("userid");
		service.deleteUserById(userids);
		req.getRequestDispatcher("/sys/user?cmd=toList").forward(req, resp);
	}
	/**
	 * 	显示角色列表
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PageBean<SysUserDto> page = service.getPage(req.getParameterMap());
		req.setAttribute("pb", page);
		String url = PageTools.parseUrl(req);
		req.setAttribute("url", url);
		req.getRequestDispatcher("/admin/user/list.jsp").forward(req, resp);
		
	}
	/**
	 * 登录方法
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String username = req.getParameter("username");
		String userpwd = req.getParameter("userpwd");
		User user = service.login(username, userpwd);
		if(user!=null) {
			req.getSession().setAttribute("login_user", user);
			resp.sendRedirect(req.getContextPath()+"/admin/main.jsp");
		}else {
			req.setAttribute("login_msg","用户名或密码错误,登录失败!");
			req.getRequestDispatcher("/admin/login.jsp").forward(req, resp);
		}

		
	}
	/**
	 * 登录
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void toLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		User user = (User) req.getSession().getAttribute("login_user");
		//System.out.println(user);
		if (user!=null) {
			//System.out.println(user.getRid());
			resp.sendRedirect(req.getContextPath()+"/admin/main.jsp");
		}else {
			req.getRequestDispatcher("/sys/user?cmd=login").forward(req, resp);
		}
		
	}
	
	

}
