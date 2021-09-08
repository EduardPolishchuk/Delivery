<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locale/resources"/>
<html>
<head>
    <title>Delivery Service</title>
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
                    <h3 class="display-4">Delivery Service</h3>
                    <form action="${pageContext.request.contextPath}/calculate">
                        <div class="mb-3 w-75">
                            <label  class="form-label">City From</label>
                            <select class="form-select" name="cityFrom" aria-label="Default select example">
                                <c:forEach var="city" items="${cityList}">
                                    <option  value="${city.id}">${city.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="mb-3 w-75">
                            <label  class="form-label">City To</label>
                            <select class="form-select" name="cityTo" aria-label="Default select example">

                                <c:forEach var="city" items="${cityList}">
                                    <option value="${city.id}">${city.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Calculate</button>
                    </form>
                    <h3 class="display-4">${calculatedValue}</h3>
                </div>
            </div>
        </div>
    </div>
</div>
<%--<jsp:include page="WEB-INF/common/footer.jsp"/>--%>
</body>

</html>