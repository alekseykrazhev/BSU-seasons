<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>

<jsp:include page="blocks/header.jsp">
    <jsp:param name="title" value="My App. Workers"/>
</jsp:include>

<main class="main bets-page title-regular-14">
    <c:if test="${errorString != null}">
        <section class="page-error"
                 style="padding: 16px; margin-bottom: 16px; background-color: #d4d4d4; border-radius: 12px;"
        >
            <h2 class="page-error__title title-regular-28" style="color: rgb(247, 114, 73)">Server error</h2>
            <p class="title-regular-14">${errorString}</p>
        </section>
    </c:if>

    <div id="workplans-result">
        <table class="table">
            <tr>
                <th>NAME</th>
                <th>ID</th>
                <th>ID TYPE</th>
                <th>WORKER</th>
            </tr>

            <c:forEach items="${workplansList}" var="workplans">
                <tr>
                    <td>${workplans.getName()}</td>
                    <td>${workplans.getId()}</td>
                    <td>${workplans.getId_type()}</td>
                    <td>${workplans.getWorker().toString()}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="container mt-5">
        <h2>Add new Worker</h2>
        <form id="myForm" class="form" action="${pageContext.request.contextPath}/?command=workplans" method="post">
            <div class="form-group">
                <label for="id_type">ID Type</label>
                <input type="number" class="form-control" id="id_type" name="id_type" placeholder="enter here...">
            </div>
            <div class="form-group">
                <label for="worker_id">ID</label>
                <input type="number" class="form-control" id="worker_id" name="worker_id" placeholder="enter here...">
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
        const name = "name";
        const id_type = form.elements['id_type'].value;
        const worker_id = form.elements['worker_id'].value;

        const request = new XMLHttpRequest();
        console.log(name, id_type, worker_id);
        request.open('POST', '${pageContext.request.contextPath}/?command=workplans&name=' + name + '&id_type=' + id_type + '&worker_id=' + worker_id);

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