const WebSocket = require('ws');

const ws = new WebSocket('ws://localhost:8080');

ws.on('open', () => {
    console.log("Connected to server.");

    ws.send("INSERT Alice 50000 Developer IT 5");
    ws.send("RETRIEVE");
    ws.send("INVALID");
});

ws.on('message', (message) => {
    console.log(`Server: ${message.toString()}`);
});

ws.on('close', () => {
    console.log("Disconnected from server.");
});
