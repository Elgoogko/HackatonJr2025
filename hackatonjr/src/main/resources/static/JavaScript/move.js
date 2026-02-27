async function updateLocations(){
    var elements = document.getElementsByClassName("location");

    try{
        const response = await fetch('/api/locations',{
            method: 'GET'
        })

        if (!response.ok) {
            throw new Error(`HTTP ${response.status}`);
        }

        const locations = await response.json();

        for(var i = 0; i < elements.length; i++){
            if(elements[i].classList.contains("green")){
                elements[i].classList.remove("green");
            }
        }

        if(locations[0] == "-1"){
            return ;
        }

        for(var i = 0; i < locations.length; i++){
            var location = document.getElementById("location_" + locations[i]);
            location.classList.add("green");
        }

    } catch(e) {
        console.error(e);
    }
}

async function move(id){
    var element = document.getElementById(id);
    var elements = document.getElementsByClassName("location");

    document.getElementById("errorLocation").hidden = true;
    document.getElementById("errorLocation2").hidden = true;
    document.getElementById("showMoneyHunger").hidden = true;

    if(element.classList.contains("red") || !element.classList.contains("green")){
        document.getElementById("errorLocation").hidden = false;
        return;
    }

    for(var i = 0; i < elements.length; i++){
        if(elements[i].id == id){
            var numberLocation = i;
        }
    }

    try{
        const response = await fetch('/api/move',{
            method: 'POST',
            headers: {
                'Content-type': 'text/plain;charset=UTF-8',
            },
            body: String(numberLocation)
        })

        if (!response.ok) {
            throw new Error(`HTTP ${response.status}`);
        }

        const user = await response.json();

        if(user.name == null){
            document.getElementById("errorLocation2").hidden = false;
            return ;
        }

        var oldMoney = parseFloat(document.getElementById("money").textContent);
        var oldHunger = parseInt(document.getElementById("hunger").textContent.split(' ')[2]);

        document.getElementById("money").textContent = Number(user.money).toFixed(1) + " zénis";
        document.getElementById("hunger").textContent = "Hunger : " + user.hunger + " %";
        document.getElementById("current").textContent = user.currentLocation.name;

        var here = document.getElementsByClassName("here")[0];

        element.insertBefore(here,element.querySelector("span"));

        element.classList.remove("green");

        var money = parseFloat(user.money) - oldMoney;
        var hunger = parseInt(user.hunger) - oldHunger;

        document.getElementById("showMoneyHunger").textContent = "+ " + money + " zénis | Hunger rate : + " + hunger + " %";
        document.getElementById("showMoneyHunger").hidden = false;

        updateLocations();

    } catch(e) {
        console.error(e);
    }
}