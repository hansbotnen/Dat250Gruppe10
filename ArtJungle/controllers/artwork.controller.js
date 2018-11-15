const Artwork = require('../models/artwork.model.js');
const Account = require('../models/account.model.js');
const Bid = require('../models/bid.model.js');
const mongoose = require('mongoose');

exports.create = (req, res) => {
    var artId = new mongoose.Types.ObjectId();

    if(!req.body.name || !req.body.artist) {
        res.status(400).send();
    }
    const bid = new Bid({bidAmount: req.body.bid, account:req.body.account});
    
    const art = new Artwork({
        _id: artId,
        name: req.body.name, 
        artist: req.body.artist,
        account: req.body.account,
        bid: bid
    });

    bid.save().then(() =>{}).catch(err => {
        res.status(500).send({
            message: err.message || "Some error occurred while creating the art."
        });
    });;


    art.save() 
    .then(data => {
        Account.findOneAndUpdate({_id: req.body.account}, {artworks: artId}, {new: true})
        .then().catch(err => {
        res.status(500).send({
          message : err.message || "Some error occurred while retreiving art"
        });
      });
      res.redirect('/artworks/' + artId);
    }).catch(err => {
        res.status(500).send({
            message: err.message || "Some error occurred while creating the art."
        });
    });
};

exports.findAll = (req, res) => {
    Artwork.find()
    .populate('bid')
    .populate('account')
    .then(art => {
        res.render('pages/view_arts', {
            art: art
        });
    }).catch(err => {
        res.status(500).send({
            message: err.message || "Some error occurred while retrieving artworks."
        });
    });
};

exports.findOne = (req, res) => {
    var artworkId = req.params.artworkId;
    Artwork.findOne({_id: artworkId})
    .populate('bid')
    .populate('account')
      .then(art => {
        res.render('pages/view_artwork', {
            art: art
        });
      }).catch(err => {
        res.status(500).send({
          message : err.message || "Some error occurred while retreiving artwork"
        });
    });
};

exports.deleteOne = (req, res) => {
    var artworkId = req.params.artworkId;
    Artwork.findOneAndRemove({_id: artworkId})
        .then(artwork => {
        res.send(artwork);
    }).catch(err => {
        res.status(500).send({
            message : err.message || "Some error occurred while retreiving artwork"
        });
    });
};

exports.updateOne = (req, res) => {
    var artworkId = req.params.artworkId;
    Artwork.findOneAndUpdate({_id: artworkId}, req.body, {new: true})
        .then(artwork => {
            res.redirect('/artworks/' + artworkId);
        }).catch(err => {
        res.status(500).send({
            message : err.message || "Some error occurred while retreiving artwork"
        });
    });
}