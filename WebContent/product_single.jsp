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
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
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
            <li class="nav-item"><a href="shoppingCart.jsp" class="nav-link">Cart</a></li>
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

<section class="product-sec">
    <div class="container col-md-6">
        <div class="py-5">
            <h1>Harry Potter and the Sorcerer's Stone</h1>
        </div>
        <div class="row">
            <div class="col-md-4">
                <img src="images/harry2.jpg" alt="book" width="200" height="300">
            </div>


            <div class="col-md-8">
                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's printer took a galley of type and Scrambled it to make a type and typesetting industry. Lorem Ipsum has been the book. </p>
                <p>t has survived not only fiveLorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's printer took a galley of type and</p>

                <div class="btn-sec">
                    <button>Add To Cart</button>
                    <button>Buy Now</button>
                </div>
            </div>
        </div>
    </div>
</section>






<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>