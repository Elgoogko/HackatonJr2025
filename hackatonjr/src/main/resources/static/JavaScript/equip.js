async function equip(id){
    var stockableId = "";
    var elements = document.getElementsByClassName("stockable2");

    for(var i = 0; i < elements.length; i++){
        if(id == elements[i].id){
            stockableId = i;
            break;
        }
    }

    document.getElementById("errorEquip").hidden = true;

    try{
        const response = await fetch('/api/equip',{
            method: 'POST',
            headers: {
                'Content-type': 'text/plain;charset=UTF-8',
            },
            body: String(stockableId)
        });

        if (!response.ok) {
            throw new Error(`HTTP ${response.status}`);
        }

        const message = await response.text();

        if(message == "-1"){
            document.getElementById("errorEquip").hidden = false;
            return ;
        }

        var type = message.split('_')[0];

        var element = document.getElementById(id);

        if(type == "Food"){
            document.getElementById("hunger").textContent = "Hunger : " + message.split('_')[1] + " %";
            element.remove();
        }
        else{
            var lower = type.toLowerCase();
            document.getElementById(lower).textContent = message.split('_')[1];
            var buttons = document.getElementsByClassName("equiped");

            for(var i = 0; i < buttons.length; i++){
                if(buttons[i].dataset.extra == lower){
                    buttons[i].textContent = "Equip";
                    buttons[i].disabled = false;
                    buttons[i].dataset.extra = "";
                    buttons[i].classList.add("equip");
                    buttons[i].classList.remove("equiped");
                }
            }

            var button = element.querySelector("button");

            button.textContent = "Equiped";
            button.dataset.extra = lower;
            button.disabled = true;
            button.classList.add("equiped");
            button.classList.remove("equip");

            if(type == "Vehicle"){
                updateLocations();
            }
            
        }

    } catch (e) {
        console.error(e);
    }
}