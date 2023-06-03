<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="blocks/header.jsp">
    <jsp:param name="title" value="My App. Home"/>
    <jsp:param name="pageStyle" value="${pageContext.request.contextPath}/static/styles/pages/home.css"/>
</jsp:include>

<main class="main home-preview">
    <div class="container mt-5">
        <h1>Добро пожаловать на страницу Ремонтной Мастерской</h1>
        <p>Здесь вы найдете всю необходимую информацию о ремонте</p>
    </div>
</main>

<script>const onLoadPage = () => {
}</script>
<jsp:include page="blocks/footer.jsp"/>
