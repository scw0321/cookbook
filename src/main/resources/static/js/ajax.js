//$("document").ready(function(){
//	$(".search_form").submit(function(e){
//		e.preventDefault();
//		$.ajax({
//			method: "GET",
//			url: "https://api.edamam.com/search?q="+$(".ingredient").val()+"&app_id=659e59e4&app_key=fa04c651c0c6cfe0d7ac0216c4742e2a&from=0&to=3&calories=591-722&health=alcohol-free",
//			success: function(res){
//				console.log("success!");
//				console.log(res);
//				var resHits = res.hits;
//				$.ajax({
//					method:"POST",
//					url: "/apisearch",
//					data:JSON.stringify(res),
//					success: function(page_res){
//						console.log("2nd success!");
//						console.log(page_res);
//					}
//				});
//			}
//		});
//	});
//});
////		
//////		$.ajax({
//////			method:"GET",
//////			url: "/asearch",
//////			success: function(res){
//////				console.log("success");
//////				console.log(res);
//////			}
//////		})
////	})
////});