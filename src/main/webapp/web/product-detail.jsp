<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Fruitables - Product Detail</title>
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
        
        <style>
			.star-rating {
			  display: flex;
			  direction: rtl;
			}
			
			.star {
			  cursor: pointer;
			  color: #ccc;
			}
			
			.star:hover,
			.star:hover ~ .star {
			  color: gold;
			}
			
			.star.selected {
			  color: gold;
			}
						
		</style>

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
    </head>

    <body>

        <jsp:include page="./layout/header.jsp"></jsp:include>


		<jsp:include page="./layout/modal_search.jsp"></jsp:include>


        <!-- Single Product Start -->
        <div class="container-fluid py-5 mt-5">
            <div class="container py-5" style="margin-top: 10px;">
                <div class="row g-4 mb-5">
                    <div class="col-lg-8 col-xl-9">
                        <div class="row g-4">
                           	
                            <div class="col-lg-6">
                                <div class="border rounded">
                                    <a href="#">
                                        <img src="${pageContext.request.contextPath}/uploads/${productDTO.image}" class="img-fluid rounded" alt="Image">
                                    </a>
                                </div>
                            </div>
	                        
                        	<div class="col-lg-6">
                                <form id="productForm">
								    <input type="hidden" name="id" value="${productDTO.id}" />
								    <h4 class="fw-bold mb-3">${productDTO.productName}</h4>
								    <p class="mb-3">Category: ${productDTO.category.categoryName}</p>
								    <h5 class="fw-bold mb-3">${productDTO.price} VND</h5>
								    <div class="d-flex mb-4">
								        <i class="fa fa-star text-secondary"></i>
								        <i class="fa fa-star text-secondary"></i>
								        <i class="fa fa-star text-secondary"></i>
								        <i class="fa fa-star text-secondary"></i>
								        <i class="fa fa-star"></i>
								    </div>
								    <p class="mb-4">${productDTO.shortDesc}</p>
								
								    <div class="input-group quantity mb-5" style="width: 100px;">
								        <div class="input-group-btn">
								            <button type="button" class="btn btn-sm btn-minus rounded-circle bg-light border">
								                <i class="fa fa-minus"></i>
								            </button>
								        </div>
								        <input type="text" name="quantity" class="form-control form-control-sm text-center border-0" value="1">
								        <div class="input-group-btn">
								            <button type="button" class="btn btn-sm btn-plus rounded-circle bg-light border">
								                <i class="fa fa-plus"></i>
								            </button>
								        </div>
								    </div>
								    <button type="button" id="addToCartBtn" class="btn border border-secondary rounded-pill px-4 py-2 mb-4 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i>
								        Add to cart
								    </button>
								    <button type="button" id="buyNowBtn" class="btn border border-secondary rounded-pill px-4 py-2 mb-4 text-primary">
								        Buy now
								    </button>
								</form>
                            </div>   
                            
                            <div class="col-lg-12">
                                <nav>
                                    <div class="nav nav-tabs mb-3">
                                        <button class="nav-link active border-white border-bottom-0" type="button" role="tab"
                                            id="nav-about-tab" data-bs-toggle="tab" data-bs-target="#nav-about"
                                            aria-controls="nav-about" aria-selected="true">Description</button>
                                        <button class="nav-link border-white border-bottom-0" type="button" role="tab"
                                            id="nav-mission-tab" data-bs-toggle="tab" data-bs-target="#nav-mission"
                                            aria-controls="nav-mission" aria-selected="false">Reviews</button>
                                    </div>
                                </nav>
                                <div class="tab-content mb-5">
                                    <div class="tab-pane active" id="nav-about" role="tabpanel" aria-labelledby="nav-about-tab">
                                        <p>${productDTO.detailDesc} </p>
                                    </div>
                                    
                                    <div class="tab-pane" id="nav-mission" role="tabpanel" aria-labelledby="nav-mission-tab">
                                        <div class="d-flex">
                                            <c:forEach var="review" items="${productDTO.reviewDTOs}">
                                            	<img src="img/avatar.jpg" class="img-fluid rounded-circle p-3" style="width: 100px; height: 100px;" alt="">
	                                            <div class="">
	                                                <p class="mb-2" style="font-size: 14px;">${review.createdAt}</p>
	                                                <div class="d-flex justify-content-between">
	                                                    <h5>${review.user.firstName} ${review.user.lastName}</h5>
	                                                    <c:if test="${review.star == 1}">
														    <div class="d-flex mb-3">
														        <i class="fa fa-star text-secondary"></i>
														        <i class="fa fa-star"></i>
														        <i class="fa fa-star"></i>
														        <i class="fa fa-star"></i>
														        <i class="fa fa-star"></i>
														    </div>
														</c:if>
														
														<c:if test="${review.star == 2}">
														    <div class="d-flex mb-3">
														        <i class="fa fa-star text-secondary"></i>
														        <i class="fa fa-star text-secondary"></i>
														        <i class="fa fa-star"></i>
														        <i class="fa fa-star"></i>
														        <i class="fa fa-star"></i>
														    </div>
														</c:if>
														
														<c:if test="${review.star == 3}">
														    <div class="d-flex mb-3">
														        <i class="fa fa-star text-secondary"></i>
														        <i class="fa fa-star text-secondary"></i>
														        <i class="fa fa-star text-secondary"></i>
														        <i class="fa fa-star"></i>
														        <i class="fa fa-star"></i>
														    </div>
														</c:if>
														
														<c:if test="${review.star == 4}">
														    <div class="d-flex mb-3">
														        <i class="fa fa-star text-secondary"></i>
														        <i class="fa fa-star text-secondary"></i>
														        <i class="fa fa-star text-secondary"></i>
														        <i class="fa fa-star text-secondary"></i>
														        <i class="fa fa-star"></i>
														    </div>
														</c:if>
														
														<c:if test="${review.star == 5}">
														    <div class="d-flex mb-3">
														        <i class="fa fa-star text-secondary"></i>
														        <i class="fa fa-star text-secondary"></i>
														        <i class="fa fa-star text-secondary"></i>
														        <i class="fa fa-star text-secondary"></i>
														        <i class="fa fa-star text-secondary"></i>
														    </div>
														</c:if>

	                                                </div>
	                                                <p>${review.content}</p>
	                                            </div>
                                            </c:forEach>
                                        </div>
                                        
                                    </div>
                                </div>
                            </div>
                            <form action="review" method="post">
                            	<input type="hidden" name="product_id" value="${productDTO.id}"/>
							    <h4 class="mb-5 fw-bold">Leave a Reply</h4>
							    <div class="row g-4">
							        <div class="col-lg-6">
							            <div class="border-bottom rounded">
							                <input type="text" class="form-control border-0 me-4" name="name" placeholder="Your Name *" value="${user.firstName} ${user.lastName}">
							            </div>
							        </div>
							        <div class="col-lg-6">
							            <div class="border-bottom rounded">
							                <input type="email" class="form-control border-0" name="email" placeholder="Your Email *" value="${user.email}">
							            </div>
							        </div>
							        <div class="col-lg-12">
							            <div class="border-bottom rounded my-4">
							                <textarea name="content" class="form-control border-0" cols="30" rows="8" placeholder="Your Review *" spellcheck="false"></textarea>
							            </div>
							        </div>
							        <div class="col-lg-12">
							            <div class="d-flex justify-content-between py-3 mb-5">
							                <div class="d-flex align-items-center">
							                    <p class="mb-0 me-3">Please rate:</p>
							                    <div class="d-flex align-items-center star-rating" style="font-size: 24px;">
							                        <i class="fa fa-star star" data-value="5"></i>
							                        <i class="fa fa-star star" data-value="4"></i>
							                        <i class="fa fa-star star" data-value="3"></i>
							                        <i class="fa fa-star star" data-value="2"></i>
							                        <i class="fa fa-star star" data-value="1"></i>
							                    </div>
							                </div>
							                <button type="submit" class="btn border border-secondary text-primary rounded-pill px-4 py-3">Post Comment</button>
							            </div>
							        </div>
							    </div>
							    <input type="hidden" name="rating" id="ratingValue">
							</form>
                        </div>
                    </div>
                    <div class="col-lg-4 col-xl-3">
                        <div class="row g-4 fruite">
                            <div class="col-lg-12">
                            	<form action="search" method="get">
	                                <div class="input-group w-100 mx-auto d-flex mb-4">
                                    	<input type="search" class="form-control p-3" placeholder="keywords" name="key" aria-describedby="search-icon-1">
                                    	<button type="submit" id="search-icon-1" class="input-group-text p-3"><i class="fa fa-search"></i></button>
	                                </div>
                                </form>
                                <div class="mb-4">
                                    <h4>Categories</h4>
                                    <ul class="list-unstyled fruite-categorie">
                                        <c:forEach var="category" items="${categoryDTOs}">
                                        	<li>
	                                            <div class="d-flex justify-content-between fruite-name">
	                                                <a href="/BTL_WEB/web/shop/category?id=${category.id}">${category.categoryName}</a>
	                                                <span>
                                                      	<c:if test="${not empty category.products}">
												        	${fn:length(category.products)}
													    </c:if>
													    <c:if test="${empty category.products}">
													        0
													    </c:if>
                                                     </span>
	                                            </div>
	                                        </li>
                                        </c:forEach>
                                        
                                    </ul>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <h4 class="mb-4">Featured products</h4>
                                <div class="d-flex align-items-center justify-content-start">
                                    <div class="rounded" style="width: 100px; height: 100px;">
                                        <img src="img/featur-1.jpg" class="img-fluid rounded" alt="Image">
                                    </div>
                                    <div>
                                        <h6 class="mb-2">Big Banana</h6>
                                        <div class="d-flex mb-2">
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star"></i>
                                        </div>
                                        <div class="d-flex mb-2">
                                            <h5 class="fw-bold me-2">2.99 $</h5>
                                            <h5 class="text-danger text-decoration-line-through">4.11 $</h5>
                                        </div>
                                    </div>
                                </div>
                                <div class="d-flex align-items-center justify-content-start">
                                    <div class="rounded" style="width: 100px; height: 100px;">
                                        <img src="img/featur-2.jpg" class="img-fluid rounded" alt="">
                                    </div>
                                    <div>
                                        <h6 class="mb-2">Big Banana</h6>
                                        <div class="d-flex mb-2">
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star"></i>
                                        </div>
                                        <div class="d-flex mb-2">
                                            <h5 class="fw-bold me-2">2.99 $</h5>
                                            <h5 class="text-danger text-decoration-line-through">4.11 $</h5>
                                        </div>
                                    </div>
                                </div>
                                <div class="d-flex align-items-center justify-content-start">
                                    <div class="rounded" style="width: 100px; height: 100px;">
                                        <img src="img/featur-3.jpg" class="img-fluid rounded" alt="">
                                    </div>
                                    <div>
                                        <h6 class="mb-2">Big Banana</h6>
                                        <div class="d-flex mb-2">
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star"></i>
                                        </div>
                                        <div class="d-flex mb-2">
                                            <h5 class="fw-bold me-2">2.99 $</h5>
                                            <h5 class="text-danger text-decoration-line-through">4.11 $</h5>
                                        </div>
                                    </div>
                                </div>
                                <div class="d-flex align-items-center justify-content-start">
                                    <div class="rounded me-4" style="width: 100px; height: 100px;">
                                        <img src="img/vegetable-item-4.jpg" class="img-fluid rounded" alt="">
                                    </div>
                                    <div>
                                        <h6 class="mb-2">Big Banana</h6>
                                        <div class="d-flex mb-2">
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star"></i>
                                        </div>
                                        <div class="d-flex mb-2">
                                            <h5 class="fw-bold me-2">2.99 $</h5>
                                            <h5 class="text-danger text-decoration-line-through">4.11 $</h5>
                                        </div>
                                    </div>
                                </div>
                                <div class="d-flex align-items-center justify-content-start">
                                    <div class="rounded me-4" style="width: 100px; height: 100px;">
                                        <img src="img/vegetable-item-5.jpg" class="img-fluid rounded" alt="">
                                    </div>
                                    <div>
                                        <h6 class="mb-2">Big Banana</h6>
                                        <div class="d-flex mb-2">
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star"></i>
                                        </div>
                                        <div class="d-flex mb-2">
                                            <h5 class="fw-bold me-2">2.99 $</h5>
                                            <h5 class="text-danger text-decoration-line-through">4.11 $</h5>
                                        </div>
                                    </div>
                                </div>
                                <div class="d-flex align-items-center justify-content-start">
                                    <div class="rounded me-4" style="width: 100px; height: 100px;">
                                        <img src="img/vegetable-item-6.jpg" class="img-fluid rounded" alt="">
                                    </div>
                                    <div>
                                        <h6 class="mb-2">Big Banana</h6>
                                        <div class="d-flex mb-2">
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star"></i>
                                        </div>
                                        <div class="d-flex mb-2">
                                            <h5 class="fw-bold me-2">2.99 $</h5>
                                            <h5 class="text-danger text-decoration-line-through">4.11 $</h5>
                                        </div>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-center my-4">
                                    <a href="#" class="btn border border-secondary px-4 py-3 rounded-pill text-primary w-100">Vew More</a>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="position-relative">
                                    <img src="img/banner-fruits.jpg" class="img-fluid w-100 rounded" alt="">
                                    <div class="position-absolute" style="top: 50%; right: 10px; transform: translateY(-50%);">
                                        <h3 class="text-secondary fw-bold">Fresh <br> Fruits <br> Banner</h3>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <h1 class="fw-bold mb-0">Related products</h1>
                <div class="vesitable">
                    <div class="owl-carousel vegetable-carousel justify-content-center">
                        <div class="border border-primary rounded position-relative vesitable-item">
                            <div class="vesitable-img">
                                <img src="img/vegetable-item-6.jpg" class="img-fluid w-100 rounded-top" alt="">
                            </div>
                            <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style="top: 10px; right: 10px;">Vegetable</div>
                            <div class="p-4 pb-0 rounded-bottom">
                                <h4>Parsely</h4>
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                <div class="d-flex justify-content-between flex-lg-wrap">
                                    <p class="text-dark fs-5 fw-bold">$4.99 / kg</p>
                                    <a href="#" class="btn border border-secondary rounded-pill px-3 py-1 mb-4 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                </div>
                            </div>
                        </div>
                        <div class="border border-primary rounded position-relative vesitable-item">
                            <div class="vesitable-img">
                                <img src="img/vegetable-item-1.jpg" class="img-fluid w-100 rounded-top" alt="">
                            </div>
                            <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style="top: 10px; right: 10px;">Vegetable</div>
                            <div class="p-4 pb-0 rounded-bottom">
                                <h4>Parsely</h4>
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                <div class="d-flex justify-content-between flex-lg-wrap">
                                    <p class="text-dark fs-5 fw-bold">$4.99 / kg</p>
                                    <a href="#" class="btn border border-secondary rounded-pill px-3 py-1 mb-4 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                </div>
                            </div>
                        </div>
                        <div class="border border-primary rounded position-relative vesitable-item">
                            <div class="vesitable-img">
                                <img src="img/vegetable-item-3.png" class="img-fluid w-100 rounded-top bg-light" alt="">
                            </div>
                            <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style="top: 10px; right: 10px;">Vegetable</div>
                            <div class="p-4 pb-0 rounded-bottom">
                                <h4>Banana</h4>
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                <div class="d-flex justify-content-between flex-lg-wrap">
                                    <p class="text-dark fs-5 fw-bold">$7.99 / kg</p>
                                    <a href="#" class="btn border border-secondary rounded-pill px-3 py-1 mb-4 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                </div>
                            </div>
                        </div>
                        <div class="border border-primary rounded position-relative vesitable-item">
                            <div class="vesitable-img">
                                <img src="img/vegetable-item-4.jpg" class="img-fluid w-100 rounded-top" alt="">
                            </div>
                            <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style="top: 10px; right: 10px;">Vegetable</div>
                            <div class="p-4 pb-0 rounded-bottom">
                                <h4>Bell Papper</h4>
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                <div class="d-flex justify-content-between flex-lg-wrap">
                                    <p class="text-dark fs-5 fw-bold">$7.99 / kg</p>
                                    <a href="#" class="btn border border-secondary rounded-pill px-3 py-1 mb-4 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                </div>
                            </div>
                        </div>
                        <div class="border border-primary rounded position-relative vesitable-item">
                            <div class="vesitable-img">
                                <img src="img/vegetable-item-5.jpg" class="img-fluid w-100 rounded-top" alt="">
                            </div>
                            <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style="top: 10px; right: 10px;">Vegetable</div>
                            <div class="p-4 pb-0 rounded-bottom">
                                <h4>Potatoes</h4>
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                <div class="d-flex justify-content-between flex-lg-wrap">
                                    <p class="text-dark fs-5 fw-bold">$7.99 / kg</p>
                                    <a href="#" class="btn border border-secondary rounded-pill px-3 py-1 mb-4 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                </div>
                            </div>
                        </div>
                        <div class="border border-primary rounded position-relative vesitable-item">
                            <div class="vesitable-img">
                                <img src="img/vegetable-item-6.jpg" class="img-fluid w-100 rounded-top" alt="">
                            </div>
                            <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style="top: 10px; right: 10px;">Vegetable</div>
                            <div class="p-4 pb-0 rounded-bottom">
                                <h4>Parsely</h4>
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                <div class="d-flex justify-content-between flex-lg-wrap">
                                    <p class="text-dark fs-5 fw-bold">$7.99 / kg</p>
                                    <a href="#" class="btn border border-secondary rounded-pill px-3 py-1 mb-4 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                </div>
                            </div>
                        </div>
                        <div class="border border-primary rounded position-relative vesitable-item">
                            <div class="vesitable-img">
                                <img src="img/vegetable-item-5.jpg" class="img-fluid w-100 rounded-top" alt="">
                            </div>
                            <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style="top: 10px; right: 10px;">Vegetable</div>
                            <div class="p-4 pb-0 rounded-bottom">
                                <h4>Potatoes</h4>
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                <div class="d-flex justify-content-between flex-lg-wrap">
                                    <p class="text-dark fs-5 fw-bold">$7.99 / kg</p>
                                    <a href="#" class="btn border border-secondary rounded-pill px-3 py-1 mb-4 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                </div>
                            </div>
                        </div>
                        <div class="border border-primary rounded position-relative vesitable-item">
                            <div class="vesitable-img">
                                <img src="img/vegetable-item-6.jpg" class="img-fluid w-100 rounded-top" alt="">
                            </div>
                            <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style="top: 10px; right: 10px;">Vegetable</div>
                            <div class="p-4 pb-0 rounded-bottom">
                                <h4>Parsely</h4>
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                <div class="d-flex justify-content-between flex-lg-wrap">
                                    <p class="text-dark fs-5 fw-bold">$7.99 / kg</p>
                                    <a href="#" class="btn border border-secondary rounded-pill px-3 py-1 mb-4 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Single Product End -->
    

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
    <script src="js/main.js"></script>
    
    <script>
    	document.addEventListener('DOMContentLoaded', function() {
    	  const stars = document.querySelectorAll('.star');
    	  
    	  stars.forEach(star => {
    	      star.addEventListener('click', selectStar);
    	  });
    	  
    	  function selectStar(event) {
    	      const selectedValue = parseInt(event.target.getAttribute('data-value'));
    	      
    	      stars.forEach(star => {
    	          const starValue = parseInt(star.getAttribute('data-value'));
    	          if (starValue <= selectedValue) {
    	              star.classList.add('selected');
    	          } else {
    	              star.classList.remove('selected');
    	          }
    	      });
    	  }
    	});

    	document.querySelectorAll('.star-rating .star').forEach(star => {
    	    star.addEventListener('click', function() {
    	        const rating = this.getAttribute('data-value');
    	        document.getElementById('ratingValue').value = rating;
    	    });
    	});
    	
    	$(document).ready(function () {
    	    $("#addToCartBtn").click(function () {
    	        var formData = $("#productForm").serialize();
    	        $.ajax({
    	            type: "POST",
    	            url: "/BTL_WEB/web/addToCart",
    	            data: formData,
    	            success: function(response) {
    	            	window.location.href="/BTL_WEB/web/cart";
    	            },
    	            error: function(xhr, status, error) {
    	                console.error("Add to cart failed: " + error);
    	            }
    	        });
    	    });

    	    $("#buyNowBtn").click(function () {
    	        var formData = $("#productForm").serialize();
    	        $.ajax({
    	            type: "POST",
    	            url: "/BTL_WEB/web/buyNow",
    	            data: formData,
    	            success: function(response) {
    	                window.location.href="/BTL_WEB/web/checkout-page";
    	            },
    	            error: function(xhr, status, error) {
    	                console.error("Buy now failed: " + error);
    	            }
    	        });
    	    });
    	});



    </script>
    
    </body>

</html>