<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Web聊天--登录页</title></head>
<body>
<jsp:include page="/header.jsp"></jsp:include>
<%   String loginmsg = (String) request.getAttribute("loginmsg");
      if (loginmsg == null) {  loginmsg = "";  }
%>
    <center><h2><%=loginmsg%></h2></center><br> 
<form action="ex4-17-loginpro.jsp" method="post" name="logform">
    <center>用户名：<input type="text" name="usrname" ></center><br><br> 
    <center><input type="submit" value="登录" >&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="button" value="退出" onClick="parent.location.href='showBook.jsp'"></center></form>
</body></html>
