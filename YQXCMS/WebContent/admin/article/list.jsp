<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/stylejs.js"></script>
    <title>文章列表</title>

    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body{
            background-color: #F5F5F5;
        }
        a{
            text-decoration: none;
            color: #2087CC;
        }

        #head{
            margin: 10px 0px 10px 0px;
            height: 40px;
            line-height: 40px;
            border-bottom: 1px solid #CDC1AF;
            padding-left: 15px;
        }
        #btn-add{
            background-color: #6A7D7C;
        }
		#btn-del{
			background-color: #00B0F9;
		}
        #form-div{
            margin-left: 20px;
            float: left;
        }
        #table-div{
            margin-top: 15px;
        }
        .mt-hover{
            background-color: white;
        }
        #page-num{
            float: right;
            margin-right: 50px;
            margin-top: 0px;
        }
    </style>
	
</head>
<script type="text/javascript">
		$(function(){
			
			//加载文章类型到查询下拉框
			$.post("/YQXCMS/art/news?cmd=getType",{},function(data){
				//[{"tid":"02c94a96-971f-4a2e-8a44-fd359348af29","tname":"国内新闻","tdesc":"中国人民最幸福","tstate":"1","tsort":"1"}...]
				//alert(data);
				var type = $("#searchType");
				for (var i = 0; i < data.length; i++) {
					//创建option子节点
					type.append("<option value='"+data[i].tname+"'>"+data[i].tname+"</option>");
				}
				
			},"json");
			//根据第一个checkbox的状态来确定页面的checkbox选中状态.
			$("#first-cb").click(function() {
				$(".tid-cb").prop("checked",$(this).prop("checked"));
				
			});
			//点击删除按钮
			$("#btn-del").click(function() {
			    if(confirm("您确定弃用这些文章吗?")){

	                //var cbs = document.getElementsByName("uid");
	                var flag = false;
	                $(".tid-cb").each(function () {
	                    if($(this).prop("checked")){
	                        flag = true;
	                        return false;
	                    }
	                });
	                //如果有表单被选中,就提交表单
	                if(flag){
	                	//alert($("#table-form").prop("action")+":"+$("#table-form").prop("method"));
	                	$("#table-form").submit();
	                }
	            }
			});
			
			//根据value选择option
			var key = $("#data-searchState").val();
			$("#searchState option[value='"+key+"']").attr("selected","selected");
			
		});
	</script>
<body>
    <div id="head">
        <a href="javascript:void(0);">文章管理</a> &nbsp;&nbsp;/&nbsp;&nbsp;
        <a href="javascript:void(0);" style="color: #AAA">文章列表</a>
    </div>

    <div>
        <div id="form-div">
            <form class="form-inline" action="${pageContext.request.contextPath}/art/news?cmd=list" method="post">
                <div class="form-group">
                    <label  for="searchName">文章名</label>
                    <input type="text" class="form-control" id="searchName" name="arttitle" value="${condition.arttitle[0]}">
                </div>
              
                <div class="form-group">
                    <label  for="searchType">文章类型</label>
                    <input type="text" hidden="true" id="data-searchType" value="${condition.tname[0]}">
                    <select class="form-control" id="searchType" name="tname" >
                    	<option value="">-请选择-</option>            
                    </select> 
                </div>
                
                <div class="form-group">
                    <label  for="searchUser">上传者</label>        
                   	<input type="text" class="form-control" id="searchUser" name="usertruename" value="${condition.usertruename[0]}">
                </div>
                
                <div class="form-group">
                	<input type="text" hidden="true" id="data-searchState" value="${condition.astate[0]}">
                    <label  for="searchState">文章状态</label>
                    <select class="form-control" id="searchState" name="astate" >
                    	<option value="1">可用</option>
                    	<option value="0">不可用</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-default">查找</button>
            </form>
        </div>
    </div>
    <div style="float:right;margin:0 10px 10px 0">
    	<a href="javascript:void(0);" class="btn btn-default" id="btn-del">弃用选中</a>
    	<a href="${pageContext.request.contextPath}/art/news?cmd=toAdd" class="btn btn-default" id="btn-add">增加</a>
    </div>
    
    <div style="float: none"></div>
    <div id="table-div">
    <form id="table-form" action="${pageContext.request.contextPath}/art/news?cmd=del" method="post">
        <table class="table table-bordered">
            <tr>
            	<th><input type="checkbox" id="first-cb"></th>
                <th>文章名称</th>
                <th>文章类型</th>
                <th>上传人</th>
                <th>文章图片</th>
                <th>文章摘要</th>
                <th>出版日期</th>
                <th>作者</th>
                <th>来源</th>
                <th>文章状态</th>
                <th>操作</th>
            </tr>

			<c:forEach items="${pb.list}" var="art">
				<tr>
					<td>
					<input type="checkbox" class="tid-cb" name="aid" value="${art.aid}"
						dataArttitle="${art.arttitle}" dataTid="${art.tid}" dataArtimg="${art.artimg}" 
						dataArtabs="${art.artabs}" datapubData="${art.pubDate}" dataAuthor="${art.author}"
						dataArtsourde="${art.artsource}" dataAstate="${art.astate}" dataUserid="${art.userid}"
					></td>
	                <td>${art.arttitle}</td>
	                <td>${art.tname}</td>
	                <td>${art.usertruename}</td>
	                <td>${art.artimg}</td>
	                <td>${art.artabs}</td>
	                <td>${art.pubDate}</td>
	                <td>${art.author}</td>
	                <td>${art.artsource}</td>	      
	                <td> 
	                	<c:if test="${art.astate==1}">可用</c:if>
	                	<c:if test="${art.astate==0}">不可用</c:if>
	                 </td>	       
	                <td>
	                <%-- <a href="${pageContext.request.contextPath}/art/news?cmd=toEdit&arttitle=${art.arttitle}&artimg=${art.artimg}&arttext=${art.arttext}&aid=${art.aid}&tid=${art.tid}">
	                	编辑</a> --%>
	                	<a href="${pageContext.request.contextPath}/art/news?cmd=toEdit&aid=${art.aid}&tid=${art.tid}">
	                	编辑</a>
	                	
	                	&nbsp;&nbsp;|&nbsp;&nbsp;
	                <a href="角色权限分配.html">权限</a>
	                </td>
            	</tr>
			</c:forEach>
            
        </table>
    </form>
    </div>
	<%@include file="../pager.jsp" %>
</body>


</html>