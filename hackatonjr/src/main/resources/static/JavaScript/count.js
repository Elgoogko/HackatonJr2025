function numberOfCaracters(){
    var input = document.getElementById("name");
    var number = document.getElementById("number");
    var max = number.textContent.split('/')[1].trim();

    number.textContent = input.value.length + " / " + max;
}