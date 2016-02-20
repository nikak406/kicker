<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="content" fragment="true" %>
<html>
<head>
    <!--link rel="stylesheet" type="text/css" href="styles.css"/-->
    <style>
        <%@include file="styles.css"%>
    </style>
    <title>kickr</title>
</head>
<body>
<div class="container">
    <div class="header">
        <h1 class="header-heading"><img src="../../ball.png" alt="" style="margin-bottom: -0.2em;"/>kickr</h1>
    </div>
    <div class="nav-bar">
            <ul class="nav">
                <li><a href="/table.jsp">Таблица</a></li>
                <li><a href="/forecast.jsp">Расписание</a></li>
                <li><a href="/history.jsp">История</a></li>
                <li><a href="/result.jsp">Добавить счет</a></li>
                <li class="alignright">
                    <a href="/rules.jsp">
                        <img src="../../question.png" alt="Правила" style="width:24px;height: 24px; border: 4px;"/>
                    </a>
                </li>
            </ul>
    </div>
    <div class="content" id="content" align="center">
        <jsp:invoke fragment="content"/>
    </div>
    <div id="bottom" class="footer">
            © Skywind Kicker Association, 2016
    </div>
</div>
</body>
</html>