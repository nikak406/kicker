<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored ="false" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.PlayerRating"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Championship" %>

<%
    List<PlayerRating> ratings = Championship.getInstance().getRatings();
    request.setAttribute("ratings", ratings);
%>


<t:templatepage>

    <jsp:attribute name="content">
              <table class="table table-striped">
                  <tr>
                      <th> Имя </th>
                      <th> № </th>
                      <th> Поб </th>
                      <th> Пор </th>
                      <th> И </th>
                      <th> Г </th>
                      <th> П </th>
                      <th> Р </th>
                      <th> О </th>
                      <th> % </th>
                  </tr>
                  <c:forEach items="${ratings}" var="rating">
                      <tr>
                          <td> <c:out value="${rating.name}"/></td>
                          <td> <c:out value="${rating.place}"/></td>
                          <td> <c:out value="${rating.wins}"/></td>
                          <td> <c:out value="${rating.loses}"/></td>
                          <td><b> <c:out value="${rating.gamesPlayed}"/></b></td>
                          <td> <c:out value="${rating.winScore}"/></td>
                          <td> <c:out value="${rating.loseScore}"/></td>
                          <td> <c:out value="${rating.difference}"/></td>
                          <td><c:out value="${rating.score}"/></td>
                          <td><b> <c:out value="${rating.coef}"/></b></td>
                      </tr>
                  </c:forEach>
              </table>
    </jsp:attribute>
</t:templatepage>