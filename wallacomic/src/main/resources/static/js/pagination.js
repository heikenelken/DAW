$(document).ready(function(){
	
	loadMore = function(cont){

		$('#spinner-div').html("<img src='img/spinner.gif'/>");
		
	    console.log(cont);
		$.get("/home" , {
			page: cont,
			size: 10
		})
		.done(function(data){
			$('#spinner-div').empty();
			var datos = JSON.stringify(data);
			var datos1 = datos.replace("<html>\r\n\r\n","");
			var datos2 = datos.replace("\r\n\r\n</html>","");
			var datos3 = JSON.parse(datos2);
			$("html").empty();
			$("html").append(datos3);
			
		});
	}

});
