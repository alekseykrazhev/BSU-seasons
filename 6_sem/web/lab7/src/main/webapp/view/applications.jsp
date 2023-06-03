<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="blocks/header.jsp">
    <jsp:param name="title" value="My App. Repairs"/>
</jsp:include>

<main class="main applications-page title-regular-14">
    <c:if test="${errorString != null}">
        <section class="page-error"
                 style="padding: 16px; margin-bottom: 16px; background-color: #d4d4d4; border-radius: 12px;"
        >
            <h2 class="page-error__title title-regular-28" style="color: rgb(247, 114, 73)">Server error</h2>
            <p class="title-regular-14">${errorString}</p>
        </section>
    </c:if>

    <div id="applications-result">
        <table class="table">
            <tr>
                <th>ID</th>
                <th>NAME</th>
                <th>DISPATCHER</th>
                <th>ID TYPE</th>
                <th>DATE</th>
            </tr>

            <c:forEach items="${applicationsList}" var="application">
                <tr>
                    <td>${application.getId()}</td>
                    <td>${application.getName()}</td>
                    <td>${application.getDispatcher() != null ? application.getDispatcher() : "none"}</td>
                    <td>${application.getId_type()}</td>
                    <td>${application.getDate()}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="container mt-5">
        <form class="form" action="${pageContext.request.contextPath}/?command=applications" method="post">
            <div class="form-group">
                <label for="name">Введите ID:</label>
                <input type="number" class="form-control" id="name" name="name" placeholder="Ввод здесь...">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>

</main>

<script>
    function submitForm(event) {
        event.preventDefault();
        const form = document.getElementById('myForm');
        const formData = new FormData(form);
        const name = form.elements['name'].value;

        const request = new XMLHttpRequest();
        request.open('GET', '${pageContext.request.contextPath}/?command=applications&name=' + name);

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

<jsp:include page="blocks/footer.jsp"/>