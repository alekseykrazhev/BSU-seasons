<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <meta charset="utf-8">
    <title><fmt:message key="app.title"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row justify-content-center mt-5">
        <div class="col-md-6">
            <h1 class="text-center mb-4">Login</h1>
            <form action="${pageContext.request.contextPath}/?command=authorization" method="POST" id="myForm">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary">Login</button>
            </form>
            <p class="mt-3 text-center">Don't have an account? <a href="${pageContext.request.contextPath}/?command=signup">Sign up</a></p>
        </div>
    </div>
</div>
<script>
    function submitForm(event) {
        event.preventDefault();
        const form = document.getElementById('myForm');
        const formData = new FormData(form);
        const username = form.elements['username'].value;
        const password = form.elements['password'].value;
        console.log(username, password);
        const request = new XMLHttpRequest();
        request.open('POST', '${pageContext.request.contextPath}/?command=authorization&username=' + username + '&password='
            + password);

        request.onload = function () {
            if (request.status === 200) {
                console.log('Form submitted successfully');
            } else {
                console.log('Form submission failed');
            }
        };

        request.send(formData);
    }

    const form = document.getElementById('myForm');
    form.addEventListener('submit', submitForm);
</script>
</body>
</html>