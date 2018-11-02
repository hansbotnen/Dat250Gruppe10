const Artwork = require('../models/artwork.model.js');

exports.create = (req, res) => {
    if(!req.body.name || !req.body.artist) {
        res.status(400).send();
    }

    const art = new Artwork({
        name: req.body.name, 
        artist: req.body.artist
    });

    art.save(function (err) { 
        if (err) res.status(500);
        res.status(201).send(art);
    })
};

exports.findAll = (req, res) => {
    console.log("test");
    Artwork.find()
    .then(art => {
        res.send(art);
    }).catch(err => {
        res.status(500).send({
            message: err.message || "Some error occurred while retrieving notes."
        });
    });
};