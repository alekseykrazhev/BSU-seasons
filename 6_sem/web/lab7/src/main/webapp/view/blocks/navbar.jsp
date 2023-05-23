<head>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css">
</head>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Housings App</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <a href="${pageContext.request.contextPath}/?command=workplans" class="nav-link">WorkPlans</a>
        </li>
        <li class="nav-item">
          <a href="${pageContext.request.contextPath}/?command=applications" class="nav-link">Applications</a>
        </li>
        <li class="nav-item">
          <a href="${pageContext.request.contextPath}/?command=about" class="nav-link">About</a>
        </li>
        <li class="nav-item">
          <a href="${pageContext.request.contextPath}/?command=authorization" class="nav-link">Sign In</a>
        </li>
        <li class="nav-item">
          <a href="https://github.com/alekseykrazhev" class="nav-link">Github</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
