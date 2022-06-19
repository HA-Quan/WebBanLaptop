<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--Favicon-->
        <link rel="icon" type="image/png" href="image/faviconLogo.png" />
        <title>Laptop Gia re</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <style>
            .container table img{
                height: 100px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
            <h1 style="text-align: center">Giỏ hàng của bạn</h1>
            <div class="container">
                <div id="print">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Name</th>
                                <th scope="col">Image</th>
                                <th scope="col">Price</th>
                                <th scope="col">Amount</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="o">
                            <tr>
                                <th scope="row">${o.getP().getId()}</th>
                                <td>${o.getP().getName()}</td>
                                <td><img src="${o.getP().getImage()}"></td>
                                <td>${o.getP().getPriceWithDot()} VND</td>
                                <td class="align-middle">
                                    <a href="ChangeProductInCart?ProductID=${o.p.id}&isChange=dec"><button class="btnSub">-</button></a> 
                                    <strong>${o.amount}</strong>
                                    <a href="ChangeProductInCart?ProductID=${o.p.id}&isChange=sum"><button class="btnAdd">+</button></a>
                                </td>
                                <td><a href="deleteProductInCart?ProductID=${o.getP().getId()}" type="button" class="btn btn-outline-info">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="btn-group" role="group" aria-label="Basic outlined example">
                    <button type="button" class="btn btn-warning">Total</button>
                    <button type="button" class="btn btn-outline-primary">${total} VND</button>
                </div>
            </div>
            <div>&emsp;</div>
            <div><a type="button" onclick="buy()" class="btn btn-info btn-lg">Thanh Toán</a></div>
            <button onclick="inHoaDon()" style="margin-top: 20px" class="btn btn-info btn-lg">In hóa đơn</button>
            <a type="button" href="deleteCart" class="btn btn-danger" style="float: right">Delete Cart</a>
            <div>&emsp;</div><div>&emsp;</div><div>&emsp;</div>
        </div>
        <script>
            function buy() {
            <c:if test="${list!=null}">
                location.href = "buy";
            </c:if>
            <c:if test="${list.isEmpty()}">
                alert("Gio hang cua ban chua co san pham de thanh toan");
                location.href = "HomeControl";
            </c:if>
            }

            function inHoaDon() {
                
                window.print();
                
            }
        </script>
    </body>
</html>
