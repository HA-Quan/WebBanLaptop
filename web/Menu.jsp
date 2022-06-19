<%@page import="dao.CartDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="dao" class="dao.CartDAO"></jsp:useBean>
<!--begin of menu-->

        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="HomeControl"><img src="video/images.jpg" width="100px" height="50px"></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
                    <ul class="navbar-nav m-auto">
                        <c:if test="${sessionScope.account.isSell == 1}">
                            <li class="nav-item">
                                <a class="nav-link" href="ManagerControl">Manager Product</a>
                            </li>
                        </c:if>
                            <c:if test="${sessionScope.account != null}">
                            <li class="nav-item">
                                <a class="nav-link" href="history">History</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="LogoutControl">Logout</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Hello ${sessionScope.account.user}</a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account == null}">
                            <li class="nav-item">
                                <a class="nav-link" href="Login.jsp">Login</a>
                            </li>
                        </c:if>    
                        
                    </ul>
                    <!<!--start Search -->
                    <form action="SearchControl" method="post" class="form-inline my-2 my-lg-0">
                        <div class="input-group input-group-sm">
                            <input value="${txt}" name="search" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                            <div class="input-group-append">
                                <button type="submit" class="btn btn-secondary btn-number">
                                    <i class="fa fa-search"></i>
                                </button>
                            </div>
                        </div>
                        <li style="margin-right: 20px">
                                    <a class="btn btn-info btn-sm ml-3" onclick="showCart()" style="height: 30px; padding-top: 2px">
                                        <i class="fa fa-shopping-cart"></i> Cart
                                        <span class="badge badge-light">${dao.countNumCart(sessionScope.account.id)}</span>
                                </a>
                        </li>
                    </form>
                    <!<!--end Search -->
                </div>
            </div>
        </nav>
        <script>
                                   

            function showCart() {
                <c:if test="${sessionScope.account != null}">
                    location.href = "show"                 
                </c:if>
                <c:if test="${sessionScope.account == null}">
                    location.href = "Login.jsp";
                </c:if>
            }
                                   
        </script>  
        <section class="text-center" style="background-color: white;">
                <div class="container-fluid" style="background-image: url('video/banner-n04.jpg'); height: 400px;background-size: cover">
                </div>
            </section>
                            
        <!--end of menu-->
