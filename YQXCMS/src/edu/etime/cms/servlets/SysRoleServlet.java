package edu.etime.cms.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.etime.cms.pojo.PageBean;
import edu.etime.cms.pojo.ReturnBean;
import edu.etime.cms.pojo.SysRole;
import edu.etime.cms.services.impl.SysRoleServiceImpl;
import edu.etime.cms.services.interfaces.SysRoleService;
import edu.etime.cms.utils.PageTools;

/**
 * 角色管理
 * @author 1
 *
 */
@WebServlet("/sys/role")
public class SysRoleServlet extends HttpServlet{
	private SysRoleService service = new SysRoleServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		if("toList".equals(cmd)) {
			list(req,resp);
		}else if("del".equals(cmd)) {
			del(req,resp);
		}else if("add".equals(cmd)) {
			add(req,resp);
		}else if("edit".equals(cmd)) {
			edit(req,resp);
		}else if("toEdit".equals(cmd)) {
			toEdit(req,resp);
		}else if("getAll".equals(cmd)) {
			getAll(req,resp);
		}
	}
	/**
	 * 获得所有的角色
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void getAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<SysRole> all = service.getAll();
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(all);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resp.getWriter().write(json);
		
		
	}
	/**
	 * 跳转到修改界面
	 * @param req
	 * @param resp
	 */
	private void toEdit(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, String[]> map = req.getParameterMap();
		SysRole role = new SysRole();
		try {
			BeanUtils.populate(role, map);
			req.setAttribute("role", role);
			req.getRequestDispatcher("/admin/role/edit.jsp").forward(req, resp);
		} catch (IllegalAccessException | InvocationTargetException e) {
			
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 修改角色信息
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String rid = req.getParameter("rid");
		String rname = req.getParameter("rname");
		String rdesc = req.getParameter("rdesc");
		int rstate = Integer.parseInt(req.getParameter("rstate"));
		SysRole role = new SysRole(rid, rname, rdesc,rstate);
		boolean success = service.edit(role);
		req.setAttribute("msg", success?"修改成功":"修改失败");
		req.getRequestDispatcher("/admin/role/edit.jsp").forward(req, resp);
		
		
	}
	/**
	 * 添加角色
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("rname");
		String desc = req.getParameter("rdesc");
		String id = UUID.randomUUID().toString();
		//创建一个role
		SysRole role = new SysRole(id,name,desc,1);
		ReturnBean rtn = service.add(role);
		req.setAttribute("rtn", rtn);
		req.getRequestDispatcher("/admin/role/add.jsp").forward(req, resp);
		
	}
	/**
	 * 弃用角色
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] rids = req.getParameterValues("rid");
		service.deleteRoleByIds(rids);
		req.getRequestDispatcher("/sys/role?cmd=toList").forward(req, resp);
		
	}
	/**
	 * 得到角色列表
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String[]> map = req.getParameterMap();
		PageBean<SysRole> page = service.getPage(map);
		String url = PageTools.parseUrl(req);
		req.setAttribute("pb", page);
		req.setAttribute("url", url);
		req.getRequestDispatcher("/admin/role/list.jsp").forward(req, resp);;
		
	}


}
