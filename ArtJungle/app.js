const express = require('express');
const morgan = require('morgan');
const bodyParser = require('body-parser');
const app = express();
const artworkController = require('./controllers/artwork.controller.js');
const Artwork = require('./models/artwork.model.js');

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

app.post('/accounts', function(req, res){
    response = {
        name : req.body.name,
        phone : req.body.phone,
        email : req.body.gender
    };

    console.log(response);

    res.end(JSON.stringify(response));
});

app.get('/view_artworks', function(req, res) {
    Artwork.find()
    .populate('bid')
    .populate('account')
    .then(art => {
        res.render('pages/view_artworks', {
            art: art
        });
    });
});

app.get('/view_art/:id', function(req, res){
    req.get('headerName');
    Artwork.findById(id)
    .populate('bid')
    .populate('account')
    .then(art => {
        res.render('pages/view_art', {
            art: art
        });
    });
});

module.exports = app;