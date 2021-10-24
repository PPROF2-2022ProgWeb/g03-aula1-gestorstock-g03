let toggle = document.querySelector('#toggleMenuButton');
let menu = document.querySelector('.side-nav');
let main = document.querySelector('main');

toggle.onclick = function () {
    menu.classList.toggle('active');
    main.classList.toggle('active');
}

function activateButton(evt){
    var menuItems = document.querySelectorAll('.option-container .menuItem');
    for (const menuItem of menuItems) {
        menuItem.classList.remove('active');
    }
    var tabName = evt.currentTarget.id.split('-')[1]; 
    var tabs = document.querySelectorAll('.tab-container');
    for (const tab of tabs) {
        tab.style.display = tab.id === tabName ? '' : 'none'; 
    }
    evt.currentTarget.classList.add('active');
}

function initiateTabs() {
    var menuItems = document.querySelectorAll('.option-container .menuItem');
    
    for (const selector of menuItems) {
        selector.onclick = activateButton;
        var tabName = selector.id.split('-')[1];
        document.getElementById(tabName).style.display = selector.classList.contains('active') ? '' : 'none'
    }
}

initiateTabs();