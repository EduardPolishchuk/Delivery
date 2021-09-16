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
    <title>OrderList</title>
    <jsp:include page="/WEB-INF/common/windowstyle.jsp"/>
</head>
<body style="background-color: black">
<jsp:include page="/WEB-INF/common/header2.jsp"/>
<h2 class="display-3 text-center" style="color: #000102; background-color: rgba(255,238,231,0.87)">Orders to confirm</h2>

<div class="container justify-content-center  ">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-1 g-3">
        <div class="col ">
            <div class="card shadow-sm">
                <div class="card-body">
                    <table class="table">
                        <c:set var="counter" value="${(currentPage - 1)*4 + 1}"/>
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col"><a href="?sortBy=${param.sortBy == 'orderId'? 'orderIdDesc':'orderId' }" style="color: black">Order ID</a></th>
                            <th scope="col"><a href="?sortBy=${param.sortBy == 'requestDate'? 'requestDateDesc':'requestDate' }" style="color: black">Request Date</a></th>
                            <th scope="col">Confirming</th>
                            <th scope="col"><a href="?sortBy=${param.sortBy == 'cityFrom'? 'cityFromDesc':'cityFrom' }" style="color: black">City From</a></th>
                            <th scope="col"><a href="?sortBy=${param.sortBy == 'cityTo'? 'cityToDesc':'cityTo' }" style="color: black">City To</a></th>
                            <th scope="col"><a href="?sortBy=${param.sortBy == 'status'? 'statusDesc':'status' }" style="color: black">Status</a></th>
                            <th scope="col"><a href="?sortBy=${param.sortBy == 'type'? 'typeDesc':'type' }" style="color: black">Type</a></th>
                            <th scope="col">Length</th>
                            <th scope="col">Width</th>
                            <th scope="col">Height</th>
                            <th scope="col">Weight</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="order" items="${userOrders}">
                        <tr>
                            <td>${counter}</td>
                            <c:set var="counter" value="${counter + 1}"/>
                            <td><strong>${order.id}</strong></td>
                            <td>${order.requestDate}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/manager/managerOrderView">
                                    <button name="order" value="${order.id}" type="submit" class="btn btn-sm btn-outline-secondary">
                                        Send Receipt
                                    </button>
                                </form>
                            </td>
                            <td>${order.cityFrom.name}</td>
                            <td>${order.cityTo.name}</td>
                            <td>${order.status}</td>
                            <td>${order.parcel.type}</td>
                            <td>${order.parcel.length} mm</td>
                            <td>${order.parcel.width} mm</td>
                            <td>${order.parcel.height} mm</td>
                            <td>${order.parcel.weight} kg</td>
                        </tr>
                        </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<hr>
<footer>
<c:if test="${noOfPages > 1}">
    <div>
        <nav aria-label="...">
            <ul class="pagination justify-content-center">
                <c:set var="cnl" value="${not empty param.canceled? '&canceled=1':''}"/>
                <c:choose>
                    <c:when test="${currentPage <= 1}">
                        <li class="page-item disabled">
                            <span class="page-link"><fmt:message key="previous"/></span>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link"
                               href="?page=${currentPage - 1}&sortBy=${sortBy}${cnl}"><fmt:message key="previous"/></a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item active" aria-current="page">
                                <span class="page-link">${i}</span>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link"
                                                     href="?page=${i}&sortBy=${sortBy}${cnl}">${i}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>
                    <c:when test="${currentPage >= noOfPages}">
                        <li class="page-item disabled">
                            <span class="page-link"><fmt:message key="next"/></span>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link" href="?page=${currentPage + 1}&sortBy=${sortBy}${cnl}"><fmt:message key="next"/></a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
    </div>
</c:if>
</footer>


</body>
</html>



