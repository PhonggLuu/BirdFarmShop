<%-- 
    Document   : register
    Created on : Sep 13, 2023, 11:29:01 PM
    Author     : tlminh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="assets/images/logo-title-bar.png"/>
        <title>V.E.T - Đăng ký</title>
        <link rel="stylesheet" href="assets/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="assets/css/font-awesome.css"/>
        <link rel="stylesheet" href="assets/css/templatemo-hexashop.css"/>
    </head>
    <style>
    </style>
    <body
        <!-- ***** Preloader Start ***** -->
        <div id="preloader">
            <div class="jumper">
                <div></div>
                <div></div>
                <div></div>
            </div>
        </div>  
        <!-- ***** Preloader End ***** -->
        <section class="vh-100 bg-register">
            <div class="container pt-5 h-75">
                <div class="row d-flex h-75 align-items-center justify-content-end form-custom">
                    <div class="col-md-6 col-lg-5 col-xl-5">
                        <form action="MainController?action=register" class="form-register" method="POST">  
                            <div class="form-outline text-center mb-3">
                                <a href="MainController?action=NavToHome">
                                    <img src="assets/images/logo.png"
                                         class="img-fluid"  alt="Bird image">
                                </a>
                            </div>  
                            <p class="error-notification mb-2" style="color: red"><c:out value="${requestScope.error}"/></p>
                            <!-- name input -->
                            <div class="form-outline">
                                <label class="form-label" for="name">Họ và tên</label>
                                <input type="text" id="name" name="name" class="input form-control form-control-lg" value="${param.name}" required=""/>
                            </div>
                            <!-- email input -->
                            <div class="form-outline mt-2">
                                <label class="form-label" for="email">Email</label>
                                <input type="text" id="email" name="email" class="input form-control form-control-lg" value="${param.email}" required=""/>                          
                            </div>
                            <!-- mobile input -->
                            <div class="form-outline mt-2">
                                <label class="form-label" for="mobile">Số điện thoại</label>
                                <input type="text" id="mobile" name="mobile" class="input form-control form-control-lg" value="${param.mobile}" required=""/>      
                            </div>
                            <!-- user input -->
                            <div class="form-outline mt-2">
                                <label class="form-label" for="account">Tên đăng nhập</label>
                                <input type="text" id="account" name="account" class="input form-control form-control-lg" value="${param.account}" required=""/>
                            </div>

                            <!-- Password input -->
                            <div class="form-outline  mt-2">
                                <label class="form-label" for="password">Mật khẩu</label>
                                <input type="password" id="password" name="password" class="input form-control form-control-lg" value="${param.password}" required=""/>
                                <div class="show-password">
                                    <i class="fa fa-eye" aria-hidden="true"></i>
                                </div>
                            </div>
                            <!--Password input -->
                            <div class="form-outline mt-2">
                                <label class="form-label" for="re-password">Xác nhận mật khẩu</label>
                                <input type="password" id="re-password" name="re-password" class="input form-control form-control-lg" value="${param.password}" required=""/>
                                <div class="show-re-password">
                                    <i class="fa fa-eye" aria-hidden="true"></i>
                                </div>
                            </div>
                            <!-- Submit button -->
                            <button type="submit" class="btn btn-primary btn-lg btn-block mt-3">Đăng kí</button>
                            <div class="mt-2 pb-5"style="color: white">Đã có tài khoản? <a href="MainController?action=NavToLogin"> Đăng nhập ngay</a></div>
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
        <script src="https://www.google.com/recaptcha/api.js"></script>
        <script type="text/javascript" src="assets/js/birdshop.js"></script>  
        <!-- Plugins -->
        <script src="assets/js/owl-carousel.js"></script>
        <script src="assets/js/accordions.js"></script>
        <script src="assets/js/datepicker.js"></script>
        <script src="assets/js/scrollreveal.min.js"></script>
        <script src="assets/js/waypoints.min.js"></script>
        <script src="assets/js/jquery.counterup.min.js"></script>
        <script src="assets/js/imgfix.min.js"></script> 
        <script src="assets/js/slick.js"></script> 
        <script src="assets/js/lightbox.js"></script> 
        <script src="assets/js/isotope.js"></script> 

        <!-- Global Init -->
        <script src="assets/js/custom.js"></script>

    </body>
</html>
