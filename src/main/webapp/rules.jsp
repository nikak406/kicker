<%@ page import="model.Championship" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored ="false" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="model.Schedule"%>
<%@ page import="model.Match"%>
<%@ page import="model.Championship" %>

<%
    int f = Championship.getInstance().getForecast().size();
    String fs = "" + f;
    request.setAttribute("fs", fs);
    int m = f + Championship.getInstance().getHistory().size();
    String ms = "" + m;
    request.setAttribute("ms", ms);
%>

<t:templatepage>
    <jsp:attribute name="content">
                  <div align="justify">
                      <p> Рэндом построен так, чтобы каждый отыграл в паре с каждым, за минимально возможное количество матчей.
                          При этом команда - соперник определяется случайно.
                          Если количество игроков не кратно 4, то некоторые игроки сыграют больше матчей, чем другие.</p>
                      <p>Рейтинг игрока определяется по проценту побед, в случае равенства - по разнице забитых-пропущенных в среднем за матч,
                          в случае равенства - по количеству забитых мячей в среднем за матч, в случае равенства - по количеству сыгранных матчей</p>
                      <p>Одновременно выводится определенное количество матчей. Матчи подбираются таким образом, чтобы у каждого был хотя бы один матч.
                          Чтобы открылись следующие матчи, нужно отыграть то, что есть.
                      <p>Матчей в текущей конфигурации : ${ms}, при этом осталось сыграть: ${fs}</p>
                  </div>
    </jsp:attribute>
</t:templatepage>