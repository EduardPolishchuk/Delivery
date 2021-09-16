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
    <title>OrderView</title>
    <jsp:include page="/WEB-INF/common/windowstyle.jsp"/>
</head>
<body style="background-color: black">
<jsp:include page="/WEB-INF/common/header2.jsp"/>
<h2 class="display-3 text-center" style="color: #000102; background-color: rgba(255,238,231,0.87)">Order Details</h2>
<div class="container justify-content-center w-50 ">
    <div class="row row-cols-1 row-cols-sm-1 row-cols-md-1 g-3 mb-6">
        <div class="col">
            <div class="card shadow-sm">
                <div class="card-body">
                    <form action="/success.jsp">
                        <div class="mb-3">
                            <label class="form-label"><fmt:message key="theme"/> </label>
                            <input type="text" class="form-control " name="theme"
                                   value="${''}"
                                   disabled>
                        </div>
                        <div class="mb-3">
                            <label class="form-label"><fmt:message key="price"/> (<fmt:message key="uah"/>)</label>
                            <input type="text" class="form-control " name="price" value="${''}"
                                   disabled>
                        </div>

                        <div class="mb-3">
                            <label class="form-label"><fmt:message key="description"/></label>
                            <textarea class="form-control" name="description" disabled
                                      rows="1">${''}</textarea>
                        </div>
                        <div class="mb-3">
                            <label class="form-label"><fmt:message key="halls"/></label>
                            <input type="text" class="form-control " name="imageUrl" value="${''}"
                                   disabled>
                        </div>
                        <div class="mb-3">
                            <label class="form-label"><fmt:message key="ticketsLeft"/> </label>
                            <input type="text" class="form-control " name="imageUrl"
                                   value="${''}"
                                   disabled>
                        </div>
                        <div>
                            <hr>
                        </div>
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



