<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 22/05/2023
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row justify-content-center mt-5">
        <div class="col-md-6">
            <h1 class="text-center mb-4">Sign Up</h1>
            <form action="${pageContext.request.contextPath}/?command=signup" method="POST" id="myForm">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="myCheckbox">
                    <label class="form-check-label" for="myCheckbox">Admin</label>
                </div>
                <button type="submit" class="btn btn-primary">Sign Up</button>
            </form>
            <p class="mt-3 text-center">Already have an account? <a href="${pageContext.request.contextPath}/?command=authorization">Login</a></p>
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
        const checkbox = document.getElementById('myCheckbox');
        const isChecked = checkbox.checked;

        console.log(username, password, isChecked);
        const request = new XMLHttpRequest();
        request.open('POST', '${pageContext.request.contextPath}/?command=signup&username=' + username + '&password='
            + password + '&checked=' + isChecked);

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

