<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List" errorPage="/WEB-INF/error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会议室配置文件备份管理</title>
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<script>
$(document).ready(function(){
  $("#deleteselected").click(function(){
	  var str="";
	  $(".confname:checked").each(function(){  
	  str+=$(this).attr("name")+"/"; 
	  })
	  $("input[name='allselectedname']").val(str);
	  if(confirm("是否确认删除")) 
		  return true;
	  else
		  return false;
  });
  $(".setdefault").click(function(){
	  if(confirm("是否确认操作")) 
		  return true;
	  else
		  return false;
  });
});
</script>
</head>
<body>
<h1>会议室配置文件备份管理</h1><br><br>
<form action="FileManage" method="post">
	<input type="submit" name="backindex" value="返回上级菜单">
</form>
<body>  
  <form name="confGroup" id="confGroup" action="FileManage" method="post" target="_self">  
    <table border="1" cellpadding="2" cellspacing="1">  
      <tr class="table_title">  
        <td width="50" align="center" class="text_center">序号</td>  
        <td width="40" align="center" class="text_center">选择</td>  
        <td width="100" align="center"></td>  
        <td width="100" align="center"></td>  
      </tr>
      <%
      	List<String> allconfname = (List<String>)request.getAttribute("allconffilename");
      	int i = 1;
		for(String confname:allconfname){
      %>  
      <tr>  
        <td align="center" class="text_center"><%=i%></td>  
        <td align="center" class="text_center"><input name=<%="checkbox"+confname%> type="checkbox" value="" class="confname"></td>  
        <td align="center"><input name="filename" type="text" value=<%=confname%> readonly="readonly" style="border:0px;"></td>  
        <td align="center"><input type="submit" value="设置为生效文件" name = <%="setdefault"+confname%> class="setdefault"></td>  
      </tr>
      <%
      		i++;
      	}
	  %>
      <tr>  
        <td align="center">全选</td>  
        <!-- 复选框单击方式 -->  
        <td align="center"><input name="allselect" id="allselect" type="checkbox" onClick="CheckSelect(this.form);return false;"></td>  
        <td align="center"><input name="deleteselected" id="deleteselected" type="submit" value="删除"></td>  
        <td align="center"><input type="text" name = "allselectedname" readonly="readonly" style="visibility:hidden"></td>  
      </tr>  
    </table>  
  </form>  
</body>  
<script>  
  // 选择或者反选 checkbox  
  function CheckSelect(thisform)  
  {  
	if(thisform.allselect.value)
	{
		// 遍历 form
		for ( var i = 0; i < thisform.elements.length; i++)  
	    {  
	      // 提取控件  
	      var checkbox = thisform.elements[i]; 
	     
    	  if (checkbox.type === "checkbox")  
          {  
            // 正选  
            checkbox.checked = false;  
          }  
	    } 
		thisform.allselect.value=null;
	}
	else
	{
		for ( var i = 0; i < thisform.elements.length; i++)  
	    {  
	      // 提取控件  
	      var checkbox = thisform.elements[i]; 
	     
	      if (checkbox.type === "checkbox")  
	      {  
	        // 反选  
	        checkbox.checked = true;  
	      }  
	    } 
		thisform.allselect.value=true;
	}
    
  }  
</script> 
</body>
</html>