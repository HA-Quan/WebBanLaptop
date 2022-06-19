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
            <h1 style="text-align: center">History Shoping</h1>
            <div class="container">
                <div id="print">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col">Image</th>
                                <th scope="col">Amount</th>
                                <th scope="col">Price</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="o">
                            <tr>
                                <th scope="row">${o.getNameProduct()}</th>
                                <td><img src="${o.getImage()}"></td>
                                <td class="align-middle">
                                    <strong>${o.getAmount()}</strong>
                                </td>
                                <td class="align-middle">
                                    <strong>${o.getTotalWithDot()} VND</strong>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            <div>&emsp;</div><div>&emsp;</div>
            <div class="btn-group" role="group" aria-label="Basic outlined example">
                    <button type="button" class="btn btn-warning">Total Amount</button>
                    <button type="button" class="btn btn-outline-primary">${amount} </button>
            </div>
            <div>&emsp;</div><div>&emsp;</div>
            <div class="btn-group" role="group" aria-label="Basic outlined example">
                    <button type="button" class="btn btn-warning">Total Payment</button>
                    <button type="button" class="btn btn-outline-primary">${total} VND</button>
            </div>
            <div>&emsp;</div><div>&emsp;</div>
            <button onclick="inLichSu()" style="margin-top: 20px" class="btn btn-info btn-lg">In lịch sử mua hàng</button>
            <div>&emsp;</div><div>&emsp;</div>
        </div>
        </div>
        <script>
            function inLichSu() {
                
                window.print();
                
            }
        </script>
    </body>
</html>
