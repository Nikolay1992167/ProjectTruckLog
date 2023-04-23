<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add product</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Weight</th>
        <th>Loading location</th>
        <th>Unloading location</th>
        <th>Cargo cost</th>
    </tr>
    </thead>
    <tbody>
    <form action="/creat_product" method="post">
        <tr>
            <td><input type="text" name="weight" placeholder="Put your weight" required></td>
            <td><input type="text" name="loadingLocation" placeholder="Put your loading location" required></td>
            <td><input type="text" name="unloadingLocation" placeholder="Put your unloading location" required></td>
            <td><input type="text" name="cargoCost" placeholder="Put your cargo cost" required></td>
            <td><input type="submit" value="Add"></td>
        </tr>
    </form>
    </tbody>
</table>
</body>
</html>
