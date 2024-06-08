<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Fruitables - Profile</title>
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


        <!-- Hero Start -->
        <div class="container" style="margin-top: 150px;">
          <h2 class="mb-4">Edit Profile</h2>
          <div class="row">
              
              <div class="col-md-12">
                  <form action="save-profile" method="post">
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
                      
                      <input type="hidden" name="id" value="${user.id}"/>
                      <input type="hidden" name="role" value="${user.role}"/>
                      <input type="hidden" name="status" value="${user.status}"/>

                      <br>
                      <button type="submit" class="btn btn-primary">Save Profile</button>
                  </form>
              </div>
            </div>
        </div>
        <!-- Hero End -->


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