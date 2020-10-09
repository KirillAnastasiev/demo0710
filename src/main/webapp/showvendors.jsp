<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Vendors of ${countryBean.country.name}</title>
    <link rel="stylesheet" type="text/css" href="resources/styles.css">
</head>
<body>
    <h2>Vendors of ${countryBean.country.name}</h2>
    <table>
        <thead>
            <th>id</th><th>Name</th><th>Marks</th>
        </thead>
        <tbody>
        <jsp:useBean id="vendorBean" scope="request" type="beans.VendorBean" />
        <c:forEach items="${vendorBean.vendors}" var="v">
            <tr>
                <td>${v.id}</td>
                <td>${v.name}</td>
                <td>
                    <a href="showmarks.html?vendorId=${v.id}">Marks of ${v.name}</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br/>
    <a href="index.html">Back to main page</a>
</body>
</html>
