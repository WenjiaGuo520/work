package edu.etime.cms.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
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

import edu.etime.cms.pojo.ArtType;
import edu.etime.cms.pojo.PageBean;
import edu.etime.cms.services.impl.ArtTypeServiceImpl;
import edu.etime.cms.services.interfaces.ArtTypeService;

/**
 * 	文章类型servlet
 */
@WebServlet("/art/type")
public class ArtTypeServlet extends HttpServlet {
	//注入service业务逻辑层对象
	private ArtTypeService service = new ArtTypeServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp){
		String cmd = req.getParameter("cmd");
		if("add".equals(cmd)) {
			addArtType(req,resp);
		}else if("list".equals(cmd)) {
			list(req,resp);
		}else if("toList".equals(cmd)) {
			toList(req,resp);	
		}else if("del".equals(cmd)) {	
			del(req,resp);
		}else if ("toEdit".equals(cmd)) {
			toEdit(req,resp);
		}else if ("edit".equals(cmd)) {
			edit(req,resp);
		}else if("nav".equals(cmd)) {
			nav(req,resp);
		}
	}
	/**
	 * 获取导航栏列表
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void nav(HttpServletRequest req, HttpServletResponse resp){
		List<ArtType> list = service.getNav();
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json= mapper.writeValueAsString(list);
			resp.getWriter().write(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	/**
	 * 修改文章类型
	 * @param req
	 * @param resp
	 */
	private void edit(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, String[]> map = req.getParameterMap();
		ArtType type = new ArtType();
		try {
			BeanUtils.populate(type, map);
			boolean flag = service.edit(type);
			if (flag) {
				req.setAttribute("msg", "修改成功");
			}else {
				req.setAttribute("msg", "修改失败");
			}
			req.getRequestDispatcher("/admin/arttype/edit.jsp").forward(req, resp);
		} catch (IllegalAccessException | InvocationTargetException e) {
			
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	/**
	 * 跳转到修改文章类型界面
	 * @param req
	 * @param resp
	 */
	private void toEdit(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, String[]> map = req.getParameterMap();
		req.setAttribute("condition", map);
		try {
			req.getRequestDispatcher("/admin/arttype/edit.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * toList方法,相当于list方法没有参数.(map为空)
	 * @param req
	 * @param resp
	 */
	private void toList(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		PageBean<ArtType> pb = service.getArtTypeList(map);
		//存储到request域
        req.setAttribute("pb",pb);
        //存储查询条件
        req.setAttribute("condition",map);
        //转发
        try {
			req.getRequestDispatcher("/admin/arttype/list.jsp").forward(req,resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 	删除指定的文章类型,假删除,只是改变其状态
	 * @param req
	 * @param resp
	 */
	private void del(HttpServletRequest req, HttpServletResponse resp) {
		//得到封装有id的参数
		String[] tids = req.getParameterValues("tid");
		boolean flag = service.delTypeById(tids);
		
		
		try {
			req.getRequestDispatcher("/art/type?cmd=toList").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * arttype数据列表
	 * @param req
	 * @param resp
	 */
	private void list(HttpServletRequest req, HttpServletResponse resp) {
		//得到参数
        Map<String, String[]> map = req.getParameterMap();
        PageBean<ArtType> pb= service.getArtTypeList(map);
        //存储到request域
        req.setAttribute("pb",pb);
        //存储查询条件
        req.setAttribute("condition",map);
        //转发
        try {
			req.getRequestDispatcher("/admin/arttype/list.jsp").forward(req,resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 增加文章类型
	 * @param req
	 * @param resp
	 */
	private void addArtType(HttpServletRequest req, HttpServletResponse resp) {
		//获得传入参数
		Map<String, String[]> parameterMap = req.getParameterMap();
		ArtType artType = new ArtType();
		//设置一个随机的id
		artType.setTid(UUID.randomUUID().toString());
		try {
			//通过BeanUtils将数据移植到artType对象
			BeanUtils.populate(artType, parameterMap);
			boolean success = service.addArtType(artType);
			//往request中存入状态信息
			if(success) {
				req.setAttribute("msg", "添加成功!");
			}else {
				req.setAttribute("msg", "添加失败!");
			}
			req.getRequestDispatcher("/admin/arttype/add.jsp").forward(req, resp);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
