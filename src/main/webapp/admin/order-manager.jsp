<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Admin - Orders</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link
	href="${pageContext.request.contextPath}/admin/css/sb-admin-2.min.css"
	rel="stylesheet">

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
				<div class="container-fluid">

					<!-- Page Heading -->
					<div class="d-sm-flex align-items-center justify-content-between">
						<h1 class="h3 mb-0 text-gray-800">Order List</h1>
					</div>

					<div class="container-fluid px-3 py-3">
						<form id="filterForm" action="searchOrder" method="get">
							<div class="row align-items-center">
								<div class="col-md-4">
									<label for="fromDate">From Date:</label> <input type="date"
										id="fromDate" name="fromDate" class="form-control" required>
								</div>
								<div class="col-md-4">
									<label for="toDate">To Date:</label> <input type="date"
										id="toDate" name="toDate" class="form-control" required>
								</div>
								<div class="col-md-4" style="margin-top: 20px;">
									<button type="submit" class="btn btn-primary mt-2">Search</button>
								</div>
							</div>
						</form>
					</div>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Orders</h6>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" style="width=100%; cellspacing=0;">
									<thead>
										<tr>
											<th>Name</th>
											<th>Email</th>
											<th>Address</th>
											<th>Phone Number</th>
											<th>Order Date</th>
											<th>Status</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="orderDTO" items="${orderDTOs}">
											<tr>
											
												<td>${orderDTO.user.firstName} ${orderDTO.user.lastName}</td>
												<td>${orderDTO.user.email}</td>
												<td>${orderDTO.user.address}</td>
												<td>${orderDTO.user.phoneNumber}</td>
												<td>${orderDTO.createdAt}</td>
												<c:if test="${orderDTO.status == 0}">
													<td>Chờ duyệt</td>
												</c:if>
												<c:if test="${orderDTO.status == 1}">
													<td>Đang giao</td>
												</c:if>
												<c:if test="${orderDTO.status == 2}">
													<td>Đã nhận</td>
												</c:if>
												<c:if test="${orderDTO.status == 3}">
													<td>Đã bị hủy</td>
												</c:if>
												<td class="text-center">
													<a href="/BTL_WEB/admin/edit-order?id=${orderDTO.id}" class="btn btn-success btn-circle mr-2"> 
														<i class="fas fa-edit"></i> 
													</a> 
													
													<c:if test="${orderDTO.status < 2}">
														<a href="/BTL_WEB/admin/delete-order?id=${orderDTO.id}" class="btn btn-danger btn-circle"> 
															<i class="fas fa-trash"></i>
														</a>
													</c:if>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>

			</div>
			<!-- End of Main Content -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Bootstrap core JavaScript-->
	<script
		src="${pageContext.request.contextPath}/admin/vendor/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="${pageContext.request.contextPath}/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script
		src="${pageContext.request.contextPath}/admin/js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script
		src="${pageContext.request.contextPath}/admin/vendor/chart.js/Chart.min.js"></script>

	<!-- Page level custom scripts -->
	<script
		src="${pageContext.request.contextPath}/admin/js/demo/chart-area-demo.js"></script>
	<script
		src="${pageContext.request.contextPath}/admin/js/demo/chart-pie-demo.js"></script>

</body>

</html>