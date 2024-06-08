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

    <title>Admin - Add product</title>

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
                <div class="container mt-5">
                  <h2 class="mb-4">Add New Product</h2>
                  <form action="${pageContext.request.contextPath}/admin/add-product" enctype="multipart/form-data" method="post">
                      <div class="mb-3">
                          <label for="productName" class="form-label">Product Name</label>
                          <input type="text" class="form-control" id="productName" name="name" required>
                      </div>
                      <div class="mb-3">
                          <label for="productCategory" class="form-label">Category</label>
                          <select class="form-select" id="productCategory" name="category" required>
                              <c:forEach var="category" items="${categories}">
                              	<option value="${category.id}">${category.categoryName}</option>
                              </c:forEach>
                          </select>
                      </div>
                      <div class="mb-3">
                          <label for="productPrice" class="form-label">Price</label>
                          <input type="number" class="form-control" id="productPrice" name="price" required>
                      </div>
                      <div class="mb-3">
                          <label for="productQuantity" class="form-label">Quantity</label>
                          <input type="number" class="form-control" id="productQuantity" name="quantity" required>
                      </div>
                      <div class="mb-3">
                          <label for="shortDescription" class="form-label">Short Description</label>
                          <textarea class="form-control" id="shortDescription" name="shortDescription" rows="3" required></textarea>
                      </div>
                      <div class="mb-3">
                          <label for="detailedDescription" class="form-label">Detailed Description</label>
                          <textarea class="form-control" id="detailedDescription" name="detailedDescription" rows="5" required></textarea>
                      </div>
                      <div class="mb-3">
                          <label for="productStatus" class="form-label">Status</label>
                          <select class="form-select" id="productStatus" name="status" required>
                              <option value="1">Active</option>
                              <option value="0">UnActive</option>
                          </select>
                      </div>
                      <div class="mb-3">
                          <label for="productImage" class="form-label">Image</label>
                          <input type="file" class="form-control" id="productImage" name="image" accept="image/*" required>
                          <img id="previewImage" src="#" alt="Image Preview" class="img-thumbnail mt-2" style="display: none; max-height: 200px;">
                      </div>
                      <button type="submit" class="btn btn-primary">Add Product</button>
                  </form>
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
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <script>
      // Preview image when a file is selected
      $('#productImage').change(function() {
          var reader = new FileReader();
          reader.onload = function(e) {
              $('#previewImage').attr('src', e.target.result).show();
          }
          reader.readAsDataURL(this.files[0]);
      });
  
      // Handle form submission
      $('#addProductForm').submit(function(event) {
          event.preventDefault();
          // Handle form submission logic here, like using AJAX to send data to the server
          alert('Form submitted!');
      });
  </script>

</body>

</html>