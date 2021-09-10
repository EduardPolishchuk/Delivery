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
                        <h4 class="display-5" style="align-content: center">Calculate</h4>
                        <div class="row g-3 mb-3">
                            <div class="col">
                                <label  class="form-label">Length (mm)</label>
                                <input type="number" min="1" class="form-control" placeholder="First name" aria-label="First name">
                            </div>
                            <div class="col">
                                <label  class="form-label">Width (mm)</label>
                                <input type="number" min="1" class="form-control" placeholder="Last name" aria-label="Last name">
                            </div>
                            <div class="col">
                                <label  class="form-label">Height (mm)</label>
                                <input type="number" min="1" class="form-control" placeholder="Last name" aria-label="Last name">
                            </div>
                            <div class="col">
                                <label  class="form-label">Weight (kg)</label>
                                <input type="number" min="0" class="form-control "  aria-label="Last name">
                            </div>
                        </div>
                        <div class="row g-3 mb-3">
                            <div class="col">
                                <label  class="form-label">City From</label>
                                <select class="form-select hide-icon" name="cityFrom" aria-label="Default select example">
                                    <c:forEach var="city" items="${cityList}">
                                        <option  value="${city.id}" ${city.id == cityFrom ? 'selected':''}>${city.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col">
                                <label  class="form-label">City To</label>
                                <select class="form-select" name="cityTo" aria-label="Default select example">
                                    <c:forEach var="city" items="${cityList}">
                                        <option value="${city.id}" ${city.id == param.cityTo ? 'selected':''}>${city.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Calculate</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/test">
                        <button type="submit" class="btn btn-outline-light " ><strong>EN</strong></button>
                    </form>
                    <h3 class="display-4">${calculatedValue}</h3>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="WEB-INF/common/footer.jsp"/>
</body>

</html>