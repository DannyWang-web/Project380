<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
<h2>user #${userId}: <c:out value="${user.userDescription}"/></h2>
[<a href="<c:url value="/user/delete/${user.userId}" />">Delete</a>]<br/><br/>
<i>User Name - <c:out value="${user.userName}"/></i><br/><br/>
<i>Phone Number - <c:out value="${user.phoneNumber}"/></i><br/><br/>
<i>User Email - <c:out value="${user.userEmail}"/></i><br/><br/>
<i>User Password - <c:out value="${user.userPassword}"/></i><br/><br/>
<i>User Description - <c:out value="${user.userDescription}"/></i><br/><br/>
<a href="<c:url value="/photo/index" />">Return to photo index</a>
</body>
</html>
