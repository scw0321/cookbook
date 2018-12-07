<%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
  
<h1>Edit ${recipe.title}</h1>
<form:form action="/recipes/${recipe.id}" method="post" modelAttribute="recipe">
    <input type="hidden" name="_method" value="put">
    
    

	
    <p>
        <form:label path="title">Title:</form:label>
        <form:errors path="title"/>
        <form:input path="title"/>
    </p>    
    <p>
    	  	
        <form:label path="ingredient">Ingredients: </form:label>
        <form:errors path="ingredient"/>  
        <form:input path="ingredient"/>
 		     
    </p>
    <p>
        <form:label path="prep">Preparation"</form:label> 
        <form:errors path="prep"/>       
        <form:input path="prep"/>          	
    </p>       
    <input type="submit" value="Submit"/>
    </form:form>