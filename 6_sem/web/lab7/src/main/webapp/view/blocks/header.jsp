<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" xmlns="">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>${param.title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/main.css">
    <link rel="stylesheet" href="${param.pageStyle}">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/navbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/footer.css">
    <script src="${pageContext.request.contextPath}/static/js/form/form-generator.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/form/form-validation.js"></script>
</head>

<body class="layout" onload="onLoadPage()">
<header class="header">
    <jsp:include page="navbar.jsp"/>
</header>