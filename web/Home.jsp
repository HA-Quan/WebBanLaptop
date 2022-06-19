<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Laptop Gia re</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <style>
            html,body{
                margin: 0;
            }
            
            .header {
                width: 100%;
                height: 100%;
                background-image:url(https://media.gettyimages.com/photos/seamless-rice-paper-background-picture-id184882351?s=612x612);
                background-size: 100% auto;
                background-repeat:repeat;
            }
        </style>
    </head>
    <body >
        
        <div class="header">
            <jsp:include page="Menu.jsp"></jsp:include>
           
                <div class="container">
                    <div class="row">
                        <div class="col-sm-3.5">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="HomeControl">All Product</a></li>
                                    <li class="breadcrumb-item"><a href="#">Category</a></li>
                                    <li class="breadcrumb-item active" aria-current="#">Sub-category</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                </div>
                <div class="container">
                    <div class="row">
                         <jsp:include page="CategoryVsLast.jsp"></jsp:include>

                        <div class="col-sm-9">
                            <div class="row">
                                <c:forEach items="${listP}" var="o">
                                    <div class="col-13 col-md-8 col-lg-4">
                                        <div class="card-bg-info">
                                            <img class="card-img-top" src="${o.image}" alt="Card image cap">
                                            <div class="card-body">
                                                <h4 class="card-title show_txt"><a href="DetailControl?pid=${o.id}" title="View Product">${o.name}</a></h4>
                                                <p class="card-text show_txt">${o.title}</p>
                                                <div class="row">
                                                    <div class="col">
                                                        <span class="badge badge-warning">${o.getPriceWithDot()} VND</span>

                                                    </div>
                                                    
                                                    <div class="col">
                                                    <c:if test="${o.amount!=0}">
                                                        <span class="badge badge-pill badge-info">Amount ${o.amount}</span>
                                                    </c:if>
                                                     <c:if test="${o.amount==0}">
                                                        <span class="badge badge-danger">Hết hàng</span>
                                                    </c:if>   
                                                    </div>
                                                    <div class="col">
                                                        <a onclick="addCart(${o.id})" class="btn btn-outline-success">Add to cart</a>
                                                    </div>
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>

                    </div>
                </div>
                         
                         <div class="clearfix">
                             <div class="hint-text">Showing <b>4</b> out of <b>25</b> entries</div>
                        <ul class="pagination">
                             <c:if test="${index >1}">
                                <li class="page-item disabled"><a href="HomeControl?index=${index-1}">Previous</a></li>
                            </c:if>
                                <c:forEach begin="1" end="${endPage}" var="i">
                                    <li class="page-item ${index == i? "active":""}"><a href="HomeControl?index=${i}" class="page-link">${i}</a></li>
                                </c:forEach>
                            <c:if test="${index < endPage}">
                                <li class="page-item"><a href="HomeControl?index=${index+1}" class="page-link">Next</a></li>
                            </c:if>
                        </ul>
                    </div>

                <jsp:include page="Footer.jsp"></jsp:include>
            </div>
            <script src="https://uhchat.net/code.php?f=56ac67"></script>
            <div class="zalo-chat-widget" data-oaid="589634003921980460" 
                 data-welcome-message="Rất vui khi được hỗ trợ bạn!" data-autopopup="0" data-width="300" data-height="350"></div>

<script src="https://sp.zalo.me/plugins/sdk.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            function addCart(ProductID) {
                <c:if test="${sessionScope.account != null}">
                    //Sử dụng Ajax
                    $.ajax({
                        url: "/DemoBTL/addToCart",
                        type: "get", //send it through get method
                            data: {
                                ProductID: ProductID
                            },
                            success: function (message) {
                                alert(message);
                                location.href = "HomeControl";    
                            },
                            error: function () {
                            }
                        });
            </c:if>
            <c:if test="${sessionScope.account == null}">
                location.href = "Login.jsp";
            </c:if>
            }
        </script>
    </body>
</html>

