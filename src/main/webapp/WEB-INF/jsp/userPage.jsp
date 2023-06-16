<%@ page import="hkmu.comps380f.model.User" %>
<%@ page import="org.springframework.data.jpa.repository.support.SimpleJpaRepository" %>
<%@ page import="hkmu.comps380f.dao.UserRepository" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
<%--Profile Page Here--%>
<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
    <input type="submit" value="Log out" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<c:if test="${SPRING_SECURITY_CONTEXT.authentication.principal.username eq User.userName}">
    <p>Hello <security:authentication property="principal.username" />!</p>
</c:if>

<h2>userID: #${User.userId}</h2>

<i>Hello - <c:out value="${User.userName}"/> - This is your profile></i><br/><br/>
<i>Your Phone Number: - <c:out value="${User.phoneNumber}"/></i><br/><br/>
<i>Your Email :- <c:out value="${User.userEmail}"/></i><br/><br/>

<i>Description - <c:out value="${User.userDescription}"/></i><br/><br/>

<h2>${User.userName}'s photo uploaded history</h2>
<c:if test="${!empty User.attachmentList}">
    Attachments:
    <c:forEach items="${User.attachmentList}" var="attachment" varStatus="status">
        <c:if test="${!status.first}"></c:if><br>
        <a href="<c:url value="/photo/view/${attachment.attachmentId}"/>">
            <img style="width:465px;height:232px" src="<c:url value="/photo/attachment/download/${attachment.attachmentId}" />" />
        </a>
        <br/>
        <c:out value="${attachment.attachmentName}"/>
        Create at:<c:out value="${attachment.createTime}"/>
        <security:authorize access="hasRole('ADMIN') or principal.username=='${User.userName}'">
            [<a href="<c:url value="/user/${userId}/delete/attachment/${attachmentList.attachmentId}" />">Delete</a>]
        </security:authorize>
        </br>
    </c:forEach><br/><br/>
</c:if>

<%--//从User的 attachmentList中提取所有跟userId相同的attachment--%>
<security:authorize access="hasRole('ADMIN') or principal.username=='${User.userName}'">

    <br>
    <a href="<c:url value="/photo/addPhoto" />">Add a photo</a><br><br>

    [<a href="<c:url value="/photo/profile/${User.userName}/DescriptionPage"/>">Go to Edit the Description</a>]</br><br>
    [<a href="<c:url value="/photo/profile/${User.userName}/PhonePage"/>">Go to Edit the PhoneNumber</a>]</br><br>
    [<a href="<c:url value="/photo/profile/${User.userName}/EmailPage"/>">Go to Edit the Email</a>]

    <h2>Your comments history</h2>
    <c:if test="${!empty commentContent}">
        <c:forEach items="${commentContent}" var="comment">
            User: ${User.userName}<br>
            Comment: ${comment}<br><br>
        </c:forEach>
    </c:if>
</security:authorize>


<br><br>
<a href="<c:url value="/photo/index" />">Return to photo index</a>
</body>
</html>