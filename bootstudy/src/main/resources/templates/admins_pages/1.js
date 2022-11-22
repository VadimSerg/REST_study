let divs = document.getElementById("diva");
console.log(divs);
let button = document.getElementById("add");
let id=0;


// button.addEventListener('click', function()
async function add_string()
{
    // alert("!!!!!!!!!!!!!1")
    await fetch('http://localhost:8060/admin/test').then(
        respj => {
            let respr = respj.text();
            console.log(respr);
            return respr;
        }
    ).then(
        text1 => {
            console.log(text1);
            id+=1;
            divs.innerHTML += `<div id=${id}>${id}: ${text1}</div>`;
            return divs.innerHTML;
        }
    ) .then(
        dele =>{
            // var parent = document.getElementById("main");
            // var child = document.getElementById("id1");
            // parent.removeChild(child);
            console.log(dele)
        }
    )

// });
}

// function delStr(){
//     var parent = document.getElementById("diva");
//     var child = document.getElementById("1");
//     parent.removeChild(child);
// }