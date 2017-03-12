$(document).ready(function(){

	var pageRequested = 0;
	
	$('#moreComics').click(function () {
		console.log('boton pulsado');
		
		console.log('en peticion');
		
		pageRequested++;
	    
		$.get("/home/more" , {
			page: pageRequested,
			size: 10
		})
		.done(function(data){
			console.log('Pagina cargada: ' + JSON.stringify(data));
		    $("#moreElementsPaged").append(data.content);
		});
		
	});

});
