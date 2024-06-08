<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>Fruitables - Register</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
	rel="stylesheet">

<!-- Icon Font Stylesheet -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
	rel="stylesheet">

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
		<form action="signup" method="POST">
			<div class="container col-9"
				style="padding: 20px; background: white;">

				<div class="form-group text-center">
					<h1>REGISTER</h1>
				</div>

				<div class="form-group">
					
					<div class="row">
						<div class="col-6">
							<label for="firstname">First Name</label> 
							<input type="text"
								class="form-control" id="firstname" name="firstName"
								placeholder="Your firstname" required/>
						</div>

						<div class="col-6">
							<label for="lastname">Last Name</label> 
							<input type="text"
								class="form-control" id="lastname" name="lastName"
								placeholder="Your lastname" required/>
						</div>
					</div>
					
					<br>
				
					<div class="row">
						<div class="col-6">
							<label for="username">Username</label> <input type="text"
								class="form-control" id="username" name="userName"
								placeholder="Your username" required/>
						</div>

						<div class="col-6">
							<label for="password">Password</label> <input type="text"
								class="form-control" id="password" name="password"
								placeholder="Your password" required/>
						</div>
					</div>
					<br>

					<div class="row">
						<div class="col-6">
						    <label for="email">Email</label> 
						    <span class="text-danger" style="color: red; margin-left: 10px;">
						        <% 
						            String emailError = (String) session.getAttribute("emailError");
						            if (emailError != null) { 
						                out.print(emailError);
						                session.removeAttribute("emailError");
						            }
						        %>
						    </span>
						    <input type="email" class="form-control" id="email" name="email" placeholder="Your email" required/>
						</div>

						<div class="col-6">
							<label for="phonenumber">Phone number</label> <input type="text"
								class="form-control" id="phonenumber" name="phoneNumber"
								placeholder="Your phonenumber" required/>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-12">
							<label for="address">Address</label> <input type="text"
								class="form-control" id="address" name="address"
								placeholder="Your address" required/>
						</div>
					</div>

				</div>
				<br>

				<div class="form-group">
					<div class="row text-center">
						<button type="submit" class="btn btn-primary">Register</button>
					</div>

					<div class="text-center" style="margin-top: 10px;">
						<p>
							Have an account? <a href="${pageContext.request.contextPath}/web/login.jsp">Login</a>
						</p>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- Hero End -->

	<jsp:include page="./layout/footer.jsp"></jsp:include>

	<!-- Back to Top -->
	<a href="#"
		class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i
		class="fa fa-arrow-up"></i></a>

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
