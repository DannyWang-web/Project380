<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
<h2>user #${userId}: <c:out value="${user.comment}"/></h2>
[<a href="<c:url value="/user/delete/${user.id}" />">Delete</a>]<br/><br/>
<i>User Name - <c:out value="${user.userName}"/></i><br/><br/>
<c:out value="${user.body}"/><br/><br/>
<c:if test="${!empty user.attachments}">
    Attachments:
    <c:forEach items="${user.attachments}" var="attachment" varStatus="status">
        <c:if test="${!status.first}">, </c:if>
        <a href="<c:url value="/user/${userId}/attachment/${attachment.id}" />">
            <c:out value="${attachment.name}"/></a>
        [<a href="<c:url value="/user/${userId}/delete/${attachment.id}"/>">Delete</a>]
    </c:forEach><br/><br/>
</c:if>
<a href="<c:url value="/user" />">Return to list users</a>
</body>
</html>
