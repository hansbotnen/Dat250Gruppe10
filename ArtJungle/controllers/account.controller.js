const Account = require('../models/account.model.js');

exports.create = (req, res) => {
    if(!req.body) {
        return res.status(400).send({
            message: "Note content can not be empty"
        });
    }

    const account = new Account({
        name: req.body.name,
        phone: req.body.phone,
        email: req.body.email,
        password: req.body.password
    });

    account.save()
    .then(data => {
        res.redirect('')
    }).catch(err => {
        res.status(500).send({
            message: err.message || "Some error occurred while creating the account."
        });
    });
};

exports.findAll = (req, res) => {
    Account.find()
    .populate('bids')
    .populate('artworks')
    .then(account => {
        res.send(account);
    }).catch(err => {
        res.status(500).send({
            message: err.message || "Some error occurred while retrieving account."
        });
    });
};

exports.findOne = (req, res) => {
  var accountId = req.params.accountId;
  Account.findOne({_id: accountId})
    .populate('bids')
    .populate('artworks')
    .then(account => {
      res.send(account);
    }).catch(err => {
      res.status(500).send({
        message : err.message || "Some error occurred while retreiving note"
      });
    });
};

exports.deleteOne = (req, res) => {
  var accountId = req.params.accountId;
  Account.findOneAndRemove({_id: accountId})
    .then(account => {
      res.send(account);
    }).catch(err => {
      res.status(500).send({
        message : err.message || "Some error occurred while retreiving note"
      });
    });
};

exports.updateOne = (req, res) => {
  var accountId =  req.params.accountId;
  Account.findOneAndUpdate({_id: accountId}, req.body, {new: true})
    .then(account => {
      res.send(account);
    }).catch(err => {
      res.status(500).send({
        message : err.message || "Some error occurred while retreiving note"
      });
    });
}
