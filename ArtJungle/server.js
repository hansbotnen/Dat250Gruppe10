const app = require('./app');
//const port = 3000;
const dbConfig = require('./config.js');
const mongoose = require('mongoose');
const loadData = require('./LoadData.js');

const server = require('http').Server(app);
const io = require('socket.io')(server);
const port = process.env.PORT || 3000;

mongoose.Promise = global.Promise;
mongoose.connect(dbConfig.url, {
    useNewUrlParser: true
}).then(() => {
    mongoose.connection.db.dropDatabase();
    loadData.load();
    console.log("Successfully connected to the database");
}).catch(err => {
    console.log('Could not connect to the database. Exiting now...', err);
    process.exit();
});

function onConnection(socket){
    socket.on('drawing', (data) => socket.broadcast.emit('drawing', data));
  }
  
io.on('connection', onConnection);


server.listen(port, () => {
    console.log("Server is listening on port 3000");
});

