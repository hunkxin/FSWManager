<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List"  errorPage="/WEB-INF/error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会议室管理</title>
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<script>
$(document).ready(function(){
  $(".DeleteConf").click(function(){
	  if(confirm("是否确认删除")) 
		  return true;
	  else
		  return false;
  });
  $(".RefreshServer").click(function(){
	  if(confirm("是否确认刷新服务器？点击后请耐心等待，勿频繁刷新")) 
		  return true;
	  else
		  return false;
  });
});
</script>
</head>
<body>
<h1>会议室管理</h1>
	<form name="form1" action="HeadSelect" method="post">
		<tr>
			<td>
				<input type="submit" value="新建会议室" name="NewConf">
			</td>
			<td>
				<input type="submit" value="返回上级菜单" name="BackIndex">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td>
				<input type="submit" value="刷新服务器" name="RefreshServer" class="RefreshServer">
			</td>
		</tr>
	</form>
	<br>
	<%
		List<String> allconf = (List<String>)request.getAttribute("allconf");
		for(String conf:allconf){
	%>
	<form name="form2" action="ModifyConf" method="post">		
		<tr>
			<td><input type="text" value=<%=conf%> name=<%=conf%> readonly="readonly" style="border:0px;width:210px"></td>
			<td><input type="submit" value="修改" name=<%="ModifyConf_"+conf%>></td>
			<td><input type="submit" value="删除" name=<%="DeleteConf_"+conf%> class="DeleteConf"></td>
		</tr>
	</form>
	<%
		}
	%>
</body>
</html>