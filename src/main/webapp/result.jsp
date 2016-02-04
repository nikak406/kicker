<%@ page import="model.Schedule" %>
<%@ page import="model.Championship" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored ="false" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Schedule forecast = Championship.getInstance().getForecast();
    request.setAttribute("forecast", forecast);
%>

<t:templatepage>
    <jsp:attribute name="content">
        <form action="/state"
              method="post">
        <select name='match'>
            <c:forEach items="${forecast}" var="match">
                <option value="${match}">${match}</option>
            </c:forEach>
            </select>
            <input type="text" name="score" size="7">
            <input type="submit" class="btn" value="Send">
        </form>
    </jsp:attribute>
</t:templatepage>