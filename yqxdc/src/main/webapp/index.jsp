<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2019/10/24
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <script
            src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <title>首页</title>
</head>
<body>
    <script>
        $(function () {
            window.location.href='${pageContext.request.contextPath}/toMain'
        })
    </script>
</body>
</html>
