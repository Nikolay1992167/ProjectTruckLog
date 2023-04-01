<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="cor" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title> admin page | functions</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
    <link href='//fonts.googleapis.com/css?family=Lato:400,100,100italic,300,300italic,400italic,700,700italic,900,900italic'
          rel='stylesheet' type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
          rel='stylesheet' type='text/css'>
    <style type="text/css">
        TABLE {
            width: 1200px;
            border: 2px solid black;
            background: silver;
        }
        TD, TH {
            text-align: center;
            padding: 3px;
        }
        TH {
            background: #4682b4;
            color: white;
            border-bottom: 2px solid black;
        }
        h2 {
            margin-bottom: 30px;
        }
        h3 {
            margin-top: 30px;
        }
    </style>
</head>
<body>
<div class="logo_nav">
    <div class="container">
        <nav class="navbar navbar-default">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <div class="logo">
                    <h1><a class="navbar-brand" href="index.jsp">Truc<span>k</span>age & Logi<span>s</span>tic</a></h1>
                </div>
            </div>
            <div class="collapse navbar-collapse nav-wil" id="bs-example-navbar-collapse-1">
                <nav class="link-effect-2" id="link-effect-2">
                    <ul class="nav navbar-nav">
                        <li><a href="services.jsp"><span data-hover="Services">Services</span></a></li>
                        <li><a href="about.jsp"><span data-hover="About">About</span></a></li>
                        <li><a href="mail.jsp"><span data-hover="Mail Us">Mail Us</span></a></li>
                    </ul>
                </nav>
            </div>
        </nav>
    </div>
</div>
<div class="products">
    <div class="container">
        <h2>Welcome! </h2>
        <p></p>
        <h3>List of users</h3>
       <div>
           <table>
               <tr>
                   <th>ID</th>
                   <th>Name Company</th>
                   <th>Location adresse</th>
                   <th>email</th>
                   <th>User name</th>
                   <th>Password user</th>
                   <th>User type</th>
               </tr>
               <cor:forEach var="user" items="${users}">
                   <tr>
                       <td>${user.id}</td>
                       <td>${user.nameCompany}</td>
                       <td>${user.location}</td>
                       <td>${user.email}</td>
                       <td>${user.userName}</td>
                       <td>${user.password}</td>
                       <td>${user.userType}</td>
                   </tr>
               </cor:forEach>
           </table>
       </div>
        <p></p>
        <h3>Remove user from database</h3>
        <form action="/delete_user" method="post">
            <input type="text" name="id" placeholder="Put your id">
            <input type="submit" value="Remove">
        </form>
        <p></p>
        <h3>Product List</h3>
        <div>
            <%@include file="productslist.jsp" %>
        </div>
        <div>
            <h3>Add product</h3>
            <%@include file="addproduct.jsp" %>
        </div>
        <div>
            <h3>Remove a product from the list</h3>
            <%@include file="deleteproduct.jsp" %>
        </div>
        <div class="agile_back_home">
            <a href="index.jsp">Back to home</a>
        </div>
    </div>
</div>
</body>
</html>