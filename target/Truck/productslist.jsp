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
        <th>Name</th>
        <th>Weight</th>
        <th>Loading location</th>
        <th>Unloading location</th>
        <th>Cargo cost</th>
        <th>Transport characteristic</th>
    </tr>
    <cor:forEach var="product" items="${products}">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.weight}</td>
            <td>${product.loadingLocation}</td>
            <td>${product.unloadingLocation}</td>
            <td>${product.cargoCost}</td>
            <td>${product.transportCharacteristic}</td>
        </tr>
    </cor:forEach>
</table>
</body>
</html>
