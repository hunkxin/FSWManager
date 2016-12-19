<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,getFSWmessages.Content"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FswStatus</title>
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<script>
$(document).ready(function(){
  $("#status").click(function(){
	  $("#submitform").attr("action", "FswStatus?action=status");
	  return true;
  });
  $("#Regs").click(function(){
	  $("#submitform").attr("action", "FswStatus?action=Regs");
	  return true;
  });
  $("#Channels").click(function(){
	  $("#submitform").attr("action", "FswStatus?action=Channels");
	  return true;
  });
  $("#Confs").click(function(){
	  $("#submitform").attr("action", "FswStatus?action=Confs");
	  return true;
  });
  $("#backtoindex").click(function(){
	  $("#submitform").attr("action", "FswStatus?action=backtoindex");
	  return true;
  });
});
</script>
</head>
<body>
<div id="container" style="width:1200px">

<div id="header" style="background-color:#FFA500;text-align:center">
<h1 style="margin-bottom:0;">服务器状态</h1></div>


<div id="menu" style="background-color:#FFD700;width:1200px;text-align:center;">
<form name="form1" action="FswStatus" method="post" id="submitform">
<button type="submit" id="backtoindex" style="background-color:#90D7F0;width:105px;">返回上级菜单</button>
<br>
<button type="submit" id="status" style="background-color:#90D7F0;width:130px;">总体概况</button>
<button type="submit" id="Regs" style="background-color:#90D7F0;width:130px;">在线用户</button>
<button type="submit" id="Channels" style="background-color:#90D7F0;width:130px;">活动用户</button>
<button type="submit" id="Confs" style="background-color:#90D7F0;width:130px;">会议室</button>
</form>
</div>


<div id="content" style="background-color:#EEEEEE;width:1200px;text-align:center;">
<%
	if(request.getAttribute("hstatus")!=null&&request.getAttribute("hstatus").equals("status")){
		if(request.getAttribute("msgstatus")==null){
			out.print("freeSWITCH运行异常或8021端口被占用！");
		}else{
			%>
				<table border="1" cellpadding="2" cellspacing="1" style="margin: 0 auto">
			<%
			for(String content:(List<String>)request.getAttribute("msgstatus")){
				out.print("<tr>"+"<td align='left'>"+content+"</td>"+"</tr>");
			}
			%>
			</table>
			<%
		}
	}else if(request.getAttribute("hstatus")!=null&&request.getAttribute("hstatus").equals("Regs")){
		if(request.getAttribute("msgReg")==null){
			out.print("当前没有注册用户上线");
		}else{
			out.print("总数："+request.getAttribute("msgRegCount")+"<br>");
			%>
			<table border="1" cellpadding="2" cellspacing="1" style="margin: 0 auto">
			<%
			for(String content:(List<String>)request.getAttribute("msgReg")){
				out.print("<tr>"+"<td align='left'>"+content+"</td>"+"</tr>");
			}
			%>
			</table>
			<%
		}
	}else if(request.getAttribute("hstatus")!=null&&request.getAttribute("hstatus").equals("Confs")){
		if(request.getAttribute("msgConfs")==null){
			out.print("当前没有会议室运行");
		}else{
			out.print("总数："+request.getAttribute("msgConfsCount")+"<br>");
	%>
			<table border="1" cellpadding="2" cellspacing="1" style="margin: 0 auto">
	<%
			for(Content content:(List<Content>)request.getAttribute("msgConfs")){
	%>
				<tr>  
			        <td align="center">会议室名称</td>  
			        <td align="center"><%=content.getConfname() %></td>  
			    </tr>
			    <tr>  
			        <td align="center">运行时间</td>  
			        <td align="center"><%=content.getConfmember().get(0)+"分"+content.getConfmember().get(1)+"秒"%></td>  
			    </tr>
			    <tr>  
			        <td align="center">会议室人数</td>  
			        <td align="center"><%=content.getConfmemcount()%></td>  
			    </tr>
			    <tr>  
			        <td align="center">会议室成员</td>  
			        <td align="center">
        <%
	    		for(String mem:content.getConfmember().subList(2, content.getConfmember().size())){
	    			out.print(mem+"<br>");
	    		}
		%>
	        		</td>  
	    		</tr>
	<%
			}
	%>
			</table>
	<%
		}
	}else if(request.getAttribute("hstatus")!=null&&request.getAttribute("hstatus").equals("Channels")){
		if(request.getAttribute("msgChannels")==null){
			out.print("当前没有活动用户");
		}else{
			out.print("总数："+request.getAttribute("msgChannelsCount")+"<br>");
	%>
			<table border="1" cellpadding="2" cellspacing="1" style="margin: 0 auto">
				<tr>
					<td>用户名</td>
					<td>通话方向</td>
					<td>起始时间</td>
					<td>主叫方</td>
					<td>被叫方</td>
					<td>呼叫参数</td>
				</tr>
	<%
			for(Content content:(List<Content>)request.getAttribute("msgChannels")){
	%>
				<tr>
					<td><%=content.getRegname()%></td>
					<td><%=content.getDirection().equals("inbound")?"主叫":"被叫"%></td>
					<td><%=content.getActivebgtime()%></td>
					<td><%=content.getCaller()%></td>
					<td><%=content.getDest()%></td>
					<td><%=content.getApplicationdata()%></td>
				</tr>
	<%		
			}
	%>
			</table>
	<%		
		}
	}
%>
</div>

<div id="footer" style="background-color:#FFA500;clear:both;text-align:center;">&nbsp</div>

</div>

</body>
</html>