"use strict";

var cont = 1;
function clickar() {
    if (cont === 1) {
        document.getElementById("commentsTable").style.display = "block";
        cont = 2;
    }else{
        document.getElementById("commentsTable").style.display = "none";
        cont = 1;
    }
    
}