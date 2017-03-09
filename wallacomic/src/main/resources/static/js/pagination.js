function loadMoreElements() {
	console.log('en peticion')
	    //TODO Make GET request to load items. When request
	    //returns, we'll call to 'callback' function with loaded items
	    $.ajax({
	        method: "GET",
	        url: "http://localhost:8080/home?page=1&size=10"
	    }).done(function(page){
	        console.log('Pagina cargada: ' + JSON.stringify(page));
	    })
	    //For now, as there is no request, then we call with empty array
}

$(document).ready(function(){

	$('#moreComics').click(function () {
		console.log('boton pulsado')
		loadMoreElements()
	})
	//loadMoreElements();
	
//Handle add button
/*$("#add-button").click(function () {

    var value = input.val();
    input.val('');

    var item = {
        description: value,
        checked: false
    }

    createItem(item, function (itemWithId) {
        //When item with id is returned from server
        showItem(itemWithId);
    });
})*/

});