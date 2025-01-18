const express = require('express');
const dotenv = require('dotenv');
const http = require('http');


dotenv.config();
const app = express();
const server = http.createServer(app);
const io = require('socket.io')(server);


io.on('connection',(client)=>{
    console.log(`connection received...`);
    client.on('New_Message',(chat)=>{
        console.log(`Message from ${chat}`);
        io.emit('broadcast',chat);
    });

});
io.on('connect_error', (err) => {
    console.error('Connection error:', err.message);
});

// app.get('/',(req,res)=>{
//     res.send('Hello World!..');
// });

const port = process.env.port
server.listen(port,()=>{
    console.log(`Server running on port : ${port}`);
})