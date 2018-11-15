const express = require('express');
const morgan = require('morgan');
const bodyParser = require('body-parser');
const app = express();
const formidable = require('formidable');
const Artwork = require('./models/artwork.model.js');
const Bid = require('./models/bid.model.js');


app.use(express.static('public'));
app.use(bodyParser.urlencoded({ extended: true }))
app.use(bodyParser.json())
app.set('view engine', 'ejs');
app.use(morgan("dev"));
require('./routers/account.router.js')(app);
require('./routers/artwork.router.js')(app);
require('./routers/bid.router.js')(app);

app.get('/api', function(req, res){
    let data = {
        message: 'Hello World!'
    };
    res.status(200).send(data);
});

app.post('/api', function(req, res) {
    let data = {
        response: 'You sent: ' + req.body.message
    };

    res.status(200.).send(data);
});

app.get('/', function(req, res){
    res.render('./pages/index');
});

app.get('/create_account', function(req, res) {
    res.render('./pages/create_account');
});

app.get('/create_artwork', function(req, res) {
    res.render('./pages/create_artwork');
});

module.exports = app;