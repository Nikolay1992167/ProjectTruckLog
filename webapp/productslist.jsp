<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cor" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<html>
<head>
    <title>products list</title>
</head>
<body>
<table>
    <tr>
        <th>Product ID</th>
        <th>Weight</th>
        <th>Loading location</th>
        <th>Unloading location</th>
        <th>Cargo cost</th>
    </tr>
    <cor:forEach var="product" items="${products}">
        <tr>
            <td>${product.id}</td>
            <td>${product.weight}</td>
            <td>${product.loading_location}</td>
            <td>${product.unloading_location}</td>
            <td>${product.cargo_cost}</td>
        </tr>
    </cor:forEach>
</table>
</body>
</html>
