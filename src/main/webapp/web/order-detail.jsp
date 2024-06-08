<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Fruitables - Order Detail</title>
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


        <!-- Checkout Page Start -->
        <div class="container-fluid py-5">
            <div class="container py-5" style="margin-top: 55px;">
                <h1 class="mb-4">Billing details</h1>
                <form action="cancel-order" action="post">
                	<input type="hidden" name="id" value="${orderDTO.id}"/>
                	<input type="hidden" name="user_id" value="${orderDTO.user.id}"/>
                	<input type="hidden" name="total" value="${orderDTO.total}"/>
                	<c:forEach var="order" items="${orderDTO.orderedProductDTOs}">
                		<input type="hidden" name="product_id" value="${order.product.id}"/>
                		<input type="hidden" name="quantity" value="${order.quantity}"/>
                	</c:forEach>
                    <div class="row g-5">
                        <div class="col-md-12 col-lg-6 col-xl-7">
                            <div class="row">
                                <div class="col-md-12 col-lg-6">
                                    <div class="form-item w-100">
                                        <label class="form-label my-3">First Name<sup>*</sup></label>
                                        <input type="text" class="form-control" name="firstName" value="${orderDTO.user.firstName}">
                                    </div>
                                </div>
                                <div class="col-md-12 col-lg-6">
                                    <div class="form-item w-100">
                                        <label class="form-label my-3">Last Name<sup>*</sup></label>
                                        <input type="text" class="form-control" name="lastName" value="${orderDTO.user.lastName}">
                                    </div>
                                </div>
                            </div>
                            <div class="form-item">
                                <label class="form-label my-3">Address <sup>*</sup></label>
                                <input type="text" class="form-control" name="address" value="${orderDTO.user.address}">
                            </div>
                            
                            <div class="form-item">
                                <label class="form-label my-3">Mobile<sup>*</sup></label>
                                <input type="tel" class="form-control" name="phoneNumber" value="${orderDTO.user.phoneNumber}">
                            </div>
                            <div class="form-item">
                                <label class="form-label my-3">Email Address<sup>*</sup></label>
                                <input type="email" class="form-control" name="email" value="${orderDTO.user.email}">
                            </div>
                            
                            <!--
                            <hr> 
                            <div class="form-check my-3">
                                <input class="form-check-input" type="checkbox" id="Address-1" name="Address" value="Address">
                                <label class="form-check-label" for="Address-1">Ship to a different address?</label>
                            </div>
                            <div class="form-item">
                                <textarea name="text" class="form-control" spellcheck="false" cols="30" rows="11" placeholder="Oreder Notes (Optional)"></textarea>
                            </div>
                             -->
                        </div>
                        <div class="col-md-12 col-lg-6 col-xl-5">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">Products</th>
                                            <th scope="col">Name</th>
                                            <th scope="col">Price</th>
                                            <th scope="col">Quantity</th>
                                            <th scope="col">Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="product_ordered" items="${orderDTO.orderedProductDTOs}">
	                                        <tr>
	                                            <th scope="row">
	                                                <div class="d-flex align-items-center mt-2">
	                                                    <img src="${pageContext.request.contextPath}/uploads/${product_ordered.product.image}" class="img-fluid rounded-circle" style="width: 90px; height: 90px;" alt="">
	                                                </div>
	                                            </th>
	                                            <td class="py-5">${product_ordered.product.productName}</td>
	                                            <td class="py-5">${product_ordered.product.price}</td>
	                                            <td class="py-5">${product_ordered.quantity}</td>
	                                            <td class="py-5">${product_ordered.product.price * product_ordered.quantity}</td>
	                                        </tr>
                                        </c:forEach>
                                        
                                        <!-- 
                                        <tr>
                                            <th scope="row">
                                            </th>
                                            <td class="py-5"></td>
                                            <td class="py-5"></td>
                                            <td class="py-5">
                                                <p class="mb-0 text-dark py-3">Subtotal</p>
                                            </td>
                                            <td class="py-5">
                                                <div class="py-3 border-bottom border-top">
                                                    <p class="mb-0 text-dark">$414.00</p>
                                                </div>
                                            </td>
                                        </tr>
                                         -->
                                        <!-- 
                                        <tr>
                                            <th scope="row">
                                            </th>
                                            <td class="py-5">
                                                <p class="mb-0 text-dark py-4">Shipping</p>
                                            </td>
                                            <td colspan="3" class="py-5">
                                                <div class="form-check text-start">
                                                    <input type="checkbox" class="form-check-input bg-primary border-0" id="Shipping-1" name="Shipping-1" value="Shipping">
                                                    <label class="form-check-label" for="Shipping-1">Free Shipping</label>
                                                </div>
                                                <div class="form-check text-start">
                                                    <input type="checkbox" class="form-check-input bg-primary border-0" id="Shipping-2" name="Shipping-1" value="Shipping">
                                                    <label class="form-check-label" for="Shipping-2">Flat rate: $15.00</label>
                                                </div>
                                                <div class="form-check text-start">
                                                    <input type="checkbox" class="form-check-input bg-primary border-0" id="Shipping-3" name="Shipping-1" value="Shipping">
                                                    <label class="form-check-label" for="Shipping-3">Local Pickup: $8.00</label>
                                                </div>
                                            </td>
                                        </tr>
                                         -->
                                        <tr>
                                            <th scope="row">
                                            </th>
                                            <td class="py-5">
                                                <p class="mb-0 text-dark text-uppercase py-3">TOTAL</p>
                                            </td>
                                            <td class="py-5"></td>
                                            <td class="py-5"></td>
                                            <td class="py-5">
                                                <div class="py-3 border-bottom border-top">
                                                    <p class="mb-0 text-dark">${orderDTO.total}</p>
                                                </div>
                                            </td>
                                        </tr>
                                        
                                        <tr>
                                            <th scope="row">
                                            </th>
                                            <td class="py-5">
                                                <p class="mb-0 text-dark text-uppercase py-3">STATUS</p>
                                            </td>
                                            <td class="py-5"></td>
                                            <td class="py-5"></td>
                                            <td class="py-5">
                                                <c:if test="${orderDTO.status == 0}">
	                                                <div class="py-3 border-bottom border-top">
	                                                    <p class="mb-0 text-dark">Chờ duyệt</p>
	                                                </div>
                                                </c:if>
                                                <c:if test="${orderDTO.status == 1}">
	                                                <div class="py-3 border-bottom border-top">
	                                                    <p class="mb-0 text-dark">Đang giao</p>
	                                                </div>
                                                </c:if>
                                                <c:if test="${orderDTO.status == 2}">
	                                                <div class="py-3 border-bottom border-top">
	                                                    <p class="mb-0 text-dark">Đã nhận</p>
	                                                </div>
                                                </c:if>
                                                <c:if test="${orderDTO.status == 3}">
	                                                <div class="py-3 border-bottom border-top">
	                                                    <p class="mb-0 text-dark">Đã hủy</p>
	                                                </div>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <!-- 
                            <div class="row g-4 text-center align-items-center justify-content-center border-bottom py-3">
                                <div class="col-12">
                                    <div class="form-check text-start my-3">
                                        <input type="checkbox" class="form-check-input bg-primary border-0" id="Transfer-1" name="Transfer" value="Transfer">
                                        <label class="form-check-label" for="Transfer-1">Direct Bank Transfer</label>
                                    </div>
                                    <p class="text-start text-dark">Make your payment directly into our bank account. Please use your Order ID as the payment reference. Your order will not be shipped until the funds have cleared in our account.</p>
                                </div>
                            </div>
                            <div class="row g-4 text-center align-items-center justify-content-center border-bottom py-3">
                                <div class="col-12">
                                    <div class="form-check text-start my-3">
                                        <input type="checkbox" class="form-check-input bg-primary border-0" id="Payments-1" name="Payments" value="Payments">
                                        <label class="form-check-label" for="Payments-1">Check Payments</label>
                                    </div>
                                </div>
                            </div>
                            <div class="row g-4 text-center align-items-center justify-content-center border-bottom py-3">
                                <div class="col-12">
                                    <div class="form-check text-start my-3">
                                        <input type="checkbox" class="form-check-input bg-primary border-0" id="Delivery-1" name="Delivery" value="Delivery">
                                        <label class="form-check-label" for="Delivery-1">Cash On Delivery</label>
                                    </div>
                                </div>
                            </div>
                            <div class="row g-4 text-center align-items-center justify-content-center border-bottom py-3">
                                <div class="col-12">
                                    <div class="form-check text-start my-3">
                                        <input type="checkbox" class="form-check-input bg-primary border-0" id="Paypal-1" name="Paypal" value="Paypal">
                                        <label class="form-check-label" for="Paypal-1">Paypal</label>
                                    </div>
                                </div>
                            </div>
                             -->
                            
                            <c:if test="${orderDTO.status < 2}">
	                            <div class="row g-4 text-center align-items-center justify-content-center pt-4">
	                                <button type="submit" class="btn border-danger py-3 px-4 text-uppercase w-100 text-danger">Cancel Order</button>
	                            </div>
                            </c:if>
                            
                            <c:if test="${orderDTO.status >= 2}">
	                            <div class="row g-4 text-center align-items-center justify-content-center pt-4">
	                                <a href="/BTL_WEB/web/order" class="btn border-secondary py-3 px-4 text-uppercase w-100 text-secondary">Back</a>
	                            </div>
                            </c:if>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- Checkout Page End -->


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