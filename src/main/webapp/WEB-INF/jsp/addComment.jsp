<%--
  Created by IntelliJ IDEA.
  User: jiangyufeng
  Date: 2023/3/23
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
  <title>Customer Support</title>
</head>
<body>

<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
  <input type="submit" value="Log out" />
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<h2>Add Comment</h2>

<form:form method="POST" modelAttribute="cform">
  <form:label path="userName">userName</form:label><br/>
  <form:input type="text" path="userName"/><br/>

  <form:label path="cContent">comment content</form:label><br/>
  <form:textarea path="cContent" rows="5" cols="30"/><br/><br/>

  <input type="submit" value="Submit"/>

</form:form>
</body>
</html>
