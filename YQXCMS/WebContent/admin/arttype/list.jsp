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
    <title>文章类型列表</title>

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
			
			$("#first-cb").click(function() {
				$(".tid-cb").prop("checked",$(this).prop("checked"));
				
			});
			
			$("#btn-del").click(function() {
			    if(confirm("您确定弃用这些文章类型吗?")){

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
        <a href="javascript:void(0);">文章类型管理</a> &nbsp;&nbsp;/&nbsp;&nbsp;
        <a href="javascript:void(0);" style="color: #AAA">文章类型列表</a>
    </div>

    <div>
        <div id="form-div">
            <form class="form-inline" action="${pageContext.request.contextPath}/art/type?cmd=list&currentPage=1&rows=5" method="post">
                <div class="form-group">
                    <label  for="searchName">文章类型名</label>
                    <input type="text" class="form-control" id="searchName" name="tname" value="${condition.tname[0]}">
                </div>
				<div class="form-group">
                    <label  for="searchDesc">文章描述</label>
                    <input type="text" class="form-control" id="searchDesc" name="tdesc" value="${condition.tdesc[0]}">
                </div>
                <div class="form-group">
                	<input type="text" hidden="true" id="data-searchState" value="${condition.tstate[0]}">
                    <label  for="searchState">文章状态</label>
                    <select class="form-control" id="searchState" name="tstate" >
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
    	<a href="${pageContext.request.contextPath}/admin/arttype/add.jsp" class="btn btn-default" id="btn-add">增加</a>
    </div>
    
    <div style="float: none"></div>
    <div id="table-div">
    <form id="table-form" action="${pageContext.request.contextPath}/art/type?cmd=del" method="post">
        <table class="table table-bordered">
            <tr>
            	<th><input type="checkbox" id="first-cb"></th>
                <th>文章类型名称</th>
                <th>文章类型描述</th>
                <th>文章类型状态</th>
                <th>文章类型排序</th>
                <th>操作</th>
            </tr>

			<c:forEach items="${pb.list}" var="arttype">
				<tr>
					<td><input type="checkbox" class="tid-cb" name="tid" value="${arttype.tid}"></td>
	                <td>${arttype.tname}</td>
	                <td>${arttype.tdesc}</td>
	                <td> 
	                	<c:if test="${arttype.tstate==1}">可用</c:if>
	                	<c:if test="${arttype.tstate==0}">不可用</c:if>
	                 </td>
	                <td>${arttype.tsort}</td>
	                <td>
	                <a href="${pageContext.request.contextPath}/art/type?cmd=toEdit&tid=${arttype.tid}&tname=${arttype.tname}&tsort=${arttype.tsort}&tsate=${arttype.tstate}&tdesc=${arttype.tdesc}">
	                	编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;
	                <a href="javascript:void(0);">权限</a>
	                </td>
            	</tr>
			</c:forEach>
            
        </table>
    </form>
    </div>
    <div id="page-num">
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <li><a href="#">共计${pb.totalPage}页/${pb.totalCount}条记录</a></li>
                <c:if test="${pb.currentPage==1}">
                	<li class="disabled">
                	 <a href="javascript:void(0);" aria-label="Previous">
                        <span aria-hidden="true">上一页</span>
                    </a>
                	</li>
                </c:if>
               <c:if test="${pb.currentPage>1}">
               		<li>
                	 <a href="${pageContext.request.contextPath}/art/type?cmd=list&currentPage=${pb.currentPage-1}&rows=5&tdesc=${condition.tdesc[0]}&tname=${condition.tname[0]}&tstate=${condition.tstate[0]}" aria-label="Previous">
                        <span aria-hidden="true">上一页</span>
                    </a>
                	</li>
               </c:if>
                
                <c:forEach begin="1" end="${pb.totalPage}" var="i">
                	<c:if test="${pb.currentPage==i}">
                		<li class="active"><a href="${pageContext.request.contextPath}/art/type?cmd=list&currentPage=${i}&rows=5&tdesc=${condition.tdesc[0]}&tname=${condition.tname[0]}&tstate=${condition.tstate[0]}">${i}</a></li>
                	</c:if>
                	<c:if test="${pb.currentPage!=i}">
                		<li><a href="${pageContext.request.contextPath}/art/type?cmd=list&currentPage=${i}&rows=5&tdesc=${condition.tdesc[0]}&tname=${condition.tname[0]}&tstate=${condition.tstate[0]}">${i}</a></li>
                	</c:if>
                </c:forEach>
                
                
                <c:if test="${pb.currentPage==pb.totalPage}">
                	<li class="disabled">
                	 <a href="javascript:void(0);" aria-label="Previous">
                        <span aria-hidden="true">下一页</span>
                    </a>
                	</li>
                </c:if>
               <c:if test="${pb.currentPage<pb.totalPage}">
               		<li>
                	 <a href="${pageContext.request.contextPath}/art/type?cmd=list&currentPage=${pb.currentPage+1}&rows=5&tdesc=${condition.tdesc[0]}&tname=${condition.tname[0]}&tstate=${condition.tstate[0]}" aria-label="Previous">
                        <span aria-hidden="true">下一页</span>
                    </a>
                	</li>
               </c:if>
            </ul>
        </nav>
    </div>
</body>


</html>