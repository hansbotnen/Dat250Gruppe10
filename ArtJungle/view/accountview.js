const app = document.getElementById('root')

const container = document.createElement('div');
container.setAttribute('class', 'container');

app.appendChild(container);

var request = new XMLHttpRequest();
request.open('GET','http://localhost:3000/account', true);

request.onload = function() {
    // Access JSON data here
    var data = JSON.parse(this.response);
    
    if (request.status >= 200 && request.status < 400) {
        data.forEach(account => {
            console.log(account)
            const card = document.createElement('div');
            card.setAttribute('class', 'card');

            const h1 = document.createElement('h1')
            h1.textContent = account.name;


            container.appendChild(card);
            card.appendChild(h1);
            
            readAttributes(account, card);
            //card.appendChild(p);
        
        });
    } else {
        const errorMessage = document.createElement('marquee');
        errorMessage.textContent = `Something went wrong`;
        app.appendChild(errorMessage);
      }
}

request.send();

ignore = ["_id", "__v"]

function readAttributes(jsonobject, card) {
    for (var key in jsonobject) {
        if (!(ignore.includes(key))) {
            var p = document.createElement('p');
            p.textContent = key + ": " + jsonobject[key];
            card.appendChild(p);
        }
    }
}
