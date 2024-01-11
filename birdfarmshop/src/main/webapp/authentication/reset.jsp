<%-- 
    Document   : reset
    Created on : Sep 22, 2023, 10:08:57 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="assets/images/logo-title-bar.png"/>
        <title>V.E.T - Quên mật khẩu</title>
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
        <section class="vh-100 bg-register">
            <div class="container pt-5 h-75">
                <div class="row d-flex h-75 align-items-center justify-content-end form-custom">
                    <div class="col-md-6 col-lg-5 col-xl-5">
                        <form action="MainController?action=reset" class="form-register" method="POST"> 
                            <div class="form-outline mb-5">
                                <div class="d-flex align-items-center">
                                    <div class="col-1 col-sm-2 col-md-2 col-lg-3 col-xl-2">
                                        <a class="ml-3"href="MainController?action=NavToLogin">
                                            <i style="color: white; font-size: 1.2rem; width: 30px;" class="fa fa-arrow-left" aria-hidden="true"></i>
                                        </a>
                                    </div>
                                    <div class="col-12 col-sm-8 col-md-10 col-lg-8 col-xl-8 text-center">
                                        <h3 style="color: white">Quên mật khẩu</h3>
                                    </div>

                                </div>
                            </div>  

                            <!-- email input -->
                            <div class="form-outline mt-4">
                                <label class="form-label ml-1 mb-1" for="email">Email</label>
                                <input type="text" id="email" name="email" class="input form-control form-control-lg" required=""/>                          
                            </div>
                            <p class="error-notification mt-3 mb-2" style="color: red"><c:out value="${requestScope.error}"/></p>
                            <!-- Submit button -->
                            <button type="submit" class="btn btn-primary btn-lg btn-block mt-3">Gửi đi</button>
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
