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
<h2 class="display-3 text-center" style="color: #000102; background-color: rgba(255,238,231,0.87)">Delivery Service</h2>
<div class="container justify-content-center w-75 ">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-1 g-3">
        <div class="col ">
            <div class="card shadow-sm">
                <div class="card-body">

                    <form id="form2" action="${pageContext.request.contextPath}/calculate">
                        <h4 class="display-5 text-center" style="align-content: center">Calculate the cost</h4>
                        <hr>
                        <h5 class="display-7" style="align-content: center">Parcel parameters</h5>
                        <div class="row g-3 mb-3">
                            <div class="col">
                                <label class="form-label">Length</label>
                                <input name="length" value="${param.length}" type="number" min="1" class="form-control"
                                       placeholder="mm"
                                       aria-label="First name">
                            </div>
                            <div class="col">
                                <label class="form-label">Width</label>
                                <input name="width" value="${param.width}" type="number" min="1" class="form-control"
                                       placeholder="mm"
                                       aria-label="Last name">
                            </div>
                            <div class="col">
                                <label class="form-label">Height </label>
                                <input name="height" value="${param.height}" type="number" min="1" class="form-control"
                                       placeholder="mm"
                                       aria-label="Last name">
                            </div>
                            <div class="col">
                                <label class="form-label">Weight </label>
                                <input name="weight" value="${param.weight}" type="number" min="0,1"
                                       class="form-control " placeholder="kg"
                                       aria-label="Last name">
                            </div>
                        </div>
                        <h5 class="display-7" style="align-content: center">Route</h5>
                        <div class="row g-3 mb-3">
                            <div class="col">
                                <label class="form-label">City From</label>
                                <select class="form-select hide-icon" name="cityFrom"
                                        aria-label="Default select example">
                                    <c:forEach var="city" items="${cityList}">
                                        <option value="${city.id}" ${city.id == cityFrom ? 'selected':''}>${city.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col">
                                <label class="form-label">City To</label>
                                <select class="form-select" name="cityTo" aria-label="Default select example">
                                    <c:forEach var="city" items="${cityList}">
                                        <option value="${city.id}" ${city.id == param.cityTo ? 'selected':''}>${city.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <hr>
                    </form>

                    <div class="row g-3 mb-3">
                        <div class="col">
                            <button form="form2" type="submit" class="btn btn-primary">Calculate</button>
                        </div>
                    </div>

                    <h3 class="display-4">${calculatedValue}</h3>
                    ${calculatedValue = null}
                    <p>
                        <a class="btn btn-primary" data-bs-toggle="collapse" href="#multiCollapseExample1" role="button"
                           aria-expanded="false" aria-controls="multiCollapseExample1">Tariff</a>
                    </p>
                    <div class="row">
                        <div class="col">
                            <div class="collapse multi-collapse" id="multiCollapseExample1">
                                <div class="card card-body">
                                    Tariff is ${tariff.uahPerKilometerDistance} uah/km distance,
                                    ${tariff.uahPerMillimeterWidth} uah/mm width,
                                    ${tariff.uahPerMillimeterHeight} uah/mm height,
                                    ${tariff.uahPerMillimeterLength} uah/mm length,
                                    ${tariff.uahPerKilogramWeight} uah/kg weight,
                                    + additional ${tariff.additional} uah .
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="WEB-INF/common/footer.jsp"/>
</body>

</html>