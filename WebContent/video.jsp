<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>视频介绍</title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    %>
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css"/>
</head>
<body>
<script type="text/javascript"
        src="<%=basePath%>/js/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/util.js"></script>
<jsp:include page="header1.jsp"></jsp:include>

<div style="text-align:center">
    <button onclick="playPause()">播放/暂停</button>
    <button onclick="makeBig()">放大</button>
    <button onclick="makeSmall()">缩小</button>
    <button onclick="makeNormal()">普通</button>
    <br>
    <video id="video1" width="700">
        <source src="<%=basePath%>/video/${book.filename}" type="video/mp4">
        <!--<source src="mov_bbb.ogg" type="video/ogg">-->
        您的浏览器不支持 HTML5 video 标签。
    </video>
</div>

<script>
    var myVideo=document.getElementById("video1");

    function playPause()
    {
        if (myVideo.paused)
            myVideo.play();
        else
            myVideo.pause();
    }

    function makeBig()
    {
        myVideo.width=900;
    }

    function makeSmall()
    {
        myVideo.width=50;
    }

    function makeNormal()
    {
        myVideo.width=700;
    }
</script>

</body>
</html>