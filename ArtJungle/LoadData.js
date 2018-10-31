var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/mydb";
const artwork = require('./artwork/Artwork');
const collections = ["Artwork", "User", "Bid"];

MongoClient.connect(url, function(err, db) {
    if (err) throw err;
    var dbo = db.db("mydb");

    //Remove all existing collections in the DB
    collections.forEach(col => dbo.dropCollection(col, function(err, res) {}));

    //Add all new collections to DB
    collections.forEach(col => dbo.createCollection(col, function(err, res) {}));

    const art = [new artwork(1,'Skrik', 'Munch'), new artwork(2, 'Mona Lisa')];
    const users = [{name:"Philip", phone:"98765432", email:"email@email.com"},
                   {name:"Mikal", phone:"12345678", email:"f@uglestein.com"}];
    const bids = [{bidAmount:"100"}, {bidAmount:"200"}];
    dbo.collection("Artwork").insertMany(art);
    dbo.collection("User").insertMany(users);
    dbo.collection("Bid").insertMany(bids);
    
    setTimeout(function(){ db.close(); console.log("Database created!"); }, 2000);
})

