<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="blocks/header.jsp">
    <jsp:param name="title" value="Housings. Home"/>
    <jsp:param name="pageStyle" value="${pageContext.request.contextPath}/static/styles/pages/home.css"/>
</jsp:include>

<main class="main home-preview">
    <div class="container mt-5">
        <h1>Добро пожаловать на страницу информации ЖКХ</h1>
        <p>Здесь вы найдете всю необходимую информацию о Жилищно-коммунальном хозяйстве</p>
        <div class="header__logo title-semi-32" id="logo">
            <img class="header__logo_img" src="${pageContext.request.contextPath}/static/img/home_preview.jpg" alt="logo"/>
            Housings
        </div>
    </div>
</main>

<script>const onLoadPage = () => {
}</script>
<jsp:include page="blocks/footer.jsp"/>
