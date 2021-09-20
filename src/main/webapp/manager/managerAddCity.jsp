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
<h2 class="display-3 text-center" style="color: #000102; background-color: rgba(255,238,231,0.87)">Adding a new city</h2>
<div class="container justify-content-center w-75 ">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-1 g-3">
        <div class="col ">
            <div class="card shadow-sm">
                <div class="card-body">
                    <form id="form2" action="${pageContext.request.contextPath}/user/userMain">
                        <hr>
                        <div class="row g-3 mb-3">
                            <div class="col w-75">
                                <label class="form-label">Name </label>
                                <input type="text" class="form-control">
                            </div>
                            <div class="col w-75">
                                <label class="form-label">Name uk</label>
                                <input type="text" class="form-control">
                            </div>

                        </div>
                        <div class="row g-3 mb-3">
                            <div class="col">
                                <label class="form-label">Longitude</label>
                                <div class="input-group mb-3 w-75">
                                <input type="text" class="form-control"  aria-label="Username">
                                <span class="input-group-text">°</span>
                                <input type="text" class="form-control"  aria-label="Server">
                                <span class="input-group-text">′</span>
                                <input type="text" class="form-control"  aria-label="Server">
                                <span class="input-group-text">″</span>
                            </div>
                            </div>
                            <div class="col">
                                <label class="form-label">Latitude</label>
                                <div class="input-group mb-3 w-75">
                                    <input type="text" class="form-control"  aria-label="Username">
                                    <span class="input-group-text">°</span>
                                    <input type="text" class="form-control"  aria-label="Server">
                                    <span class="input-group-text">′</span>
                                    <input type="text" class="form-control"  aria-label="Server">
                                    <span class="input-group-text">″</span>
                                </div>
                        </div>
                        <div class="row g-3 mb-3">
                            <div class="col">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="inlineRadioOptions"
                                           id="inlineRadio1" value="option1">
                                    <label class="form-check-label" for="inlineRadio1">1</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="inlineRadioOptions"
                                           id="inlineRadio2" value="option2">
                                    <label class="form-check-label" for="inlineRadio2">2</label>
                                </div>

                            </div>
                            <div class="col">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="inlineRadioOptions"
                                           id="inlineRadio3" value="option1">
                                    <label class="form-check-label" for="inlineRadio3">3</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="inlineRadioOptions"
                                           id="inlineRadio4" value="option2">
                                    <label class="form-check-label" for="inlineRadio4">4</label>
                                </div>

                            </div>
                        </div>

                        <hr>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/common/footer.jsp"/>
</body>
</html>



