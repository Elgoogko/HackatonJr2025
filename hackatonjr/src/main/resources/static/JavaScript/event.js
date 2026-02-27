async function end(){
    try {
        const response = await fetch('/api/endGame',{
            method: 'GET'
        });

        if (!response.ok) {
            throw new Error(`HTTP ${response.status}`);
        }

        const end = await response.json();

        if(end){
            window.location.href = "/end";
        }

    } catch(e) {
        console.error(e);
    }
}

async function triggerEvent(event){

    try {
        const response = await fetch('/api/triggerEvent',{
            method: 'GET'
        });

        if (!response.ok) {
            throw new Error(`HTTP ${response.status}`);
        }

        const user = await response.json();

        

        if(event.type == 'BONUS' || event.type == 'MALLUS'){
            document.getElementById("money").textContent = user.money + " zénis";
        }
        else if(event.type == 'ATTACK'){
            end();
            document.getElementById("vehicle").textContent = (user.vehicle != null ? user.vehicle.name : 'None');
            document.getElementById("head").textContent = (user.outfit.head != null ? user.outfit.head.name : 'None');
            document.getElementById("top").textContent = (user.outfit.top != null ? user.outfit.top.name : 'None');
            document.getElementById("bottom").textContent = (user.outfit.bottom != null ? user.outfit.bottom.name : 'None');

            var inventory = document.getElementsByClassName("stockable2");

            for(var i = inventory.length - 1; i >= 0; i--){
                var verify = false;

                for(var j = 0; j < user.inventory.length; j++){
                    if(inventory[i].id == "stockable_" + user.inventory[j].id){
                        verify = true;
                    }
                }

                if(!verify){
                    inventory[i].remove();
                }
            }

        }
        else if(event.type == 'DANGER'){
            end();
            for(var i = 0; i < event.targeLocations.length; i++){
                var element = document.getElementById("location_" + event.targeLocations[i].id);
                if(element.classList.contains("green")){
                    element.classList.remove("green");
                }
                element.classList.add("red");
                element.classList.remove("target");
            }
            updateLocations();
        }
        else if(event.type == 'STORY'){
            end();
        }

        document.getElementById("eventAttributs").dataset.extra = "";
        document.getElementById("timer").classList.add("hidden");
        document.getElementById("timer").classList.remove("timer");
        

    } catch(e) {
        console.error(e);
    }
}

function hideEvent(){
    document.getElementById("eventAttributs").classList.add("hidden");
    document.getElementById("eventAttributs").classList.remove("event");
}

function hideImportant(){
    document.getElementById("survive").classList.add("hidden");
    document.getElementById("survive").classList.remove("event");
}

function reduceTimer(){
    var time = document.getElementById("time");
    time.textContent = parseInt(time.textContent) - 1;
}

async function event(){
    var eventElement = document.getElementById("eventAttributs");

    if(eventElement.dataset.extra == "progress"){
        return ;
    }

    try {
        const response = await fetch('/api/event',{
            method: 'GET'
        });

        if (!response.ok) {
            throw new Error(`HTTP ${response.status}`);
        }

        const event = await response.json();

        document.getElementById("eventName").textContent = event.type;
        document.getElementById("eventDescription").textContent = event.description;
        document.getElementById("eventAttributs").hidden = false;
        document.getElementById("eventAttributs").classList.remove("hidden");
        document.getElementById("eventAttributs").classList.add("event");

        if(event.type == 'DANGER'){
            var string = "Avoid the condemned locations !!!";
        }
        else if(event.type == 'ATTACK'){
            var string = "Equip yourself with a full outfit !!!";
        }
        else{
            var string = "";
        }

        document.getElementById("eventAdvice").textContent = string;

        eventElement.dataset.extra = "progress";

        setTimeout(hideEvent,3000);

        if(event.triggerTimeSec != 0){
            setTimeout(() => triggerEvent(event), event.triggerTimeSec * 1000);
            var timer = document.getElementById("timer");
            timer.classList.remove("hidden");
            timer.classList.add("timer");
            document.getElementById("time").textContent = event.triggerTimeSec;

            var interval = setInterval(reduceTimer,1000);
            setTimeout(() => clearInterval(interval), event.triggerTimeSec * 1000);

            for(var i = 0; i < event.targeLocations.length; i++){
                var element = document.getElementById("location_" + event.targeLocations[i].id);
                element.classList.add("target");
            }
        }
        else if(event.type == 'STORY'){
            setTimeout(() => triggerEvent(event), 3000);
        }
        else{
            triggerEvent(event);
        }

    } catch(e) {
        console.error(e);
    }
}