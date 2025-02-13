// <!--
// Order Management - using JSON Object
// Objective: Implement the CRUD operations.

// Requirements:
// ------------------------------------------------
// Method	    Endpoint	    Description
// ------------------------------------------------
// POST	   /orders	    	Create a new order
// GET	       /orders	    	Get all orders
// GET	       /orders/:id	    Get a order by ID
// PUT	       /orders/:id		Update a order by ID
// DELETE	   /orders/:id		Delete a order by ID
// ------------------------------------------------

// Reference JSON format for Object:
// ---------------------------------
// { 
// 	id: 1,
// 	customerName: "Azar",
// 	totalPrice: 1	50.0
// }

// NOTE: id value starts with 1, and increments by 1, for every new entry.

// 2. Implementation Requirements:
// -------------------------------
// Create a JSON Object (local)
// Implement proper error handling
// Add data validation


// 3. API Response Format:
// -----------------------	
// Method: POST
// Path: /orders

// Response:
//     If successful:
//       res.status(201).send(order);

// ====================================

// Method: GET
// Path: /orders

// Response:
//     If successful:
//       res.status(200).send(orders);

// =====================================
// Method: GET
// Path: /orders/:id

// NOTE: pass (id value as URI params)

// Response:
//     If successful:
// 		res.status(200).send(order);

//     If not found:
//         res.status(404).send();

// ====================================
// Method: PUT
// Path: /orders/:id

// NOTE: pass (id value as URI params)

// Response:
//     If successful:
// 		res.status(200).send(order);

//     If not found:
//         res.status(404).send();

// ===================================

// Method: DELETE
// Path: /orders/:id

// NOTE: pass (id value as URI params)

// Response:
//     If successful:
//         res.status(200).send();

// -->

// <config>
//     <url value=""></url>
// </config>


const express = require('express');
const app = express();
let orders = require('./data.json');
app.use(express.json());

app.get('/orders', async (req, res) => {
    res.status(200).send(orders);
});

app.get('/orders/:id', async (req, res) => {
    const order = orders.find((order) => order.id === parseInt(req.params.id));
    if (!order) {
        res.status(404).send();
    }
    else {
        res.status(200).send(order);
    }

});

app.post("/orders", async (req, res) => {
    try {
        const order = req.body;
        order.id = orders.length;
        orders.push(order);
        res.status(201).send(order);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

app.put("/orders/:id", async (req, res) => {
    try {
        const id = parseInt(req.params.id);
        const order = orders.find((order) => order.id === id);
        if (!order) {
            res.status(404).send();
        }
        orders[id-1] = req.body;
        res.status(200).send(req.body);
    } catch (error) {
        res.status(500).send();
    }
});

app.delete("/orders/:id", async (req, res) => {
    try {
        const id = parseInt(req.params.id);
        orders = orders.filter((order) => order.id != id);
        res.status(200).send("Deleted Sucessfully");
    } catch (error) {
        res.status(500).send();
    }
});

app.listen(4000, () => {
    console.log("listening at 4000");
});

