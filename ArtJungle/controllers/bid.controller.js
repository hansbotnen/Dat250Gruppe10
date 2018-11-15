const Bid = require('../models/bid.model.js');
const mongoose = require('mongoose');

exports.create = (req, res) => {
  var bidId = new mongoose.Types.ObjectId();

  if(!req.body) {
      res.status(400).send();
  }
  const bid = new Bid({
      _id: bidId,
      bidAmount: req.body.bidAmount,
      account: req.body.account,
      artwork: req.body.artwork
  });

  bid.save() 
  .then(data => {
    res.send(data);
  }).catch(err => {
    res.status(500).send({
        message: err.message || "Some error occurred while creating the account."
    });
  });
};

exports.findAll = (req, res) => {
    Bid.find()
    .populate('account')
    .populate('artwork')
    .then(bid => {
        res.send(bid);
    }).catch(err => {
        res.status(500).send({
            message: err.message || "Some error occurred while retrieving bids."
        });
    });
};

exports.findOne = (req, res) => {
    var bidId = req.params.bidId;
    Bid.findOne({_id: bidId})
    .populate('account')
    .then(Bid => {
        res.send(Bid);
    }).catch(err => {
        res.status(500).send({
          message : err.message || "Some error occurred while retreiving bid"
        });
      });
  };
  
  exports.deleteOne = (req, res) => {
    var bidId = req.params.bidId;
    Bid.findOneAndRemove({_id: bidId})
      .then(Bid => {
        res.send(Bid);
      }).catch(err => {
        res.status(500).send({
          message : err.message || "Some error occurred while retreiving bid"
        });
      });
  };
  
  exports.updateOne = (req, res) => {
    var bidId = req.params.bidId;
    console.log(bidId);
    Bid.findOneAndUpdate({_id: bidId}, req.body, {new: true})
      .then(Bid => {
          res.redirect('/artworks/' + Bid.artwork._id);
      }).catch(err => {
        res.status(500).send({
          message : err.message || "Some error occurred while retreiving bid"
        });
      });
  }