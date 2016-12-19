<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>错误</title>
 </head>
 <body>
  <h2>对不起，出现异常错误</h2>
  <br>
  ErrorMessage: <%=exception.getMessage()%>
 </body>
</html>