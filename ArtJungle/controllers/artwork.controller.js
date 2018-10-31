const Artwork = require('../models/artwork.model.js');

// Create and Save a new Note
exports.create = (req, res) => {
    // Validate request
    if(!req.body.content) {
        return res.status(400).send({
            message: "Note content can not be empty"
        });
    }

    // Create a Note
    const art = new Artwork({
        name: req.body.name, 
        artist: req.body.artist
    });

    // Save Note in the database
    art.save()
    .then(data => {
        res.send(data);
    }).catch(err => {
        res.status(500).send({
            message: err.message || "Some error occurred while creating the Artwork."
        });
    });
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