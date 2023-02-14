<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="cor" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title> carrier page | functions</title>
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
        <h2>Welcome! We are glad to cooperate with you.</h2>
        <h3>Product List</h3>
        <%@include file="productslist.jsp" %>
        <h3>Reserve an item</h3>
        <%@include file="deleteproduct.jsp" %>
        <div class="agile_back_home">
            <a href="index.jsp">back to home</a>
        </div>
    </div>
</div>
</body>
</html>