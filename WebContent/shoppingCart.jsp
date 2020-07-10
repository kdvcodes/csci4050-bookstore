<%--
  Created by IntelliJ IDEA.
  User: kdv
  Date: 7/2/20
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.css"
          rel="stylesheet"  type='text/css'>
    <title>IMA Bookstore</title>
</head>
<body>
<nav class="navbar  navbar-expand-sm">
    <div class="container-fluid">
        <a id="branding" class="navbar-brand" href="index.jsp">IMA Bookstore</a>
        <ul class="navbar-nav mr-auto">
            <li class="nav-item"><a href="index.jsp" class="nav-link">Home</a></li>
            <li class="nav-item"><a href="editProfile.jsp" class="nav-link">Profile</a></li>
            <li class="nav-item"><a href="books.jsp" class="nav-link">Shop</a></li>
            <li class="nav-item"><a href="checkout.jsp" class="nav-link">Checkout</a></li>
        </ul>
        <form class="form-inline ml auto">
	<div class="container">
    <div class="row">    
        <div class="col-xs-8 col-xs-offset-2">
		    <div class="input-group">
                <div class="input-group-btn search-panel">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                      <span id="search_concept"><span class="glyphicon glyphicon-align-justify"></span> All</span>  <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                      <li><a href="#contains"> <span class="glyphicon glyphicon-envelope text-danger"></span>Title</a></li>
                      <li><a href="#its_equal"> <span class="glyphicon glyphicon-music text-warning"></span>Author</a></li>
                      <li><a href="#greather_than"> <span class="glyphicon glyphicon-user text-success"></span>Year</a></li>
                    </ul>
                </div>
                <input type="hidden" name="search_param" value="all" id="search_param">         
                <input type="text" class="form-control" name="x" placeholder="Search term...">
                <span class="input-group-btn">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </span>
            </div>
        </div>
	</div>
</div>
    </form>

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
    </div>
</nav>

<div class="px-4 px-lg-0">
    <!-- For demo purpose -->
    <div class="container text-white py-5 text-center">
        <h1 class="display-4">Shopping Cart</h1>
    </div>

    <div class="pb-5">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

                    <!-- Shopping cart table -->
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col" class="border-0 bg-light">
                                    <div class="p-2 px-3 text-uppercase">Product</div>
                                </th>
                                <th scope="col" class="border-0 bg-light">
                                    <div class="py-2 text-uppercase">Price</div>
                                </th>
                                <th scope="col" class="border-0 bg-light">
                                    <div class="py-2 text-uppercase">Quantity</div>
                                </th>
                                <th scope="col" class="border-0 bg-light">
                                    <div class="py-2 text-uppercase">Remove</div>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <th scope="row" class="border-0">
                                    <div class="p-2">
                                        <img src="images/harry1.jpeg" alt="hello" width="70" class="img-fluid rounded shadow-sm">
                                        <div class="ml-3 d-inline-block align-middle">
                                            <h5 class="mb-0"> <a href="#" class="text-dark d-inline-block align-middle">Harry Potter and the Sorcerer's Stone</a></h5><span class="text-muted font-weight-normal font-italic d-block"></span>
                                        </div>
                                    </div>
                                </th>
                                <td class="border-0 align-middle"><strong>$49.00</strong></td>
                                <td class="border-0 align-middle">
                                    <strong>
                                        <div class="def-number-input number-input safari_only mb-0 w-100">
                                            <input class="quantity" min="0" name="quantity" value="1" type="number">
                                        </div>
                                    </strong>
                                </td>
                                <td class="border-0 align-middle"><a href="#" class="text-dark"><i class="fa fa-trash"></i></a></td>
                            </tr>
                            <tr>
                                <th scope="row">
                                    <div class="p-2">
                                        <img src="images/harry2.jpg" alt="" width="70" class="img-fluid rounded shadow-sm">
                                        <div class="ml-3 d-inline-block align-middle">
                                            <h5 class="mb-0"><a href="#" class="text-dark d-inline-block">Harry Potter and the Chamber of Secrets</a></h5><span class="text-muted font-weight-normal font-italic"></span>
                                        </div>
                                    </div>
                                </th>
                                <td class="align-middle"><strong>$29.00</strong></td>
                                <td class="align-middle">
                                    <strong>
                                        <div class="def-number-input number-input safari_only mb-0 w-100">
                                            <input class="quantity" min="0" name="quantity" value="1" type="number">
                                        </div>
                                    </strong>
                                </td>
                                <td class="align-middle"><a href="#" class="text-dark"><i class="fa fa-trash"></i></a>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">
                                    <div class="p-2">
                                        <img src="images/harry3.jpg" alt="" width="70" class="img-fluid rounded shadow-sm">
                                        <div class="ml-3 d-inline-block align-middle">
                                            <h5 class="mb-0"> <a href="#" class="text-dark d-inline-block">Harry Potter and the Prisoner of Azkaban</a></h5><span class="text-muted font-weight-normal font-italic"></span>
                                        </div>
                                    </div>
                                <td class="align-middle"><strong>$16.00</strong></td>
                                <td class="align-middle">
                                    <strong>
                                        <div class="def-number-input number-input safari_only mb-0 w-100">
                                            <input class="quantity" min="0" name="quantity" value="1" type="number">
                                        </div>

                                    </strong>
                                </td>
                                <td class="align-middle"><a href="#" class="text-dark"><i class="fa fa-trash"></i></a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <!-- End -->
                </div>
            </div>

            <div class="row py-5 p-4 bg-white rounded shadow-sm">
                <div class="col-lg-6">
                    <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Coupon code</div>
                    <div class="p-4">
                        <p class="font-italic mb-4">If you have a coupon code, please enter it in the box below</p>
                        <div class="input-group mb-4 border rounded-pill p-2">
                            <input type="text" placeholder="Apply coupon" aria-describedby="button-addon3" class="form-control border-0">
                            <div class="input-group-append border-0">
                                <button id="button-addon3" type="button" class="btn btn-dark px-4 rounded-pill"><i class="fa fa-gift mr-2"></i>Apply coupon</button>
                            </div>
                        </div>
                    </div>
                    <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Instructions for seller</div>
                    <div class="p-4">
                        <p class="font-italic mb-4">If you have some information for the seller you can leave them in the box below</p>
                        <textarea name="" cols="30" rows="2" class="form-control"></textarea>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Order summary </div>
                    <div class="p-4">
                        <p class="font-italic mb-4">Shipping and additional costs are calculated based on values you have entered.</p>
                        <ul class="list-unstyled mb-4">
                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Order Subtotal </strong><strong>$390.00</strong></li>
                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Shipping and handling</strong><strong>$10.00</strong></li>
                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Tax</strong><strong>$0.00</strong></li>
                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total</strong>
                                <h5 class="font-weight-bold">$400.00</h5>
                            </li>
                        </ul><a href="#" class="btn btn-dark rounded-pill py-2 btn-block">Procceed to checkout</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>