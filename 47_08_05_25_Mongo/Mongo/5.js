/*
Compute the monthly revenue and number of orders for the current year.
Display the month number, total revenue, and order count for each month.


Sample output:
--------------
[
  { monthlyRevenue: 8828, orders: 15, month: 1 },
  { monthlyRevenue: 5043, orders: 9, month: 2 }
]


Query Reference:
-------------------
printjson() : JS library function to display the JSON Object data.
    In db.<collection>.find()/aggregate():
    	db -> Refers to the database.
    	<collection> -> Your collection name
	
*/
	
printjson(
    db.Orders.aggregate([
        
    {
        $group:{
            _id:{"$month":"$ordered_at"},
            monthlyRevenue:{$sum:"$total_amount"},
            orders:{$sum:1},
            month:{$first:{"$month":"$ordered_at"}}
        }
    },
    {
        $project:{
            _id:0
        }
    },
    {
        $sort:{
            month:1
        }
    }
    
    ])
)
