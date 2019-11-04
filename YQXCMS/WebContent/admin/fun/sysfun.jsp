<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>系统功能管理</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <!-- <base target="_blank"> -->
    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body{
            background-color: #F5F5F5;
        }
        #head{
            color: #28A0DC;
            text-align: left;
            font-size: 25px;
            height: 40px;
            line-height: 40px;
            border-bottom: 1px solid #AAAAAA;
            margin-top: 18px;
            padding-left: 18px;
        }
        #left{
            float: left;
            width: 20%;
            margin-top: 30px;
        }
        #left dt{
            display: block;
            font-size: 25px;
            font-weight: bolder;
            color: #605A4D;
            margin-left: 30px;
            margin-bottom: 15px;
        }

        #left dd{
            display: block;
            font-size: 20px;

            margin-left: 40px;
            margin-bottom: 15px;

        }
        a{
            text-decoration: none;
        }
        a:link{
            color:#605A4D;
        }

        #right{
            float: left;
            width: 80%;
            margin-top: 10px;
            padding-top: 20px;
            padding-left: 10%;
            padding-right: 40%;
        }
        .btn{
            background-color: #68748E;
        }
        .addChild{
            background-color:#EAEAEA;
        }
        .btn-addroot{
            margin-left: 40px;
        }
    </style>
    
    <script type="text/javascript">
    	function edit(obj) {
    		//获得属性
			var pfname = $(obj).attr("datapname");
			var fid = $(obj).attr("dataid");
			var fname = $(obj).attr("dataname");
			var faddr=$(obj).attr("dataurl");
			var pfid =$(obj).attr("datapid");
			var fstate = $(obj).attr("datastate");
    		//填充到右边界面上
    		
    		$("#parentFun").val(pfname);
    		$("#childFun").val(fname);
    		$("#addrFun").val(faddr);
    		if(fstate == 1){
    			$("#funState option[value=1]").prop("selected","selected");
    		}else{
    			$("#funState option[value=0]").prop("selected","selected");
    		}
    		$("#fid").val(fid);
    		$("#pfid").val(pfid);
    		
    		if(pfid!=-1){
    			$("#addf").prop("disabled","disabled");
    		}else{
    			$("#addf").removeAttr("disabled");	
    			
    		}
    	}
    	
    	$(function(){
    		//增加普通功能节点
    		$("#addf").click(function(){
    			var pfid =$("#pfid").val();
    			//alert(fid);
    			if(pfid==-1){
    				$("#parentFun").val($("#childFun").val());
        			$("#childFun").val("");
        			$("#pfid").val($("#fid").val());
        			$("#fid").val("");
    			}else{
    				//alert("请选择正确的根节点!");
    				return;
    			}
    			
    		});
    		//增加根节点
    		$("#addP").click(function(){
    			$("#parentFun").val("");
    			$("#childFun").val("");
    			$("#pfid").val("-1");
    			$("#fid").val("");
    		});
    		
    		
    		
    		/* $("#save").click(function(){
    			//得到表单内容
				var data = $("#fun-form").serialize();
    			//发起ajax请求
    			$.post("/YQXCMS/sys/fun?cmd=add",data,function(rtnData){   				
    				//alert(rtnData);
    				if(rtnData.flag){
    					alert("添加成功!");
    					//刷新
    					location.href="/YQXCMS/sys/fun?cmd=toList";
    	    			
    				}else{
    					alert("添加失败!");
    				}
    			},"json");
    			
    			
    		}); */
    	})
    	//表单数据校验
    	function check(){
    		//var fid = $("#fid").val();
    		var pfid = $("#pfid").val();
    		var fname = $("#childFun").val();
    		var faddr = $("#addrFun").val();
    		var fstate = $("#funState").val();
    		if(pfid=="" || fname=="" || fstate==""){
    			alert("参数错误,请校正数据后再试!  pfid="+pfid+",fname="+fname+",fstate="+fstate+",faddr="+faddr+"");
    			return false;
    		}
    		return true;
    	}
    	
    </script>
</head>
<body>

<div id="root">

    <div id="head">
        <h3><a href="#">系统功能管理</a></h3>
    </div>

    <div id="left">
        <dl>
        	<!-- 遍历功能列表 -->
        	<c:forEach items="${list}" var="fun">
        		<!-- pfid==-1为根节点 -->
        		<c:if test="${fun.pfid==-1}">
        			<dt><a href="javascript:void(0);"
        					datapname="无"  dataid="${fun.fid}" 
        					dataname="${fun.fname}" dataurl="" 
							datapid="-1" datastate="${fun.fstate}"
        					onclick="edit(this);"
        			>${fun.fname}</a></dt>
        		</c:if>
        		
        		<!-- 遍历子节点 -->
	        	<c:forEach items="${list}" var="child">
	        		<c:if test="${child.pfid==fun.fid}">
	        			<dd>
	        			<a href="javascript:void(0);"
	        						datapname="${child.pfname}" dataid="${child.fid}"
									dataname="${child.fname}" dataurl="${child.faddr}"
									datastate="${child.fstate}" datapid="${child.pfid}"
							onclick="edit(this);"
	        			>${child.fname}</a></dd>
	        		</c:if>
	        	</c:forEach>
        		
        	</c:forEach>
        	
        	
        </dl>
        <button type="button" class="btn btn-default btn-addroot" id="addP">增加根节点</button>
    </div>
    <div id="right">
        <form id="fun-form" action="${pageContext.request.contextPath}/sys/fun?cmd=save" method="post">
            <div class="form-group">
                <label for="parentFun">父功能</label>
                <input type="text" class="form-control" id="parentFun" name="pfname" disabled>
                <input type="text" hidden="true" id="pfid" name="pfid">
            </div>
            <div class="form-group">
                <label for="childFun">功能名称</label>
                <input type="text" class="form-control" id="childFun" name="fname">
                <input type="text" hidden="true" id="fid" name="fid">
            </div>
            <div class="form-group">
                <label for="addrFun">功能地址</label>
                <input type="text" class="form-control" id="addrFun" name="faddr">
                
            </div>
            <div class="form-group">
               
                    <label  for="funState">功能状态</label>
                    <select class="form-control" id="funState" name="fstate" >
                    	<option value="1">可用</option>
                    	<option value="0">不可用</option>
                    </select>
            </div>
            <button type="button" class="btn btn-default addChild" id="addf">增加子节点</button>
            <button type="submit" class="btn btn-default" id="save" onclick="return check();">保存</button>
        </form>

    </div>

</div>

	<div style="margin: auto;width: 200px;font-size: 25px;">${rb.msg}</div>
</body>
</html>