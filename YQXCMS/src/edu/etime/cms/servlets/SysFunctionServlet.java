package edu.etime.cms.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
/**
 * 系统功能管理
 * @author 1
 *
 */
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.etime.cms.dto.SysFunctionDto;
import edu.etime.cms.pojo.ReturnBean;
import edu.etime.cms.pojo.SysFunction;
import edu.etime.cms.services.impl.SysFunctionServiceImpl;
import edu.etime.cms.services.interfaces.SysFunctionService;
@WebServlet("/sys/fun")
public class SysFunctionServlet extends HttpServlet{
	//注入业务层对象
	private SysFunctionService service = new SysFunctionServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		if ("toList".equals(cmd)) {
			list(req,resp);
		}else if ("save".equals(cmd)) {
			save(req,resp);
		}else if("permission".equals(cmd)) {
			listPermission(req,resp);
		}else if("listByRid".equals(cmd)) {
			listByRid(req,resp);
		}else if("root".equals(cmd)) {
			rootList(req,resp);
		}
	}
	/**
	 * 保存功能
	 * @param req
	 * @param resp
	 */
	private void save(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, String[]> map = req.getParameterMap(); 
		SysFunction fun = new SysFunction();
		try {
			BeanUtils.populate(fun, map);
			ReturnBean rb = service.save(fun);
			req.setAttribute("rb", rb);
			req.getRequestDispatcher("/sys/fun?cmd=toList").forward(req, resp);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 修改系统功能
	 * @param req
	 * @param resp
	 */
	/*
	 * private void edit(HttpServletRequest req, HttpServletResponse resp) {
	 * Map<String, String[]> map = req.getParameterMap(); SysFunction fun = new
	 * SysFunction(); try { BeanUtils.populate(fun, map); boolean isSuccess =
	 * service.editFunction(fun); req.setAttribute("msg", isSuccess?"修改成功":"修改失败");
	 * 
	 * req.getRequestDispatcher("/sys/fun?cmd=toList").forward(req, resp); } catch
	 * (IllegalAccessException | InvocationTargetException e) { e.printStackTrace();
	 * } catch (ServletException e) { e.printStackTrace(); } catch (IOException e) {
	 * e.printStackTrace(); }
	 * 
	 * 
	 * 
	 * }
	 */
	/**
	 * 返回根节点列表
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void rootList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<SysFunction> rootList = service.getRootList();
		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(rootList);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		resp.getWriter().write(json);
		
	}
	/**
	 * 根据角色id获得功能列表
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void listByRid(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String rid = req.getParameter("rid");
		List<SysFunctionDto> list = service.getListByRid(rid);
		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		resp.getWriter().write(json);
		
		
	}
	/**
	 * 返回权限列表(即功能列表)
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void listPermission(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<SysFunctionDto> list = service.getList();
		String roleid = req.getParameter("rid");
		//查询该用户所拥有的功能
		List<SysFunctionDto> list2 = service.getListByRid(roleid);
		//判断dto集合中的功能是否被role拥有,并标记
		for (SysFunctionDto fundto : list) {
			for (SysFunctionDto fundto2 : list2) {
				if(fundto.getFid().equals(fundto2.getFid())) {
					fundto.setIsRoleFun(1);
				}
			}
		}
		req.setAttribute("list", list);
		req.setAttribute("roleid", roleid);
		
		req.getRequestDispatcher("/admin/fun/permission.jsp").forward(req, resp);
	}
	/**
	 * 添加系统功能
	 * @param req
	 * @param resp
	 */
	/*
	 * private void add(HttpServletRequest req, HttpServletResponse resp) { //获得参数
	 * Map<String, String[]> map = req.getParameterMap(); SysFunction fun = new
	 * SysFunction(); try { BeanUtils.populate(fun, map); ReturnBean rb =
	 * service.add(fun); ObjectMapper mapper = new ObjectMapper(); //返回信息 String
	 * jsonString = mapper.writeValueAsString(rb);
	 * //resp.setContentType("application/json;chaset=utf-8");
	 * resp.getWriter().write(jsonString);
	 * 
	 * } catch (IllegalAccessException | InvocationTargetException e) {
	 * e.printStackTrace(); } catch (JsonGenerationException e) {
	 * e.printStackTrace(); } catch (JsonMappingException e) { e.printStackTrace();
	 * } catch (IOException e) { e.printStackTrace(); } }
	 */
	/**
	 * 得到系统功能列表
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<SysFunctionDto> list = service.getList();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/admin/fun/sysfun.jsp").forward(req, resp);
		
	}
}
