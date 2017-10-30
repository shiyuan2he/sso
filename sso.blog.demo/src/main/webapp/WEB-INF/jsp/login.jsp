<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<html>
<script type="text/javascript" src="<%=basePath%>/jquery-3.2.1.js"></script>
<body>
    username:<input id="username" name="username" value="admin"/><br/>
    password:<input id="password" name="password" value="123"/><br/>
    <input type="submit" value="submit" onclick="javascript:login.doLogin();"/>
    <script type="text/javascript" src="<%=basePath%>/login.js"></script>
</body>
</html>
