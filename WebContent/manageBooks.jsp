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
  	<title>Manage Books</title>
</head>
<body>
  <nav class="navbar navbar-expand-md navbar-dark">
    <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
    	<div class="navbar-header">
	  		<a  class="navbar-brand" href="index.jsp">IMA Bookstore</a>
	  	</div>
      
    </div>
    
    <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
        <ul class="navbar-nav ml-auto">
            
            <li class="nav-item">
                <div class="dropdown">
			      <button class="btn dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			        Account
			      </button>
			      <div class="dropdown-menu dropdown-menu-right">
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
        <h1 class="display-4">Add Book</h1>
    </div>


	
	<div class="pb-5">
        <div class="container col-lg-6">
            <div class="row">
    <!-- edit form column -->
			    <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">
			        <form id="manage" class="form-horizontal" role="form" method="post" action="manage">
			        	<div class="form-group">
			                <label class="col-lg-3 control-label">Cover</label>
			                <div class="col-lg-8">
			                    <input type="file" id="cover_picture" name="cover_picture" accept="image"><br>
			                </div>
			            </div>
			            
			            <div class="form-group">
			                <label class="col-md-3 control-label">Genre</label>
			                <div class="col-md-8">
			                    <select id="cars" name="cars">
								  <option value="volvo">Fiction</option>
								  <option value="saab">Non-Fiction</option>
								</select>
			                </div>
			            </div>
			            
			            <div class="form-group">
			                <label class="col-lg-3 control-label">ISBN</label>
			                <div class="col-lg-12">
			                    <input class="form-control" type="text" name="isbn">
			                </div>
			            </div>
			            <div class="form-group">
			                <label class="col-lg-3 control-label">Title</label>
			                <div class="col-lg-12">
			                    <input class="form-control" type="text" name="title">
			                </div>
			            </div>
			            
			
			            <div class="form-group">
			                <label class="col-lg-3 control-label">Author</label>
			                <div class="col-lg-12">
			                    <input class="form-control" type="text" name="author">
			                </div>
			            </div>
			            
			            <div class="form-group">
			                <label class="col-lg-3 control-label">Publication Year</label>
			                <div class="col-lg-12">
			                    <input class="form-control" type="text" name="year">
			                </div>
			            </div>
			            
			             <div class="form-group">
			                <label class="col-lg-3 control-label">Price</label>
			                <div class="col-lg-12">
			                    <input class="form-control" type="text" name="price">
			                </div>
			            </div>
			
			            
			            <div class="form-group">
			                <label class="col-md-3 control-label">Quantity in Stock</label>
			                <div class="col-md-12">
			                    <input class="form-control" type="password" name="quantity">
			                </div>
			            </div>
			           
			            <div class="form-group">
			                <label class="col-lg-3 control-label">Description</label>
			                <div class="col-lg-12">
			                    <textarea id="w3review" name="description" rows="4" cols="50">
						
								 </textarea>
			                </div>
			            </div>
			            
			
			            <div class="form-group">
			                <label class="col-md-3 control-label"></label>
			                <div class="col-md-12">
			                    <input type="button" class="btn btn-primary" value="Add Book" name="addBook" form="manage">
			                </div>
			            </div>
			
			        </form>
			
			
			
			    </div>
			 </div>
		</div>
	</div>
			    
</div>

</body>
</html>