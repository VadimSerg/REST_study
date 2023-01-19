fetch("http://localhost:8060/admin/users")
    .then(response => response.json())
    .then(data=>
       // console.log(data));
        document.getElementById('paragraph').innerHTML=JSON.stringify(data));