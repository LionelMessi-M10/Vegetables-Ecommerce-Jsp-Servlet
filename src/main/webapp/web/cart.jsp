<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Fruitables - Cart</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap" rel="stylesheet"> 

        <!-- Icon Font Stylesheet -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="${pageContext.request.contextPath}/web/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/web/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


        <!-- Customized Bootstrap Stylesheet -->
        <link href="${pageContext.request.contextPath}/web/css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="${pageContext.request.contextPath}/web/css/style.css" rel="stylesheet">
    </head>

    <body>

        <jsp:include page="./layout/header.jsp"></jsp:include>


		<jsp:include page="./layout/modal_search.jsp"></jsp:include>


        <!-- Cart Page Start -->
        <div class="container-fluid py-5" style="margin-top: 100px;">
            <div class="container py-5">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                          <tr>
                            <th scope="col">Products</th>
                            <th scope="col">Name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Total</th>
                            <th scope="col">Handle</th>
                          </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="cartItem" items="${cartDTO.cartItemDTOs}">
                            	<tr>
	                                <th scope="row">
	                                    <div class="d-flex align-items-center">
	                                        <img src="${pageContext.request.contextPath}/uploads/${cartItem.product.image}" class="img-fluid me-5 rounded-circle" style="width: 80px; height: 80px;" alt="Image Product">
	                                    </div>
	                                </th>
	                                <td>
	                                    <p class="mb-0 mt-4">${cartItem.product.productName}</p>
	                                </td>
	                                <td>
	                                    <p class="mb-0 mt-4">${cartItem.product.price}</p>
	                                </td>
	                                <td>
	                                    <div class="input-group quantity mt-4" style="width: 100px;">
	                                        <div class="input-group-btn">
	                                            <a class="btn btn-sm btn-minus rounded-circle bg-light border" >
	                                            	<i class="fa fa-minus"></i>
	                                            </a>
	                                        </div>
	                                        <input type="text" class="form-control form-control-sm text-center border-0" value="${cartItem.quantity}">
	                                        <div class="input-group-btn">
	                                            <a class="btn btn-sm btn-plus rounded-circle bg-light border">
	                                                <i class="fa fa-plus"></i>
	                                            </a>
	                                        </div>
	                                    </div>
	                                </td>
	                                <td>
	                                    <p class="mb-0 mt-4">${cartItem.product.price * cartItem.quantity}</p>
	                                </td>
	                                <td>
	                                    <a href="/BTL_WEB/web/cart/delete-from-cart?id=${cartItem.id}" class="btn btn-md rounded-circle bg-light border mt-4" >
	                                        <i class="fa fa-times text-danger"></i>
	                                    </a>
	                                </td>
	                            
	                            </tr>
                            </c:forEach>
                            
                        </tbody>
                    </table>
                </div>
                <div class="mt-5">
                    <input type="text" class="border-0 border-bottom rounded me-5 py-3 mb-4" placeholder="Coupon Code">
                    <button class="btn border-secondary rounded-pill px-4 py-3 text-primary" type="button">Apply Coupon</button>
                </div>
                <form action="checkout-from-cart" method="post">
	                <div class="row g-4 justify-content-end">
	                    <div class="col-8"></div>
	                    <div class="col-sm-8 col-md-7 col-lg-6 col-xl-4">
	                        <div class="bg-light rounded">
	                            <div class="p-4">
	                                <h1 class="display-6 mb-4">Cart <span class="fw-normal">Total</span></h1>
	                                <div class="d-flex justify-content-between mb-4">
	                                    <h5 class="mb-0 me-4">Subtotal:</h5>
	                                    <c:if test="${not empty totalMoneyInCart}">
	                                    	<p class="mb-0">${totalMoneyInCart}</p>
	                                    </c:if>
	                                    
	                                    <c:if test="${empty totalMoneyInCart}">
	                                    	<p class="mb-0">0</p>
	                                    </c:if>
	                                </div>
	                                <div class="d-flex justify-content-between">
	                                    <h5 class="mb-0 me-4">Shipping</h5>
	                                    <div class="">
	                                        <p class="mb-0">Flat rate: $3.00</p>
	                                    </div>
	                                </div>
	                                <p class="mb-0 text-end">Shipping to Ukraine.</p>
	                            </div>
	                            <div class="py-4 mb-4 border-top border-bottom d-flex justify-content-between">
	                                <h5 class="mb-0 ps-4 me-4">Total</h5>
	                                <c:if test="${not empty totalMoneyInCart}">
	                                	<p class="mb-0 pe-4">${totalMoneyInCart}</p>
	                                </c:if>
	                                
	                                <c:if test="${empty totalMoneyInCart}">
	                                	<p class="mb-0 pe-4">0</p>
	                                </c:if>
	                            </div>
	                            <button type="submit" class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4" type="button">Proceed Checkout</button>
	                        </div>
	                    </div>
	                </div>
                </form>
            </div>
        </div>
        <!-- Cart Page End -->


        <jsp:include page="./layout/footer.jsp"></jsp:include>



        <!-- Back to Top -->
        <a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>   

        
    <!-- JavaScript Libraries -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/web/lib/easing/easing.min.js"></script>
    <script src="${pageContext.request.contextPath}/web/lib/waypoints/waypoints.min.js"></script>
    <script src="${pageContext.request.contextPath}/web/lib/lightbox/js/lightbox.min.js"></script>
    <script src="${pageContext.request.contextPath}/web/lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Template Javascript -->
    <script src="${pageContext.request.contextPath}/web/js/main.js"></script>
    </body>

</html>