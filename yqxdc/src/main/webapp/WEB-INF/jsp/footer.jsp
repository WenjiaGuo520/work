<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2019/10/22
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
    .selectLi{
        background-color: #3C8DBC!important;
    }
</style>
<div>
    <div class="pull-left">
        <input type="hidden" id="data-size" value="${page.pageSize}">
        <div class="form-group form-inline">
            总共${page.pages} 页，共${page.total} 条数据。 每页 <select class="form-control" id="size">
            <option value="1">1</option>
            <option value="3">3</option>
            <option value="5">5</option>
            <option value="10">10</option>
            <option value="15">15</option>
        </select> 条
        </div>
    </div>

    <div class="box-tools pull-right">
        <ul class="pagination">
            <li><a href="${url}&index=1" aria-label="Previous">首页</a></li>

            <c:if test="${page.pageNum==1}">
                <li><a href="javascript:void(0);">上一页</a></li>
            </c:if>
            <c:if test="${page.pageNum>1}">
                <li><a href="${url}&index=${page.pageNum-1}">上一页</a></li>
            </c:if>

            <c:forEach begin="${page.minpage}" end="${page.maxpage}" var="i" step="1">
                <c:if test="${i==page.pageNum}" var="nonow">
                    <li ><a class="selectLi" href="${url}&index=${i}">${i}</a></li>
                </c:if>
                <c:if test="${!nonow}">
                    <li><a href="${url}&index=${i}">${i}</a></li>
                </c:if>

            </c:forEach>

            <c:if test="${page.pageNum==page.pages}">
                <li><a href="javascript:void(0);" disabled>下一页</a></li>
            </c:if>
            <c:if test="${page.pageNum<page.pages}">
                <li><a href="${url}&index=${page.pageNum+1}">下一页</a></li>
            </c:if>
            <li><a href="${url}&index=${page.pages}" aria-label="Next">尾页</a></li>
        </ul>
    </div>
</div>
