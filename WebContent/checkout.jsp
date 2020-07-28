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
  	<title>Checkout</title>
</head>
<body>
<jsp:include page="/checkout"></jsp:include>
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
				                <button class="btn btn-primary" type="submit" form="search">Search</button>
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
			        <a class="dropdown-item" href="register.jsp">Register</a>
			        <a class="dropdown-item" href="message.jsp">Message</a>
			        <a class="dropdown-item" href="editProfile.jsp">Edit Profile</a>
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
        <h1 class="display-4">Checkout</h1>
    </div>
    <div class="pb-5">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">
                    <div class="row">
                        <div class="col-md-4 order-md-2 mb-4">
                            <h4 class="d-flex justify-content-between align-items-center mb-3">
                                <span class="text-muted">Order Summary</span>
                            </h4>
                            
                          
                            <ul class="list-group mb-3 sticky-top">
                                <li class="list-group-item d-flex justify-content-between lh-condensed">
                                    <div>
                                        <h6 class="my-0">Order Subtotal</h6>
                                    </div>
                                    <span class="text-muted">$${totalPrice}</span>
                                </li>
                                <li class="list-group-item d-flex justify-content-between lh-condensed">
                                    <div>
                                        <h6 class="my-0">Shipping and handling</h6>
                                    </div>
                                    <span class="text-muted">$${shipping}</span>
                                </li>
                                <li class="list-group-item d-flex justify-content-between lh-condensed">
                                    <div>
                                        <h6 class="my-0">Tax</h6>
                                    </div>
                                    <span class="text-muted">$${tax}</span>
                                </li>
                                <li class="list-group-item d-flex justify-content-between bg-light">
                                    <div class="text-success">
                                        <h6 class="my-0">Promo code</h6>
                                        <small>${promoCode}</small>
                                    </div>
                                    <span class="text-success">-$${promoAmount}</span>
                                </li>
                                <li class="list-group-item d-flex justify-content-between">
                                    <span>Total (USD)</span>
                                    <strong>$${totalOrder}</strong>
                                </li>
                            </ul>
                            <form class="card p-2" id="addPromo" action="applyPromo?userId=${userId}&totalPrice=${totalPrice}&shipping=${shipping}&tax=${tax}" method="post">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Promo Code" name="promoCodeInput">
                                    <div class="input-group-append">
                                        <button type="submit" class="btn btn-secondary" form="addPromo">Redeem</button>
                                    </div>
                                </div>
                                ${promoError}
                            </form>
                        </div>
                        
                        
                <div class="col-md-8 order-md-1">
                <p>${addressExist}</p>
                <p>${paymentExist}</p>
                            <h4 class="mb-3">Billing Address</h4>
                            <form id="checkout" class="needs-validation" novalidate="" action="checkout" method="post">
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="firstName">First name</label>
                                        <input type="text" class="form-control" name="firstName" id="firstName" placeholder="${firstName}" value="" required="">
                                        <div class="invalid-feedback"> Valid first name is required. </div>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="lastName">Last name</label>
                                        <input type="text" class="form-control" name="lastName" id="lastName" placeholder="${lastName}" value="" required="">
                                        <div class="invalid-feedback"> Valid last name is required. </div>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <label for="street">Street</label>
                                    <input class="form-control" name="street" type="text" placeholder="${street}">
                                    <div class="invalid-feedback"> Please enter your shipping address. </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-5 mb-3">
                                        <label for="city">City</label>
                                         <input type="text" class="form-control" name="city" id="city" placeholder="${city}">
                                    </div>
                                    <div class="col-md-4 mb-3">
                                        <label for="state">State</label>
                                        <select class="custom-select d-block w-100" name="state" id="state" required="">
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
                                        <div class="invalid-feedback"> Please provide a valid state. </div>
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label for="zip">Zip</label>
                                        <input type="text" class="form-control" name="zip" id="zip" placeholder="${zip}" required="">
                                        <div class="invalid-feedback"> Zip code required. </div>
                                    </div>
                                </div>
<!--                                 <hr class="mb-4"> -->
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" name="newAddress" id="same-address" ${addressExistCheck} value="newAddr">
                                    <label class="custom-control-label" for="same-address">Use saved address for shipping and billing</label>
                                </div>
                                <hr class="mb-4">
                                <h4 class="mb-3">Payment</h4>
                                <div class="d-block my-3">
                                    <div class="custom-control custom-radio">
		                                <input id="credit" name="paymentMethod" type="radio" class="custom-control-input" checked="" value="credit">
		                                <label class="custom-control-label" for="credit">Credit card</label>
		                            </div>
		                            <div class="custom-control custom-radio">
		                                <input id="debit" name="paymentMethod" type="radio" class="custom-control-input" value="debit">
		                                <label class="custom-control-label" for="debit">Debit card</label>
		                            </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="cc-name">Name on card</label>
                                        <input type="text" class="form-control" name="nameOnCard" id="cc-name" placeholder="${cardName}" required="">
                                        <small class="text-muted">Full name as displayed on card</small>
                                        <div class="invalid-feedback"> Name on card is required </div>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="cc-number">Credit Card number</label>
                                        <input type="text" class="form-control" name="cardNumber" id="cc-number" placeholder="${cardNumber}" required="">
                                        <div class="invalid-feedback"> Credit card number is required </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-3 mb-3">
                                        <label for="cc-expiration">Expiration</label>
                                        <input type="text" class="form-control" name="expiration" id="cc-expiration" placeholder="${cardExp}" required="">
                                        <div class="invalid-feedback"> Expiration date required </div>
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label for="cc-cvv">CVV</label>
                                        <input type="text" class="form-control" name="cvv" id="cc-cvv" placeholder="${cardCVV}" required="">
                                        <div class="invalid-feedback"> Security code required </div>
                                    </div>
                                </div>
                                
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" name="newPayment" id="same-payment" ${paymentExistCheck} value="newPmt">
                                    <label class="custom-control-label" for="same-payment">Use saved payment method</label>
                                </div>
                                
                                <hr class="mb-4">
                                <button class="btn btn-primary btn-lg btn-block" type="submit" form="checkout">Checkout</button>
                            </form>
                        </div>
                    </div>
                    <footer class="my-5 pt-5 text-muted text-center text-small">
                        <ul class="list-inline">
                            <li class="list-inline-item"><a href="#">Privacy</a></li>
                            <li class="list-inline-item"><a href="#">Terms</a></li>
                            <li class="list-inline-item"><a href="#">Support</a></li>
                        </ul>
                    </footer>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>