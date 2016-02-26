<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored ="false" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Schedule"%>
<%@ page import="model.Match"%>
<%@ page import="model.Championship" %>

<%
    Schedule forecast = Championship.getInstance().getForecast().head();
    request.setAttribute("forecast", forecast);
%>


<t:templatepage>
    <jsp:attribute name="content">
        <table class="table table-striped">
            <tr>
            <th> Имя </th>
            <th> Счет </th>
            <th> Имя </th>
            </tr>
            <c:forEach items="${forecast}" var="match">
            <tr>
              <td> <c:out value="${match.team1}"/></td>
              <td> <c:out value="${match.score}"/></td>
              <td> <c:out value="${match.team2}"/></td>
            </tr>
      </c:forEach>
        </table>
    </jsp:attribute>
</t:templatepage>