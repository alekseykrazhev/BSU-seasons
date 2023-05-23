<jsp:include page="blocks/header.jsp">
    <jsp:param name="title" value="Housings. About"/>
    <jsp:param name="pageStyle" value="${pageContext.request.contextPath}/static/styles/pages/about.css"/>
</jsp:include>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<div class="container">
    <div class="row">
        <div class="col-lg-6">
            <h2 class="section-title">About Housings App</h2>
            <p class="section-text">Welcome to Housings App, your go-to platform for finding your dream home.
                We provide a wide range of housing options to suit your needs, whether you're looking to buy or
                rent. Our team of experts is dedicated to helping you find the perfect property and making your
                housing journey a seamless experience.</p>
        </div>
    </div>
</div>

<script>const onLoadPage = () => {
}</script>
<jsp:include page="blocks/footer.jsp"/>

