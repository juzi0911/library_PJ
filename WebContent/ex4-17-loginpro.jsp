<%@ page language="java" contentType="text/html; charset=GB18030"
     pageEncoding="GB18030" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Web����--�û���¼����</title></head>
<body>
<%  request.setCharacterEncoding("UTF-8");          
     String usrname = request.getParameter("usrname"); //��ȡ�û��� 
     if (usrname.trim().length() == 0) { //�ж��û����Ƿ�Ϊ��
        request.setAttribute("loginmsg", "�������û���");
        request.getRequestDispatcher("ex4-17-login.jsp").forward(request,response);
        return;
  }
  String users;
if (application.getAttribute("users")==null)
     users="";
  else
     users=(String)application.getAttribute("users");
  if (users!=""){
	  if (users.indexOf(usrname)==-1) //��ǰ�ѵ�¼�û�������usrname
	  {   users=users+','+usrname;    //����ǰ�û����������û���
		  application.setAttribute("users", users);
	  }
	  else
	  {	  request.setAttribute("loginmsg", "�û�"+usrname+"�ѵ�¼�������������û���");
		  request.getRequestDispatcher("ex4-17-login.jsp").forward(request,response);
		  return;
	  }
  }
  else
      {   application.setAttribute("users", usrname);   }
  session.setAttribute("user", usrname);
  response.sendRedirect("ex4-17-page.jsp");
%>  
</body></html>
  