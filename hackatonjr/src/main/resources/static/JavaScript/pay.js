function updatePrice(){
    var elements = document.getElementsByClassName("selected");
    var price = 0;

    for(var i = 0; i < elements.length; i++){
        price += parseFloat(elements[i].dataset.extra);
    }

    var button = document.getElementById("pay");
    button.textContent = "Pay " + price + " zénis";
}

function select(idStockable){
    var element = document.getElementById(idStockable);
    if(element.classList.contains("selected")){
        element.classList.remove("selected");
    }
    else{
        element.classList.add("selected");
    }
    updatePrice();
}

async function pay(){
    var stockableIds = "";
    var elements = document.getElementsByClassName("stockable");
    var money = document.getElementById("money");
    var price = 0;

    var inventorySize = document.getElementsByClassName("stockable2").length;

    for(var i = 0; i < elements.length; i++){
        if(elements[i].classList.contains("selected")){
            price += parseFloat(elements[i].dataset.extra);
            if(i == elements.length - 1){
                stockableIds += i;
            }
            else{
                stockableIds += i + "_";
            }
        }
    }

    document.getElementById("error").hidden = true;

    if(stockableIds == ""){
        return ;
    }

    if(price > parseFloat(money.textContent)){
        document.getElementById("error").hidden = false;
        return;
    }

    try {
        const response = await fetch('/pay',{
            method: 'POST',
            headers: {
                'Content-type': 'text/plain;charset=UTF-8',
            },
            body: stockableIds
        });

        if (!response.ok) {
            throw new Error(`HTTP ${response.status}`);
        }

        const user = await response.json();

        if(user.name == null){
            document.getElementById("error").hidden = false;
            return ;
        }

        for(var i = elements.length - 1; i >= 0; i--){
            if(elements[i].classList.contains("selected")){
                if(i > 2){
                    elements[i].remove();
                }
                else{//For the capsules
                    elements[i].classList.remove("selected");
                }
            }
        }

        for(var i = inventorySize; i < user.inventory.length; i++){
            var item = document.createElement("li");
            item.classList.add("stockable2");
            item.id = "stockable_" + user.inventory[i].id;
            item.innerHTML = `
                <img src="/Data/namek.jpg"> 
                <div class="container2"> 
                    <span>${user.inventory[i].name}</span> 
                    <button class="equip" onclick="equip('stockable_${user.inventory[i].id}')" data-extra=""> 
                        <span>Equip</span> 
                    </button> 
                </div>`;
            document.getElementById("inventoryList").appendChild(item);
        }

        updatePrice();

        var moneyElement = document.getElementById("money");
        moneyElement.textContent = user.money + " zénis";

    } catch (e) {
        console.error(e);
    }

}