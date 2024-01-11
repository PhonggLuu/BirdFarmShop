
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" type="image/png" href="assets/images/logo-title-bar.png"/>
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">

        <title>V.E.T - Cập nhật thông tin vẹt cảnh</title>

        <!-- Additional CSS Files -->
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-hexashop.css">
        <link rel="stylesheet" href="assets/css/owl-carousel.css">
        <link rel="stylesheet" href="assets/css/lightbox.css">

    </head>

    <style>
        .col-lg-12 .product-part {
            margin: 0 0 10px 10px;
        }
        .col-lg-12 input {
            margin-bottom: 10px;
            width: 50%;
        }
        .col-lg-12 span {
            margin-top: 100px;
        }
    </style>

    <body>
        <!-- ***** Header Area Start ***** -->
                <%@include file="../layout/header.jsp" %>
        <!-- ***** Header Area End ***** -->

        <!-- ***** Main Banner Area Start ***** -->
        <div class="page-heading" id="top">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="inner-content">
                            <h2>Vẹt cảnh</h2>
                            <span>Những chú vẹt thông minh và đáng yêu nhất đã có mặt tại V.E.T</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ***** Main Banner Area End ***** -->


        <!-- ***** Products Area Starts ***** -->
        <c:set value="${requestScope.BIRDNEST}" var="birdnest"/>
        <section class="section" id="products">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-heading">
                            <h2>Thêm mới sản phẩm</h2>
                        </div>
                    </div>
                    <div class="col-lg-12 form-custom">
                        <form action="MainController?action=UpdateBirdNest" class="" method="POST">
                            <input type="hidden" name="action" value="AddNewBird">
                            <div class="form-outline">
                                <label>ID của tổ chim (Bao gồm 2 chữ hoa và 3 chữ số)</label>
                                <input type="text" class="input form-control form-control" value="${birdnest.nest_id}" disabled/>
                                <input type="hidden" name="nest_id" value="${birdnest.nest_id}"/>

                            </div>
                            <div class="form-outline mt-2">
                                <label>Tên tổ chim</label>
                                <input type="text" name="name" class="input form-control" required="" value="${birdnest.nest_name}"/>                          
                            </div>
                            <div class="form-outline mt-2">
                                <label>Giống loài</label>
                                <select name="breed-id">
                                    <option value="-1">Chọn giống</option>
                                    <c:forEach items="${requestScope.BREEDLIST}" var="breed">
                                        <c:if test= "${breed.breed_id == birdnest.breed_id}">
                                            <option value="${breed.breed_id}" selected>${breed.breed_name}</option>
                                        </c:if>
                                        <c:if test= "${breed.breed_id != birdnest.breed_id}">
                                            <option value="${breed.breed_id}" >${breed.breed_name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-outline mt-2">
                                <label>Giá</label>
                                <input type="number" name="price" class="input form-control" required="" value="${birdnest.price}">
                            </div>
                            <div class="form-outline mt-2">
                                <label>Mô tả</label>
                                <input type="text" name="description"  class="input form-control" value="${birdnest.description}" >
                            </div>
                            <div class="form-outline mt-2">
                                <select name="dad_bird_id" >
                                    <option value="-1"> Chọn chim bố </option>
                                    <c:forEach items="${requestScope.BIRDDADLIST}" var="bird">
                                        <c:if test= "${bird.bird_id == birdnest.dad_bird_id}">
                                            <option value="${bird.bird_id}" selected>${bird.bird_name}</option>
                                        </c:if>
                                        <c:if test= "${bird.bird_id != birdnest.dad_bird_id}">
                                            <option value="${bird.bird_id}" >${bird.bird_name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-outline mt-2">
                                <select name="mom_bird_id">
                                    <option value="-1"> Chọn chim mẹ </option>
                                    <c:forEach items="${requestScope.BIRDMOMLIST}" var="bird">
                                        <c:if test= "${bird.bird_id == birdnest.mom_bird_id}">
                                            <option value="${bird.bird_id}" selected>${bird.bird_name}</option>
                                        </c:if>
                                        <c:if test= "${bird.bird_id != birdnest.mom_bird_id}">
                                            <option value="${bird.bird_id}" >${bird.bird_name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>

                            </div>
                            <div class="form-outline mt-2">
                                <label>Số lượng chim trong tổ</label>
                                <input type="number" name="baby_quantity"  class="input form-control" min="1" max="100" step="1" required="" value="${birdnest.baby_quantity}">
                            </div>
                            <div class="form-outline mt-2">
                                <label>Giảm giá</label>
                                <input type="number" name="discount" class="input form-control" min="0" max="100" step="1" value="${birdnest.discount}"/>
                            </div>
                            <div class="form-outline mt-2">
                                <label>Trạng thái</label>

                                <c:if test= "${birdnest.status.equals('avaiable') }">
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="status" id="flexRadioDefault1" value="avaiable" checked>
                                        <label class="form-check-label" for="flexRadioDefault1">
                                            Còn hàng
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="status" id="flexRadioDefault2" value="non-avaiable" >
                                        <label class="form-check-label" for="flexRadioDefault2">
                                            Hết hàng
                                        </label>
                                    </div>
                                </c:if>
                                <c:if test= "${!birdnest.status.equals('avaiable')}">
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="status" id="flexRadioDefault1" value="avaiable">
                                        <label class="form-check-label" for="flexRadioDefault1">
                                            Còn hàng
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="status" id="flexRadioDefault2" value="non-avaiable" checked >
                                        <label class="form-check-label" for="flexRadioDefault2">
                                            Hết hàng
                                        </label>
                                    </div>
                                </c:if>                                    


                            </div>

                            <div class="form-outline mt-2">
                                <label>Hình ảnh sản phẩm 1 (Bắt buộc)</label>
                                <input type="text" name="txtImage_1" class="input form-control" required="" placeholder="URL" pattern="^(http|https|ftp)://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}(:[0-9]+)?(/.*)?$"/>
                            </div>
                            <div class="form-outline mt-2">
                                <label>Hình ảnh sản phẩm 2</label>
                                <input type="text" name="txtImage_2" class="input form-control" placeholder="URL" pattern="^(http|https|ftp)://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}(:[0-9]+)?(/.*)?$"/>
                            </div>
                            <div class="form-outline mt-2">
                                <label>Hình ảnh sản phẩm 3</label>
                                <input type="text" name="txtImage_3" class="input form-control" placeholder="URL" pattern="^(http|https|ftp)://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}(:[0-9]+)?(/.*)?$"/>

                                <button type="submit" class="btn btn-success"  name="action" value="UpdateBirdNest">Cập Nhật</button>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <!-- ***** Products Area Ends ***** -->

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
                                <li><a href="#">thegioivetcanh@petshop.com</a></li>
                                <li><a href="#">0913-244-567</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <h4>Sản phẩm và dịch vụ</h4>
                        <ul>
                            <li><a href="${pageScope.toBirds}">Vẹt cảnh</a></li>
                            <li><a href="${pageScope.toBirdNests}">Tổ chim non</a></li>
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

        <!-- jQuery -->
        <script src="assets/js/jquery-2.1.0.min.js"></script>

        <!-- Bootstrap -->
        <script src="assets/js/popper.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>

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
