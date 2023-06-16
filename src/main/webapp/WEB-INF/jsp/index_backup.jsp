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

<html>
<head>
    <title>This is the index page</title>
    <a href="<c:url value="/photo/addPhoto"/>"> Upload new photo </a>
    </br></br>
</head>

<body>
<c:choose>
    <c:when test="${fn:length(userDatabase) == 0}">
        <i>There are no user in the system.</i>
    </c:when>

    <c:otherwise>
        <c:forEach items="${userDatabase}" var="user">
            User: <a href="<c:url value="/user/view/${user.userId}" />">${user.userName}</a>
            </br>
            UserName: <c:out value="${user.userName}"/>
            </br>
            <c:if test="${!empty user.attachmentList}">
                Photo:  </br>
                <c:forEach items="${user.attachmentList}" var="attachment" varStatus="status">
                    <a href="<c:url value="/photo/view/${attachment.attachmentId}"/>">
<%--                        <img style="width:400px;height:200px" src="<c:url value="/user/${user.userId}/attachment/${attachment.attachmentId}" />" />--%>
                            <img style="width:400px;height:200px" src="<c:url value="/photo/user/${user.userId}/attachment/${attachment.attachmentId}" />" />
                    </a>
                    </br>
                    <a href="<c:url value="/photo/user/${user.userId}/attachment/${attachment.attachmentId}"/>">
                        Download <c:out value="${attachment.attachmentName}"/>  </br></br>
                    </a>
                </c:forEach>
                <br/><br/>
            </c:if>
            </br> </br>
        </c:forEach>
    </c:otherwise>
</c:choose>




</body>
</body>
</html>
