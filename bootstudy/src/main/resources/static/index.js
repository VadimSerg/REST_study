async function index() {
    await fetch("http://localhost:8080/api/employees")
        .then((response) => {
            const data = response.json();
            // console.log(data);
            return data;
        })
        .then((user) => {
                let placeholder = document.querySelector("#data-output");
                let out = "";
                for (let product of user) {
                    out += `
                    <tr id="${product.id}">
                    <th scope="row">${product.id}</th>
                        <td>
                            <div id="name_${product.id}">${product.name}</div>
                        </td>
                        <td>
                            <div id="username_${product.id}">${product.username}</div>
                        </td>
                        <td>
                            <div id="age_${product.id}">${product.age}</div>
                        </td>
                        <td>
                            <div id="email_${product.id}">${product.email}</div>
                        </td>
                        <td>
                            <div id="role_${product.id}">${product.roles.map(e => e.name)}</div>
                        </td>
                        <td>
                            <a href="#" class="btn btn-primary" role="button" data-toggle="modal" 
                                data-target="#modelArticulo" onclick="aaa(${product.id});">
                                Edit
                            </a>
                        </td>
                        <td>
                        <a href="#" class="btn btn-danger btn-sm"
                        role="button"  onclick="delete_user(${product.id});">
                         Delete
                     </a>
                        </td>
                  </tr>
                    `;
                }
                placeholder.innerHTML = out;
            }
        );

}

index();
