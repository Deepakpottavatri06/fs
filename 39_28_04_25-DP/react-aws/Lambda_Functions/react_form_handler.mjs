
import { SNSClient, PublishCommand } from "@aws-sdk/client-sns";


const snsClient = new SNSClient({ region: "your-region" });

// Lambda handler
export async function ReactFormhandler(event) {
  try {
    const formData = typeof event.body === "string" ? JSON.parse(event.body) : event.body;
    console.log("Received form data:", formData);
    const params = {
      TopicArn: "arn:aws:sns:your-region:your-account:your-topic-arn",
      Message: JSON.stringify(formData),
    };

    await snsClient.send(new PublishCommand(params));

    return {
      statusCode: 200,
      body: JSON.stringify({ message: "Published to SNS successfully!" }),
    };
  } catch (error) {
    console.error(error);
    return {
      statusCode: 500,
      body: JSON.stringify({ error: "Failed to publish to SNS" }),
    };
  }
}
