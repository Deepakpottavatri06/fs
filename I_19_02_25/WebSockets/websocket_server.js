/*<!--
Employee Management WebSocket Application with MongoDB

Objective:
----------
Your task is to develop a WebSocket-based Employee Management System using Node.js and MongoDB. 
The system should allow multiple clients to interact with a database to perform the following operations:
    1. Insert Employee Records (INSERT <name> <salary> <role> <department> <experience>)
    2. Retrieve Employee List (RETRIEVE)
    3. Retrieve Employee List who belongs to a department (RETRIEVE_BY_DEPT <department>)
	
The WebSocket server should be capable of handling multiple concurrent clients and persist employee data in MongoDB.


// MongoDB Employee Schema
const employeeSchema = new mongoose.Schema({
    name: String,
    salary: Number,
    role: String,
    department: String,
    experience: Number
});

Requirements:
-------------
Implement WebSocket Server
    The server should:
        -> Accept multiple client connections. (give a response as "Connected" )
        -> Process incoming commands from clients as discussed above.
        -> Log each received command on the console.
        -> Ensure proper error handling (e.g., invalid salary, missing name, etc.).
    	
Expected Behavior
-----------------

============================================================================================
Client Command			                Server Response
============================================================================================
INSERT Alice 50000 Developer IT 5	    "Employee inserted successfully."
INSERT Bob 60000 Manager IT 5	        "Employee inserted successfully."

RETRIEVE				                "ID: 1, Name: Alice, Salary: 50000, Role: Developer, Department: IT, Experience: 5 years"
                                        "ID: 2, Name: Bob, Salary: 60000, Role: Manager, Department: IT, Experience: 5 years"

RETRIEVE_BY_DEPT IT                     "ID: 1, Name: Alice, Salary: 50000, Role: Developer, Department: IT, Experience: 5 years"
                                        "ID: 2, Name: Bob, Salary: 60000, Role: Manager, Department: IT, Experience: 5 years"


INVALID					                "Invalid command."
============================================================================================

Note: 
-> Your implementation must use MongoDB for data persistence.
-> The server should run on port 8080.
-> The system should allow multiple clients to connect.


EXAMPLE URL value=>   ws://10.11.xx.xx:8080

-->
<config>
    <url value=""></url>
</config> */
const mongoose = require("mongoose");
const webSocket = require("ws");
const AutoIncrement = require("mongoose-sequence")(mongoose);

mongoose.connect("mongodb://127.0.0.1:27017/websocket")
    .then(() => {
        console.log("mongodb connected!");
        
    })
    .catch((err) => {
        console.log("Error", err);
    })

const employeeSchema = new mongoose.Schema({
    name: { type: String, required: true },
    salary: { type: Number, required: true },
    role: { type: String, required: true },
    department: { type: String, required: true },
    experience: { type: Number, required: true }
},{_id:false});

employeeSchema.plugin(AutoIncrement, {id:"counter", inc_field: "_id" });
const Employee = mongoose.model("Employee", employeeSchema);


const wss = new webSocket.Server({ port: 8080 });
// console.log("WebSocket Server running on ws://localhost:8080")
wss.on('connection', (ws) => {
    // console.log("client connected!");
    // ws.send("Connected");
    ws.on("connection",()=>{
        ws.send('Connected');
    })
    ws.on("message", async (message) => {
        // console.log("Received:", message.toString());
        const parts = message.toString().split(" ");
        const cmd = parts[0];
        if (cmd === "INSERT") {
                const [c, name, salary, role, department, experience] = parts;
                const newEmployee = new Employee({
                    name,
                    salary: parseInt(salary),
                    role,
                    department,
                    experience: parseInt(experience)
                })
                await newEmployee.save();
                // console.log(newEmployee);
                ws.send(`Employee inserted successfully.ID: ${newEmployee._id}`);
                // console.log(newEmployee._id);
                
        }
        else if(cmd==="RETRIEVE"){

                const employees = await Employee.find({});
                if(employees.length==0){
                    ws.send("No Employees Found");
                }
                employees.forEach((item)=>{
                    ws.send(`ID: ${item._id}, Name: ${item.name}, Salary: ${item.salary}, Role: ${item.role}, Department: ${item.department}, Experience: ${item.experience} years`)
                })
           
        }
        else if(cmd==="RETRIEVE_BY_DEPT")
        {
            const dept = parts[1];
            const employees = await Employee.find({department:dept});
            if(employees.length==0){
                ws.send("No Employees Found");
            }
            employees.forEach((item)=>{
                ws.send(`ID: ${item._id}, Name: ${item.name}, Salary: ${item.salary}, Role: ${item.role}, Department: ${item.department}, Experience: ${item.experience} years`);
            });
        }
        else{
            ws.send("Invalid command.")
        }
    });

 
})