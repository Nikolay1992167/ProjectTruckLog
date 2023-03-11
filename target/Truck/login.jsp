<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cor" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<!DOCTYPE html>
<html>
<head>
    <title>Truckage and Logistic | Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Truckage Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
    function hideURLbar(){ window.scrollTo(0,1); } </script>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
    <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
    <link href='//fonts.googleapis.com/css?family=Lato:400,100,100italic,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
</head>
<body>
<div class="logo_nav">
    <div class="container">
        <nav class="navbar navbar-default">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
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
                        <li><a href="about.jsp"><span data-hover="About">About</span></a></li>
                        <li><a href="services.jsp"><span data-hover="Services">Services</span></a></li>
                        <li><a href="mail.jsp"><span data-hover="Mail Us">Mail Us</span></a></li>
                    </ul>
                </nav>
            </div>
        </nav>
    </div>
</div>
<div class="banner1">
    <div class="container">
    </div>
</div>

<div class="login">
    <div class="container">
        <h6>Login</h6>
        <h3>Welcome to our Company. Please enter your
            <a href="login.jsp">Login</a> details to login here. Or <a href="sign_up.jsp">
                Register</a> here.</h3>
        <form action="/authorize" method="post">
            <td><input type="text" name="userName" placeholder="Put your User Name" required></td>
            <td><input type="password" class="lock" name="password" placeholder="Put your Password" required></td>
            <input type="submit" value="Login">
        </form>
        <div class="agile_back_home">
            <a href="index.jsp">Back to home</a>
        </div>
    </div>
</div>
<div class="agileinfo_copy_right">
    <div class="container">
        <div class="agileinfo_copy_right_left">
            <p>© 2023 Truckage and Logistic. All rights reserved | Developed by Nikolai Minich.</p>
        </div>
        <div class="agileinfo_copy_right_right">
            <ul class="social">
                <li><a class="social-linkedin" href="#">
                    <i></i>
                    <div class="tooltip"><span>Facebook</span></div>
                </a></li>
                <li><a class="social-twitter" href="#">
                    <i></i>
                    <div class="tooltip"><span>Twitter</span></div>
                </a></li>
                <li><a class="social-google" href="#">
                    <i></i>
                    <div class="tooltip"><span>Google+</span></div>
                </a></li>
                <li><a class="social-facebook" href="#">
                    <i></i>
                    <div class="tooltip"><span>Pinterest</span></div>
                </a></li>
                <li><a class="social-instagram" href="#">
                    <i></i>
                    <div class="tooltip"><span>Instagram</span></div>
                </a></li>
            </ul>
        </div>
        <div class="clearfix"> </div>
    </div>
</div>
</body>
</html>

