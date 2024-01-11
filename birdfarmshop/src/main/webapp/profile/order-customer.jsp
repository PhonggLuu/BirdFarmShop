<%-- 
   Document   : order-customer
   Created on : Oct 18, 2023, 12:19:32 AM
   Author     : phong pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" type="image/png" href="assets/images/logo-title-bar.png"/>
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />


        <title>V.E.T - Thông tin cá nhân</title>

        <!-- Additional CSS Files -->
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-hexashop.css">
        <link rel="stylesheet" href="assets/css/owl-carousel.css">
        <link rel="stylesheet" href="assets/css/lightbox.css">


        <style type="text/css">
            body {
                margin: 0;
                padding-top: 150px;
                color: #2e323c;
                background: #f5f6fa;
                position: relative;
                height: 100%;
            }
            .stars {
                margin-left: 32px;
            }
            .rating-box {
                text-align: center;
                position: relative;
                background: #fff;
                padding: 25px 50px 35px;
                border-radius: 25px;
                box-shadow: 0 5px 10px rgba(0, 0, 0, 0.05);
            }
            .rating-box header {
                font-size: 22px;
                color: #dadada;
                font-weight: 500;
                margin-bottom: 20px;
                text-align: center;
            }
            .rating-box .stars {
                display: flex;
                align-items: center;
                gap: 25px;
            }
            .stars i {
                color: #e6e6e6;
                font-size: 35px;
                cursor: pointer;
                transition: color 0.2s ease;
            }
            .stars i.active {
                color: #ff9c1a;
            }
            .account-settings .user-profile {
                margin: 0 0 1rem 0;
                padding-bottom: 1rem;
                text-align: center;
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
            .about .active-profile a
            {
                color:  orange;
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
            .image-item img{
                height: 100px;
                width: 100px;
            }
            .card .bird {
                border: 1px solid #e9e9ed;
                border-radius: 15px;
                padding: 0 12px;
            }
            .card .order{
                width: 40px;
            }
            .body-bird{
                max-height: 585px;
                overflow-y: scroll;
            }
            .body-bird::-webkit-scrollbar {
                border-radius: 0;
                width: 8px;
            }

            .body-bird::-webkit-scrollbar-thumb {
                border-radius: 4px;
                background-color: rgba(22, 24, 35, 0.06);
            }

            .body-bird::-webkit-scrollbar-track {
                border-radius: 0;
                background-color: rgba(0, 0, 0, 0);
            }
            .nav-item{
                width: 14%;
            }
            #orderTabs li:hover{
                cursor: pointer;
            }
            .nav-link span {
                font-size: 12px;
            }

            .scrollable-container {
                width: 100%;
                max-height: 300px; /* Điều chỉnh kích thước tối đa theo nhu cầu */
                overflow-y: auto;
            }

            .scrollable-list {
                width: 100%;
            }
            .scrollable-list th, .scrollable-list td {
                text-align: center;
            }

            div {
                display: block;
            }
            .order-info {
                margin: 12px 0;
                border: 1px skyblue double;
                box-shadow: 0 1px 1px 0 rgba(0,0,0,.05);
                border-radius: 0.125rem;
                width: 100%;
            }
            .order-detail:first-child {
                padding-top: 24px;
            }
            .order-detail {
                padding: 12px 24px;
                background: #fff;
            }
            .order-bar {
                padding: 0 0 12px;
                justify-content: space-between;
                display: flex;
                align-items: center;
            }
            .order-id {
                display: flex;
                align-items: center;
                white-space: nowrap;
            }
            .order-status {
                float: right;
                line-height: 24px;
                color: #ee4d2d;
                text-align: right;
                text-transform: uppercase;
                white-space: nowrap;
            }
            .detail-inner-1:first-child {
                margin: 0;
                border: 0;
            }
            .detail-inner-1 {
                margin: 10px 0 0;
                border-top: 1px solid rgba(0,0,0,.09);
            }
            .info {
                display: flex;
                word-wrap: break-word;
                padding: 12px 0 0;
                align-items: center;
                flex-wrap: nowrap;
                color: rgba(0,0,0,.87);
            }
            .info-left {
                display: flex;
                word-wrap: break-word;
                padding: 0 12px 0 0;
                flex: 1;
                align-items: flex-start;
                flex-wrap: nowrap;
            }
            .product-image {
                width: 80px;
                height: 80px;
                flex-shrink: 0;
                border: 1px solid #e1e1e1;
                background: #e1e1e1;
            }
            .showAll {
                min-width: 0;
                padding: 0 0 0 12px;
                display: flex;
                flex: 1;
                flex-direction: column;
                align-items: flex-start;
                word-wrap: break-word;
            }
            .product-name {
                overflow: hidden;
                display: -webkit-box;
                text-overflow: ellipsis;
                -webkit-box-orient: vertical;
                -webkit-line-clamp: 2;
                margin: 0 0 5px;
                font-size: 16px;
                line-height: 22px;
                max-height: 48px;
            }
            .product-category {
                margin: 0 0 5px;
                color: rgba(0,0,0,.54);
            }
            .product-quantity {
                margin: 0 0 5px;
            }
            .info-right {
                text-align: right;
            }
            .price{
                margin-left: 12px;
            }
            .old-price {
                margin: 0 4px 0 0;
                -webkit-text-decoration-line: line-through;
                -moz-text-decoration-line: line-through;
                text-decoration: line-through;
                color: #000;
                opacity: .26;
                overflow: hidden;
                text-overflow: ellipsis;
                vertical-align: middle;
                font-size: 14px;
                line-height: 16px;
                color: rgba(0,0,0,.87);
            }
            .new-price {
                vertical-align: middle;
                font-size: 14px;
                line-height: 16px;
                color: rgba(0,0,0,.87);
            }
            .boundary {
                width: 100%;
                height: 0;
                border-bottom: 1px dotted rgba(0,0,0,.09);
                position: relative;
            }
            .boundary-1 {
                left: 0;
                -webkit-transform: translate(-50%,-50%);
                transform: translate(-50%,-50%);
            }
            .boundary-2 {
                right: 0;
                -webkit-transform: translate(50%,-50%);
                transform: translate(50%,-50%);
            }
            .boundary-both {
                -moz-box-sizing: border-box;
                box-sizing: border-box;
                background: #f5f5f5;
                width: 0.4375rem;
                height: 0.4375rem;
                border-radius: 50%;
                z-index: 1;
                position: absolute;
                top: 0;
            }
            .total-price {
                padding: 24px 24px 12px;
                background: #fffefb;
            }
            .show-price {
                display: flex;
                justify-content: flex-end;
                align-items: center;
            }
            .show-price span {
                cursor: pointer;
            }
            .show-price label {
                margin: 0 10px 0 0;
                font-size: 14px;
                line-height: 20px;
                color: rgba(0,0,0,.8);
            }
            .total {
                color: #ee4d2d;
                font-size: 24px;
                line-height: 30px;
            }

            .pair-status {
                line-height: 36px;
                width: 150px;
                border-radius: 20px;
                border: none;
                color: white;
                background: rgb(254,96,84);
                background: linear-gradient(90deg, rgba(254,96,84,1) 0%, rgba(239,50,162,1) 100%);
            }
        </style>

    </head>

    <body>
        <!-- Header Start -->
        <%@include file="../layout/header.jsp" %>
        <!-- Header End -->
        <c:set value="${requestScope.FEEDBACKLIST}" var="feedbackList"/>
        <c:set value="${requestScope.FREEITEMLIST}" var="freeItemList"/>
        <c:set value="${requestScope.ITEMMAP}" var="itemMap"/>
        <main>
            <div class="container container-custome">
                <div class="row gutters">
                    <%@include file="../layout/sidebar-profile.jsp" %>
                    <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
                        <div class="card h-100">
                            <div class="card-body profile-section">
                                <div class="row gutters">
                                    <div class="card-body order-section">
                                        <!-- Tab buttons -->
                                        <ul style="border-color: skyblue;" class="nav nav-tabs" id="orderTabs">
                                            <li style="border-color: skyblue;" class="nav-item">
                                                <a ${param.status == null || requestScope.status == null || empty param.status ? "style='border-color: skyblue;'":""} onclick="event.stopPropagation();" class="nav-link ${param.status == null || requestScope.status == null || empty param.status ? "active":""}" data-toggle="tab" href="MainController?action=NavToCustomerOrder">Tất cả</a>
                                            </li>
                                            <li class="nav-item">
                                                <a ${param.status || requestScope.status == 'wait' ? "style='border-color: skyblue;'":""} onclick="event.stopPropagation();" class="nav-link ${param.status || requestScope.status == 'wait' ? "active":""}" data-toggle="tab" href="MainController?action=NavToCustomerOrder&AMP;status=wait">Chờ xử lý</a>
                                            </li>
                                            <li class="nav-item">
                                                <a ${param.status || requestScope.status == 'inProgress' ? "style='border-color: skyblue;'":""} onclick="event.stopPropagation();" class="nav-link ${param.status || requestScope.status == 'inProgress' ? "active":""}" data-toggle="tab" href="MainController?action=NavToCustomerOrder&AMP;status=inProgress">Đang xử lý</a>
                                            </li>
                                            <li class="nav-item">
                                                <a ${param.status || requestScope.status == 'delivering' ? "style='border-color: skyblue;'":""} onclick="event.stopPropagation();" class="nav-link ${param.status || requestScope.status == 'delivering' ? "active":""}" data-toggle="tab" href="MainController?action=NavToCustomerOrder&AMP;status=delivering">Đang giao</a>
                                            </li>
                                            <li class="nav-item">
                                                <a ${param.status || requestScope.status == 'delivered' ? "style='border-color: skyblue;'":""} onclick="event.stopPropagation();" class="nav-link ${param.status || requestScope.status == 'delivered' ? "active":""}" data-toggle="tab" href="MainController?action=NavToCustomerOrder&AMP;status=delivered">Đã giao</a>
                                            </li>
                                            <li class="nav-item">
                                                <a ${param.status || requestScope.status == 'rated' ? "style='border-color: skyblue;'":""} onclick="event.stopPropagation();" class="nav-link ${param.status || requestScope.status == 'rated' ? "active":""}" data-toggle="tab" href="MainController?action=NavToCustomerOrder&AMP;status=rated">Đã đánh giá</a>
                                            </li>
                                            <li class="nav-item">
                                                <a ${param.status || requestScope.status == 'cancel' ? "style='border-color: skyblue;'":""} onclick="event.stopPropagation();" class="nav-link ${param.status || requestScope.status == 'cancel' ? "active":""}" data-toggle="tab" href="MainController?action=NavToCustomerOrder&AMP;status=cancel">Đã hủy</a>
                                            </li>
                                        </ul>
                                        <div class="tab-content">
                                            <div class="tab-pane fade show active">
                                                <div style="margin-left: 1px;" class="row"><!-- all orders -->
                                                    <c:forEach items="${itemMap}" var="map">
                                                        <c:set value="${map.key}" var="order"/>
                                                        <c:set value="${map.value}" var="itemList"/>
                                                        <fmt:formatDate value="${order.order_date}" pattern="dd/MM/yyyy" var="formattedDate" />
                                                        <div class="order-info"><!-- display order -->
                                                            <div style="width: 100%;"><!-- cover product, status -->
                                                                <div class="order-detail"><!-- small div product, status -->
                                                                    <section style="display: block;">
                                                                        <div class="order-bar">
                                                                            <!-- order id -->
                                                                            <div class="order-id">Mã đơn hàng: ${order.order_id} - Ngày đặt hàng: ${formattedDate}.</div>
                                                                            <!-- order status -->
                                                                            <c:set var="viewCancel" value="false" />
                                                                            <c:if test="${order.order_status eq 'Chờ xử lý'}">
                                                                                <c:forEach items="${itemList}" var="item">
                                                                                    <c:if test="${item.birdPair.status eq 'Chờ lấy chim'}">
                                                                                        <c:set var="viewCancel" value="true" />
                                                                                    </c:if>
                                                                                </c:forEach>
                                                                            </c:if>
                                                                            <c:if test="${order.order_status ne 'Chờ xử lý'}">
                                                                                <c:set var="viewCancel" value="true" />
                                                                            </c:if>
                                                                            <c:if test="${!viewCancel}">
                                                                                <div><a onclick="show('${order.order_id}'); event.stopPropagation();" style="color: white; background-color: #0066ff; padding: 5px 10px; border-radius: 20px; font-size: 16px;cursor: pointer">HỦY ĐƠN</a></div>
                                                                            </c:if>
                                                                            <div class="order-status">${order.order_status}</div>
                                                                        </div>
                                                                    </section>
                                                                    <div class="seperate"></div>
                                                                    <section>
                                                                        <h3></h3>
                                                                        <a href="#">
                                                                            <div>
                                                                                <div class="detail-inner-1">
                                                                                    <div class="detail-inner-2">
                                                                                        <div>
                                                                                            <c:forEach items="${itemList}" var="orderItem">
                                                                                                <c:choose>
                                                                                                    <c:when test="${orderItem.bird != null}">
                                                                                                        <section>
                                                                                                            <div class="info">
                                                                                                                <div class="info-left">
                                                                                                                    <img src="${orderItem.bird.image_url}" class="product-image" alt="" />
                                                                                                                    <div class="showAll">
                                                                                                                        <div>
                                                                                                                            <div class="product-name">
                                                                                                                                <span>
                                                                                                                                    ${orderItem.bird.bird_name}
                                                                                                                                    <c:if test="${orderItem.bird.gender}"> (Trống)</c:if>
                                                                                                                                    <c:if test="${!orderItem.bird.gender}"> (Mái)</c:if>
                                                                                                                                </span>
                                                                                                                            </div>
                                                                                                                        </div>
                                                                                                                        <div>
                                                                                                                            <div class="product-category"><span></span></div>
                                                                                                                            <div class="product-quantity"><span>x${orderItem.order_quantity}</span></div>
                                                                                                                        </div>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                                <div class="info-right">
                                                                                                                    <div class="price">
                                                                                                                        <span class="new-price"><fmt:formatNumber value="${orderItem.unit_price}" pattern="#,###"/>₫</span>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                            <c:set var="isFeedbacked" value="false" />
                                                                                                            <c:if test="${order.order_status eq 'Đã giao hàng' || order.order_status eq 'Đã đánh giá'}">
                                                                                                                <c:forEach items="${feedbackList}" var="feedback">
                                                                                                                    <c:if test="${orderItem.order_item_id == feedback.order_item_id}">
                                                                                                                        <c:set var="isFeedbacked" value="true" />
                                                                                                                    </c:if>
                                                                                                                </c:forEach>
                                                                                                                <c:if test="${!isFeedbacked}">
                                                                                                                    <button onclick="createFeedback('${orderItem.order_item_id}')" type="button" name="btndanhgia" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" >
                                                                                                                        Đánh giá
                                                                                                                    </button>
                                                                                                                </c:if>
                                                                                                            </c:if>
                                                                                                            <!-- Modal -->
                                                                                                            <form action = "MainController" method="POST">
                                                                                                                <c:if test="${param.status != null}">
                                                                                                                    <input type="hidden" name="status" value="${param.status}"/>
                                                                                                                </c:if>
                                                                                                                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                                                                                    <div class="modal-dialog">
                                                                                                                        <div class="modal-content">
                                                                                                                            <div class="modal-header">
                                                                                                                                <h5 class="modal-title" id="exampleModalLabel">Đánh giá của bạn</h5>
                                                                                                                                <i class="fa fa-times btn-close" data-bs-dismiss="modal" aria-label="Close" class="fa fa-times" aria-hidden="true"></i>
                                                                                                                            </div>
                                                                                                                            <div class="modal-body">
                                                                                                                                <input type="hidden" name="order_item_id" id="order_feedback"/>
                                                                                                                                <input type="hidden" class="" min="1" max="5" name="star" value="1" required="" id="star_number"/>
                                                                                                                                <div class="rating-box">
                                                                                                                                    <h4>Trải nghiệm của bạn thế nào ?</h4>
                                                                                                                                    <div class="stars">
                                                                                                                                        <i class="fa-solid fa-star active"></i>
                                                                                                                                        <i class="fa-solid fa-star"></i>
                                                                                                                                        <i class="fa-solid fa-star"></i>
                                                                                                                                        <i class="fa-solid fa-star"></i>
                                                                                                                                        <i class="fa-solid fa-star"></i>
                                                                                                                                    </div>
                                                                                                                                </div>
                                                                                                                                <div class="mb-3">
                                                                                                                                    <label for="exampleFormControlTextarea1" class="form-label">Nhập đánh giá</label>
                                                                                                                                    <textarea class="form-control" name="feedback" id="exampleFormControlTextarea1" rows="3" required="" title="Vui lòng nhập nội dung"></textarea>
                                                                                                                                </div>
                                                                                                                            </div>
                                                                                                                            <div class="modal-footer">
                                                                                                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                                                                                                                <button type="submit" id="submit" class="btn btn-primary" name="action" value="CreateFeedback">Lưu</button>
                                                                                                                            </div>
                                                                                                                        </div>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                            </form>
                                                                                                        </section>
                                                                                                    </c:when>
                                                                                                    <c:when test="${orderItem.accessory != null}">
                                                                                                        <section>
                                                                                                            <div class="info">
                                                                                                                <div class="info-left">
                                                                                                                    <img src="${orderItem.accessory.image_url}" class="product-image" alt="" />
                                                                                                                    <div class="showAll">
                                                                                                                        <div>
                                                                                                                            <div class="product-name"><span>${orderItem.accessory.accessory_name}</span></div>
                                                                                                                        </div>
                                                                                                                        <div>
                                                                                                                            <c:forEach items="${freeItemList}" var="item">
                                                                                                                                <c:if test="${orderItem.order_id eq item.order_id && orderItem.accessory.accessory_id eq item.accessory.accessory_id}">
                                                                                                                                        <div class="product-category"><span>Tặng kèm: ${item.order_quantity}</span></div>
                                                                                                                                </c:if>
                                                                                                                            </c:forEach>
                                                                                                                            <c:if test="${orderItem.order_quantity != 0}">
                                                                                                                                <div class="product-quantity"><span>x${orderItem.order_quantity}</span></div>
                                                                                                                            </c:if>
                                                                                                                        </div>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                                <div class="info-right">
                                                                                                                    <div class="price">
                                                                                                                        <c:if test="${not empty freeItemList}">
                                                                                                                            <c:set value="false" var="isFree"/>
                                                                                                                            <c:forEach items="${freeItemList}" var="item">
                                                                                                                                <c:if test="${orderItem.order_id eq item.order_id && orderItem.accessory.accessory_id eq item.accessory.accessory_id && orderItem.unit_price != 0}">
                                                                                                                                    <c:set value="true" var="isFree"/>
                                                                                                                                    <span class="old-price"><fmt:formatNumber value="${orderItem.accessory.unit_price * (orderItem.order_quantity + item.order_quantity)}" pattern="#,###"/>₫</span>
                                                                                                                                    <c:if test="${orderItem.order_quantity == 0}">
                                                                                                                                        <span class="new-price">0₫</span>
                                                                                                                                    </c:if>
                                                                                                                                </c:if>
                                                                                                                            </c:forEach>
                                                                                                                            <c:if test="${orderItem.unit_price != orderItem.accessory.unit_price && !isFree}">
                                                                                                                                <span class="old-price"><fmt:formatNumber value="${orderItem.accessory.unit_price * orderItem.order_quantity}" pattern="#,###"/>₫</span>
                                                                                                                            </c:if>
                                                                                                                            <c:if test="${orderItem.order_quantity != 0}">
                                                                                                                                <span class="new-price"><fmt:formatNumber value="${orderItem.unit_price * orderItem.order_quantity}" pattern="#,###"/>₫</span>
                                                                                                                            </c:if>
                                                                                                                        </c:if>
                                                                                                                        <c:if test="${empty freeItemList}">
                                                                                                                            <c:if test="${orderItem.unit_price != orderItem.accessory.unit_price}">
                                                                                                                                <span class="old-price"><fmt:formatNumber value="${orderItem.accessory.unit_price * orderItem.order_quantity}" pattern="#,###"/>₫</span>
                                                                                                                                <span class="new-price"><fmt:formatNumber value="${orderItem.unit_price * orderItem.order_quantity}" pattern="#,###"/>₫</span>
                                                                                                                            </c:if>
                                                                                                                            <c:if test="${orderItem.unit_price == orderItem.accessory.unit_price}">
                                                                                                                                <span class="new-price"><fmt:formatNumber value="${orderItem.unit_price * orderItem.order_quantity}" pattern="#,###"/>₫</span>
                                                                                                                            </c:if>
                                                                                                                        </c:if>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                            <c:set var="isFeedbacked" value="false" />
                                                                                                            <c:if test="${order.order_status eq 'Đã giao hàng' || order.order_status eq 'Đã đánh giá'}">
                                                                                                                <c:forEach items="${feedbackList}" var="feedback">
                                                                                                                    <c:if test="${orderItem.order_item_id == feedback.order_item_id}">
                                                                                                                        <c:set var="isFeedbacked" value="true" />
                                                                                                                    </c:if>
                                                                                                                </c:forEach>
                                                                                                                <c:if test="${!isFeedbacked}">
                                                                                                                    <button onclick="createFeedback('${orderItem.order_item_id}')" type="button" name="btndanhgia" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" >
                                                                                                                        Đánh giá
                                                                                                                    </button>
                                                                                                                </c:if>
                                                                                                            </c:if>
                                                                                                            <!-- Modal -->
                                                                                                            <form action = "MainController" method="POST">
                                                                                                                <c:if test="${param.status != null}">
                                                                                                                    <input type="hidden" name="status" value="${param.status}"/>
                                                                                                                </c:if>
                                                                                                                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                                                                                    <div class="modal-dialog">
                                                                                                                        <div class="modal-content">
                                                                                                                            <div class="modal-header">
                                                                                                                                <h5 class="modal-title" id="exampleModalLabel">Đánh giá của bạn</h5>
                                                                                                                                <i class="fa fa-times btn-close" data-bs-dismiss="modal" aria-label="Close" class="fa fa-times" aria-hidden="true"></i>
                                                                                                                            </div>
                                                                                                                            <div class="modal-body">
                                                                                                                                <input type="hidden" name="order_item_id" id="order_feedback"/>
                                                                                                                                <input type="hidden" class="" min="1" max="5" name="star" required="" value="1" id="star_number"/>
                                                                                                                                <div class="rating-box">
                                                                                                                                    <h4>Trải nghiệm của bạn thế nào ?</h4>
                                                                                                                                    <div class="stars">
                                                                                                                                        <i class="fa-solid fa-star active"></i>
                                                                                                                                        <i class="fa-solid fa-star"></i>
                                                                                                                                        <i class="fa-solid fa-star"></i>
                                                                                                                                        <i class="fa-solid fa-star"></i>
                                                                                                                                        <i class="fa-solid fa-star"></i>
                                                                                                                                    </div>
                                                                                                                                </div>
                                                                                                                                <div class="mb-3">
                                                                                                                                    <label for="exampleFormControlTextarea1" class="form-label">Nhập đánh giá</label>
                                                                                                                                    <textarea class="form-control" name="feedback" id="exampleFormControlTextarea1" rows="3" required="" title="Vui lòng nhập đánh giá"></textarea>
                                                                                                                                </div>
                                                                                                                            </div>
                                                                                                                            <div class="modal-footer">
                                                                                                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                                                                                                                <button type="submit" id="submit" class="btn btn-primary" name="action" value="CreateFeedback">Lưu</button>
                                                                                                                            </div>
                                                                                                                        </div>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                            </form>
                                                                                                        </section>
                                                                                                    </c:when>
                                                                                                    <c:when test="${orderItem.birdNest != null}">
                                                                                                        <section>
                                                                                                            <div class="info">
                                                                                                                <div class="info-left">
                                                                                                                    <img src="${orderItem.birdNest.image_url}" class="product-image" alt="" />
                                                                                                                    <div class="showAll">
                                                                                                                        <div>
                                                                                                                            <div class="product-name"><span>${orderItem.birdNest.nest_name}</span></div>
                                                                                                                        </div>
                                                                                                                        <div>
                                                                                                                            <div class="product-category"><span></span></div>
                                                                                                                            <div class="product-quantity"><span>x${orderItem.order_quantity}</span></div>
                                                                                                                        </div>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                                <div class="info-right">
                                                                                                                    <div class="price">
                                                                                                                        <span class="new-price"><fmt:formatNumber value="${orderItem.unit_price * orderItem.order_quantity}" pattern="#,###"/>₫</span>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                            <c:set var="isFeedbacked" value="false" />
                                                                                                            <c:if test="${order.order_status eq 'Đã giao hàng' || order.order_status eq 'Đã đánh giá'}">
                                                                                                                <c:forEach items="${feedbackList}" var="feedback">
                                                                                                                    <c:if test="${orderItem.order_item_id == feedback.order_item_id}">
                                                                                                                        <c:set var="isFeedbacked" value="true" />
                                                                                                                    </c:if>
                                                                                                                </c:forEach>
                                                                                                                <c:if test="${!isFeedbacked}">
                                                                                                                    <button onclick="createFeedback('${orderItem.order_item_id}')" type="button" name="btndanhgia" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" >
                                                                                                                        Đánh giá
                                                                                                                    </button>
                                                                                                                </c:if>
                                                                                                            </c:if>
                                                                                                            <!-- Modal -->
                                                                                                            <form action = "MainController" method="POST">
                                                                                                                <c:if test="${param.status != null}">
                                                                                                                    <input type="hidden" name="status" value="${param.status}"/>
                                                                                                                </c:if>
                                                                                                                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                                                                                    <div class="modal-dialog">
                                                                                                                        <div class="modal-content">
                                                                                                                            <div class="modal-header">
                                                                                                                                <h5 class="modal-title" id="exampleModalLabel">Đánh giá của bạn</h5>
                                                                                                                                <i class="fa fa-times btn-close" data-bs-dismiss="modal" aria-label="Close" class="fa fa-times" aria-hidden="true"></i>
                                                                                                                            </div>
                                                                                                                            <div class="modal-body">
                                                                                                                                <input type="hidden" name="order_item_id" id="order_feedback"/>
                                                                                                                                <input type="hidden" class="" min="1" max="5" name="star" required="" value="1" id="star_number"/>
                                                                                                                                <div class="rating-box">
                                                                                                                                    <h4>Trải nghiệm của bạn thế nào ?</h4>
                                                                                                                                    <div class="stars">
                                                                                                                                        <i class="fa-solid fa-star active"></i>
                                                                                                                                        <i class="fa-solid fa-star"></i>
                                                                                                                                        <i class="fa-solid fa-star"></i>
                                                                                                                                        <i class="fa-solid fa-star"></i>
                                                                                                                                        <i class="fa-solid fa-star"></i>
                                                                                                                                    </div>
                                                                                                                                </div>
                                                                                                                                <div class="mb-3">
                                                                                                                                    <label for="exampleFormControlTextarea1" class="form-label">Nhập đánh giá</label>
                                                                                                                                    <textarea class="form-control" name="feedback" id="exampleFormControlTextarea1" rows="3" required="" title="Vui lòng nhập đánh giá"></textarea>
                                                                                                                                </div>
                                                                                                                            </div>
                                                                                                                            <div class="modal-footer">
                                                                                                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                                                                                                                <button type="submit" id="submit" class="btn btn-primary" name="action" value="CreateFeedback">Lưu</button>
                                                                                                                            </div>
                                                                                                                        </div>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                            </form>
                                                                                                        </section>
                                                                                                    </c:when>
                                                                                                    <c:when test="${orderItem.birdPair != null}">
                                                                                                        <section>
                                                                                                            <div class="d-flex align-items-center justify-content-between">
                                                                                                                <c:choose>
                                                                                                                    <c:when test="${orderItem.birdPair.birdCustomer != null}">
                                                                                                                        <div class="pair-img pl-3 py-3 text-center">
                                                                                                                            <img style="width: 80px;" src="${orderItem.birdPair.birdCustomer.img_url}"/>
                                                                                                                            <p>${orderItem.birdPair.birdCustomer.name}
                                                                                                                                <c:if test="${orderItem.birdPair.birdCustomer.gender}"> (Trống)</c:if>
                                                                                                                                <c:if test="${!orderItem.birdPair.birdCustomer.gender}"> (Mái)</c:if>
                                                                                                                            </p>
                                                                                                                        </div>  
                                                                                                                    </c:when>
                                                                                                                    <c:otherwise>
                                                                                                                        <div class="pair-img pl-3 py-3 text-center">
                                                                                                                            <img style="width: 80px;" src="${orderItem.birdPair.male_bird.image_url}"/>
                                                                                                                            <p>${orderItem.birdPair.male_bird.bird_name} (Trống)</p>
                                                                                                                        </div> 
                                                                                                                    </c:otherwise>
                                                                                                                </c:choose>
                                                                                                                <div class="text-center">
                                                                                                                    <div class="pair-status mb-4">
                                                                                                                        ${orderItem.birdPair.status}
                                                                                                                    </div>
                                                                                                                    <a href="MainController?action=NavToBirdPairDetail&order_id=${orderItem.birdPair.order_id}&pair_id=${orderItem.birdPair.pair_id}"><span>Xem chi tiết</span></a>
                                                                                                                </div>
                                                                                                                <c:choose>
                                                                                                                    <c:when test="${orderItem.birdPair.female_bird != null}">
                                                                                                                        <div class="pair-img pl-3 py-3 text-center">
                                                                                                                            <img style="width: 80px;" src="${orderItem.birdPair.female_bird.image_url}"/>
                                                                                                                            <p>${orderItem.birdPair.female_bird.bird_name} (Mái)</p>
                                                                                                                        </div>  
                                                                                                                    </c:when>
                                                                                                                    <c:otherwise>
                                                                                                                        <div class="pair-img pl-3 py-3 text-center">
                                                                                                                            <img style="width: 80px;" src="${orderItem.birdPair.male_bird.image_url}"/>
                                                                                                                            <p>${orderItem.birdPair.male_bird.bird_name}</p>
                                                                                                                        </div> 
                                                                                                                    </c:otherwise>
                                                                                                                </c:choose>
                                                                                                            </div>
                                                                                                            <c:set var="isFeedbacked" value="false" />
                                                                                                            <c:if test="${order.order_status eq 'Đã giao hàng' || order.order_status eq 'Đã đánh giá'}">
                                                                                                                <c:forEach items="${feedbackList}" var="feedback">
                                                                                                                    <c:if test="${orderItem.order_item_id == feedback.order_item_id}">
                                                                                                                        <c:set var="isFeedbacked" value="true" />
                                                                                                                    </c:if>
                                                                                                                </c:forEach>
                                                                                                                <c:if test="${!isFeedbacked}">
                                                                                                                    <button onclick="createFeedback('${orderItem.order_item_id}')" type="button" name="btndanhgia" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" >
                                                                                                                        Đánh giá
                                                                                                                    </button>
                                                                                                                </c:if>
                                                                                                            </c:if>
                                                                                                            <!-- Modal -->
                                                                                                            <form action = "MainController" method="POST">
                                                                                                                <c:if test="${param.status != null}">
                                                                                                                    <input type="hidden" name="status" value="${param.status}"/>
                                                                                                                </c:if>
                                                                                                                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                                                                                    <div class="modal-dialog">
                                                                                                                        <div class="modal-content">
                                                                                                                            <div class="modal-header">
                                                                                                                                <h5 class="modal-title" id="exampleModalLabel">Đánh giá của bạn</h5>
                                                                                                                                <i class="fa fa-times btn-close" data-bs-dismiss="modal" aria-label="Close" class="fa fa-times" aria-hidden="true"></i>
                                                                                                                            </div>
                                                                                                                            <div class="modal-body">
                                                                                                                                <input type="hidden" name="order_item_id" id="order_feedback"/>
                                                                                                                                <input type="hidden" class="" min="1" max="5" name="star" value="1" required="" id="star_number"/>
                                                                                                                                <div class="rating-box">
                                                                                                                                    <h4>Trải nghiệm của bạn thế nào ?</h4>
                                                                                                                                    <div class="stars">
                                                                                                                                        <i class="fa-solid fa-star active"></i>
                                                                                                                                        <i class="fa-solid fa-star"></i>
                                                                                                                                        <i class="fa-solid fa-star"></i>
                                                                                                                                        <i class="fa-solid fa-star"></i>
                                                                                                                                        <i class="fa-solid fa-star"></i>
                                                                                                                                    </div>
                                                                                                                                </div>
                                                                                                                                <div class="mb-3">
                                                                                                                                    <label for="exampleFormControlTextarea1" class="form-label">Nhập đánh giá</label>
                                                                                                                                    <textarea class="form-control" name="feedback" id="exampleFormControlTextarea1" rows="3" required="" title="Vui lòng nhập đánh giá"></textarea>
                                                                                                                                </div>
                                                                                                                            </div>
                                                                                                                            <div class="modal-footer">
                                                                                                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                                                                                                                <button type="submit" id="submit" class="btn btn-primary" name="action" value="CreateFeedback">Lưu</button>
                                                                                                                            </div>
                                                                                                                        </div>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                            </form>
                                                                                                        </section>
                                                                                                    </c:when>
                                                                                                    <c:otherwise>
                                                                                                    </c:otherwise>
                                                                                                </c:choose>
                                                                                            </c:forEach>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </a>
                                                                    </section>
                                                                </div>
                                                            </div>
                                                            <div class="boundary">
                                                                <div class="boundary-1 boundary-both"></div>
                                                                <div class="boundary-2 boundary-both"></div>
                                                            </div>
                                                            <div class="total-price">
                                                                <div style="float: left;" class="order-status">${order.payment_status}</div>
                                                                <div class="show-price">
                                                                    <label style="font-weight: bold; font-size: 16px;">Thành tiền: </label>
                                                                    <div style="font-weight: bold;" class="total"><fmt:formatNumber value="${order.total_price}" pattern="#,###"/>₫</div>
                                                                </div>
                                                            </div>
                                                            <div style="margin-bottom: 20px;" class="total-price">
                                                                <c:if test="${order.name_receiver != null}">
                                                                    <div style="float: left;">
                                                                        <span>Người nhận: ${order.name_receiver}</span>
                                                                    </div>
                                                                <br/>
                                                                </c:if>
                                                                <c:if test="${order.phone_receiver != null}">
                                                                    <div style="float: left;">
                                                                        <span>Số điện thoại: 0${order.phone_receiver}</span>
                                                                    </div>
                                                                <br/>
                                                                </c:if>
                                                                <c:if test="${order.address_receiver != null}">
                                                                    <div style="float: left;">
                                                                        <span>Địa chỉ nhận hàng: ${order.address_receiver}</span>
                                                                    </div>
                                                                <br/>
                                                                </c:if>
                                                                <c:if test="${order.cancel_reason != null}">
                                                                    <div style="float: left;">
                                                                        <c:set var="reasonParts" value="${fn:split(order.cancel_reason, '(')}" />
                                                                        <span>Lí do hủy đơn: ${fn:replace(reasonParts[0], ')', '')}</span>
                                                                    </div>
                                                                <br/>
                                                                </c:if>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
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
        </main>
        <section id="confirm-remove" class="container-fluid">
            <div class="vh-100 row">
                <div class="h-100 m-auto d-flex align-items-center">
                    <div  style="width: 400px" class="box-remove bg-white p-4">
                        <div class="d-flex align-items-center justify-content-between">
                            <h4>Xác nhận</h4>
                            <div onclick="hide()" class="btn-close">
                                <i class="fa fa-times" aria-hidden="true"></i>
                            </div>
                        </div> 
                        <p class="mb-4 mt-4">
                            Lý do hủy đơn của bạn là gì ?
                        </p>
                        <div class="float-right">
                            <form action="MainController" method="POST">
                                <div style="float: left;">
                                    <input type="radio" id="reason-1" name="reason" value="Tôi không có nhu cầu mua nữa" checked><label for="reason-1">Tôi không có nhu cầu mua nữa</label><br/>
                                    <input type="radio" id="reason-2" name="reason" value="Tôi muốn cập nhật địa chỉ giao hàng"><label for="reason-2">Tôi muốn cập nhật địa chỉ giao hàng</label><br/>
                                    <input type="radio" id="reason-3" name="reason" value="Tôi muốn thay đổi sản phẩm"><label for="reason-3">Tôi muốn thay đổi sản phẩm</label><br/>
                                    <input type="radio" id="reason-4" name="reason" value="Tôi không tìm thấy lý do hủy phù hợp"><label for="reason-4">Tôi không tìm thấy lý do hủy phù hợp</label><br/>
                                </div>
                                <input type="hidden" name="action" value="NavToUpdateOrder"/>
                                <input type="hidden" name="order_id" value=""/>
                                <button id="btn-confirrm" class="btn btn-group-sm btn-primary">Xác nhận</button>
                                <button onclick="return hide();" class="btn btn-group-sm btn-secondary">Hủy</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section> 
        <!-- Start Footer -->
        <%@include file="../layout/footer.jsp" %>
        <!-- End Footer -->
        <script>
            function show(orderId) {
                $('#confirm-remove').css('display', 'block')
                $('input[name=order_id]').val(orderId);
            }
            function hide() {
                $('#confirm-remove').css('display', 'none')
                return false;
            }
        </script>
        <script>
            const stars = document.querySelectorAll(".stars i");
            stars.forEach((star, index1) => {
                star.addEventListener("click", () => {
                    document.getElementById("star_number").value = index1 + 1;
                    stars.forEach((star, index2) => {
                        index1 >= index2 ? star.classList.add("active") : star.classList.remove("active");
                    });
                });
            });

            function createFeedback(id) {
                document.getElementById("order_feedback").value = id;
            }
        </script>
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>