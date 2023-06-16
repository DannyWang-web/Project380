<%--
  Created by IntelliJ IDEA.
  User: jiangyufeng
  Date: 2023/3/23
  Time: 13:58
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

<h2>Add Photo</h2>
<form:form method="POST" enctype="multipart/form-data" modelAttribute="photoFormModel">
  <b>Attachments</b><br/>
  <input type="file" name="attachments" multiple="multiple"/><br/><br/>

  <form:label path="description">description</form:label><br/>
  <form:textarea path="description" rows="5" cols="30"/><br/><br/>

  <input type="submit" value="Submit"/>
</form:form>
</body>
</html>