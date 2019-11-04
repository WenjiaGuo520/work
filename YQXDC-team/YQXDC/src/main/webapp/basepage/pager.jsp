<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <style>
    	.pagination-right{
    		float: right;
    	}
    	.pager li{
    		float: left;
    	}
    </style>
<div class="pagination pagination-right">
	<ul class="pager">
		<!-- 总记录数，总页数 -->
		<li>
			<a>共计：${p.pages}页/${p.total}条记录</a>
		</li>
		<!-- 上一页 -->
		<li>
			<c:if test="${p.pageNum==1}" var="fp">
				<a style="disabled: true">上一页</a>
			</c:if> <c:if test="${!fp}">
				<a href="${url}&index=${p.pageNum-1}">上一页</a>
			</c:if>
		</li>
		<!-- 中间部分 -->
		<c:forEach begin="${p.minpage}" step="1"
			end="${p.maxpage}" var="index">
			<li><c:if test="${p.pageNum==index}" var="t">
					<a style="color: red; background-color: #bbb">${index}</a>
				</c:if> <c:if test="${!t}">
					<a href="${url}&index=${index}">${index}</a>
				</c:if>
			</li>
		</c:forEach>
		<!-- 下一页 -->
		<li>
			<c:if test="${p.pageNum==p.pages}" var="fp">
				<a style="disabled: true">下一页</a>
			</c:if> <c:if test="${!fp}">
				<a href="${url}&index=${p.pageNum+1}">下一页</a>
			</c:if>
		</li>
	</ul>
</div>