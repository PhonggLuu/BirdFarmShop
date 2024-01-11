<%-- 
    Document   : footer
    Created on : Sep 21, 2023, 9:57:35 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- ***** Footer Start ***** -->
<footer>
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="first-item">
                    <div class="logo">
                        <img src="assets/images/logo.png" alt="hexashop ecommerce templatemo">
                    </div>
                    <ul>
                        <li><a href="#">284 Pasteur, P.8 Q.3, TP.HCM</a></li>
                        <li><a href="#">thegioivetcanh@gmail.com</a></li>
                        <li><a href="#">0913-244-567</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-3">
                <h4>Sản phẩm và dịch vụ</h4>
                <ul>
                    <li><a href="${pageScope.toBirds}">Vẹt cảnh</a></li>
                    <li><a href="${pageScope.toAccessories}">Phụ kiện</a></li>
                    <li><a href="${pageScope.toCompare}">So sánh</a></li>
                    <li><a href="${pageScope.toPair}">Nhân giống</a></li>
                </ul>
            </div>
            <div class="col-lg-3">
                <h4>Đường dẫn hữu ích</h4>
                <ul>
                    <li><a href="${pageScope.toHome}">Trang chủ</a></li>
                    <li><a href="#">Về chúng tôi</a></li>
                    <li><a href="#">Hỗ trợ</a></li>
                    <li><a href="#">Liên hệ</a></li>
                </ul>
            </div>
            <div class="col-lg-3">
                <h4>Thông tin hỗ trợ</h4>
                <ul>
                    <li><a href="#">Hỗ trợ</a></li>
                    <li><a href="#">Câu hỏi thường gặp</a></li>
                    <li><a href="#">Giao hàng</a></li>
                    <li><a href="#">Theo dõi đơn hàng</a></li>
                </ul>
            </div>
            <div class="col-lg-12">
                <div class="under-footer">
                    <p>Copyright © 2023 V.E.T Co., Ltd. All Rights Reserved. 

                    <ul>
                        <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                        <li><a href="#"><i class="fa fa-instagram"></i></a></li>
                        <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</footer>
<!-- ***** Footer Area Ends ***** -->
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
