<%@ page language ="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>

<!DOCTYPE html>
<html>
<head>


<Title>Recipes</Title>
<meta charset = "UFT-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
        <script type="text/javascript" src="js/app.js"></script>
</head>

<body>

<form action="/search" method="POST">
	<label>Search recipes by ingredient(s):</label>       
        <input type="text" name="ingredient">
		<input type="submit" value="Find">
</form>




<h1>${recipe.title}</h1>
<img src="#" alt="${recipe.title}" height="42" width="42">
<p>Ingredient: <c:out value="${recipe.ingredient}"/></p>
<p>Preparation: <c:out value="${recipe.prep}"/></p>   


<a href="/recipes/${recipe.id}/edit">Edit Recipe</a><br><br>

<form action="/recipes/${recipe.id}/edit" method="post">
    <input type="hidden" name="_method" value="edit">
    <input type="submit" value="Edit">
</form><br>

<form action="/recipes/${recipe.id}/delete" method="post">
    <input type="hidden" name="_method" value="delete">
    <input type="submit" value="Delete">
</form><br>
<a href="/recipes">Home</a>
</body>
</html>
