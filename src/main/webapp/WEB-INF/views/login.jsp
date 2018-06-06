<%--
  Created by IntelliJ IDEA.
  User: zkw
  Date: 2018/4/19
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  String title = request.getParameter("title");
%>
<html>
  <head>
    <title>mvc example</title>
  </head>
  <body>
    <form action="reallogin.do" method="post">
      <div>${error}</div>
      <label for="username">用户名：</label><input type="text" id="username" name="username" /><br/>
      <label for="password">密码：</label><input type="password" id="password" name="password" /><br/>
      <button type="submit">登录</button>
    </form>
  </body>
</html>
