<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
 
<h1>New Recipe</h1>

<!-- <p><c:out value="${error}"/></p>  -->
<form:form action="/recipes/new" method="post" modelAttribute="recipe">

		
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
        <form:label path="prep">Preparation: </form:label>
        <form:errors path="prep"/>
        <form:input path="prep"/>        
                  	
    </p>
       
    <input type="submit" value="Submit"/>
</form:form>    