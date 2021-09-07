<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <jsp:include page="WEB-INF/common/windowstyle.jsp"/>
</head>
<body>
<jsp:include page="WEB-INF/common/header2.jsp"/>
<br/>
<div class="container justify-content-center w-75 ">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-1 g-3">
        <div class="col ">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h3 class="display-4">Delivery</h3>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">NAME</th>
                            <th scope="col">LATITUDE</th>
                            <th scope="col">LONGITUDE</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="city" items="${cityList}">
                        <tr>
                            <td><strong>${city.id}</strong></td>
                            <td>${city.name}</td>
                            <td>${city.latitude}</td>
                            <td>${city.longitude}</td>
                        </tr>
                        </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<%--<jsp:include page="WEB-INF/common/footer.jsp"/>--%>
</body>

</html>