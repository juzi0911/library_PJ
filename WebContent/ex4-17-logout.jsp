<%@ page language="java" contentType="text/html; charset=GB18030"
     pageEncoding="GB18030" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Web����--�û��ǳ�</title></head>
<body>
<%  String users = (String)application.getAttribute("users");
     String username = (String) session.getAttribute("user");
     String s[] = users.split(",");
     String newusers="";   //�����Ƴ���ǰ�û����ȫ���û�
//��application����users�������Ƴ���ǰ�û�
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
     session.invalidate();//ע����ǰ�û��Ự
    %>   
    <jsp:forward page="ex4-17-login.jsp"/>    
</body></html>
     