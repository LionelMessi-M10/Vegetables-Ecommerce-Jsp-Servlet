<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin - Categories</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

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
                    <h1 class="h3 mb-0 text-gray-800">Category List</h1>
                    <button type="button" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm" data-toggle="modal" data-target="#addCategoryModal">
                        <i class="fas fa-plus fa-sm text-white-50"></i> Add Category
                    </button>
                  </div>

                  <!-- DataTales Example -->
                  <div class="card shadow mb-4">
                      <div class="card-header py-3">
                          <h6 class="m-0 font-weight-bold text-primary">Categories</h6>
                      </div>
                      <div class="card-body">
                          <div class="table-responsive">
                              <table class="table table-bordered" id="dataTable" style="width:100%; cellspacing:0;">
                                  <thead>
                                      <tr>
                                          <th>Name</th>
                                          <th>Product Quantity</th>
                                          <th>Status</th>
                                          <th>Action</th>
                                      </tr>
                                  </thead>
                                  <tbody>
                                      <c:forEach var="category" items="${categories}">
                                      	<tr>
                                          <td>${category.categoryName}</td>
                                          <td>
											<c:if test="${not empty category.products}">
										        ${fn:length(category.products)}
										    </c:if>
										    <c:if test="${empty category.products}">
										        0
										    </c:if>
										  </td>
                                          <td>
                                          	<c:if test="${category.status == 1}">
											    <span style="padding: 5px; background: green; color: white; border-radius: 20px;">Active</span>
											</c:if>
											<c:if test="${category.status == 0}">
											    <span style="padding: 5px; background: red; color: white; border-radius: 20px;">UnActive</span>
											</c:if>
                                          </td>
                                          <td class="text-center">
                                            <a href="/BTL_WEB/admin/category-edit-page?id=${category.id}" type="button" class="btn btn-primary btn-circle mr-1">
                                              <i class="fas fa-edit"></i>
                                            </a>
                                            <c:if test="${category.status == 1}">
	                                            <a href="/BTL_WEB/admin/category-delete?id=${category.id}" class="btn btn-danger btn-circle">
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

    <!-- Add category -->
    <div class="modal fade" id="addCategoryModal" tabindex="-1" role="dialog" aria-labelledby="addCategoryModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
          <div class="modal-content">
              <div class="modal-header">
                  <h5 class="modal-title" id="addCategoryModalLabel">Add Category</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                  </button>
              </div>
              <div class="modal-body">
                  <form id="addCategoryForm" action="add-category" method="post">
                      <div class="form-group">
                          <label for="categoryName">Category Name</label>
                          <input type="text" class="form-control" id="categoryName" name="categoryName" placeholder="Enter category name" required>
                      </div>
                      <div class="text-right">
                        <button type="submit" class="btn btn-primary">Add Category</button>
                      </div>
                  </form>
              </div>
          </div>
      </div>
    </div>
    <!-- End add category -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="js/demo/chart-area-demo.js"></script>
    <script src="js/demo/chart-pie-demo.js"></script>

</body>

</html>