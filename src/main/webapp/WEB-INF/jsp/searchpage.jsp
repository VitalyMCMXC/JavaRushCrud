<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Searching Results</title>
</head>
<body>
<h1>Searching Results</h1>

<table style="border: 1px solid; width: 500px; text-align:center">
    <thead style="background:#1ee2ff">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Age</th>
        <th>isAdmin</th>
        <th>createdDate</th>
        <th colspan="2">Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${user}" var="users">
        <c:url var="editUrl" value="/test/main/users/edit?id=${users.id}" />
        <c:url var="deleteUrl" value="/test/main/users/delete?id=${users.id}" />
        <tr>
            <td><c:out value="${users.id}" /></td>
            <td><c:out value="${users.name}" /></td>
            <td><c:out value="${users.age}" /></td>
            <td><c:out value="${users.getisAdmin()}" /></td>
            <td><c:out value="${users.createDate}" /></td>
            <td><a href="${editUrl}">Edit</a></td>
            <td><a href="${deleteUrl}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:if test="${empty user}">
    There are no search results :(
</c:if>

<c:url var="mainUrl" value="/test/main/users?page=1"/>
<p>Return <a href="${mainUrl}">Home</a></p>

</body>
</html>