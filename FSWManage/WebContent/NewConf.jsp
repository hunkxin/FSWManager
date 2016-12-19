<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  errorPage="/WEB-INF/error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
	function isValidate(form)
	{
		number = form.confnumber.value;
		if(number==null||number=="")
		{
			alert("会议室人数不能为空");
			form.confnumber.focus();
			return false;
		}
		if (!(/(^\d{1,2}$)/.test(number))) 
		{ 
			alert("人数只能设置为小于100的非负整数"); 
			form.confnumber.focus();
			return false; 
		}
		confname = form.confname.value;
		if(confname==null||confname=="")
		{
			alert("会议室名称不能为空");
			form.confname.focus();
			return false;
		}
		if(confname.length>=30)
		{
			alert("会议室名称长度不能超过30");
			form.confname.focus();
			return false;
		}
		return true;
	}
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>新建会议室</h1><br><br>
<form name="form1" action="ConfDetail" method="post">
	<input type="submit" value="返回上级菜单" name="backtoconfmanage">
</form>
	<br><br>
<form name="form2" action="ConfDetail" method="post" onsubmit="return isValidate(form2)">
	
	会议室名称：<input type="text" name="confname"
	 onkeyup="value=value.replace(/[\W]/g,'') " 
	 onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
	 maxlength=30>
	 &nbsp;注：请按照规范命名，如yike_video_mcu_V09room，不要包含中文等其它非标准字符
	<br><br>
	会议室人数：<input type="text" name="confnumber" maxlength=2>
	<br><br>
	会议室配置文件模板选择：<select name="ConfFileTmp">
						<option value="Default" selected="selected">Default</option>
					</select>
	<br><br>
	<input type="submit" value="生成" name="submitDetail">
</form>
</body>
</html>