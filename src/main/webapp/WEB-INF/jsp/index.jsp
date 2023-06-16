<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <security:authorize access="hasRole('USER') or hasRole('ADMIN')">
        <a href="<c:url value="/photo/addPhoto"/>"> Upload new photo </a>
        </br></br>
    </security:authorize>

    <security:authorize access="hasRole('USER') or hasRole('ADMIN')">
        <a href="<c:url value="/photo/profile/${SPRING_SECURITY_CONTEXT.authentication.principal.username}"/>"> Go to my Profile </a>
        </br></br>
    </security:authorize>

    <security:authorize access="hasRole('ADMIN')">
        <a href="<c:url value="/user/list" />">Go to user list</a><br>
    </security:authorize>

    <c:if test="${userName eq 'anonymousUser'}">
        <a href="<c:url value="/login"/>"> Login in </a></br></br>
    </c:if>

</head>

<body>

<security:authorize access="hasRole('USER') or hasRole('ADMIN')">
    </br></br><c:url var="logoutUrl" value="/logout"/>
    <form action="${logoutUrl}" method="post">
        <input type="submit" value="Log out" />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</security:authorize>


<c:choose>
    <c:when test="${userName eq 'anonymousUser'}">
        <h2>No user currently logged in</h2>
        <span style="color:red">
            <h3>You are in Visitor mode. You can only read photo and comments.</h3>
            <h3>Rest of actions is not allowed.</h3>
        </span>


    </c:when>
    <c:otherwise>
        <h2>Hello, <c:out value="${userName}"/></h2>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${fn:length(userDatabase) == 0}">
        <i>There are no user in the system.</i>
    </c:when>

    <c:otherwise>
        <table>
        <c:forEach items="${userDatabase}" var="user">
            <c:if test="${!empty user.attachmentList}">
                <c:forEach items="${user.attachmentList}" var="attachment" varStatus="status">
                    <a href="<c:url value="/photo/view/${attachment.attachmentId}"/>">
                        <img style="width:465px;height:232px" src="<c:url value="/photo/user/${user.userId}/attachment/${attachment.attachmentId}" />" />
                    </a>
                </c:forEach>
            </c:if>
        </c:forEach>
        </table>
    </c:otherwise>
</c:choose>


</body>
</html>
