<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  errorPage="/WEB-INF/error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>用户登陆</title>
  <script>
 function isValidate(form)
 {
   // 得到用户输入的信息
   username = form.username.value;
   userpass = form.userpass.value;
   // 判断用户名长度
   if(!minLength(username,6))
   {
     alert("用户名长度小于6位！");
     form.username.focus();
     return false;
   }
   // 判断口令长度
  if(!minLength(userpass,6))
   {
     alert("口令长度小于6位！");
     form.userpass.focus();
     return false;
   }
   return true;
 }
 // 验证是否满足最小长度
 function minLength(str,length)
 {
   if(str.length>=length)
     return true;
   else
     return false;
 }
</script>
 </head>
 <body>
  <h1>用户登陆</h1>
  <form name="form1" action="Index" method="post"
    onsubmit="return isValidate(form1)">
    <tr>
		<td><input type="text" value="用户名：" style="width:50px;border:0px" readonly="readonly"></td>
		<td><input type="text" name="username" maxlength=30 style="width:200px"></td>
	</tr>
	<br>
	<tr>
		<td><input type="text" value="密码：" style="width:50px;border:0px" readonly="readonly"></td>
		<td><input type="password" name="userpass" maxlength=30 style="width:200px"></td>
	</tr>
	<br><br>
    <input type="reset" value="重置">
    <input type="submit" value="提交">
  </form>
 </body>
</html>