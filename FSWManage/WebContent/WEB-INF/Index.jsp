<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  errorPage="/WEB-INF/error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会议室管理系统</title>
</head>
<body>
<h1>会议室管理系统</h1><br><br>
<form name="form1" action="FileManage" method="post">
    <input type="submit" value="会议室配置文件管理" name="filemanage">
  </form>
  <br>
<form name="form2" action="ConfManageIndex" method="post">
    <input type="submit" value="会议室管理">
  </form>
  <br>
<form name="form2" action="FswStatus?action=status" method="post">
    <input type="submit" value="服务器状态">
  </form>
</body>
</html>