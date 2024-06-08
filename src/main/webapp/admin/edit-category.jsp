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

    <title>Admin - Edit Category</title>

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
              <div class="container mt-5">
                <h2 class="mb-4">Edit Category</h2>
                <div class="row">
                    
                    <div class="col-md-12">
                        <form id="editCategoryForm" action="edit-category" method="post">
                        	<input type="hidden" name="id" value="${categoryDTO.id}"/>
                            <div class="row mb-3">
                              <div class="col-6">
                                <label for="categoryname" class="form-label">Category Name</label>
                                <input type="text" class="form-control" id="categoryname" name="categoryName" value="${categoryDTO.categoryName}">
                              </div>
                              <div class="col-6">
                                  <label for="quantity" class="form-label">Product Quantity</label>
                                  <c:if test="${not empty categoryDTO.products}">
									<input type="number" class="form-control" id="quantity" name="quantity" value="${fn:length(categoryDTO.products)}" readonly>
							      </c:if>
							      <c:if test="${empty categoryDTO.products}">
							        <input type="number" class="form-control" id="quantity" name="quantity" value="0" readonly>
							      </c:if>
                                  
                              </div>
                            </div>
                            
                            <div class="row">
                              <div class="col-6">
                                  <label for="status" class="form-label">Status</label>
                                  <select class="form-select" name="status" id="status">
                                  	<c:if test="${categoryDTO.status == 1}">
                                  		<option value="1" selected>Active</option>
                                  		<option value="0">UnActive</option>	
                                  	</c:if>
                                  	<c:if test="${categoryDTO.status == 0}">
                                  		<option value="1">Active</option>
                                  		<option value="0" selected>UnActive</option>
                                  	</c:if>
                                  </select>
                              </div>
                            </div>

                            <br>
                            <button type="submit" class="btn btn-primary">Save Changes</button>
                        </form>
                    </div>
                  </div>
                </div>
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