<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" import="java.util.*,java.util.Date,java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Web聊天--消息发送窗口</title>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            ;
%>
<link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css" />
</head>
<body>
<script type="text/javascript"
        src="<%=basePath%>/js/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/util.js"></script>

<%--<form action="" method="post"><%=session.getAttribute("user")%>，发言：
    <input type="text" name="message" size="40" ><br><br>
    <input type="submit" value="发言" >&lt;%&ndash;在这里添加连接数据库&ndash;%&gt;
    <input type="button" value="退出" onClick="parent.location.href='ex4-17-logout.jsp'">
</form>--%>

<form action="<%=basePath%>/recall/RecallServlet" method="post"><%=session.getAttribute("user")%>，发言：
    <input type="hidden" name="op" value="storemessage">
    <input type="text" name="record_word" size="40" ><br><br>
    <%--<input type="hidden" name="record_word" value="message">--%>
    <input type="hidden" name="record_id" value="<%=session.getAttribute("user")%>">
    <input type="hidden" name="record_time" value=" ">
    <input type="submit" value="发言" ><%--在这里添加连接数据库--%>
    <input type="button" value="退出" onClick="parent.location.href='ex4-17-logout.jsp'">
</form>

<%--<form action="" method="post"><%=session.getAttribute("user")%>，发言：
    <input type="text" name="message" size="40" ><br><br>
    <input type="submit" value="发言" onClick="parent.location.href='<%=basePath%>/recall/RecallServlet?op=storemessage&record_id='+<%=session.getAttribute("user")%>+'&record_word='+message">&lt;%&ndash;在这里添加连接数据库&ndash;%&gt;
    <input type="button" value="退出" onClick="parent.location.href='ex4-17-logout.jsp'">
</form>--%>


<%   String msgs=(String)application.getAttribute("msgs"); //获取全部消息
     String user=(String)session.getAttribute("user");     //获取当前用户 haishi session
     SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String s=df.format(new Date());        
     request.setCharacterEncoding("UTF-8");
     String message = (String) request.getAttribute("message"); //获取当前用户发送的消息
     /*String message = request.getParameter("message"); //获取当前用户发送的消息*/

if (application.getAttribute("msgs")!=null)        //已有用户发消息
     {  if (request.getAttribute("message")==null)    //当前用户刚上线尚未发消息
        	   msgs=msgs+"<br>"+user+":上线 ( "+s+")";//提示该用户上线
        else	
        	   msgs=msgs+"<br>"+user+ "说："+message+ " ("+s+")"; //显示该用户的消息
     }
     else  //尚未有用户发消息
     {  	if (request.getAttribute("message")==null)    //当前用户刚上线尚未发消息
         	msgs=user+":上线 ( "+s+")";    
        else
         	msgs="<br>";
     }     
     application.setAttribute("msgs", msgs);  //更新消息内容
 %>   
</body></html>
     