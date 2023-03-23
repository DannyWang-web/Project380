<!DOCTYPE html>
<html>
<head>
  <title>Customer Support</title>
</head>
<body>
<h2>Users</h2>
<a href="<c:url value="/user/create" />">Create a User</a><br/><br/>
<c:choose>
  <c:when test="${fn:length(userDatabase) == 0}">
    <i>There are no user in the system.</i>
  </c:when>
  <c:otherwise>
    <c:forEach items="${userDatabase}" var="user">
      User: <a href="<c:url value="/user/view/${user.id}" />">${user.userName}</a>
      Comment: <c:out value="${user.comment}"/>
      [<a href="<c:url value="/user/delete/${user.id}" />">Delete</a>]<br />
      <br/>
    </c:forEach>
  </c:otherwise>
</c:choose>
</body>
</html>
