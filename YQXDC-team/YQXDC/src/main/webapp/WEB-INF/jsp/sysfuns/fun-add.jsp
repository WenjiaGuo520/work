<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2019/10/24
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>
        <c:if test="${isChild==true}">
            添加子节点
        </c:if>
        <c:if test="${isChild==false}">
            添加根节点
        </c:if>
    </title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.js"></script>
    <STYLE>
        body{
            padding-top: 30px;
        }
    </STYLE>
</head>
<body>

    <div style="width: 50%;margin: auto;">

        <form class="form-horizontal" action="##" method="post" id="addForm" >
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">父功能</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="inputEmail3" disabled value="${pfname}">
                    <input type="hidden" value="${pfid}" name="funpid">
                </div>
            </div>
            <div class="form-group">
                <label for="funname" class="col-sm-2 control-label">功能名称</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control"  placeholder="请输入功能名" id="funname" name="funname">
                </div>
            </div>
            <div class="form-group">
                <label for="funurl" class="col-sm-2 control-label">功能路径</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control"  placeholder="请输入功能路径"  id="funurl" name="funurl">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" class="btn btn-default" id="saveBtn">保存</button>
                </div>
            </div>
        </form>
    </div>
    <script>
        $(function () {
            $("#saveBtn").click(function () {

                if(!checkForm()){
                    alert("表单数据不合法!功能名必须为4-10位汉字、字符、数字，且url不能为空");
                    
                    return;
                }
                $.post("${pageContext.request.contextPath}/sys/fun/add",$("#addForm").serialize(),function (data) {
                    if(data.success){
                        alert("添加成功!");
                        //alert( window.opener.document.location);
                        //重新加载父窗口
                        window.opener.document.location.href='${pageContext.request.contextPath}/sys/fun/list';
                        window.close();
                    }else{
                        alert("添加失败!");
                    }

                },"json");
            });
        });

        function checkForm() {
            var $funname = $("#funname").val();
            var $funurl = $("#funurl").val();
            var reg = /^[a-zA-Z\u4e00-\u9fa5]{4,10}$/;
            if(reg.test($funname) && ""!=$funurl){
                return true;
            }
            return false;
        }
    </script>
</body>
</html>