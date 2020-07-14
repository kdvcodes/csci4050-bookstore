<%--
  Created by IntelliJ IDEA.
  User: kdv
  Date: 7/2/20
  Time: 5:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
  	<title>IMA Bookstore</title>
</head>
<body>
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
                <a href="editProfile.jsp" class="nav-link">Profile</a>
            </li>
            <li class="nav-item">
               <a href="books.jsp" class="nav-link">Shop</a>
            </li>
            <li class="nav-item">
                <a href="shoppingCart.jsp" class="nav-link">Cart</a>
            </li>
        </ul>
    </div>
    
    <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
            	<div class="container">
				    <form class="form-inline">
				        <div class="input-group" id="dropdown">
				            
				            <div class="btn-group">
							    <button type="button" class="form-control btn btn-default dropdown-toggle filterToggle" data-toggle="dropdown">
							        Filter By <span class="caret"></span>
							    </button>
							    <div class="dropdown-menu" id="bookFilter" aria-labelledby="dropdownMenuButton">
					                <a class="dropdown-item" href="#">Title</a>
					                <a class="dropdown-item" href="#">Author</a>
					                <a class="dropdown-item" href="#">ISBN</a>
					            </div>
							</div>
				        </div>
				        <div class="input-group" id="search">
				            <input type = "text" class="form-control" placeholder="Search Books" name="Search">
				            <div class="input-group-btn">
				                <button class="btn btn-primary" type="submit">Submit</button>
				            </div>
				        </div>
				    </form>
				</div>
            </li>
            <li class="nav-item">
                <div class="dropdown">
			      <button class="btn dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			        Sign In
			      </button>
			      <div class="dropdown-menu dropdown-menu-right">
			        <form class="px-4 py-3">
			          <div class="form-group">
			            <label for="exampleDropdownFormEmail1">Email address</label>
			            <input type="email" class="form-control" id="exampleDropdownFormEmail1" placeholder="email@example.com">
			          </div>
			          <div class="form-group">
			            <label for="exampleDropdownFormPassword1">Password</label>
			            <input type="password" class="form-control" id="exampleDropdownFormPassword1" placeholder="Password">
			          </div>
			          <div class="form-group">
			            <div class="form-check">
			              <input type="checkbox" class="form-check-input" id="dropdownCheck">
			              <label class="form-check-label" for="dropdownCheck">
			                Remember me
			              </label>
			            </div>
			          </div>
			          <button type="submit" class="btn btn-primary">Sign in</button>
			        </form>
			        <div class="dropdown-divider"></div>
			        <a class="dropdown-item" href="register.jsp">Don't have an account? Sign up</a>
			        <a class="dropdown-item" href="forgotPassword.jsp">Forgot password?</a>
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
        <h1 class="display-4">Registration</h1>
    </div>

    <div class="pb-5">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

                    <form class="needs-validation" novalidate="" method="POST" action="register">
                    
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="firstName">First name<span class="required">*</span></label>
                                <input type="text" class="form-control" name="firstName" id="firstName" placeholder="" value="" required="">
                                <div class="invalid-feedback"> Valid first name is required. </div>

                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="lastName">Last name<span class="required">*</span></label>
                                <input type="text" class="form-control" name="lastName" id="lastName" placeholder="" value="" required="">
                                <div class="invalid-feedback"> Valid last name is required. </div>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="email">Email<span class="required">*</span></label>
                            <input type="email" class="form-control" id="email" name="email" placeholder="you@example.com">
                            <div class="invalid-feedback"> Please enter a valid email address for shipping updates. </div>
                        </div>

                        <div class="mb-3">
                            <label for="password">Password<span class="required">*</span></label>
                            <input type="password" class="form-control" name="password" id="password">
                            <div class="invalid-feedback"> Please enter a valid password</div>
                        </div>

                        <div class="mb-3">
                            <label for="password">Confirm Password<span class="required">*</span></label>
                            <input type="password" class="form-control" name="confirm" id="passwordConfirmation">
                            <div class="invalid-feedback"> Please enter a valid password</div>
                        </div>

                        <hr class="mb-4">
                        
                        <p aria-hidden="true" id="required-description"><span class="required">*</span>Required field</p>

                        <hr class="mb-4">
                        
                
                        <div class="mb-3">
                            <label for="address">Address</label>
                            <input type="text" class="form-control" id="address" name="address" placeholder="1234 Main St">
                        </div>
                        <div class="row">
                            <div class="col-md-5 mb-3">
                                <label for="city">City</label>
                                <input type="text" class="form-control" id="city" name="city" placeholder="Columbus">
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="state">State</label>
                                <select class="custom-select d-block w-100" id="state" name="state" >
                                    <option value="">Choose...</option>
                                    <option>Georgia</option>
                                </select>

                            </div>
                            <div class="col-md-3 mb-3">
                                <label for="zip">Zip</label>
                                <input type="text" class="form-control" id="zip" name="zip" placeholder="" required="">

                            </div>
                        </div>
                        <div class="d-block my-3">
                            <div class="custom-control custom-radio">
                                <input id="credit" name="paymentMethod" type="radio" class="custom-control-input" checked="" >
                                <label class="custom-control-label" for="credit">Credit card</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input id="debit" name="paymentMethod" type="radio" class="custom-control-input" >
                                <label class="custom-control-label" for="debit">Debit card</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="cc-name">Name on card</label>
                                <input type="text" class="form-control" id="cc-name" name="fullname" placeholder="" >
                                <small class="text-muted">Full name as displayed on card</small>

                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="cc-number">Credit card number</label>
                                <input type="text" class="form-control" id="cc-number" name="cardNum" placeholder="" >

                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3 mb-3">
                                <label for="cc-expiration">Expiration</label>
                                <input type="text" class="form-control" id="cc-expiration" name="expiration" placeholder="" >

                            </div>
                            <div class="col-md-3 mb-3">
                                <label for="cc-cvv">CVV</label>
                                <input type="text" class="form-control" id="cc-cvv" name="cvv" placeholder="" >

                            </div>
                            
                        </div>
                       
                            
                       	<div>
						  <input type="hidden" name="promotion" value="" />
						  <input type="checkbox" name="promotion" checked="checked" />
						  Subscribe to receive promotional emails!
						</div>

                         
                        
                        <hr class="mb-4">
                        <button class="btn btn-primary btn-lg btn-block" type="submit" form="register">Register</button>
                        <hr class="mb-4">
                    </form>
                    <!-- End -->
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>