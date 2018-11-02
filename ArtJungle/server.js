const express = require('express');
const morgan = require('morgan');
const bodyParser = require('body-parser');
const accountRouter = require('./account/accountRouter');
const bidRouter = require('./bid/bidRouter');
const dbConfig = require('./config.js');
const mongoose = require('mongoose');
const LoadData = require('./LoadData.js');
const app = express();
const port = 3000;

app.use(bodyParser.urlencoded({ extended: true }))
app.use(bodyParser.json())
app.use(morgan("dev"));
app.get('/', (req, res) => {
    res.json("Hello World");
});
require('./artwork/artworkRouter')(app);
require('./account/accountRouter')(app);
app.listen(port, () => {
    console.log("Server is listening on port 3000");
});

mongoose.Promise = global.Promise;
mongoose.connect(dbConfig.url, {
    useNewUrlParser: true
}).then(() => {
    console.log("Successfully connected to the database");
}).catch(err => {
    console.log('Could not connect to the database. Exiting now...', err);
    process.exit();
});

var loadData = new LoadData();
loadData.load();
