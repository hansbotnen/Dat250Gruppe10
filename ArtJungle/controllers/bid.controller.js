const Bid = require('../models/bid.model.js');

exports.findAll = (req, res) => {
    Bid.find()
    .populate('account')
    .then(art => {
        res.send(art);
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
    Bid.findOneAndUpdate({_id: bidId}, req.body, {new: true})
      .then(Bid => {
        res.send(Bid);
      }).catch(err => {
        res.status(500).send({
          message : err.message || "Some error occurred while retreiving bid"
        });
      });
  }