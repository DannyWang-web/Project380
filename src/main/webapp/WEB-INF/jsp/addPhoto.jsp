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
<h2>Add Photo</h2>
<form:form method="POST" enctype="multipart/form-data" modelAttribute="photoFormModel">
  <b>Attachments</b><br/>
  <input type="file" name="attachments" multiple="multiple"/><br/><br/>

  <%-- !!!!!!!!!!!!!!!! Test Only !!!!!!!!!!!!!!!! --%>
  <form:label path="date">date</form:label><br/>
  <form:input type="text" path="date"/><br/><br/>

  <form:label path="description">description</form:label><br/>
  <form:textarea path="description" rows="5" cols="30"/><br/><br/>

  <input type="submit" value="Submit"/>
</form:form>
</body>
</html>