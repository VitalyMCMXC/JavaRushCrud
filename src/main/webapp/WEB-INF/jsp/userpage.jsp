<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>List of Users</title>
</head>
<body>
<h1>List of Users</h1>

<table style="border: 1px solid; width: 500px; text-align:center">
  <thead style="background:#1ee2ff">
  <tr>
    <th>Id</th>
    <th>Name</th>
    <th>Age</th>
    <th>isAdmin</th>
    <th>createdDate</th>
    <th colspan="2">Actions</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${users}" var="users">
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

<c:if test="${empty users}">
  There are currently no users in the list.
</c:if>

<%--Search--%>
<c:url var="searchUrl" value="/test/main/users/search" />
<form:form modelAttribute="user" method="POST" action="${searchUrl}">
  <table>
    <tr>
      <td><form:label path="name">Name contains:</form:label></td>
      <td><form:input path="name"/></td>
      <td><input type="submit" value="Search"/></td>
    </tr>
  </table>
</form:form>

<%--For displaying Page numbers. The when condition does not display a link for the current page--%>
<table border="1" cellpadding="5" cellspacing="5">
  <tr>
    <c:forEach begin="1" end="${nOfPages}" var="i">
      <c:url var="pageUrl" value="/test/main/users?page=${i}&count=${count}" />
      <c:choose>
        <c:when test="${currentPage eq i}">
          <td>${i}</td>
        </c:when>
        <c:otherwise>
          <td><a href="${pageUrl}">${i}</a></td>
        </c:otherwise>
      </c:choose>
    </c:forEach>
  </tr>
</table>

<%--Paging Limit--%>
<c:url var="mainUrl" value="/test/main/users?page=1&count=" />
<table>
  <tr>
    <td>Display by </td>
    <td><a href="${mainUrl}2">2</a></td>
    <td><a href="${mainUrl}3">3</a></td>
    <td><a href="${mainUrl}5">5</a></td>
    <td><a href="${mainUrl}0">All</a></td>
  </tr>
</table>

<table>
  <c:url var="addUrl" value="/test/main/users/add" />
  <tr>
    <td>To add a new user click </td>
    <td><a href="${addUrl}">here</a></td>
  </tr>
</table>

</body>
</html>