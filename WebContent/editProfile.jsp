<%--
  Created by IntelliJ IDEA.
  User: kdv
  Date: 7/2/20
  Time: 5:18 PM
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
  	<title>Edit Profile</title>
</head>
<body>
<jsp:include page="/editProfile"></jsp:include>
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
    
    <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
            	<div class="container">
				    <form class="form-inline" id="search" method="post" action="search">
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
				                <button class="btn btn-primary" type="submit" form="search">Submit</button>
				            </div>
				        </div>
				    </form>
				</div>
            </li>
            
            <li class="nav-item">
                <div class="dropdown">
			      <button class="btn dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			        Account
			      </button>
			      <div class="dropdown-menu dropdown-menu-right">
			        <a class="dropdown-item" href="login.jsp">Sign In</a>
			        <a class="dropdown-item" href="register.jsp">Don't have an account? Sign up</a>
			        <a class="dropdown-item" href="message.jsp">Message</a>
			        <a class="dropdown-item" href="editProfile.jsp">Edit Profile</a>
			        <a class="dropdown-item" href="adminHome.jsp">Admin</a>
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
        <h1 class="display-4">Edit Profile</h1>
    </div>

    <div class="pb-5">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

                    <div class="col-md-12 personal-info">
                    <%
						String userName = null;
						Cookie[] cookies = request.getCookies();
						if(cookies !=null){
							for(Cookie cookie : cookies){
								if(cookie.getName().equals("user")) userName = cookie.getValue();
							} // for
						} // if
						System.out.println("MESSAGE BOARD:: Current User Name; " + userName);
						if(userName != null) {
							out.println("Logged in as <b>" + userName + "</b>");
						} // if
					%>
                    <hr>
                        <form class="form-horizontal" role="form" method="post">
                        <div class="error-message">${passwordMismatchError}</div>
                        	<h3>Personal Information</h3>
                        	
                        	
                            <div class="form-group">
                                <label class="col-lg-3 control-label">First name</label>
                                <div class="col-lg-12">
                                    <input class="form-control" name="firstName" type="text" value="Jane">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Last name</label>
                                <div class="col-lg-12">
                                    <input class="form-control" name="lastName" type="text" value="Bishop">
                                </div>
                            </div>
                            
                            <h3>Address Information</h3>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Street</label>
                                <div class="col-lg-12">
                                    <input class="form-control" name="street" type="text" value="123 Main St">
                                </div>
                            </div>
                            <div class="form-group row">
                            <div class="col-md-5 mb-3">
                                <label for="city">City</label>
                                <input type="text" class="form-control" name="city" id="city" placeholder="Columbus">
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="state">State</label>
                                <select class="custom-select d-block w-100" id="state" name="state" >
                                    <option value="">Choose...</option>
									<option value="AL">Alabama</option>
									<option value="AK">Alaska</option>
									<option value="AZ">Arizona</option>
									<option value="AR">Arkansas</option>
									<option value="CA">California</option>
									<option value="CO">Colorado</option>
									<option value="CT">Connecticut</option>
									<option value="DE">Delaware</option>
									<option value="DC">District Of Columbia</option>
									<option value="FL">Florida</option>
									<option value="GA">Georgia</option>
									<option value="HI">Hawaii</option>
									<option value="ID">Idaho</option>
									<option value="IL">Illinois</option>
									<option value="IN">Indiana</option>
									<option value="IA">Iowa</option>
									<option value="KS">Kansas</option>
									<option value="KY">Kentucky</option>
									<option value="LA">Louisiana</option>
									<option value="ME">Maine</option>
									<option value="MD">Maryland</option>
									<option value="MA">Massachusetts</option>
									<option value="MI">Michigan</option>
									<option value="MN">Minnesota</option>
									<option value="MS">Mississippi</option>
									<option value="MO">Missouri</option>
									<option value="MT">Montana</option>
									<option value="NE">Nebraska</option>
									<option value="NV">Nevada</option>
									<option value="NH">New Hampshire</option>
									<option value="NJ">New Jersey</option>
									<option value="NM">New Mexico</option>
									<option value="NY">New York</option>
									<option value="NC">North Carolina</option>
									<option value="ND">North Dakota</option>
									<option value="OH">Ohio</option>
									<option value="OK">Oklahoma</option>
									<option value="OR">Oregon</option>
									<option value="PA">Pennsylvania</option>
									<option value="RI">Rhode Island</option>
									<option value="SC">South Carolina</option>
									<option value="SD">South Dakota</option>
									<option value="TN">Tennessee</option>
									<option value="TX">Texas</option>
									<option value="UT">Utah</option>
									<option value="VT">Vermont</option>
									<option value="VA">Virginia</option>
									<option value="WA">Washington</option>
									<option value="WV">West Virginia</option>
									<option value="WI">Wisconsin</option>
									<option value="WY">Wyoming</option>
									</select>

                            </div>
                            <div class="col-md-3 mb-3">
                                <label for="zip">Zip</label>
                                <input type="text" class="form-control" id="zip" name="zip" placeholder="" required="">

                            </div>
                        </div>
                            
                            <h3>Payment Information</h3>
                            <div class="form-group d-block my-3">
	                            <div class="custom-control custom-radio">
	                                <input id="credit" name="paymentMethod" type="radio" class="custom-control-input" checked="" value="credit">
	                                <label class="custom-control-label" for="credit">Credit card</label>
	                            </div>
	                            <div class="custom-control custom-radio">
	                                <input id="debit" name="paymentMethod" type="radio" class="custom-control-input" value="debit">
	                                <label class="custom-control-label" for="debit">Debit card</label>
	                            </div>
                       		</div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Name on Card</label>
                                <div class="col-lg-12">
                                    <input class="form-control" name="nameOnCard" type="text" value="Jane Bishop">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Credit Card Number</label>
                                <div class="col-lg-12">
                                    <input class="form-control" name="cardNumber" type="text" value="************">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Expiration</label>
                                <div class="col-lg-12">
                                    <input class="form-control" name="expiration" type="text" value="6/2025">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">CVV</label>
                                <div class="col-lg-12">
                                    <input class="form-control" name="cvv" type="text" value="***">
                                </div>
                            </div>
                            
                            <h3>Password Information</h3>
                            <div class="form-group">
                                <label class="col-md-3 control-label">New Password</label>
                                <div class="col-md-12">
                                    <input class="form-control" type="password" value="" name="newPassword">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Confirm New Password</label>
                                <div class="col-md-12">
                                    <input class="form-control" type="password" value="" name="newPasswordConfirm">
                                </div>
                            </div>
                            
                            <h3>Confirm Current Password <span class="required">*</span></h3>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Current Password</label>
                                <div class="col-md-12">
                                    <input class="form-control" type="password" value="" name="currentPasswordConfirm">
                                </div>
                            </div>
                            
                            <div class="form-group">
                            	<div class="col-lg-12">
								  <input type="hidden" name="promotion" />
								  <input type="checkbox" name="promotion" value="1"/>
								   Subscribe to receive promotional emails!
								</div>
                            </div>  

							<br>
							<span class="required">*</span> You must confirm your current password in order to make changes!
                            <div class="form-group">
                                <label class="col-md-3 control-label"></label>
                                <div class="col-md-12">
                                    <input type="submit" class="btn btn-primary" value="Save Changes">
                                </div>
                            </div>
                                                   
                        </form>



                    </div>
                </div>
                <!-- End -->
            </div>
        </div>
    </div>
</div>

</body>
</html>