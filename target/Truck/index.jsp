<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cor" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<!DOCTYPE html>
<html>
<head>
    <title>Truckage and Logistis.</title>
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
<div class="header">
    <div class="container">
        <div class="w3l_header_left">
            <ul>
                <li><span class="glyphicon glyphicon-earphone" aria-hidden="true"></span>+ (375) 29 967 72 79</li>
                <li><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span><a href="minickola50@gmail.com">minickola50@gmail.com</a></li>
            </ul>
        </div>
        <div id="example2">
            <div class="wrapper2">
                <div class="content-wrapper2">
                    <div class="search-button2">
                        <span></span>
                    </div>
                    <div class="search-box2">
                        <form action="#" method="post">
                            <input type="text" name="Search" placeholder="Search Here..." required="">
                            <input type="submit" value="Send"> <img src="images/close.png" alt=" " />
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="w3l_header_right">
            <ul>
                <li><a href="login.jsp"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>log in</a></li>
                <li><a href="sign_up.jsp"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>sign up</a></li>
            </ul>
        </div>
    </div>
</div>
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
                    <h1><a class="navbar-brand" >Truc<span>k</span>age <p><h3>and</h3></p>Logistik</a></h1>
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

<div class="banner-bottom">
    <div class="col-md-6 banner_bottom_left">
        <h3>Our company's motto:</h3>
        <p><i>This is our guide to life.</i> To any point in the world at the speed of light!</p>
    </div>
    <div class="col-md-6 banner_bottom_right">
        <div class="wthree_banner_bottom_right_grids">
            <div class="col-md-6 banner_bottom_right_grid">
                <div class="view view-tenth">
                    <div class="agile_text_box">
                        <i></i>
                        <h3>Huge selection of carriers!</h3>
                        <p>Over 1000 partners.</p>
                    </div>
                    <div class="mask">
                        <img src="images/1.jpg" class="img-responsive" alt="" />
                    </div>
                </div>
            </div>
            <div class="col-md-6 banner_bottom_right_grid">
                <div class="view view-tenth">
                    <div class="agile_text_box">
                        <i class="men"></i>
                        <h3>Service support!</h3>
                        <p>24/7. 365 days a year.</p>
                    </div>
                    <div class="mask">
                        <img src="images/2.jpg" class="img-responsive" alt="" />
                    </div>
                </div>
            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="wthree_banner_bottom_right_grids">
            <div class="col-md-6 banner_bottom_right_grid">
                <div class="view view-tenth">
                    <div class="agile_text_box">
                        <i class="shipping"></i>
                        <h3>If necessary, free storage of goods!</h3>
                        <p>It's easy and simple with us!</p>
                    </div>
                    <div class="mask">
                        <img src="images/3.jpg" class="img-responsive" alt="" />
                    </div>
                </div>
            </div>
            <div class="col-md-6 banner_bottom_right_grid">
                <div class="view view-tenth">
                    <div class="agile_text_box">
                        <i class="clock"></i>
                        <h3>Ontime delivery!</h3>
                        <p>We can't do it the other way around.</p>
                    </div>
                    <div class="mask">
                        <img src="images/4.jpg" class="img-responsive" alt="" />
                    </div>
                </div>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
    <div class="clearfix"> </div>
</div>
<div class="banner-bottom1">
    <div class="col-md-6 agile_banner_bottom1_left">
        <img src="images/distribution.jpg" alt=" " class="img-responsive" />
    </div>
    <div class="col-md-6 agile_banner_bottom1_right">
        <h3>Many people know about us! And we'll meet the rest soon!</h3>
        <p>We have the best team! A team of specialists working in the same way, who knows the value of time.</p>
    </div>
    <div class="clearfix"> </div>
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

