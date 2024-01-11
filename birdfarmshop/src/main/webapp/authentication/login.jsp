<%-- 
    Document   : login
    Created on : Sep 13, 2023, 11:27:34 PM
    Author     : tlminh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="assets/images/logo-title-bar.png"/>
        <title>V.E.T - Đăng nhập</title>
        <link rel="stylesheet" href="assets/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="assets/css/font-awesome.css"/>
        <link rel="stylesheet" href="assets/css/templatemo-hexashop.css"/>
    </head>
    <body>
        <!-- ***** Preloader Start ***** -->
        <div id="preloader">
            <div class="jumper">
                <div></div>
                <div></div>
                <div></div>
            </div>
        </div>  
        <!-- ***** Preloader End ***** -->
        <section class="vh-100 bg-login">
            <div class="container py-5 h-100">
                <div class="row d-flex h-100 align-items-center justify-content-end form-custom"> 
                    <div class="col-md-6 col-lg-5 col-xl-5">
                        <form action="MainController?action=login" class="form-login" method="POST">
                            <!-- Email input -->
                            <div class="form-outline mb-4 text-center">
                                <a href="MainController?action=NavToHome">
                                    <img src="assets/images/logo.png"
                                         class="img-fluid"  alt="Logo image">
                                </a>
                            </div>

                            <div class="form-outline mb-3">
                                <label class="form-label mb-1" for="account">Email/Tên đăng nhập</label>
                                <input type="text" id="account" name="account" class="input form-control form-control-lg" value="${param.account}" required=""/>          
                            </div>

                            <!-- Password input -->
                            <div class="form-outline mb-3">
                                <label class="form-label mb-1" for="password">Mật khẩu</label>
                                <input type="password" id="password" name="password" class="input form-control form-control-lg" value="${param.password}" required=""/>
                                <div class="show-password">
                                    <i class="fa fa-eye" aria-hidden="true"></i>
                                </div>
                            </div>
                            <div class="d-flex justify-content-between align-items-center mb-4">
                                <!-- Checkbox -->
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="checkbox" value="" id="form1Example3"/>
                                    <label style="color: #007bff" class="form-check-label" for="form1Example3"> Ghi nhớ đăng nhập </label>
                                </div>
                                <a href="MainController?action=NavToReset">Quên mật khẩu?</a>
                            </div>

                            <!-- Submit button -->
                            <button type="submit" class="btn btn-primary btn-lg btn-block">Đăng nhập</button>
                            <div style="color: white" class="mt-2">Bạn mới biết đến V.E.T ?   <a href="MainController?action=NavToRegister">Đăng kí</a></div>
                            <div class="divider d-flex align-items-center my-4">
                                <p class="text-center fw-bold mx-3 mb-0 text-muted">Hoặc</p>
                            </div>
                            <div class="col-12 p-0">
                                <a class="btn btn-md btn-block border btn-custom"href="https://accounts.google.com/o/oauth2/auth?scope=https://www.googleapis.com/auth/userinfo.profile+https://www.googleapis.com/auth/userinfo.email
                                   &redirect_uri=http://localhost:8080/birdfarmshop/LoginGoogleController&response_type=code
                                   &client_id=563213089252-rga4rc627cobtc25fjmjuom2168u1hrp.apps.googleusercontent.com&approval_prompt=force"
                                   role="button">
                                    <div class="d-flex align-items-center justify-content-center">
                                        <img src="assets/images/google.png" alt="facebook icon"/> 
                                        <p style="color: white" class="ml-2">Google</p>
                                    </div>
                                </a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <%@include file="../layout/message.jsp" %>
        <script src="assets/js/jquery-3.7.1.min.js"></script>
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/js/jquery.validate.min.js" ></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="assets/js/birdshop.js"></script>
        <!-- Plugins -->
        <script src="assets/js/owl-carousel.js"></script>
        <script src="assets/js/accordions.js"></script>
        <script src="assets/js/datepicker.js"></script>
        <script src="assets/js/scrollreveal.min.js"></script>
        <script src="assets/js/slick.js"></script> 
        <script src="assets/js/lightbox.js"></script> 
        <script src="assets/js/isotope.js"></script> 
        <!-- Global Init -->
        <script src="assets/js/custom.js"></script>

    </body>
</html>
