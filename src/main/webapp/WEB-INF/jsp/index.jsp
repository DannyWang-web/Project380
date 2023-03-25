<%@ page import="java.io.FileOutputStream" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.IOException" %><%--
  Created by IntelliJ IDEA.
  User: chayou
  Date: 2023/3/23
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>

</script>
<html>
<head>
    <title>This is the index page</title>
    This is the User page  </br></br>

    <c:choose>
        <c:when test="${fn:length(userDatabase) == 0}">
            <i>There are no user in the system.</i>
        </c:when>
        <c:otherwise>
            <c:forEach items="${userDatabase}" var="user">
                User: <a href="<c:url value="/user/view/${user.id}" />">${user.userName}</a>
                </br>
                Comment: <c:out value="${user.comment}"/>
                </br>
                <c:if test="${!empty user.attachments}">
                    Photo:
                    <c:forEach items="${user.attachments}" var="attachment" varStatus="status">
                        <c:if test="${!status.first}">, </c:if>
                        </br>
                        <img style="width:400px;height:200px" src="<c:url value="/user/${user.id}/attachment/${attachment.id}" />" />
                        </br>
                            <a href="<c:url value="/user/${user.id}/attachment/${attachment.id}"/>">
                                <c:out value="${attachment.name}"/>
                            </a>
                        [<a href="<c:url value="/user/${user.id}/delete/${attachment.id}"/>">DeletePhoto</a>]
                        <br/>
                        [<a href="<c:url value="/user/delete/${user.id}" />">DeleteUser</a>]
                    </c:forEach><br/>
                </c:if>
                </br> </br>
            </c:forEach>
        </c:otherwise>
    </c:choose>

</head>
<body>

</body>
</html>
