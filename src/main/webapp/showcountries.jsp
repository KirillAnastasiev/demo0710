<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Countries</title>
    <link rel="stylesheet" type="text/css" href="resources/styles.css">
</head>
<body>
    <h2>Produce countries</h2>
    <table>
        <thead>
        <tr>
            <th>id</th><th>Name</th><th>Short name</th><th>Vendors</th>
        </tr>
        </thead>
        <tbody>
    <jsp:useBean id="countryBean" scope="request" type="beans.CountryBean"/>
    <c:forEach items="${countryBean.countries}" var="c">
        <tr>
            <td>${c.id}</td>
            <td>${c.name}</td>
            <td>${c.shortName}</td>
            <td>
                <a href="showvendors.html?countryId=${c.id}">Vendors of ${c.shortName}</a>
            </td>
        </tr>
    </c:forEach>
        </tbody>
    </table>
</body>
</html>
