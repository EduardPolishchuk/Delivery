<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="/WEB-INF/custom_tag.tld" prefix="custom" %>--%>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locale/resources"/>
<c:set var="vari" value="${not empty param.edit ? null : 'disabled'}" scope="session"/>
<html>
<head>
    <title>OrderView</title>
    <jsp:include page="/WEB-INF/common/windowstyle.jsp"/>
</head>
<body style="background-color: black">
<jsp:include page="/WEB-INF/common/header2.jsp"/>
<h2 class="display-3 text-center" style="color: #000102; background-color: rgba(255,238,231,0.87)">Order Details</h2>
<div class="container justify-content-center w-75 ">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-1 g-3">
        <div class="col ">
            <div class="card shadow-sm">
                <div class="card-body">
                    <form id="form2" action="${pageContext.request.contextPath}/manager/managerChangeTariff">
                        <hr>
                        <div class="row g-3 mb-3">
                            <div class="col">
                                <label class="form-label">Weight Uah/kg</label>
                                <input name="weightMulti" type="number" min="0" step="0.1" class="form-control"
                                       value="${tariff.uahPerKilogramWeight}"
                                ${vari} required>
                            </div>
                            <div class="col">
                                <label class="form-label">Distance Uah/km</label>
                                <input name="distanceMulti" type="number" min="0" step="0.1" class="form-control"
                                       value="${tariff.uahPerKilometerDistance}"
                                ${vari}
                                       required>
                            </div>
                        </div>
                        <div class="row g-3 mb-3">
                            <div class="col">
                                <label class="form-label">Length Uah/mm</label>
                                <input name="lengthMulti" min="0" step="0.1" value="${tariff.uahPerMillimeterLength}"
                                       type="number"
                                       min="1"
                                       class="form-control"
                                ${vari}
                                       required>
                            </div>
                            <div class="col">
                                <label class="form-label">Height Uah/mm</label>
                                <input name="heightMulti" min="0" step="0.1" value="${tariff.uahPerMillimeterHeight}"
                                       type="number"
                                       min="1"
                                       class="form-control"
                                ${vari}
                                       required>
                            </div>
                            <div class="col">
                                <label class="form-label">Width Uah/mm</label>
                                <input name="widthMulti" min="0" step="0.1" value="${tariff.uahPerMillimeterWidth}"
                                       type="number"
                                       min="1"
                                       class="form-control"
                                ${vari}
                                       required>
                            </div>
                            <div class="col">
                                <label class="form-label">Additional Uah</label>
                                <input name="additional" min="0" step="0.1" value="${tariff.additional}" type="number"
                                       min="1"
                                       class="form-control"
                                ${vari} required>
                            </div>
                        </div>
                        <hr>
                        <button type="submit" class="btn btn-primary"  ${vari}><fmt:message key="update"/></button>
                    </form>
                    <form action="">
                        <button type="submit" class="btn btn-dark" name="edit" value="${vari != null  ? '1' : null}">
                            <fmt:message key="edit"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<hr>

<jsp:include page="/WEB-INF/common/footer.jsp"/>
</body>
</html>



