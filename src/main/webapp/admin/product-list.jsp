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

    <title>Admin - Products</title>

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
                <div class="container-fluid">

                  <!-- Page Heading -->
                  <div class="d-sm-flex align-items-center justify-content-between mb-3">
                      <h1 class="h3 mb-0 text-gray-800">Product List</h1>
                      <a href="/BTL_WEB/admin/add-product-page" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                        class="fas fa-plus fa-sm text-white-50"></i>Add Product</a>
                  </div>

                  <!-- DataTales Example -->
                  <div class="card shadow mb-4">
                      <div class="card-header py-3">
                          <h6 class="m-0 font-weight-bold text-primary">Products</h6>
                      </div>
                      <div class="card-body">
                          <div class="table-responsive">
                              <table class="table table-bordered" id="dataTable" style="width:100%; cellspacing:0">
                                  <thead>
                                      <tr>
                                          <th>Image</th>
                                          <th>Name</th>
                                          <th>Price</th>
                                          <th>Quantity</th>
                                          <th>Status</th>
                                          <th>Action</th>
                                      </tr>
                                  </thead>
                                  <tbody>
                                      <c:forEach var="pro" items="${products}">
                                      	<tr>
                                          <td>
                                          	<img src="${pageContext.request.contextPath}/uploads/${pro.image}" alt="image product" width="100px"/>
                                          </td>
                                          <td>${pro.productName}</td>
                                          <td>${pro.price}</td>
                                          <td>${pro.quantity}</td>
                                          <td>
                                          	<c:if test="${pro.status == 1}">
											    <span style="padding: 5px; background: green; color: white; border-radius: 20px;">Active</span>
											</c:if>
											<c:if test="${pro.status == 0}">
											    <span style="padding: 5px; background: red; color: white; border-radius: 20px;">UnActive</span>
											</c:if>

                                          </td>
                                          <td class="text-center">
                                            <a href="/BTL_WEB/admin/product-edit-page?id=${pro.id}" class="btn btn-primary btn-circle mr-1">
                                              <i class="fas fa-edit"></i>
                                            </a>
                                            <c:if test="${pro.status == 1}">
	                                            <a href="/BTL_WEB/admin/product-delete?id=${pro.id}" class="btn btn-danger btn-circle">
	                                              <i class="fas fa-trash"></i>
	                                            </a>
                                            </c:if>
                                          </td>
                                      	</tr>
                                      </c:forEach>
                                  </tbody>
                              </table>

                              <!-- <div class="row text-center">

                                <div class="col-sm-12">
                                  <div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
                                    <ul class="pagination">
                                      <li class="paginate_button page-item previous disabled" id="dataTable_previous">
                                        <a href="#" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">Previous</a>
                                      </li>
                                      <li class="paginate_button page-item active">
                                        <a href="#" aria-controls="dataTable" data-dt-idx="1" tabindex="0" class="page-link">1</a>
                                      </li>
                                      <li class="paginate_button page-item ">
                                        <a href="#" aria-controls="dataTable" data-dt-idx="2" tabindex="0" class="page-link">2</a>
                                      </li>
                                      <li class="paginate_button page-item ">
                                        <a href="#" aria-controls="dataTable" data-dt-idx="3" tabindex="0" class="page-link">3</a>
                                      </li>
                                      <li class="paginate_button page-item ">
                                        <a href="#" aria-controls="dataTable" data-dt-idx="4" tabindex="0" class="page-link">4</a>
                                      </li>
                                      <li class="paginate_button page-item ">
                                        <a href="#" aria-controls="dataTable" data-dt-idx="5" tabindex="0" class="page-link">5</a>
                                      </li>
                                      <li class="paginate_button page-item ">
                                        <a href="#" aria-controls="dataTable" data-dt-idx="6" tabindex="0" class="page-link">6</a>
                                      </li>
                                      <li class="paginate_button page-item next" id="dataTable_next">
                                        <a href="#" aria-controls="dataTable" data-dt-idx="7" tabindex="0" class="page-link">Next</a>
                                      </li>
                                    </ul>
                                  </div>
                                </div>
                              </div> -->
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
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Bootstrap core JavaScript-->
    <script src="${pageContext.request.contextPath}/admin/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${pageContext.request.contextPath}/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${pageContext.request.contextPath}/admin/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="${pageContext.request.contextPath}/admin/vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="${pageContext.request.contextPath}/admin/js/demo/chart-area-demo.js"></script>
    <script src="${pageContext.request.contextPath}/admin/js/demo/chart-pie-demo.js"></script>

</body>

</html>