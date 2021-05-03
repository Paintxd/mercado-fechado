<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Produtos</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>

<div
        style="position: relative; display: inline-block; width: 50%; margin-bottom: 40px; margin-left: 15%; border-collapse: collapse;">
    <c:if test="${userList.size() > 0}">
        <table border="2" width="70%" cellpadding="2">
            <tr>
                <th>Nome</th>
                <th>Documento</th>
                <th>Endereço</th>
                <th>Email</th>
                <th>Data Nascimento</th>
            </tr>
            <c:forEach var="c" items="${userList}">
                <tr>
                    <td>${c.fullName}</td>
                    <td>${c.document}</td>
                    <td>${c.address}</td>
                    <td>${c.email}</td>
                    <td>${c.birthDate}</td>
                    <td><a href="/user/id/${c.id}?action=update">Edit</a></td>
                    <td><a href="/user/delete/id/${c.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<div
        style="position: relative; display: inline-block; width: 50%; margin-bottom: 40px; margin-left: 15%; border-collapse: collapse;">
    <form:form action="/user/${action}" method="POST" modelAttribute="user">
        <p>
            Primeiro Nome:
            <form:input path="firstName"/>
            <form:errors path="firstName" cssClass="error"/><br><br>
        </p>

        <p>
            Sobrenome:
            <form:input path="lastName"/>
            <form:errors path="lastName" cssClass="error"/><br><br>
        </p>

        <p>
            Senha:
            <form:input type="password" path="password"/>
            <form:errors path="password" cssClass="error"/><br><br>
        </p>

        <p>
            CPF:
            <form:input path="document"/>
            <form:errors path="document" cssClass="error"/><br><br>
        </p>

        <p>
            Endereço:
            <form:input path="address"/>
            <form:errors path="address" cssClass="error"/><br><br>
        </p>

        <p>
            Email:
            <form:input type="email" path="email"/>
            <form:errors path="email" cssClass="error"/><br><br>
        </p>

        <p>
            Data Nascimento:
            <form:input type="date" path="birthDate" />
            <form:errors path="birthDate" cssClass="error"/><br><br>
        </p>

        <input type="submit" value="Salvar"/>

    </form:form>
</div>

</body>
</html>