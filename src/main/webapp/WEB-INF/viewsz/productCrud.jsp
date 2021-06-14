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
</head>
<body>

<div
        style="position: relative; display: inline-block; width: 50%; margin-bottom: 40px; margin-left: 15%; border-collapse: collapse;">
    <c:if test="${productList.size() > 0}">
        <table border="2" width="70%" cellpadding="2">
            <tr>
                <th>Nome</th>
                <th>Tipo</th>
                <th>Descrição</th>
                <th>Preço</th>
                <th>Estoque</th>
                <th>Porcentagem de desconto</th>
            </tr>
            <c:forEach var="c" items="${productList}">
                <tr>
                    <td>${c.name}</td>
                    <td>${c.type}</td>
                    <td>${c.description}</td>
                    <td>${c.getPriceDiscount()}</td>
                    <td>${c.stock}</td>
                    <td>${c.discountPercentage}</td>
                    <td><a href="/product/id/${c.id}?action=update">Edit</a></td>
                    <td><a href="/product/delete/id/${c.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<div
        style="position: relative; display: inline-block; width: 50%; margin-bottom: 40px; margin-left: 15%; border-collapse: collapse;">
    <form:form action="/product/${action}" method="POST" modelAttribute="product">
        <p>
            Nome:
            <form:input path="name"/>
            <form:errors path="name" cssClass="error"/><br><br>
        </p>

        <p>
            Tipo:
            <form:input path="type"/>
            <form:errors path="type" cssClass="error"/><br><br>
        </p>

        <p>
            Estoque:
            <form:input type="number" path="stock"/>
            <form:errors path="stock" cssClass="error"/><br><br>
        </p>

        <p>
            Preço:
            <form:input type="number" path="price" step="0.1"/>
            <form:errors path="price" cssClass="error"/><br><br>
        </p>

        <p>
            Porcentagem de desconto:
            <form:input type="number" path="discountPercentage"/>
            <form:errors path="discountPercentage" cssClass="error"/><br><br>
        </p>

        <p>
            Descrição:
            <form:input type="text" path="description"/>
            <form:errors path="description" cssClass="error"/><br><br>
        </p>

        <input type="submit" value="Salvar"/>

    </form:form>
</div>

</body>
</html>