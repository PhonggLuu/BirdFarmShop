<%-- 
    Document   : home
    Created on : Sep 13, 2023, 7:25:56 PM
    Author     : tlminh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="LOGIN_USER" scope="session" value="${sessionScope.LOGIN_USER}" />

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" type="image/png" href="assets/images/logo-title-bar.png"/>
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">

        <title>V.E.T - Thế giới Vẹt Cảnh</title>

        <!-- Additional CSS Files -->
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-hexashop.css">
        <link rel="stylesheet" href="assets/css/owl-carousel.css">
        <link rel="stylesheet" href="assets/css/lightbox.css">
    </head>

    <body>
        <!-- Header Start -->
        <%@include file="../layout/header.jsp" %>
        <!-- Header End -->
        <!-- ***** Main Banner Area Start ***** -->
        <div class="main-banner" id="top">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="left-content">
                            <div class="thumb">
                                <div class="inner-content">
                                    <h4>V.E.T - Thế giới vẹt cảnh</h4>
                                    <span>Đặt niềm yêu thú cưng của bạn vào tay chuyên gia về chim vẹt.</span>
                                </div>
                                <img src="assets/images/left-banner-image.jpg" alt="">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="right-content">
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="right-first-image">
                                        <div class="thumb">
                                            <div class="inner-content">
                                                <h4>Vẹt cảnh</h4>
                                                <span>Bạn Đồng Hành Tuyệt Vời Cho Người Mê Thú Cưng</span>
                                            </div>
                                            <div class="hover-content">
                                                <div class="inner">
                                                    <h4>Vẹt cảnh</h4>
                                                    <p>Khám phá thế giới đầy màu sắc với các loài chim vẹt từ khắp nơi trên thế giới!</p>
                                                    <div class="main-border-button">
                                                        <a href="${pageScope.toBirds}">Xem thêm</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <img src="assets/images/baner-right-image-01.jpg">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="right-first-image">
                                        <div class="thumb">
                                            <div class="inner-content">
                                                <h4>Phụ kiện</h4>
                                                <span>Vật phẩm hàng đầu cho loài lông vũ</span>
                                            </div>
                                            <div class="hover-content">
                                                <div class="inner">
                                                    <h4>Phụ kiện</h4>
                                                    <p>Tự hào giới thiệu bộ sưu tập phụ kiện cho chim vẹt!</p>
                                                    <div class="main-border-button">
                                                        <a href="${pageScope.toAccessories}">Xem thêm</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <img src="assets/images/baner-right-image-02.jpg">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="right-first-image">
                                        <div class="thumb">
                                            <div class="inner-content">
                                                <h4>Nhân giống</h4>
                                                <span>Ghép cặp sinh sản chim vẹt theo yêu cầu</span>
                                            </div>
                                            <div class="hover-content">
                                                <div class="inner">
                                                    <h4>Nhân giống</h4>
                                                    <p>Cùng nhau tạo ra cá thể vẹt mới đầy sự đáng yêu, độc đáo.</p>
                                                    <div class="main-border-button">
                                                        <a href="${pageScope.toPair}">Xem thêm</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <img src="assets/images/baner-right-image-04.jpg">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="right-first-image">
                                        <div class="thumb">
                                            <img src="assets/images/baner-right-image-03.jpg">
                                        </div>
                                    </div>
                                </div>    
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ***** Main Banner Area End ***** -->

        <!-- ***** Bird Breeds Area Starts ***** -->
        <section class="section home" id="men">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="section-heading">
                            <h2>Đa dạng chủng loài</h2>
                            <span>Đắm chìm trong vẻ đẹp và sự quý giá của những chú vẹt tại cửa hàng của chúng tôi!</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="men-item-carousel">
                            <div class="owl-men-item owl-carousel">
                                <c:forEach var="breed" items="${requestScope.BIRD_BREEDS}">
                                    <div class="item">
                                        <div class="thumb">
                                            <div class="hover-content">
                                                <ul>
                                                    <li><a href="MainController?action=NavToBird&txtBreedId=${breed.breed_id}"><i class="fa fa-eye"></i></a></li>
                                                </ul>
                                            </div>
                                            <img class="breed-img" src="${breed.breed_thumbnail}" alt="${breed.breed_name}">
                                        </div>
                                        <div class="down-content">
                                            <h4 class="breed-name">${breed.breed_name}</h4>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- ***** Bird Breeds Area Ends ***** -->

        <!-- ***** Accessories Area Starts ***** -->
        <section class="section home" id="women">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="section-heading">
                            <h2>Thế giới phụ kiện</h2>
                            <span>Những sản phẩm chăm sóc thú cưng được tin dùng bởi giới mê vẹt cảnh.</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="women-item-carousel">
                            <div class="owl-women-item owl-carousel">
                                <c:forEach var="category" items="${requestScope.ACCESSORY_CATEGORIES}">
                                    <div class="item">
                                        <div class="thumb">
                                            <div class="hover-content">
                                                <ul>
                                                    <li><a href="MainController?action=NavToAccessory&txtType=${category.category_id}"><i class="fa fa-eye"></i></a></li>
                                                </ul>
                                            </div>
                                            <img class="category-img" src="${category.category_thumbnail}" alt="${category.category_name}">
                                        </div>
                                        <div class="down-content">
                                            <h4 class="category-name">${category.category_name}</h4>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- ***** Accessories Area Ends ***** -->

        <!-- ***** Explore Area Starts ***** -->
        <section class="section" id="explore">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="left-content">
                            <h2>Các sản phẩm và dịch vụ tại V.E.T</h2>
                            <span>Chào mừng đến với Shop Bán Chim Cảnh - nơi bạn có thể khám phá một thế giới tuyệt vời của động vật cưng với sự đa dạng loài chim độc đáo và dịch vụ chất lượng hàng đầu. Với hơn một thập kỷ kinh nghiệm trong việc nuôi và chăm sóc chim, chúng tôi tự hào là địa chỉ đáng tin cậy cho những người yêu thú cưng lông mịn.</span>
                            <div class="quote">
                                <i class="fa fa-quote-left"></i><p>Chúng tôi cam kết cung cấp các con vật cưng khỏe mạnh và được nuôi dưỡng cẩn thận, để bạn có thể đón những người bạn mới vào gia đình mình.</p>
                            </div>
                            <p>Bên cạnh việc bán chim, chúng tôi còn cung cấp các sản phẩm chất lượng để giúp bạn chăm sóc chim cưng của mình, từ lồng cho đến thức ăn và đồ chơi.</p>
                            <p>Chúng tôi luôn sẵn sàng tư vấn và hỗ trợ bạn trong việc nuôi dưỡng và chăm sóc chim cảnh của mình. Bạn cũng có thể yên tâm về việc giao hàng, với dịch vụ vận chuyển an toàn và đảm bảo đến tận tay bạn.</p>
                            <div class="main-border-button">
                                <a href="">Tìm hiểu thêm</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="right-content">
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="leather">
                                        <h4>Đa dạng giống loài</h4>
                                        <span>Gần 100 cá thể vẹt quý giá</span>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="first-image">
                                        <img src="assets/images/explore-image-01.jpg" alt="">
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="second-image">
                                        <img src="assets/images/explore-image-02.jpg" alt="">
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="types">
                                        <h4>Thế giới phụ kiện</h4>
                                        <span>Hơn 50 dòng sản phẩm</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- ***** Explore Area Ends ***** -->

        <!-- ***** Subscribe Area Starts ***** -->
        <div class="subscribe">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="section-heading">
                            <h2>Đăng ký thông tin để có cơ hội nhận ưu đãi lên đến 30%</h2>
                            <span>Ưu đãi và tích lũy điểm thưởng giúp bạn mua sắm thả ga tại V.E.T.</span>
                        </div>
                        <form id="subscribe" action="" method="get">
                            <div class="row">
                                <div class="col-lg-5">
                                    <fieldset>
                                        <input name="name" type="text" id="name" placeholder="Họ và tên của bạn" required="">
                                    </fieldset>
                                </div>
                                <div class="col-lg-5">
                                    <fieldset>
                                        <input name="email" type="text" id="email" pattern="[^ @]*@[^ @]*" placeholder="Địa chỉ email của bạn" required="">
                                    </fieldset>
                                </div>
                                <div class="col-lg-2">
                                    <fieldset>
                                        <button type="submit" id="form-submit" class="main-dark-button"><i class="fa fa-paper-plane"></i></button>
                                    </fieldset>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-lg-4">
                        <div class="row">
                            <div class="col-6">
                                <ul>
                                    <li>Địa chỉ:<br><span>284 Pasteur, P.8 Q.3, TP.HCM</span></li>
                                    <li>Điện thoại:<br><span>0913-244-567</span></li>
                                    <li>Kênh thông tin:<br><span><a href="#">Facebook</a>, <a href="#">Instagram</a>, <a href="#">Zalo</a>, <a href="#">Shopee</a></span></li>
                                </ul>
                            </div>
                            <div class="col-6">
                                <ul>
                                    <li>Giờ làm việc:<br><span>07:30 - 19:30 <br>Từ T2 đến T6</span></li>
                                    <li>Email:<br><span>thegioivetcanh@gmail.com</span></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ***** Subscribe Area Ends ***** -->
        <!-- Start Footer -->
        <%@include file="../layout/footer.jsp" %>
        <!-- End Footer -->
        <script>

            $(function () {
                var selectedClass = "";
                $("p").click(function () {
                    selectedClass = $(this).attr("data-rel");
                    $("#portfolio").fadeTo(50, 0.1);
                    $("#portfolio div").not("." + selectedClass).fadeOut();
                    setTimeout(function () {
                        $("." + selectedClass).fadeIn();
                        $("#portfolio").fadeTo(50, 1);
                    }, 500);

                });
            });

        </script>

    </body>
</html>
