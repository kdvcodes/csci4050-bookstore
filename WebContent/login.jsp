<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.Cookie" %>
<html lang="en">
<head>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	<meta charset="UTF-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<link rel="stylesheet" href="style.css">
  	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
  	<link rel="stylesheet" href="style.css">
  	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  	<title>Login</title>
  	<style type="text/css">
  		.imgcontainer {
		  text-align: center;
		  margin: 24px 0 12px 0;
		}
  	</style>
</head>
<body>
<%
// String userName = null;
// Cookie[] cookies = request.getCookies();
// if(cookies !=null){
// for(Cookie cookie : cookies){
// 	if(cookie.getName().equals("user")) userName = cookie.getValue();
// }
// }
// if(userName == null) response.sendRedirect("login.jsp");
%>
<nav class="navbar navbar-expand-md navbar-dark">
    <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
    	<div class="navbar-header">
	  		<a  class="navbar-brand" href="index.jsp">IMA Bookstore</a>
	  	</div>
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a href="index.jsp" class="nav-link">Home</a>
            </li>
            <li class="nav-item">
               <a href="books.jsp" class="nav-link">Shop</a>
            </li>
            <li class="nav-item">
                <a href="shoppingCart.jsp" class="nav-link">Cart</a>
            </li>
        </ul>
    </div>
    
<!--     <div class="navbar-collapse collapse w-100 order-3 dual-collapse2"> -->
<!--         <ul class="navbar-nav ml-auto"> -->
<!--             <li class="nav-item"> -->
<!--             	<div class="container"> -->
<!-- 				     <form class="form-inline" id="search" method="post" action="search"> -->
<!-- 				        <div class="input-group" id="dropdown"> -->
				            
<!-- 				            <div class="btn-group"> -->
<!-- 							    <button type="button" class="form-control btn btn-default dropdown-toggle filterToggle" data-toggle="dropdown">
<!-- 							        Filter By <span class="caret"></span> -->
<!-- 							    </button> -->
<!-- 							     <div class="dropdown-menu" id="bookFilter" aria-labelledby="dropdownMenuButton"> -->
<!-- 					                <select class="custom-select d-block w-100" id="searchCategory" name="searchCategory" > -->
<!-- 	                                    <option value="title">Title</option> -->
<!-- 										<option value="author">Author</option> -->
<!-- 										<option value="isbn">ISBN</option> -->
<!-- 									</select> -->
<!-- 					             </div> -->
<!-- 							</div> -->
<!-- 							</div> -->
<!-- 				        </div> -->
<!-- 				        <div class="input-group" id="search"> -->
<!-- 				            <input type = "text" class="form-control" placeholder="Search Books" name="Search"> -->
<!-- 				            <div class="input-group-btn"> -->
<!-- 				                <button class="btn btn-primary" type="submit" form="search">Search</button> -->
<!-- 				            </div> -->
<!-- 				        </div> -->
<!-- 				    </form> -->
<!-- 				</div> -->
            
            <li class="nav-item">
                <div class="dropdown">
			      <button class="btn dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			        Account
			      </button>
			      <div class="dropdown-menu dropdown-menu-right">
			        <a class="dropdown-item" href="login.jsp">Sign In</a>
			        <a class="dropdown-item" href="register.jsp">Register</a>
			        <a class="dropdown-item" href="message.jsp">Message</a>
			        <a class="dropdown-item" href="editProfile.jsp">Edit Profile</a>
			        <a class="dropdown-item" href="orderHistory.jsp">Orders</a>
			        <a class="dropdown-item" href="logout">Log Out</a>
			      </div>
			    </div>
            </li>
        </ul>
    </div>
</nav>
    
    

<script>
	$(document).ready(function(e) {   
		$('#bookFilter a').on('click', function(){    
		    $('.filterToggle').html($(this).html() + '<span class="caret"></span>');    
		});
	});
</script>


 <div class="px-4 px-lg-0">
    <!-- For demo purpose -->
    <div class="container text-white py-5 text-center">
        <h1 class="display-4">Sign In</h1>
    </div>

    <div class="pb-5">
        <div class="container col-md-4">
            <div class="row">
                <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">
					<div class="error-message">${emailError}</div>
					<div class="error-message">${passwordError}</div>
					<div class="error-message">${activationError}</div>
                    <form id="login" class="needs-validation" method="post" action="login">

                        <div class="mb-3">
                            <label for="email">Email </label>
                            <input type="email" class="form-control" id="email" name="loginEmail" placeholder="you@example.com">
                            <div class="invalid-feedback"> Please enter a valid email address for shipping updates. </div>
                        </div>

                        <div class="mb-3">
                            <label for="password">Password </label>
                            <input type="password" class="form-control" name="loginPassword" id="password">
                            <div class="invalid-feedback"> Please enter a valid password</div>
                        </div>
                        
                         <div class="mb-3">
                             <a href="forgotPassword.jsp">Forgot password?</a>
                        </div>
                        
                        
                        
                        <button class="btn btn-primary btn-lg btn-block" type="submit" form="login">Sign In</button>
                     

                   	</form>
           		</div>
           	</div>
       </div>
   </div>
</div>

</body>
</html>