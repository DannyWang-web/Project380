<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>Customer Support User Management</title></head>
<body>
<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
    <input type="submit" value="Log out"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<br/><br/>

<a href="<c:url value="/photo/index" />">Return to photo index</a>

<h2>Users</h2>

<a href="<c:url value="/create" />">Create a User</a><br/><br/>

<c:choose>
    <c:when test="${fn:length(userDatabase) == 0}">
        <i>There are no users in the system.</i>
    </c:when>
    <c:otherwise>
        <table>
            <tr>
                <th>Username</th>
                <th>Password</th>
                <th>Roles</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${userDatabase}" var="user">
                <tr>
                    <td><a href="<c:url value="/user/view/${user.userId}" />">${user.userName}</a></td>
                    <td>${fn:substringAfter(user.userPassword, '{noop}')}</td>
                    <td>
                        <c:forEach items="${user.roles}" var="roles" varStatus="status">
                            <c:if test="${!status.first}">, </c:if>
                            ${roles.role}
                        </c:forEach>
                    </td>
                    <td>
                        [<a href="<c:url value="/user/delete/${user.userId}" />">Delete</a>]
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
<h2>ALL USER Photo History</h2>
<c:forEach items="${userDatabase}" var="User">
    <c:if test="${!empty User.attachmentList}">

        <c:forEach items="${User.attachmentList}" var="attachment" varStatus="status">
            <c:if test="${!status.first}"></c:if><br>
            <a href="<c:url value="/photo/view/${attachment.attachmentId}" />">
                <img style="width:465px;height:232px" src="<c:url value="/photo/attachment/download/${attachment.attachmentId}" />" />
            </a>
            <br/>
            <c:out value="${attachment.attachmentName}"/>
            Create at:<c:out value="${attachment.createTime}"/> by ${User.userName}

            </br>
        </c:forEach><br/><br/>
    </c:if>
</c:forEach>

<h2>ALL USER Comment History</h2>
<table>
    <c:forEach items="${commentList}" var="comment">
        <tr><td>User Name : ${comment.userName}
Comment content: ${comment.commentContent}</td></tr>
    </c:forEach>
</table>
</body>
</html>