<%-- 
    Document   : test
    Created on : Sep 23, 2023, 12:08:39 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="../assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="../assets/css/font-awesome.css">
        <link rel="stylesheet" href="../assets/css/templatemo-hexashop.css">
        <link rel="stylesheet" href="../assets/css/owl-carousel.css">
        <link rel="stylesheet" href="../assets/css/lightbox.css">
             <style type="text/css">
            body {
                margin: 0;
                padding-top: 150px;
                color: #2e323c;
                background: #f5f6fa;
                position: relative;
                height: 100%;
            }
            .account-settings .user-profile {
                margin: 0 0 1rem 0;
                padding-bottom: 1rem;
                text-align: center;
            }
            .about .active-profile a
            {
                color:  orange;
            }
            .account-settings .user-profile .user-avatar {
                margin: 0 0 1rem 0;
            }
            .account-settings .user-profile .user-avatar img {
                width: 90px;
                height: 90px;
                -webkit-border-radius: 100px;
                -moz-border-radius: 100px;
                border-radius: 100px;
            }
            .account-settings .user-profile h5.user-name {
                margin: 0 0 0.5rem 0;
            }
            .account-settings .user-profile h6.user-email {
                margin: 0;
                font-size: 0.8rem;
                font-weight: 400;
                color: #9fa8b9;
            }
            .account-settings .about {
                margin: 2rem 0 0 0;
                text-align: center;
            }
            .account-settings .about h5 {
                margin: 0 0 15px 0;
                color: #007ae1;
            }
            .account-settings .about p {
                font-size: 0.825rem;
            }
            .form-control {
                border: 1px solid #cfd1d8;
                -webkit-border-radius: 2px;
                -moz-border-radius: 2px;
                border-radius: 2px;
                font-size: .825rem;
                background: #ffffff;
                color: #2e323c;
            }

            .form-group .form-control {
                white-space: pre-wrap;
                font-size: 16px;
            }
            .form-group a {
                text-decoration: none;
                color: black;
            }

            .card {
                background: #ffffff;
                -webkit-border-radius: 5px;
                -moz-border-radius: 5px;
                border-radius: 5px;
                border: 0;
                margin-bottom: 1rem;
            }

            .about li {
                margin-bottom: 10px;
                padding-top: 10px;
                padding-bottom: 10px;
                padding-left: 5px;
                text-align: left;
            }

            .about li:hover {
                background: #cccccc;
            }

            .about a .icon{
                width: 24px;
                height: 24px;
                margin: 0px 10px 0px 0px;
                font-size: 24px;
            }

            .about a {
                color: black;
            }
            .text-primary{
                text-transform: uppercase;
                font-size: 20px;
            }
            .gutters{
                margin-bottom: 20px;
            }
            .register-date{
                font-style: italic;
                font-weight: bold;
            }
            .nav-item{
                width: 20%;
                text-align: center;
            }
            .scrollable-container {
                overflow-x: scroll;
            }
            .scrollable-list {
                white-space: nowrap;
                display: inline-block;
            }
            .scrollable-list th {
                width: auto;
                padding: 20px;
            }
            .scrollable-list td {
                width: auto;
                padding: 20px;
            }
        </style>
    <body>
        <%@include file="../layout/header.jsp" %>
        <div class="container">
            <div class="row gutters">
                <%@include file="../layout/sidebar-profile.jsp" %>
                <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
                    <div class="card h-100">
                        <div class="card-body order-section">
                            <!-- Tab buttons -->
                            <ul class="nav nav-tabs" id="orderTabs">
                                <li class="nav-item">
                                    <a class="nav-link active" data-toggle="tab" href="#pendingTab">Tất cả</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#processedTab">Đang ghép</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#inTransitTab">Đã sinh sản</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#deliveredTab">Đã ấp nở</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#cancelTab">Thanh toán</a>
                                </li>
                            </ul>
                            <div class="tab-pane fade" id="pendingTab">
                                <!-- Similar structure as the Pending Orders Tab -->
                                <div class="row">
                                    <!-- Card components for pending orders -->
                                    <div class="col-12">
                                        <div class="card w-100"> 
                                            <div class="card-body card-pair p-0">
                                                <div class="box-pair mt-2">
                                                    <div class="d-flex align-items-center justify-content-between">
                                                        <div class="pair-img pl-3">
                                                            <img src="../assets/images/bird-compare-1.jpg" />
                                                            <p>Chim canna da astrualia</p>
                                                        </div>
                                                        <div class="pair-status">
                                                            Đã sinh sản
                                                        </div>
                                                        <div class="pair-img pr-3">
                                                            <img src="../assets/images/bird-compare-1.jpg" />
                                                            <p>Chim canna da astrualia</p>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="box-pair mt-2">
                                                    <div class="d-flex align-items-center justify-content-between">
                                                        <div class="pair-img pl-3">
                                                            <img src="../assets/images/bird-compare-1.jpg" />
                                                            <p>Chim canna da astrualia</p>
                                                        </div>
                                                        <div class="pair-status">
                                                            Đã sinh sản
                                                        </div>
                                                        <div class="pair-img pr-3">
                                                            <img src="../assets/images/bird-compare-1.jpg" />
                                                            <p>Chim canna da astrualia</p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

            </div>
        </div>
        <%@include file="../layout/footer.jsp" %>

        <!--                <table border="0" cellpadding="0" cellspacing="0" style="border-collapse:collapse;border:0;max-width:750px;background:#f8f9fa; margin: auto; margin-top: 40px"width="100%">
                            <tbody>
                                <tr>
                                    <td style="text-align:center;border-bottom-color:orange;border-bottom-style:solid;border-bottom-width:10px;border-top-width:0; padding-top: 15px; padding-bottom: 10px;">
                                        <img src="../assets/images/logo.png" alt="V.E.T"/>
                                    </td>
                                </tr>
                                <tr style="padding-top: 10px">
                                    <td style="padding-top: 30px;padding-left: 20px; font-weight: bold;">
                                        Xin chào Gia Phong,  
                                    </td>
                                </tr>
                                 <tr style="padding-top: 10px">
                                    <td style="padding-top: 30px; padding-right: 40px ;padding-left: 20px;">
                                        Cảm ơn Quý khách đã tin tưởng & sử dụng sản phẩm của V.E.T - Thế giới vẹt cảnh
                                    </td>
                                </tr>
                                <tr style="padding-top: 10px">
                                    <td style="padding-top: 15px; padding-right: 40px ;padding-left: 20px;">
                                       Đơn mua hàng của Quý khách đã được phát hành. 
                                    </td>
                                </tr>
                                <tr style="padding-top: 10px">
                                    <td style="padding-top: 30px; padding-right: 40px ;padding-left: 20px;">
                                        <p style="display: inline-block; font-weight: bold;font-size: 17px;">Sản phẩm: </p>  
                                    </td>
                                </tr>
                                <tr style="padding-top: 10px">
                                    <td style="padding-top: 15px; padding-right:40px ;padding-left: 20px;">
                                        - <p style="display: inline-block"> 1 </p><p style="display: inline-block;margin-left: 3px;"> lồng chim inox abc</p> - <p style="display: inline-block"> 5,000,000đ </p>  
                                    </td>
                                </tr>
                                 <tr style="padding-top: 10px">
                                    <td style="padding-top: 30px; padding-right: 40px ;padding-left: 20px;">
                                        <p style="display: inline-block; font-weight: bold;font-size: 17px;"> Tổng giá trị đơn hàng: </p> 
                                        <p style="display: inline-block; font-size: 19px; color: red; margin-left: 5px;">5,000,000đ</p> 
                                    </td>
                                </tr>
                                <tr style="padding-top: 10px">
                                    <td style="padding-top: 30px; padding-right: 40px ;padding-left: 20px;">
                                        <p style="display: inline-block; font-weight: bold;font-size: 17px;">Hình thức thanh toán: </p> 
                                        <p style="display: inline-block;font-size: 15px">Thanh toán khi nhận hàng</p>
                                    </td>
                                </tr>
        
                                <tr style="padding-top: 10px">
                                    <td style="padding-top: 30px; padding-right: 40px ;padding-left: 20px;">
                                        <p style="display: inline-block; font-weight: bold;font-size: 17px;">Tên người nhận: </p> 
                                        <p style="display: inline-block;font-size: 15px">Thoogn tin dág</p>
                                    </td>
                                </tr>
                                <tr style="padding-top: 10px">
                                    <td style="padding-top: 30px; padding-right: 40px ;padding-left: 20px;">
                                       <p style="display: inline-block; font-weight: bold;font-size: 17px;">Số liên lạc: </p> 
                                        <p style="display: inline-block;font-size: 15px">09420625</p>
                                    </td>
                                </tr>
                                <tr style="padding-top: 10px">
                                    <td style="padding-top: 30px; padding-right: 40px ;padding-left: 20px;">
                                        <p style="display: inline-block; font-weight: bold;font-size: 17px;"> Địa chỉ giao hàng: </p> 
                                        <p style="display: inline-block;font-size: 15px">Thông 4 - Nâm N'</p>
                                    </td>
                                </tr>
                                <tr style="padding-top: 10px">
                                    <td style="padding-top: 30px; padding-right: 40px ;padding-left: 20px;">
                                      Quý khách vui lòng chờ giao hàng trong vòng 3-5 ngày kể từ ngày phát hành đơn mua hàng.
                                    </td>
                                </tr>
                                  <tr style="padding-top: 10px">
                                    <td style="padding-top: 15px; padding-right: 40px ;padding-left: 20px;">
                                       Nếu có vấn đề cần hỗ trợ Quý khách vui lòng liên hệ: Điện thoại: 19006955  email: thegioivetcanh@gmail.com
                                    </td>
                                </tr>        
                                <tr style="padding-top: 10px">
                                    <td style="padding-top: 50px; padding-right: 40px ;padding-left: 20px; text-align: center; padding-bottom: 20px">
                                        <a href="MainController?action=active&token="><button style="background-color: orange; border:none; line-height: 40px; color: white; font-weight: bold; padding: 0 10px; cursor: pointer">Xem đơn hàng</button></a>
                                    </td>
                                </tr>
                                
                                <tr style="padding-top: 10px;">
                                    <td style="padding-top: 30px; padding-right: 40px ;padding-left: 20px; padding-bottom: 40px">
                                        Chúc Quý khách sức khoẻ và thành công!,
                                        <br>
                                        V.E.T
                                    </td>
                                </tr>
                            </tbody>
                        </table>-->
        <script src="../assets/js/birdshop.js"></script>
        <script src="../assets/js/jquery-3.7.1.min.js"></script>
        <script src="../assets/js/popper.min.js"></script>

        <!-- Bootstrap -->
        <script src="../assets/js/bootstrap.min.js"></script>

        <!-- Plugins -->
        <script src="../assets/js/owl-carousel.js"></script>
        <script src="../assets/js/accordions.js"></script>
        <script src="../assets/js/datepicker.js"></script>
        <script src="../assets/js/scrollreveal.min.js"></script>
        <!--        <script src="assets/js/waypoints.min.js"></script>-->
        <script src="../assets/js/jquery.counterup.min.js"></script>
        <script src="../assets/js/imgfix.min.js"></script> 
        <script src="../assets/js/slick.js"></script> 
        <script src="../assets/js/lightbox.js"></script> 
        <script src="../assets/js/isotope.js"></script> 

        <!-- Global Init -->
        <script src="../assets/js/custom.js"></script>
    </body>
</html>
