<%-- 
    Document   : feedback
    Created on : Sep 26, 2023, 8:34:09 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container mt-5">
    <c:if test="${not empty requestScope.feedbackList}">
        <div class="mt-3 feedback">   
            <div class="row">
                <c:set var="ratingCustomer" value="${requestScope.starCustomer}" />
                <h6 class="feedback-title" >Khách hàng đánh giá</h6>
                <div class="col-m-6 col-sm-6 col-md-5 col-lg-4 col-xl-3 my-3 total-left">
                    <div class="my-3">Tổng quan</div>
                    <div class="d-flex align-items-center">
                        <h5 class="mr-4 rating-total"><c:out value="${ratingCustomer.totalRating()}"/></h5>
                        <div class="rating big-star">
                            <span class="fa fa-star"></span>
                            <span class="fa fa-star"></span>
                            <span class="fa fa-star"></span>
                            <span class="fa fa-star"></span>
                            <span class="fa fa-star"></span> 
                            <div class="star-fill" style="width: <c:out value="${ratingCustomer.percentTotalRating()}"/>%;">
                                <span class="fa fa-star"></span>    
                                <span class="fa fa-star"></span>
                                <span class="fa fa-star"></span>
                                <span class="fa fa-star"></span>
                                <span class="fa fa-star"></span>
                            </div>
                        </div>
                    </div>
                    <p class="mt-2 text-muted">(<c:out value="${ratingCustomer.totalStar()}"/> đánh giá)</p>
                    <div class="mt-2 pb-3 review-detail">
                        <div class="d-flex align-items-center">
                            <div class="rating">
                                <span class="fa fa-star star-checked"></span>
                                <span class="fa fa-star star-checked"></span>
                                <span class="fa fa-star star-checked"></span>
                                <span class="fa fa-star star-checked"></span>
                                <span class="fa fa-star star-checked"></span>
                            </div>
                            <div class="rating-range">
                                <div style="width:<c:out value="${ratingCustomer.percentFiveStar()}"/>%" class="range-color">              
                                </div>
                            </div>
                            <span class="text-muted ml-2 user-select-none"><c:out value="${ratingCustomer.fiveStar}"/></span>
                        </div>
                        <div class="d-flex align-items-center">
                            <div class="rating">
                                <span class="fa fa-star star-checked"></span>
                                <span class="fa fa-star star-checked"></span>
                                <span class="fa fa-star star-checked"></span>
                                <span class="fa fa-star star-checked"></span>
                                <span class="fa fa-star "></span>
                            </div>
                            <div class="rating-range">
                                <div style="width:<c:out value="${ratingCustomer.percentFourStar()}"/>%" class="range-color">              
                                </div>
                            </div>
                            <span class="text-muted ml-2 user-select-none"><c:out value="${ratingCustomer.fourStar}"/></span>
                        </div>
                        <div class="d-flex align-items-center">
                            <div class="rating">
                                <span class="fa fa-star star-checked"></span>
                                <span class="fa fa-star star-checked"></span>
                                <span class="fa fa-star star-checked"></span>
                                <span class="fa fa-star "></span>
                                <span class="fa fa-star"></span>
                            </div>
                            <div class="rating-range">
                                <div style="width:<c:out value="${ratingCustomer.percentThreeStar()}"/>%" class="range-color">              
                                </div>
                            </div>
                            <span class="text-muted ml-2 user-select-none"><c:out value="${ratingCustomer.threeStar}"/></span>
                        </div>
                        <div class="d-flex align-items-center">
                            <div class="rating">
                                <span class="fa fa-star star-checked"></span>
                                <span class="fa fa-star star-checked"></span>
                                <span class="fa fa-star"></span>
                                <span class="fa fa-star"></span>
                                <span class="fa fa-star"></span>
                            </div>
                            <div class="rating-range">
                                <div style="width:<c:out value="${ratingCustomer.percentTwoStar()}"/>%" class="range-color">              
                                </div>
                            </div>
                            <span class="text-muted ml-2 user-select-none"><c:out value="${ratingCustomer.twoStar}"/></span>
                        </div>
                        <div class="d-flex align-items-center">
                            <div class="rating">
                                <span class="fa fa-star star-checked"></span>
                                <span class="fa fa-star"></span>
                                <span class="fa fa-star"></span>
                                <span class="fa fa-star"></span>
                                <span class="fa fa-star"></span>
                            </div>
                            <div class="rating-range">
                                <div style="width:<c:out value="${ratingCustomer.percentOneStar()}"/>%" class="range-color">              
                                </div>
                            </div>
                            <span class="text-muted ml-2 user-select-none"><c:out value="${ratingCustomer.oneStar}"/></span>
                        </div>
                    </div>
                </div>
                <div class="col-m-6 col-sm-6 col-md-7 col-lg-8 col-xl-9">
                    <div class="comment-list">
                        <c:forEach var="feedback" items="${requestScope.feedbackList}">
                            <div class="mx-4 mt-4 user-comment">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div>
                                        <h6 class="mb-2 font-weight-bold"><c:out value="${feedback.fullName}"/></h6>
                                        <div class="rating">
                                            <c:forEach begin="0" end="5" step="1" varStatus="loopIndex">
                                                <c:if test="${loopIndex.index < feedback.rating}">
                                                    <span class="fa fa-star star-checked"></span>
                                                </c:if>
                                                <c:if test="${loopIndex.index > feedback.rating}">
                                                    <span class="fa fa-star"></span>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div>
                                        <p class="text-muted user-select-none"><fmt:formatDate value="${feedback.feedbackDate}" pattern="dd-MM-yyyy"/></p>
                                    </div>
                                </div>
                                <p class="mt-2"><c:out value="${feedback.comment}"/></p>
                            </div>
                        </c:forEach>
                        <c:if test="${noOfPages != 1}">
                            <div class="d-flex float-right align-items-center page-pagination">
                                <c:set var="numberOfPage" value="${requestScope.currentPage}"/>
                                <input type="hidden" name="page" value="${numberOfPage}"/>
                                <c:if test="${noOfPages >= 1 && noOfPages <= 5}">  
                                    <div onclick="PrevPage()" class="page-prev ${numberOfPage <= 1 ? "page-disable":""}"><i class="fa fa-angle-left" aria-hidden="true"></i></i></div>
                                    <c:forEach begin="1" end="${noOfPages}" var="i">
                                        <div onclick="NavToNewPage(this)" class="page-navTo ${i == requestScope.currentPage ? "page-active":""}" data-value="${i}">${i}</div>       
                                    </c:forEach>
                                    <div onclick="NextPage()" class="page-next ml-3 ${numberOfPage >= noOfPages ? "page-disable":""}"><i class="fa fa-angle-right" aria-hidden="true"></i></i></div>
                                </c:if>    
                                <c:if test="${noOfPages > 5}">
                                    <c:if test="${numberOfPage > 2}">
                                        <div onclick="PrevPage()" class="page-prev ${numberOfPage <= 1 ? "page-disable":""}"><i class="fa fa-angle-left" aria-hidden="true"></i></i></div>
                                        <c:if test="${numberOfPage + 2 < noOfPages}">
                                            <c:set var="beginItem" value="${numberOfPage - 2 }"/>
                                            <c:set var="endItem" value="${numberOfPage + 2 }"/>
                                        </c:if>
                                        <c:if test="${numberOfPage + 2 >= noOfPages}">
                                            <c:set var="beginItem" value="${noOfPages-numberOfPage + 4}"/>
                                            <c:set var="endItem" value="${noOfPages}"/>
                                        </c:if>
                                        <c:forEach begin="${numberOfPage - 2}" end="${numberOfPage + 2}" var="i">
                                            <div class="page-navTo ${i == requestScope.currentPage ? "page-active":""}" data-value="${i}">${i}</div>      
                                        </c:forEach>
                                        <div onclick="NextPage()" class="page-next ml-3 ${numberOfPage >= noOfPages ? "page-disable":""}"><i class="fa fa-angle-right" aria-hidden="true"></i></i></div>
                                    </c:if>
                                    <c:if test="${numberOfPage <= 2}">
                                        <div onclick="PrevPage()" class="page-prev ${numberOfPage <= 1 ? "page-disable":""}"><i class="fa fa-angle-left" aria-hidden="true"></i></i></div>
                                        <c:forEach begin="1" end="5" var="i">   
                                            <div class="page-navTo ${i == requestScope.currentPage ? "page-active":""}" data-value="${i}">${i}</div>          
                                            </c:forEach>                   
                                            <div onclick="NextPage()" class="page-next ml-3 ${numberOfPage >= noOfPages ? "page-disable":""}"><i class="fa fa-angle-right" aria-hidden="true"></i></i></div>
                                    </c:if>        
                                </c:if>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>   
    </c:if>
</div>

