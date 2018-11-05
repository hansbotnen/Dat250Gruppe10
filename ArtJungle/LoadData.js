const Artwork = require('./models/artwork.model.js');
const Account = require('./models/account.model.js');
const Bid = require('./models/bid.model.js');
const mongoose = require('mongoose');
var ids = [];

//Create id's before creating models s.t. they can be used when creating relations
for(i = 0; i < 7 ; i++){
    ids[i] = new mongoose.Types.ObjectId();
}

var models = [
    new Artwork({
        _id:ids[0],
        name:"Skrik", 
        artist:"Munch",
        account: ids[3],
        bid: ids[5]
    }),
    new Artwork({
        _id:ids[1],
        name:"Listhaug", 
        artist:"Mikal",
        account: ids[4],
        bid: ids[6]
    }),
    new Account({
        _id:ids[2],
        name:"Philip", 
        phone:"98765432",
        email:"phil@ip.com",
        bids: [ids[5], ids[6]],
    }),
    new Account({
        _id:ids[3],
        name:"Hans", 
        phone:"98765432",
        email:"Hans@I.var",
        artworks: ids[0]
    }),
    new Account({
        _id:ids[4],
        name:"Mikal", 
        phone:"1243567",
        email:"Mikal@Fugl.stein",
        artworks: ids[1]
    }),
    new Bid({
        _id:ids[5],
        bidAmount:"100",
        account: ids[2]
    }),
    new Bid({
        _id:ids[6],
        bidAmount:"200", 
        account: ids[2]
    })];

exports.load = () => {
    models.forEach(m => m.save(function (err) { if (err) return handleError(err); }));
}