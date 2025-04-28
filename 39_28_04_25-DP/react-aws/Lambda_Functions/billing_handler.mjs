export const Billinghandler = async (event) => {
    for (const record of event.Records) {
      const message = JSON.parse(record.body);
      console.log("Billing Data:", message.Message);

    }
    return {
      statusCode: 200,
      body: "Billing processed",
    };
  };
  