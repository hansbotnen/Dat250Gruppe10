const Account = require('../models/account.model.js');
const bcrypt = require('bcrypt');

exports.create = (req, res) => {
    if(!req.body) {
        return res.status(400).send({
            message: "Note content can not be empty"
        });
    }

    bcrypt.hash(req.body.password, 10).then(function(hash){
      const account = new Account({
          name: req.body.name,
          phone: req.body.phone,
          email: req.body.email,
          password: hash
      });

      account.save()
      .then(data => {
          res.redirect('')
      }).catch(err => {
          res.status(500).send({
              message: err.message || "Some error occurred while creating the account."
          });
      });
    }).catch(err => {
      res.status(500).send({
        message: err.message || "Some error occurred while hashing the password."
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
};

exports.authenticate = (req, res) => {
  const email = req.body.email;
  const password = req.body.password;
  Account.findOne({email: email})
    .then(account => {
      if(bcrypt.compareSync(password, account.password)) {
        res.send(account);
      } else {
        res.status(500).send({
          message : err.message || "Password doesn't match"
        });
      }
    }).catch(err => {
      res.status(500).send({
        message : err.message || "Some error occurred while logging in"
      });
    });
};
