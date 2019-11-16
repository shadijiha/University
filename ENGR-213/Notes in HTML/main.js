/**
 * 
 * 
*/

function getAllTabs()   {
    const tabs = document.querySelectorAll("tab");

    return tabs;
}

function hideElements(array)    {
    for (let element of array)
        element.style.display = "none";
}

function showElements(array)    {
    for (let element of array)
        element.style.display = "block";
}

function createElement(type, id, attributes, parentID)  {
    let string = `<${type} id="${id}" `;

    for (let element in attributes) {
        string += ` ${element}="${attributes[element]}" `;
    }

    string += `></${type}>`;

    if (parentID == undefined)
        document.querySelector("body").innerHTML += string;
    else
        document.getElementById(parentID).innerHTML += string;

    return document.getElementById(id);
}

// Process tabs
function parseTabs()    {

    const tabs = getAllTabs();

    // Create a div for every tab
    let i = 0;
    for (let tab of tabs)   {

        const DIV = createElement("div", `replacementOfTab${i}`, {class: "tab"}, "body" );
        tab.style.display = "none";

        
        DIV.innerHTML += `<h2>${tab.title}</h2>`;
        DIV.innerHTML += tab.innerHTML;

        i++;
    }
}

parseTabs();