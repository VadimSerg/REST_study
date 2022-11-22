async function index() {

    await fetch("http://localhost:8060/api/users")
        .then((response) => {
          //  const data = response.json();
             console.log(new Date());
          //  return data;
        })
        .then((user) => {
                let placeholder = document.querySelector("#data-output");
                let out = "";
                for (let product of user) {
                    out += `
                    <tr id="${product.id}">
                    <th scope="row">${product.id}</th>
                        <td>
                            ${product.name}
                        </td>
                        <td>
                            ${product.username}
                        </td>
                        <td>
                            ${product.age}
                        </td>
                        <td>
                            ${product.email}
                        </td>
                        <td>
                            ${product.roles.map(e => e.name)}
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


                // let student = {
                //     "id": 41,
                //     "name": "eeee1",
                //     "age": 89,
                //     "username": "eeee",
                //     "password": "$2a$10$h5EmZ6PH.D5xuuA3OB.r5ul4Y1Oj1TlBb6QrQF02sgjlEAcP2R8FK",
                //     "email": "maratgareev97@gmail.com",
                //     "roles": [
                //         {
                //             "id": 2,
                //             "name": "ROLE_USER",
                //             "authority": "ROLE_USER"
                //         }
                //     ],
                //     "enabled": true,
                //     "authorities": [
                //         {
                //             "id": 2,
                //             "name": "ROLE_USER",
                //             "authority": "ROLE_USER"
                //         }
                //     ],
                //     "accountNonLocked": true,
                //     "credentialsNonExpired": true,
                //     "accountNonExpired": true
                // };

                // let json = JSON.stringify(student.roles[0].authority);
                // let json = JSON.stringify(student);

                //   alert(typeof json); // мы получили строку!

                // console.log(json);
            }
        );

}
