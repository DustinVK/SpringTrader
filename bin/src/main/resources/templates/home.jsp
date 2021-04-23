<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">
<head>
  <meta name="description" content="Webpage description goes here" />
  <meta charset="utf-8">
  <title>Spring Trader</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="author" content="">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
   <!-- Bootstrap core CSS -->
      
     <link th:href="@{./vendor/bootstrap/css/bootstrap.min.css}" rel="stylesheet" />
     <link th:href="@{./css/style.css}" rel="stylesheet" />
     <link rel="stylesheet" type="text/css" th:href="@{http://localhost:8080/PaperTrader/css/style.css}"/>
      <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
   <script type="text/javascript" th:src="@{./vendor/bootstrap/js/bootstrap.bundle.min.js}"></script>
    <script type="text/javascript" th:src="@{/js/scripts.js}"></script>

</head>

<body>
<!--Navigation bar-->
<div id="nav-placeholder">
</div>
<!--end of Navigation bar-->
<div sec:authorize="isAuthenticated()">
  This content is only shown to authenticated users.
</div>
<div sec:authorize="hasRole('ROLE_ADMIN')">
  This content is only shown to administrators.
</div>
<div sec:authorize="hasRole('ROLE_USER')">
  This content is only shown to users.
</div>
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header border-bottom-0">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="form-title text-center">
          <h3>Log In</h3>
        </div>
        <div class="d-flex flex-column text-center">
          <form>
            <div class="form-group">
              <input type="text" class="form-control" id="username"placeholder="Your username...">
            </div>
            <div class="form-group">
              <input type="password" class="form-control" id="password" placeholder="Your password...">
            </div>
            <button type="button" class="btn btn-info btn-block btn-round" onclick="login()">Log In</button>
          </form>
          <div class="modal-footer d-flex justify-content-center">
        <div class="signup-section">Not a member yet? <a href="#a" class="text-info"> Sign Up</a>.</div>
       
      	</div>
      	 <div id="login-message"></div>
     
        </div>
      </div>
    </div>
      
  </div>
</div>

<div id="content-placeholder">
</div>
 
<div id="pageBody" class="container">
 
</div>
<div id=script-placeholder>
</div>
</body>
</html>