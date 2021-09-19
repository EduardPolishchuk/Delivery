<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="/WEB-INF/custom_tag.tld" prefix="custom" %>--%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale/resources"/>
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
                    <form id="form2" action="${pageContext.request.contextPath}/user/userMain">
                        <hr>
                        <h5 class="display-7" style="align-content: center">Sender</h5>
                        <div class="row g-3 mb-3">
                            <div class="col">
                                <label class="form-label">First Name</label>
                                <input   type="number" min="1" class="form-control"
                                         placeholder="${userProfile.firstName}" disabled>
                            </div>
                            <div class="col">
                                <label class="form-label">Last Name</label>
                                <input   type="number" min="1" class="form-control"
                                         placeholder="${userProfile.lastName}" disabled>
                            </div>
                        </div>
                        <h5 class="display-7" style="align-content: center">Parcel parameters</h5>
                        <div class="row g-3 mb-3">
                            <div class="col">
                                <label class="form-label">Length</label>
                                <input name="length" value="${order.parcel.length}" type="number" min="1" class="form-control"
                                       placeholder="mm" disabled>
                            </div>
                            <div class="col">
                                <label class="form-label">Width</label>
                                <input name="width" value="${order.parcel.width}" type="number" min="1" class="form-control"
                                       placeholder="mm" disabled>
                            </div>
                            <div class="col">
                                <label class="form-label">Height </label>
                                <input name="height" value="${order.parcel.height}" type="number" min="1" class="form-control"
                                       placeholder="mm" disabled>
                            </div>
                            <div class="col">
                                <label class="form-label">Weight </label>
                                <input name="weight" value="${order.parcel.weight}" type="number" min="0,1"
                                       class="form-control " placeholder="kg" disabled>
                            </div>
                            <div class="col">
                                <label class="form-label">Type</label>
                                <input name="type" value="${order.parcel.type}" type="text"
                                       class="form-control " disabled>
                            </div>
                        </div>
                        <h5 class="display-7" style="align-content: center">Route</h5>
                        <div class="row g-3 mb-3">
                            <div class="col">
                                <label class="form-label">City From</label>
                                <input name="type" value="${order.cityTo.name}" type="text"
                                       class="form-control " disabled>
                            </div>
                            <div class="col">
                                <label class="form-label">City To</label>
                                <input name="type" value="${order.cityFrom.name}" type="text"
                                       class="form-control " disabled>
                            </div>
                        </div>
                        <hr>
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



