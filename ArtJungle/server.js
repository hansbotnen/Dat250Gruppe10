const express = require('express');
const morgan = require('morgan');
const bodyParser = require('body-parser');
const dbConfig = require('./config.js');
const mongoose = require('mongoose');
const loadData = require('./LoadData.js');
const app = express();
const port = 3000;

app.use(bodyParser.urlencoded({ extended: true }))
app.use(bodyParser.json())
app.use(morgan("dev"));
app.get('/', (req, res) => {
    res.json("Nothing to get here...");
});
require('./routers/account.router.js')(app);
require('./routers/artwork.router.js')(app);
require('./routers/bid.router.js')(app);
app.listen(port, () => {
    console.log("Server is listening on port 3000");
});

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


