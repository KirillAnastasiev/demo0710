<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Watches of vendor ${vendorBean.vendor.name}</title>
    <link rel="stylesheet" type="text/css" href="resources/styles.css">
</head>
<body>
    <h2>Watches of vendor ${vendorBean.vendor.name}</h2>
    <table>
        <thead>
            <th>id</th><th>Mark</th><th>Type</th>
        </thead>
        <tbody>
            <jsp:useBean id="watchBean" scope="request" type="beans.WatchBean" />
            <c:forEach items="${watchBean.watches}" var="w">
                <tr>
                    <td>${w.id}</td>
                    <td>${w.mark}</td>
                    <td>${w.type}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br/>
    <a href="index.html">Back to main page</a>
</body>
</html>
