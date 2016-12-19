<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/WEB-INF/error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
document.form2.resolution.options[7].selected=true;

 function isValidate(form)
 {
   // 得到用户输入的信息
   for(var i=11;i<form.length-2;i++)
   {
   		var element = form[i];
   		number = form[i].value;
   		
		if (!(/(^\d{1,4}$)/.test(number)))
		{
			alert("输入有误！"); 
			form[i].focus();
	        return false; 
		}
   		
   }
   return true;
 }
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<script>
$(document).ready(function(){
  $("#addconf").click(function(){
	  if(confirm("是否确认提交")) 
		  return true;
	  else
		  return false;
  });
});
</script>
</head>
<body>
	<form name="form1" action="ConfDetail" method="post">
		<input type="submit" value="返回上级菜单" name="backtoSingleConf">
		<input type="text"  value=<%=(request.getAttribute("confnameh")==null||request.getAttribute("confnameh")==""?"?":request.getAttribute("confnameh"))%> name = "confnameDhb" readonly="readonly" style="visibility:hidden">
		<input type="text"  value=<%=request.getAttribute("confnumberh")%> name = "confnumberDhb" readonly="readonly" style="visibility:hidden">
	</form>
	<br>
	<form name="form2" action="ConfDetail" method="post" onsubmit="return isValidate(form2)">
		会议室名称：<input type="text"  value=<%=request.getAttribute("confnameD")%> name = "confnameD" readonly="readonly" style="border:0px;">
		<input type="text"  value=<%=(request.getAttribute("confnameh")==null||request.getAttribute("confnameh")==""?"?":request.getAttribute("confnameh"))%> name = "confnameDh" readonly="readonly" style="visibility:hidden">
		<br><br>
		人数：<input type="text"  value=<%=request.getAttribute("confnumberD")%> name = "confnumberD" readonly="readonly" style="border:0px;">
		<input type="text"  value=<%=request.getAttribute("confnumberh")%> name = "confnumberDh" readonly="readonly" style="visibility:hidden">
		<br><br>
		配置文件模板：<input type="text"  value=<%=(request.getAttribute("ConfFileTmpD")==null||request.getAttribute("ConfFileTmpD")=="")?"\\":request.getAttribute("ConfFileTmpD")%> name = "ConfFileTmpD" readonly="readonly" style="border:0px;"><br><br>
		会议室背景图片分辨率：<select name = "resolution">
						<option value = <%=request.getAttribute("selectedvalue") %>  selected="selected"><%=request.getAttribute("selectedvalue") %></option>
						<option value = "1920x1200">1920x1200</option>
						<option value = "1920x1080">1920x1080</option>
						<option value = "1600x1200">1600x1200</option>
						<option value = "1600x1024">1600x1024</option>
						<option value = "1440x900">1440x900</option>
						<option value = "1280x1024">1280x1024</option>
						<option value = "1280x960">1280x960</option>
						<option value = "1280x800">1280x800</option>
						<option value = "1024x600">1024x600</option>
						<option value = "960x640">960x640</option>
						<option value = "800x600">800x600</option>
						<option value = "800x480">800x480</option>
						<option value = "720x480">720x480</option>
						<option value = "640x480">640x480</option>
						<option value = "640x360">640x360</option>
						<option value = "480x320">480x320</option>
						<option value = "480x240">480x240</option>
						<option value = "320x240">320x240</option>
					  </select>
	<br><br>
		<button value="addpic" name = "addpic" disabled="disabled">导入会议室背景图片</button>
	<br><br>
	会议室窗口参数设置
	<br>
	<tr>
		<td><input type="text"  value="x" readonly="readonly"></td>
		<td><input type="text"  value="y" readonly="readonly"></td>
		<td><input type="text"  value="wide" readonly="readonly"></td>
		<td><input type="text"  value="height" readonly="readonly"></td>
	</tr>
	<br>
	<%
		int n = Integer.parseInt(request.getAttribute("confnumberD").toString());
		if(n>0){
			for(int a=0;a<n;a++){
	%>
				<tr>
					<td><input type="text" 
					value=<%=(request.getAttribute("x"+a)==null||request.getAttribute("x"+a)=="")?"/":request.getAttribute("x"+a)%> 
					name=<%="x"+String.valueOf(a)%> maxlength=4 ></td>
					<td><input type="text" 
					value=<%=(request.getAttribute("y"+a)==null||request.getAttribute("y"+a)=="")?"/":request.getAttribute("y"+a)%>
					name=<%="y"+String.valueOf(a)%> maxlength=4 ></td>
					<td><input type="text" 
					value=<%=(request.getAttribute("wide"+a)==null||request.getAttribute("wide"+a)=="")?"/":request.getAttribute("wide"+a)%>
					name=<%="wide"+String.valueOf(a)%> maxlength=4 ></td>
					<td><input type="text" 
					value=<%=(request.getAttribute("height"+a)==null||request.getAttribute("height"+a)=="")?"/":request.getAttribute("height"+a)%>
					name=<%="height"+String.valueOf(a)%> maxlength=4 ></td>
				</tr>
				<br>
	<%
			}
		}
	%>
	<input type="reset" value="重置">
	<br><br>
	<input type="submit" value="提交" name = "addconf" id="addconf">
	</form>

</body>
</html>