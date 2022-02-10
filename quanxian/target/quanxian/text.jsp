<%@ page import="AiLvYou.dao.Utils" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="AiLvYou.dao.BaseDao" %>
<%@ page import="AiLvYou.entity.Picture" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Tang
  Date: 2020/6/11
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%
    Connection connection = Utils.getConnection();
    List<Picture> list = BaseDao.search(connection, Picture.class, "picture", "select * from picture", null);
    Picture p1 = list.get(0);
    Picture p2 = list.get(1);
    String s = p1.getPictureLoc();
%>
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script>
    $(function () {
        alert("test");

    });
</script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%=connection%>
<p>1:<%=s%></p>
<p>2:${s}</p>
<p>3:${p1.pictureLoc}</p>

</body>
</html>
