<%@ page language="java" contentType="text/html; charset=GB18030"
     pageEncoding="GB18030" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Web聊天--用户登录处理</title></head>
<body>
<%  request.setCharacterEncoding("UTF-8");          
     String usrname = request.getParameter("usrname"); //获取用户名 
     if (usrname.trim().length() == 0) { //判断用户名是否为空
        request.setAttribute("loginmsg", "请输入用户名");
        request.getRequestDispatcher("ex4-17-login.jsp").forward(request,response);
        return;
  }
  String users;
if (application.getAttribute("users")==null)
     users="";
  else
     users=(String)application.getAttribute("users");
  if (users!=""){
	  if (users.indexOf(usrname)==-1) //当前已登录用户不包含usrname
	  {   users=users+','+usrname;    //将当前用户加入在线用户中
		  application.setAttribute("users", users);
	  }
	  else
	  {	  request.setAttribute("loginmsg", "用户"+usrname+"已登录，请输入其他用户名");
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
  