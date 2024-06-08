<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Fruitables - Login</title>
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
        <div class="container-fluid  hero-header">
			<div class="container col-6" style="padding-top: 40px; background: white;">
			
				<div class="text-center">
					<span class="text-success">
				        <% 
				            String registerSuccess = (String) session.getAttribute("registerSuccess");
				            if (registerSuccess != null) { 
				                out.print(registerSuccess);
				                session.removeAttribute("registerSuccess");
				            }
				        %>
				    </span>
				    
				    <span class="text-danger">
				        <% 
				            String loginFailed = (String) session.getAttribute("loginFailed");
				            if (loginFailed != null) { 
				                out.print(loginFailed);
				                session.removeAttribute("loginFailed");
				            }
				        %>
				    </span>	
				</div>	    
			    
				
				<div class="form-group text-center">
					<h1>LOGIN</h1>
				</div>
			
				<form action="signin" method="POST">
					<div class="form-group">
						<div class="row">
							<div class="col-1"></div>
							<div class="col-10">
								<label for="email">Email</label>
								<input type="email" class="form-control" id="email" name="email" placeholder="Your email"/>
							</div>
							<div class="col-1"></div>
						</div>
						<br>
						<div class="row">
							<div class="col-1"></div>
							<div class="col-10">
								<label for="password">Password</label>
								<input type="password" class="form-control" id="password" name="password" placeholder="Your password"/>
							</div>
							<div class="col-1"></div>
						</div>
						
						<div class="row">
							<div class="d-flex justify-content-center" style="margin-top: 10px; margin-bottom: -10px;">
					          <div class="form-check">
					            <input class="form-check-input" type="checkbox" name="rememberMe" id="loginCheck" />
					            <label class="form-check-label" for="loginCheck"> Remember me </label>
					          </div>
					        </div>
						</div>
						<br>
						
						<div class="row text-center">
							<div class="col-3"></div>
							<div class="col-6">
								<button type="submit" class="btn btn-primary" style="width:120px;">Login</button>
							</div>
							<div class="col-3"></div>
						</div>
						
						<div class="text-center" style="margin-top: 10px;">
							<p>Not a member? <a href="register.jsp">Register</a></p>
						</div>
					</div>
				</form>
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