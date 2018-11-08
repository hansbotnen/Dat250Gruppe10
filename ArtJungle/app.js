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

app.post('/accounts', function(req, res){
    response = {
        name : req.body.name,
        phone : req.body.phone,
        email : req.body.email
    };

    console.log(response);

    res.end(JSON.stringify(response));
});

app.get('/create_artwork', function(req, res) {
    res.render('./pages/create_artwork');
});

app.post('/artworks', function(req, res) {
    response = {
        name : req.body.name,
        artist : req.body.artist,
    };
});

app.get('/view_arts', function(req, res) {
    Artwork.find()
    .populate('bid')
    .populate('account')
    .then(art => {
        res.render('pages/view_arts', {
            art: art
        });
    });
});

app.get('/view_artwork/:id', function(req, res){
    var artworkId = req.params.id;
    Artwork.findOne({_id: artworkId})
    .populate('bid')
    .populate('account')
      .then(art => {
        res.render('pages/view_artwork', {
            art: art
        });
      });
});

app.post('/view_artwork/:bid', function(req, res){
    var bidId = req.params.bid;
    var bidAmount = req.body.bidAmount;
    Bid.findOneAndUpdate({_id: bidId}, req.body, {new: true})
      .then(Bid => {
        res.send(Bid);
      });
});


module.exports = app;