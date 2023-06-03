<jsp:include page="blocks/header.jsp">
    <jsp:param name="title" value="My App. About"/>
    <jsp:param name="pageStyle" value="${pageContext.request.contextPath}/static/styles/pages/about.css"/>
</jsp:include>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<div class="container">
    <div class="row">
        <div class="col-lg-6">
            <h2 class="section-title">About My App</h2>
            <p class="section-text">We are a team of experienced repair technicians dedicated to providing high-quality repairs for all types of devices, from smartphones to laptops and more. With years of experience under our belts, we have the knowledge and expertise to diagnose and fix any issue you may be experiencing with your device.</p>
        </div>
    </div>
</div>

<script>const onLoadPage = () => {
}</script>
<jsp:include page="blocks/footer.jsp"/>

