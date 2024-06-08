<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin - Edit product</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="${pageContext.request.contextPath}/admin/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <jsp:include page="./layout/sidebar.jsp"></jsp:include>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <jsp:include page="./layout/navbar.jsp"></jsp:include>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid py-5">
            	<div class="container py-5">
                <h1 class="mb-4">Billing details</h1>
                <form action="save-edit-order" action="post">
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
                                        <input type="text" class="form-control" name="firstName" value="${orderDTO.user.firstName}" readonly>
                                    </div>
                                </div>
                                <div class="col-md-12 col-lg-6">
                                    <div class="form-item w-100">
                                        <label class="form-label my-3">Last Name<sup>*</sup></label>
                                        <input type="text" class="form-control" name="lastName" value="${orderDTO.user.lastName}" readonly>
                                    </div>
                                </div>
                            </div>
                            <div class="form-item">
                                <label class="form-label my-3">Address <sup>*</sup></label>
                                 <input type="text" class="form-control" name="address" value="${orderDTO.user.address}" readonly>
                            </div>
                            
                            <div class="form-item">
                                <label class="form-label my-3">Mobile<sup>*</sup></label>
                                <input type="tel" class="form-control" name="phoneNumber" value="${orderDTO.user.phoneNumber}" readonly>
                            </div>
                            <div class="form-item">
                                <label class="form-label my-3">Email Address<sup>*</sup></label>
                                <input type="email" class="form-control" name="email" value="${orderDTO.user.email}" readonly>
                            </div>
                            
                           
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
	                                                    <img src="${pageContext.request.contextPath}/uploads/${product_ordered.product.image}" class="img-fluid rounded-circle" style="width: auto; height: 90px;" alt="">
	                                                </div>
	                                            </th>
	                                            <td class="py-5">${product_ordered.product.productName}</td>
	                                            <td class="py-5">${product_ordered.product.price}</td>
	                                            <td class="py-5">${product_ordered.quantity}</td>
	                                            <td class="py-5">${product_ordered.product.price * product_ordered.quantity}</td>
	                                        </tr>
                                        </c:forEach>
                                        
                                        
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
                                            <td class="py-5">
                                                <select class="form-select mt-3" name="status">
				                                  <c:if test="${orderDTO.status == 0}">
					                                  <option value="0" selected>Chờ duyệt</option>
					                                  <option value="1">Đang giao</option>
					                                  <option value="2">Đã nhận</option>
					                                  <option value="3">Hủy đơn</option>
				                                  </c:if>
				                                  
				                                  <c:if test="${orderDTO.status == 1}">
					                                  <option value="0">Chờ duyệt</option>
					                                  <option value="1" selected>Đang giao</option>
					                                  <option value="2">Đã nhận</option>
					                                  <option value="3">Hủy đơn</option>
				                                  </c:if>
				                                  
				                                  <c:if test="${orderDTO.status == 2}">
					                                  <option value="0">Chờ duyệt</option>
					                                  <option value="1">Đang giao</option>
					                                  <option value="2" selected>Đã nhận</option>
					                                  
				                                  </c:if>
				                                  
				                                  <c:if test="${orderDTO.status == 3}">
					                                  <option value="0">Chờ duyệt</option>
					                                  <option value="1">Đang giao</option>
					                                  <option value="2">Đã nhận</option>
					                                  <option value="3" selected>Hủy đơn</option>
				                                  </c:if>
				                                  
				                                </select>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            
                            <div class="row g-4 text-center align-items-center justify-content-center pt-4">
                                <button type="submit" class="btn border-primary py-3 px-4 text-uppercase w-100 text-primary">Save change</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
                
            </div>
            <!-- End of Main Content -->
            <br><br>
        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Bootstrap core JavaScript-->
    <script src="${pageContext.request.contextPath}/admin/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${pageContext.request.contextPath}/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

</body>

</html>