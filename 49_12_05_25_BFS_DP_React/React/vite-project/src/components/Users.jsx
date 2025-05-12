import React, { use } from 'react'
import { useState, useEffect } from 'react'
export default function Users() {
    const [users, setUsers] = useState([]);
    useEffect(() => {
        const response = fetch('https://jsonplaceholder.typicode.com/users');
      
        response
            .then((res) => {
                const body = res.json();
                // console.log(body);
                return body;
            })
            .then((data) => {
                setUsers(data);
                console.log(data);
            })
            .catch((err) => console.log(err));
    }, []);

    return (
        <div>
            <h1>Users</h1>
            <table border={1} cellPadding={10}>
                <thead>
                    <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Website</th>
                </tr>
                </thead>
                <tbody>
                    {users.map((user) => (
                    <tr key={user.id}>
                        <td>{user.name}</td>
                        <td>{user.email}</td>
                        <td>{user.website}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
}
