package edu.etime.cms.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.etime.cms.dto.SysRoleDto;
import edu.etime.cms.pojo.SysFunction;
import edu.etime.cms.services.impl.SysPermissionServiceImpl;
import edu.etime.cms.services.interfaces.SysPermissionService;
/**
 * 权限操作
 * @author 1
 *
 */
@WebServlet("/sys/perm")
public class SysPermissionServlet extends HttpServlet {
	private SysPermissionService service = new SysPermissionServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		if("edit".equals(cmd)) {
			edit(req,resp);
		}
	}
	
	/**
	 * 给用户修改权限(即在权限表删除用户所有的权限,然后插入记录)
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获得参数
		Map<String, String[]> map = req.getParameterMap();
		SysRoleDto rd = new SysRoleDto();
		List<String> list = new ArrayList<String>();
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			if("cmd".equals(key)) {continue;}
			if ("rid".equals(key)) {
				rd.setRid(map.get(key)[0].toString());
			}
			if ("fid".equals(key)) {
				Collections.addAll(list, map.get(key));
			}
		}
		rd.setList(list);
		boolean flag = service.editPermission(rd);
		if(flag) {
			req.setAttribute("msg", "保存成功!");
		}else {
			req.setAttribute("msg", "请登录后再操作,保存失败!");
		}
		req.getRequestDispatcher("/sys/fun?cmd=permission").forward(req, resp);
		
	}
}
