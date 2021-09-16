<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="/WEB-INF/custom_tag.tld" prefix="custom" %>--%>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locale/resources"/>
<html>
<head>
    <title>MyReceipts</title>
    <jsp:include page="/WEB-INF/common/windowstyle.jsp"/>
</head>
<body style="background-color: black">
<jsp:include page="/WEB-INF/common/header2.jsp"/>
<h2 class="display-3 text-center" style="color: #000102; background-color: rgba(255,238,231,0.87)">My Receipts</h2>
<div class="container justify-content-center w-75 ">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-1 g-3">
        <div class="col ">
            <div class="card shadow-sm">
                <div class="card-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Receipt ID</th>
                            <th scope="col">Order</th>
                            <th scope="col">Price</th>
                            <th scope="col">Payment</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="receipt" items="${userReceipts}">
                        <tr>
                            <td><strong>${receipt.id}</strong></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/user/userOrderView"
                                   style="color: black"><strong>Order Link</strong></a></td>
                            <td>${receipt.price} </td>
                            <td>${receipt.paid ? 'paid' : 'not paid'} </td>
                            <td>
                                <button type="button" class="btn btn-sm btn-outline-secondary"
                                        data-bs-toggle="modal" data-bs-target="#exampleModal"
                                        data-bs-id="${receipt.id}" data-bs-price="${receipt.price}"
                                        data-bs-orderId="${receipt.order.id}" ${userProfile.balance - receipt.price < 0 ? 'disabled' : ''}>
                                    Pay
                                </button>
                            </td>
                        </tr>
                        </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<hr>
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Pay a receipt</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/user/userPayReceipt" class="row g-3 needs-validation">
                    <div class="mb-3">
                    </div>
                    <div class="mb-3">
                        <input type="hidden" class="form-control" id="exId-name" name="paidReceipt">
                        <input type="hidden" class="form-control" id="price" name="price">
                        <input type="hidden" class="form-control" id="orderId" name="orderId">
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary"><fmt:message key="confirm"/></button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><fmt:message
                                key="close"/></button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>
<script src="/docs/5.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
<script type="text/javascript">
    var exampleModal = document.getElementById('exampleModal')
    exampleModal.addEventListener('show.bs.modal', function (event) {
        var button = event.relatedTarget
        var exId = button.getAttribute('data-bs-id')
        var price = button.getAttribute('data-bs-price')
        var orderId = button.getAttribute('data-bs-orderId')
        var modalBodyInput = exampleModal.querySelector('.modal-body input')
        var modalBodyInput2 = exampleModal.querySelector('.modal-body #price')
        var modalBodyInput3 = exampleModal.querySelector('.modal-body #orderId')
        modalBodyInput.value = exId
        modalBodyInput2.value = price
        modalBodyInput3.value = orderId

    })
</script>
</body>
</html>



