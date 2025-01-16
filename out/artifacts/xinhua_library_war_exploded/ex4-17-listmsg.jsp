<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Web聊天--显示聊天内容</title>
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
<%--<%     response.setHeader("refresh", "1");
        String msgs = (String) application.getAttribute("msgs");
        if (application.getAttribute("msgs")==null) 
        	     out.print("聊天程序启动"); 
        else
        	     out.print(msgs);            
%>--%>


    <nav class="navbar navbar-default" style="
    margin-bottom: 0px;
">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed"
                        data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
                        aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span> <span
                        class="icon-bar"></span> <span class="icon-bar"></span> <span
                        class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<%=basePath%>/recall/RecallServlet?op=getcategoryrecord">聊天内容</a>
            </div>
            <div class="collapse navbar-collapse"
                 id="bs-example-navbar-collapse-1">
                <!-- 搜索框 -->
                <div style="float: right; margin-right: 7px">
                    <form class="navbar-form navbar-left"
                          action="<%=basePath%>/recall/RecallServlet?op=search" method="post">
                        <div class="form-group">
                            <input type="text" name="search" class="form-control"
                                   placeholder="搜索/关键词">
                        </div>
                        <button type="submit" class="btn btn-default">搜索</button>
                    </form>
                </div>
            </div>
        </div>
    </nav>



    <table class="table table-bordered table-hover"
           style="margin-left: 16px; width: 1890px">
        <tr>
            <th style="width: 100px">发言者</th>
            <th style="width: 100px">发言内容</th>
            <th style="width: 200px">发言时间</th>
        </tr>
        <c:forEach items="${records}" var="s" varStatus="vs">
            <tr class="active">
                <td>${s.record_id}</td>
                <td>${s.record_word}</td>
                <td>${s.record_time}</td>
            </tr>
        </c:forEach>
    </table>
</body></html>
