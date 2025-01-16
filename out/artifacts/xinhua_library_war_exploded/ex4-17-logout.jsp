<%@ page language="java" contentType="text/html; charset=GB18030"
     pageEncoding="GB18030" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Web聊天--用户登出</title></head>
<body>
<%  String users = (String)application.getAttribute("users");
     String username = (String) session.getAttribute("user");
     String s[] = users.split(",");
     String newusers="";   //保存移除当前用户后的全部用户
//从application对象users属性中移除当前用户
     if (!s[0].equals(username))   
        	newusers=s[0];
     for (int i=1;i<s.length;i++)
     {  
	    if (!s[i].equals(username))
        	{	
                            if (newusers!="")
        		    newusers=newusers+","+s[i];
        		else
        			newusers=s[i];
        	}
     }
     application.setAttribute("users", newusers);        
     session.invalidate();//注销当前用户会话
    %>   
    <jsp:forward page="ex4-17-login.jsp"/>    
</body></html>
     