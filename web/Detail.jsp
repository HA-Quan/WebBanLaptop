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
         <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <style>
            .gallery-wrap .img-big-wrap img {
                height: 450px;
                width: auto;
                display: inline-block;
                cursor: zoom-in;
            }


            .gallery-wrap .img-small-wrap .item-gallery {
                width: 60px;
                height: 60px;
                border: 1px solid #ddd;
                margin: 7px 2px;
                display: inline-block;
                overflow: hidden;
            }

            .gallery-wrap .img-small-wrap {
                text-align: center;
            }
            .gallery-wrap .img-small-wrap img {
                max-width: 100%;
                max-height: 100%;
                object-fit: cover;
                border-radius: 4px;
                cursor: zoom-in;
            }
            .img-big-wrap img{
                width: 100% !important;
                height: auto !important;
            }
        </style>
    </head>
    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="container">
                <div class="row">
                    
                    <jsp:include page="CategoryVsLast.jsp"></jsp:include>
                    
                <div class="col-sm-9">
                    <div class="container">
                        <div class="card">
                            <div class="row">
                                <aside class="col-sm-5 border-right">
                                    <article class="gallery-wrap"> 
                                        <div class="img-big-wrap">
                                            <div> <a href="#"><img src="${detail.image}"></a></div>
                                        </div> <!-- slider-product.// -->
                                       
                                        <div class="col">
                                                        <c:if test="${detail.amount!=0}">
                                                        <span class="badge badge-pill badge-info">Amount ${detail.amount}</span>
                                                    </c:if>
                                                        <c:if test="${detail.amount==0}">
                                                        <span class="badge badge-danger">Hết hàng</span>
                                                    </c:if>
                                                    </div>
                                    </article> <!-- gallery-wrap .end// -->
                                </aside>
                                <aside class="col-sm-7">
                                    <h3 style="color: red">${message}</h3>
                                    <article class="card-body p-5">
                                        <h3 class="title mb-3">${detail.name}</h3>

                                        <p class="price-detail-wrap"> 
                                            <span class="price h3 text-warning"> 
                                                <span class="num">${detail.getPriceWithDot()} </span><span class="currency">VND</span>
                                            </span> 
                                            <!--<span>/per kg</span>--> 
                                        </p> <!-- price-detail-wrap .// -->
                                        <dl class="item-property">
                                            <dt>Description</dt>
                                            <dd><p>${detail.description} </p></dd>
                                        </dl>
                                        
                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-5">
                                                <dl class="param param-inline">
                                                    <dt>Quantity: </dt>
                                                    <dd>
                                                        <select class="form-control form-control-sm" style="width:70px;" id="select_id">
                                                            <c:forEach var="i" begin="1" end="${detail.amount}">
                                                                <option> ${i} </option>
                                                            </c:forEach>
                                                        </select>
                                                    </dd>
                                                </dl>  <!-- item-property .// 
                                            </div> <!-- col.// -->
                                            
                                        </div> <!-- row.// -->
                                        <hr>
                                        <c:if test="${detail.amount!=0}">
                                             <button onclick="buy(${detail.id})" class="btn btn-lg btn-info text-uppercase"> Buy now </button>
                                             <button onclick="addCart2(${detail.id})" class="btn btn-lg btn-outline-info text-uppercase"> <i class="fas fa-shopping-cart"></i> Add to cart </button>
                                        </c:if>
                                        <c:if test="${detail.amount==0}">
                                            <button onclick="buy(${detail.id})" class="btn btn-lg btn-info text-uppercase" disabled> Buy now </button>
                                            <button onclick="addCart2(${detail.id})" class="btn btn-lg btn-outline-info text-uppercase" disabled> <i class="fas fa-shopping-cart"></i> Add to cart </button>
                                        </c:if>

                                    </article> <!-- card-body.// -->
                                </aside> <!-- col.// -->
                            </div> <!-- row.// -->
                        </div> <!-- card.// -->


                    </div>
                </div>
            </div>
        </div>
          <jsp:include page="Footer.jsp"></jsp:include>
          <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            function addCart2(ProductID) {
                <c:if test="${sessionScope.account != null}">
                    var select_value = document.getElementById("select_id").value;
                    //Sử dụng Ajax
                    $.ajax({
                    url: "/DemoBTL/addMany",
                    type: "get", //send it through get method
                    data: {
                        ProductID: ProductID,
                        Quantity: select_value
                        },
                    success: function (message) {
                        alert(message);
                    }
                    });
                </c:if>
            <c:if test="${sessionScope.account == null}">
                location.href = "Login.jsp";
            </c:if>
                                            }

            function buy(ProductID) {
                    <c:if test="${sessionScope.account != null}">
                         var select_value = document.getElementById("select_id").value;
                         location.href = "buyNow?ProductID=" + ProductID + "&Quantity=" + select_value;   
                    </c:if>
                    <c:if test="${sessionScope.account == null}">
                        location.href = "Login.jsp";
                    </c:if>                            
            }
        </script>  
    </body>
</html>
