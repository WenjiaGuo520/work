<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2019/10/28
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>${goods.gname}</h1>
    <h1>${goods.gtid}</h1>
    <img src="${pageContext.request.contextPath}/file/${goods.gimg}" alt="图片">
</body>
</html>
