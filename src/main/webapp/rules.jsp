<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored ="false" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:templatepage>
    <jsp:attribute name="content">
                  <div align="justify">
                      <p>Участники каждого матча на 100% случайны.
                          Если количество игроков не кратно четырем, то в очередном сете часть игроков
                          не принимает участие. Это определяется случайно. </p>
                      <p>Рейтинг игрока определяется по очкам, в случае равенства - по количеству сыгранных матчей,
                          в случае равенства - по забитым - пропущенным.</p>
                  </div>
    </jsp:attribute>
</t:templatepage>