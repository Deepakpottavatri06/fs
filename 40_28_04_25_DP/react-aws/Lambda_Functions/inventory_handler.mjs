export const Inventoryhandler = async (event) => {
    for (const record of event.Records) {
      const message = JSON.parse(record.body);
      console.log("Inventory Data:", message.Message);
  
    }
    return {
      statusCode: 200,
      body: "Inventory Data processed",
    };
  };
  
