<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Fruitables - Orders</title>
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


        <!-- Cart Page Start -->
        <div class="container-fluid py-5" style="margin-top: 100px;">
            <div class="container py-5">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                          <tr>
                            <th scope="col">Address</th>
                            <th scope="col">Phone</th>
                            <th scope="col">Total</th>
                            <th scope="col">Order Date</th>
                            <th scope="col">Status</th>
                            <th scope="col">Handle</th>
                          </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="orderDTO" items="${orderDTOs}">
                            	<tr>
	                                <td>
	                                    <p class="mb-0 mt-4">${orderDTO.user.address}</p>
	                                </td>
	                                <td>
	                                    <p class="mb-0 mt-4">${orderDTO.user.phoneNumber}</p>
	                                </td>
	                                <td>
	                                    <p class="mb-0 mt-4">${orderDTO.total}</p>
	                                </td>
	                                <td>
	                                    <p class="mb-0 mt-4">${orderDTO.createdAt}</p>
	                                </td>
	                                <c:if test="${orderDTO.status == 0}">
		                                <td>
		                                    <p class="mb-0 mt-4">Chờ duyệt</p>
		                                </td>
	                                </c:if>
	                                <c:if test="${orderDTO.status == 1}">
		                                <td>
		                                    <p class="mb-0 mt-4">Đang giao</p>
		                                </td>
	                                </c:if>
	                                <c:if test="${orderDTO.status == 2}">
		                                <td>
		                                    <p class="mb-0 mt-4">Đã nhận</p>
		                                </td>
	                                </c:if>
	                                <c:if test="${orderDTO.status == 3}">
		                                <td>
		                                    <p class="mb-0 mt-4">Đã bị hủy</p>
		                                </td>
	                                </c:if>
	                                <td>
	                                    <a href="/BTL_WEB/web/order-detail?id=${orderDTO.id}" class="btn btn-primary btn-circle" style="margin-top: 15px;">
                                              <i class="fas fa-eye"></i>
                                        </a>
	                                </td>
	                            
	                            </tr>
                            </c:forEach>
                            
                        </tbody>
                    </table>
                </div>
                
            </div>
        </div>
        <!-- Cart Page End -->


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