const Account = require('../models/account.model.js');
const mongoose = require('mongoose');

exports.create = (req, res) => {
    var accId = new mongoose.Types.ObjectId();

    if(!req.body) {
        return res.status(400).send({
            message: "Note content can not be empty"
        });
    }

    if(req.body.photo == null)
        req.body.photo = "default.jpg";

    const account = new Account({
        _id: accId,
        name: req.body.name,
        phone: req.body.phone,
        email: req.body.email,
        password: req.body.password,
        photo: req.body.photo
    });

    account.save()
    .then(data => {
        res.redirect('/accounts/' + accId);
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
        res.render('pages/view_accounts',{
            account:account
        })
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
        res.render('pages/account',{
            account:account
        })
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
    .then(() => {
      res.redirect('/accounts/' + accountId);
    }).catch(err => {
      res.status(500).send({
        message : err.message || "Some error occurred while retreiving note"
      });
    });
}
