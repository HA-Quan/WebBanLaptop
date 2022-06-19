<%-- 
    Document   : CategoryVsLast
    Created on : Apr 28, 2022, 1:11:34 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-sm-3">
    <div class="card bg-light mb-3">
        <div class="card-header bg-info text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
        <ul class="list-group category_block">
            <c:forEach items="${listCC}" var="o">
                <li class="list-group-item text-success ${link == o.cid ? "active":""}"><a href="CategoryControl?cid=${o.cid}">${o.cname}</a></li>
                </c:forEach>

        </ul>
    </div>
    <div class="card bg-light mb-3">
        <div class="card-header bg-info text-white text-uppercase"><i class="fa fa-list"></i> Price</div>
        <ul class="list-group category_block">
            <c:forEach items="${listPri}" var="o">
                <li class="list-group-item text-success ${tag == o.pid ? "active":""}"><a href="price?pid=${o.pid}">${o.pname}</a></li>
                </c:forEach>

        </ul>
    </div>
    <div class="card bg-light mb-3">
        <div class="card-header bg-info text-white text-uppercase">New product</div>
        <div class="card-body">
            <img class="img-fluid" src="${p.image}" />
            <h5 class="card-title"><a href="DetailControl?pid=${p.id}" >${p.name}</a></h5>
            <p class="card-text">${p.title}</p>
            <p class="bloc_left_price">${p.getPriceWithDot()} VND</p>
        </div>
    </div>
</div>
