<%@ page import="hkmu.comps380f.model.User" %>
<%@ page import="org.springframework.data.jpa.repository.support.SimpleJpaRepository" %>
<%@ page import="hkmu.comps380f.dao.UserRepository" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
<p>Hello <security:authentication property="principal.username" />!</p>

<h2>userID: #${User.userId}</h2>
<%--这是要只给管理员看见的--%>
[<a href="<c:url value="/user/delete/${User.userId}" />">Delete</a>]<br/><br/>

<i>Hello - <c:out value="${User.userName}"/> - This is your profile></i><br/><br/>
<i>Your Phone Number: - <c:out value="${User.phoneNumber}"/></i><br/><br/>
<i>Your Email :- <c:out value="${User.userEmail}"/></i><br/><br/>

<%--//暂定，需要重建一个数据库去上传和删除用户的Description--%>
<i>Description - <c:out value="${User.userDescription}"/></i><br/><br/>

<h2>Your photo uploaded history</h2>
<%--//从User的 attachmentList中提取所有跟userId相同的attachment--%>

<c:if test="${!empty User.attachmentList}">
    Attachments:
    <c:forEach items="${User.attachmentList}" var="attachment" varStatus="status">
        <c:if test="${!status.first}">, </c:if>
        <br>
        <a href="<c:url value="/userDetail/${userId}/attachment/${attachmentList.attachmentId}" />">
            <c:out value="${attachment.attachmentName}"/> </a>
        Create at:<c:out value="${attachment.createTime}"/>

        [<a href="<c:url value="/userDetail/${userId}/delete/attachment/${attachmentList.attachmentId}" />">Delete</a>]
        </br>
    </c:forEach><br/><br/>
</c:if>

<a href="<c:url value="/photo/index" />">Return to photo index</a>
<br>
<a href="<c:url value="/photo/addPhoto" />">Add a photo</a>
</br>

<br>
<i>This is Your Description - <c:out value="${User.userDescription}"/></i><br/><br/>
</br>
<a href="<c:url value="/photo/profile/${SPRING_SECURITY_CONTEXT.authentication.principal.username}/DescriptionPage"/>"> Go to Edit My Description </a>
</body>
</html>