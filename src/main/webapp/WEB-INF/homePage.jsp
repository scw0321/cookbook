<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Welcome</title>
	<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="js/ajax.js"></script>
</head>

<body>
<a href="/logout">Logout</a>
	<h1>Welcome, <c:out value="${user.name}"/></h1>	
	
	<form class="search_form" action="/apisearch" method="GET">
	<label>Search recipes by ingredient(s):</label>       
        <input class="ingredient" type="text" name="ingredient">
		<input type="submit" value="Find">
</form>
<div class="container">
             
  <table class="table table-striped">
    <thead>
        <tr>
            <th>Title</th>
            <th>Ingredient</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${recipe}" var="recipe">
        <tr>
            <td><a href="/recipes/<c:out value="${recipe.id}"/>"><c:out value="${recipe.title}"/></a></td>
            
            <td><c:out value="${recipe.ingredient}"/></td>
           
            <td><form style= "display: inline-block" action="/recipes/${recipe.id}/edit" method="post">
            	<input type="hidden" name="_method" value="edit">
    			<input type="submit" value="Edit">
				</form>
				<form style= "display: inline-block" action="/recipes/${recipe.id}/delete" method="post">
    			<input type="hidden" name="_method" value="delete">
    			<input type="submit" value="Delete">
				</form><br></td>
        	</tr>
        </c:forEach>
    </tbody>
</table>
</div>
<pre style="text-align:center"><b><a href="/recipes/new">Create your own recipe</a></b> || <b><a href="/search">Find recipe by ingredient(s)</a></b></pre>

<c:forEach items="${ingredientRecipes}" var="recipe">
	<c:out value="${recipe.q}"/>
	<c:out value="${recipe.hits.recipe}"/>
	</c:forEach>


</body>

</html>