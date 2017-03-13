$(document).ready(function(){
	
	loadMore = function(cont){

		console.log('boton pulsado');
		
		console.log('en peticion');
		
	    console.log(cont);
		$.get("/home" , {
			page: cont,
			size: 10
		})
		.done(function(data){
			console.log(JSON.stringify(data));
			var datos = JSON.stringify(data);
			var datos1 = datos.replace("<html>\r\n\r\n","");
			var datos2 = datos.replace("\r\n\r\n</html>","");
			var datos3 = JSON.parse(datos2);
			$("html").empty();
			$("html").append(datos3);
			

			/*var tag = document.createElement("div");
		    //var resto = $(data);
		    $(tag).add(data);
		    primero: <html>\r\n\r\n
		    segundo: \r\n\r\n</html>
		    */
		});
	}

});
