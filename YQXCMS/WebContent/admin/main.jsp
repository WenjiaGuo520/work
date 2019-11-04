<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主界面</title>
</head>

    <frameset cols="20%,80%" border="2" bordercolor="#F5F5F5">
    	
    	<frameset rows="10%,90%" border="1">
           <frame src="header1.jsp"/>
           <frame name="menu" src="menu.jsp"/>
        </frameset>
    	<frameset rows="10%,90%" border="1">
            <frame src="header.jsp"/>
            <frame name="main" src="welcome.jsp"/>
        </frameset>
    	<!-- 
        <frame src="header.jsp"/>
        <frameset cols="20%,80%" border="1">
            <frame name="menu" src="menu.jsp"/>
            <frame name="main" src="welcome.jsp"/>
        </frameset> -->
    </frameset>


</html>