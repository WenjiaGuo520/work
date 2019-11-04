package edu.etime.cms.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.etime.cms.dto.ArticleDto;
import edu.etime.cms.pojo.ArtType;
import edu.etime.cms.pojo.Article;
import edu.etime.cms.pojo.PageBean;
import edu.etime.cms.pojo.User;
import edu.etime.cms.services.impl.ArtTypeServiceImpl;
import edu.etime.cms.services.impl.ArticleServiceImpl;
import edu.etime.cms.services.interfaces.ArtTypeService;
import edu.etime.cms.services.interfaces.ArticleService;
import edu.etime.cms.utils.DateTools;
import edu.etime.cms.utils.PageTools;

/**
 * 文章相关servlet
 * @author 1
 *
 */
@WebServlet("/art/news")
public class ArticleServlet extends HttpServlet{
	private ArticleService service = new ArticleServiceImpl();
	private ArtTypeService typeService = new ArtTypeServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		if ("add".equals(cmd)) {
			add(req,resp);
		}else if("toAdd".equals(cmd)) {
			toAdd(req,resp);
		}else if("getType".equals(cmd)) {
			typeList(req,resp);
		}else if("list".equals(cmd)) {
			list(req,resp);
		}else if ("del".equals(cmd)) {
			del(req,resp);
		}else if("toEdit".equals(cmd)){
			toEdit(req,resp);
		}else if ("edit".equals(cmd)) {
			edit(req,resp);
		}else if ("toList".equals(cmd)) {
			toList(req, resp);
		}else if("listByTid".equals(cmd)) {
			listByTid(req,resp);
		}else if("getByAid".equals(cmd)) {
			getByAid(req,resp);
		}
	}
	/**
	 * 根据文章id获得一篇文章
	 * @param req
	 * @param resp
	 */
	private void getByAid(HttpServletRequest req, HttpServletResponse resp) {
		String aid = req.getParameter("aid");
		Article article = service.getArticle(aid);
		
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String Jaticle = mapper.writeValueAsString(article);
			resp.getWriter().write(Jaticle);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 根据类型id获得文章集合
	 * @param req
	 * @param resp
	 */
	private void listByTid(HttpServletRequest req, HttpServletResponse resp) {
		String tid = req.getParameter("tid");
		
		List<Article> list = service.getArticleListByTid(tid);
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(list);
			resp.getWriter().write(json);
			
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 修改article数据
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	
	private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean flag = true;
		Map<String, String> map = null;
		try {
			map = fileUpload(req);
		} catch (Exception e1) {
			e1.printStackTrace();
			flag = false;
		}
		
		if(flag) {			
			Article art = new Article();
			//日期处理
            if(map.containsKey("pubDate")) {
            	String date = map.get("pubDate");
            	map.remove("pubDate");
            	art.setPubDate(DateTools.getDateByDateString("yyyy-HH-dd",date));
            }
            
			try {
				BeanUtils.populate(art, map);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			int edit = service.edit(art);
			if (edit==0) {
				req.setAttribute("msg", "修改失败!");
			}else {
				req.setAttribute("msg", "修改成功!");
			}
			
		}else {
			req.setAttribute("msg", "修改失败!");
		}
		req.getRequestDispatcher("/admin/article/edit.jsp").forward(req, resp);
		
	}
	/**
	 * 转发到修改添加页面
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String aid = req.getParameter("aid");
		Article article = service.getArticle(aid);
		req.setAttribute("condition", article);
		List<ArtType> list = typeService.getAllArtType();
		req.setAttribute("typelist", list);
		req.getRequestDispatcher("/admin/article/edit.jsp").forward(req, resp);
	}
	/**
	 * 删除article
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void del(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String[] ids = req.getParameterValues("aid");
		boolean success = service.deleteArticleByIds(ids);
		if (success) {
			req.getRequestDispatcher("/art/news?cmd=list").forward(req, resp);
		}
	}
	
	
	private void toList(HttpServletRequest req, HttpServletResponse resp) {
		list(req, resp);
	}
	/**
	 * 返回一页数据
	 * @param req
	 * @param resp
	 */
	private void list(HttpServletRequest req, HttpServletResponse resp) {
		//得到参数
        Map<String, String[]> map = req.getParameterMap();
        PageBean<ArticleDto> pb= service.getArticleList(map);
        //存储到request域
        req.setAttribute("pb",pb);
        //存储查询条件
        req.setAttribute("condition",map);
        
       String url = PageTools.parseUrl(req);
        
        //System.out.println(url);//  /YQXCMS/art/news?cmd=list
        
        req.setAttribute("url", url);       		     
        //转发
        try {
			req.getRequestDispatcher("/admin/article/list.jsp").forward(req,resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void typeList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<ArtType> types = typeService.getAllArtType();
		ObjectMapper mapper = new ObjectMapper();
		String jString = mapper.writeValueAsString(types);
		//System.out.println(jString);
		resp.getWriter().write(jString);
	}

	/**
	 * 跳转到添加界面,需要一个文章类型列表
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<ArtType> list = typeService.getAllArtType();
		req.setAttribute("typelist", list);
		req.getRequestDispatcher("/admin/article/add.jsp").forward(req, resp);
	}

	/**
	 * 添加文章
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0. 导入问价上传相关的jar包.创建文件上传文件夹
		// 处理服务器文件保存目录(保证文件存储目录存在)
        // 得到上传文件的保存目录，可以将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        //String savePath = this.getServletContext().getRealPath("/files");
		//将文件存储到F:\Eclipse-Workspace\files
        String savePath = "F:\\Eclipse-Workspace\\files";
		//String savePath = "F:\\serverfiles";
        //得到相对路径,便于存储到数据库.
        //String relativePath = "\\"+request.getContextPath().substring(1);
        
        
        File file = new File(savePath);
        // 判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath + "目录不存在，需要创建");
            // 创建目录
            file.mkdir();
        }
        //0 .导入jar包commons.fileupload.jar  commons.io.jar
        //1.创建核心对象 ServletFileUpload
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 解决上传文件名的中文乱码
        upload.setHeaderEncoding("UTF-8");
        //2.判断上传的数据是否是文件请求(multipart),在form上的enctype=multipart/form-data
        if(!ServletFileUpload.isMultipartContent(request)){
            return;
        }
        //上传状态提示
        boolean flag = false;
        //artimg的数据库值
        String artimg="";
      //创建一个map封装数据
        Map<String,String[]> map = new HashMap<>();
        try {
            //3.使用ServletFileUpload解析上传的数据得到一个fileItems
            List<FileItem> fileItems = upload.parseRequest(request);
            
            for (FileItem fileItem : fileItems) {
                //如果是普通数据项
                if(fileItem.isFormField()){
                    String name = fileItem.getFieldName();           
                    String value = fileItem.getString("utf-8");                                    
                    map.put(name,new String[]{value});
                    
                }else{
                    //文件
                    //String fieldName = fileItem.getFieldName();
                    String filename = fileItem.getName();
                    if(filename==null || filename.trim().equals("")){
                        continue;
                    }
                    //得到文件后缀名
                    String[] split = filename.split("\\.");
                    String fileSuffix = split[split.length-1];
                    String filePrefix = split[split.length-2];
                    //创建文件名
                    long date = new Date().getTime();
                    //毫秒值+文件名.后缀
                    String newFilename = date+filePrefix+"."+fileSuffix;
                    //得到文件的数据库存储的路径(只存相对路径)
                    artimg = newFilename;
                    //创建输入流读取文件数据
                    InputStream is = fileItem.getInputStream();
                    //创建输出流写数据到文件上
                    FileOutputStream fos = new FileOutputStream(savePath+"\\"+newFilename);
                    int len = 0;
                    byte[] bytes = new byte[1024];
                    while ((len=is.read(bytes))!=-1){
                        fos.write(bytes,0,len);
                    }
                    //释放资源
                    fos.close();
                    is.close();
                    // 删除处理文件上传时生成的临时文件
                    fileItem.delete();
                    
                    //System.out.println(artimg);
                    
                }

            }
            //到这里就表明上传成功了
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "添加失败");
            request.getRequestDispatcher("/art/news?cmd=toAdd").forward(request,response);
        }
        //flag为false时,证明文件上传异常,所以直接返会添加失败的状态
        if(!flag) {
        	request.setAttribute("msg", "添加失败");
        	//响应消息
            request.getRequestDispatcher("/art/news?cmd=toAdd").forward(request,response);
        }else {
        	Article art = new Article();
            //如果artimg不为空,说明有文件上传.设置路径参数给map条件集合
            if(!"".equals(artimg)) {
            	map.put("artimg", new String[] {artimg});
            }
            //日期处理
            if(map.containsKey("pubDate")) {
            	String date = map.get("pubDate")[0];
            	map.remove("pubDate");
            	if(!"".equals(date)) {	
            		art.setPubDate(DateTools.getDateByDateString("yyyy-HH-dd",date));
            	}else {
            		art.setPubDate(null);
            	}
            }
            try {
    			BeanUtils.populate(art, map);
    		} catch (IllegalAccessException | InvocationTargetException e) {
    			e.printStackTrace();
    		}
            //设置添加人id
            art.setAid(UUID.randomUUID().toString());
            User loginUser = (User) request.getSession().getAttribute("login_user");
            //如果用户没有登录就设置useid为1
            art.setUserid(loginUser==null?"1":loginUser.getUserid());
            //设置其他article属性...
            
            
            boolean success = service.add(art);
            if(success) {
            	request.setAttribute("msg", "添加成功");
            }else {
            	request.setAttribute("msg", "添加失败");
            }
            //响应消息
            request.getRequestDispatcher("/art/news?cmd=toAdd").forward(request,response);
        }
        
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	/**
	 * 文件上传,返回有文件的表单的数据map集合
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private Map<String, String> fileUpload(HttpServletRequest request) throws Exception{
		//0. 导入问价上传相关的jar包.创建文件上传文件夹
		// 处理服务器文件保存目录(保证文件存储目录存在)
        // 得到上传文件的保存目录，可以将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = "F:\\Eclipse-Workspace\\files";
		
        //得到相对路径,便于存储到数据库.
        //String relativePath = "\\"+request.getContextPath().substring(1);
        
        
        File file = new File(savePath);
        // 判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath + "目录不存在，需要创建");
            // 创建目录
            file.mkdir();
        }
        //0 .导入jar包commons.fileupload.jar  commons.io.jar
        //1.创建核心对象 ServletFileUpload
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 解决上传文件名的中文乱码
        upload.setHeaderEncoding("UTF-8");
        //2.判断上传的数据是否是文件请求(multipart),在form上的enctype=multipart/form-data
        if(!ServletFileUpload.isMultipartContent(request)){
            return null;
        }
        //artimg的数据库值
        String artimg="";
      //创建一个map封装数据
        Map<String,String> map = new HashMap<>();
        
        //3.使用ServletFileUpload解析上传的数据得到一个fileItems
        List<FileItem> fileItems = upload.parseRequest(request);
        
        for (FileItem fileItem : fileItems) {
            //如果是普通数据项
            if(fileItem.isFormField()){
                String name = fileItem.getFieldName();           
                String value = fileItem.getString("utf-8");                                    
                map.put(name,value);
                
            }else{
                //文件
                //String fieldName = fileItem.getFieldName();
                String filename = fileItem.getName();
                if(filename==null || filename.trim().equals("")){
                    continue;
                }
                //得到文件后缀名
                String[] split = filename.split("\\.");
                String fileSuffix = split[split.length-1];
                String filePrefix = split[split.length-2];
                //创建文件名
                long date = new Date().getTime();
                //毫秒值+文件名.后缀
                String newFilename = date+filePrefix+"."+fileSuffix;
                //只存储文件名
                artimg = newFilename;
                //创建输入流读取文件数据
                InputStream is = fileItem.getInputStream();
                //创建输出流写数据到文件上
                FileOutputStream fos = new FileOutputStream(savePath+"\\"+newFilename);
                int len = 0;
                byte[] bytes = new byte[1024];
                while ((len=is.read(bytes))!=-1){
                    fos.write(bytes,0,len);
                }
                //释放资源
                fos.close();
                is.close();
                // 删除处理文件上传时生成的临时文件
                fileItem.delete();
                map.put("artimg", artimg);
             
            }            
        }
        return map;	
	}
}
