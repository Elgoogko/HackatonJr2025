function show(id){
    var element = document.getElementById(id);
    var elementButton = document.getElementById(id + "Button");

    var display = document.getElementsByName("display");
    var buttons = document.getElementsByName("button");

    for(var i = 0; i < display.length; i++){
        if(!display[i].classList.contains("hidden")){
            display[i].classList.add("hidden");
        }
    }

    for(var i = 0; i < buttons.length; i++){
        if(buttons[i].disabled){
            buttons[i].disabled = false;
            buttons[i].classList.remove("disabled");
            buttons[i].classList.add("button");
        }
    }

    element.classList.remove("hidden");
    elementButton.disabled = true;
    elementButton.classList.add("disabled");
    elementButton.classList.remove("button");
}

function showMap(){
    var map = document.getElementById("showmap");
    map.classList.remove("hidden");
    map.classList.add("centerMap");

    var overlay = document.getElementById("overlay");
    overlay.classList.remove("hidden");
    overlay.classList.add("overlay");
}

function hideMap(){
    var map = document.getElementById("showmap");
    map.classList.remove("centerMap");
    map.classList.add("hidden");

    var overlay = document.getElementById("overlay");
    overlay.classList.remove("overlay");
    overlay.classList.add("hidden");
}