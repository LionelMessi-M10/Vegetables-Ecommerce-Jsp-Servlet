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

    <title>Admin - Edit User</title>

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
                <h2 class="mb-4">Edit User</h2>
                <div class="row">
                    
                    <div class="col-md-12">
                        <form action="edit-user" method="post">
                        	<input type="hidden" name="id" value="${user.id}"/>
                            <div class="row mb-3">
                              <div class="col-6">
                                <label for="firstname" class="form-label">First Name</label>
                                <input type="text" class="form-control" id="firstname" name="firstName" value="${user.firstName}">
                              </div>
                              <div class="col-6">
                                  <label for="lastname" class="form-label">Last Name</label>
                                  <input type="text" class="form-control" id="lastname" name="lastName" value="${user.lastName}">
                              </div>
                            </div>
                            <div class="mb-3">
                                <label for="username" class="form-label">Username</label>
                                <input type="text" class="form-control" id="username" name="userName" value="${user.userName}">
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Email</label>
                                <input type="email" class="form-control" id="email" name="email" value="${user.email}">
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">Password</label>
                                <input type="password" class="form-control" id="password" name="password" value="${user.password}">
                            </div>
                            <div class="mb-3">
                                <label for="phonenumber" class="form-label">Phone Number</label>
                                <input type="text" class="form-control" id="phonenumber" name="phoneNumber" value="${user.phoneNumber}">
                            </div>
                            <div class="mb-3">
                                <label for="address" class="form-label">Address</label>
                                <input type="text" class="form-control" id="address" name="address" value="${user.address}">
                            </div>
                            <div class="row">
                              <div class="col-3">
                                <label for="role" class="form-label">Role</label>
                                <select class="form-select" name="role" id="role">
                                  <c:if test="${user.role eq 'Admin'}">
	                                  <option value="Admin" selected>Admin</option>
	                                  <option value="Customer">Customer</option>
                                  </c:if>
                                  
                                  <c:if test="${user.role eq 'Customer'}">
	                                  <option value="Admin">Admin</option>
	                                  <option value="Customer" selected>Customer</option>
                                  </c:if>
                                </select>
                              </div>
                              <div class="col-3">
                                  <label for="status" class="form-label">Account Status</label>
                                  <select class="form-select" name="status" id="status">
                                    <c:if test="${user.status == 1}">
                                    	<option value="1" selected>Active</option>
                                    	<option value="0">UnActive</option>
                                    </c:if>
                                    
                                    <c:if test="${user.status == 0}">
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