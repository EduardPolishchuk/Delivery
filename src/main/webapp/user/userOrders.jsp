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
    <title>MyEvents</title>
    <jsp:include page="/WEB-INF/common/windowstyle.jsp"/>
</head>
<body style="background-color: black">
<jsp:include page="/WEB-INF/common/header2.jsp"/>
<h2 class="display-3 text-center" style="color: #000102; background-color: rgba(255,238,231,0.87)">My Orders</h2>

<div class="container justify-content-center  ">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-1 g-3">
        <div class="col ">
            <div class="card shadow-sm">
                <div class="card-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col"><a href="?sortBy=${param.sortBy == 'orderId'? 'orderIdDesc':'orderId' }" style="color: black">Order ID</a></th>
                            <th scope="col"><a href="?sortBy=${param.sortBy == 'requestDate'? 'requestDateDesc':'requestDate' }" style="color: black">Request Date</a></th>
                            <th scope="col"><a href="?sortBy=${param.sortBy == 'receiveDate'? 'receiveDateDesc':'receiveDate' }" style="color: black">Receiving Date</a></th>
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
                                <%--                            <td><a href="${pageContext.request.contextPath}/manager/managerUserExhibitions?userID=${user.id}" style="color: black"><strong>${user.login}</strong></a></td>--%>
                            <td><strong>${order.id}</strong></td>
                            <td>${order.requestDate}</td>
                            <td>${order.receivingDate}</td>
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

</body>
</html>



