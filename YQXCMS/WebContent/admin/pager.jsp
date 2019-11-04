<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/stylejs.js"></script>
<title>Insert title here</title>
</head>
<body>
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
                	 <a href="${url}&currentPage=${pb.currentPage-1}&rows=5" aria-label="Previous">
                        <span aria-hidden="true">上一页</span>
                    </a>
                	</li>
               </c:if>
                
                <c:forEach begin="1" end="${pb.totalPage}" var="i">
                	<c:if test="${pb.currentPage==i}">
                		<li class="active"><a href="${url}&currentPage=${i}&rows=5">${i}</a></li>
                	</c:if>
                	<c:if test="${pb.currentPage!=i}">
                		<li><a href="${url}&currentPage=${i}&rows=5">${i}</a></li>
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
                	 <a href="${url}&currentPage=${pb.currentPage+1}&rows=5" aria-label="Previous">
                        <span aria-hidden="true">下一页</span>
                    </a>
                	</li>
               </c:if>
            </ul>
        </nav>
    </div>
</body>
</html>