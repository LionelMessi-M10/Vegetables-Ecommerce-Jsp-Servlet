<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Fruitables - Contact</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
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


        <!-- Contact Start -->
        <div class="container-fluid contact py-5">
            <div class="container py-5">
                <div class="p-5 bg-light rounded">
                    <div class="row g-4">
                        <div class="col-12">
                            <div class="text-center mx-auto" style="max-width: 700px;">
                                <h1 class="text-primary">Get in touch</h1>
                                <p class="mb-4">The contact form is currently inactive. Get a functional and working contact form with Ajax & PHP in a few minutes. Just copy and paste the files, add a little code and you're done. <a href="https://htmlcodex.com/contact-form">Download Now</a>.</p>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="h-100 rounded">
                                <iframe class="rounded w-100" 
                                style="height: 400px;" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d387191.33750346623!2d-73.97968099999999!3d40.6974881!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x89c24fa5d33f083b%3A0xc80b8f06e177fe62!2sNew%20York%2C%20NY%2C%20USA!5e0!3m2!1sen!2sbd!4v1694259649153!5m2!1sen!2sbd" 
                                loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                            </div>
                        </div>
                        <div class="col-lg-7">
                            <form action="" class="">
                                <input type="text" class="w-100 form-control border-0 py-3 mb-4" placeholder="Your Name">
                                <input type="email" class="w-100 form-control border-0 py-3 mb-4" placeholder="Enter Your Email">
                                <textarea class="w-100 form-control border-0 mb-4" rows="5" cols="10" placeholder="Your Message"></textarea>
                                <button class="w-100 btn form-control border-secondary py-3 bg-white text-primary " type="submit">Submit</button>
                            </form>
                        </div>
                        <div class="col-lg-5">
                            <div class="d-flex p-4 rounded mb-4 bg-white">
                                <i class="fas fa-map-marker-alt fa-2x text-primary me-4"></i>
                                <div>
                                    <h4>Address</h4>
                                    <p class="mb-2">123 Street New York.USA</p>
                                </div>
                            </div>
                            <div class="d-flex p-4 rounded mb-4 bg-white">
                                <i class="fas fa-envelope fa-2x text-primary me-4"></i>
                                <div>
                                    <h4>Mail Us</h4>
                                    <p class="mb-2">info@example.com</p>
                                </div>
                            </div>
                            <div class="d-flex p-4 rounded bg-white">
                                <i class="fa fa-phone-alt fa-2x text-primary me-4"></i>
                                <div>
                                    <h4>Telephone</h4>
                                    <p class="mb-2">(+012) 3456 7890</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Contact End -->


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