const Account = require('../models/account.model.js');

// Create and Save a new Note
exports.create = (req, res) => {
    // Validate request
    if(!req.body) {
        return res.status(400).send({
            message: "Note content can not be empty"
        });
    }

    // Create a Note
    const account = new Account({
        name: req.body.name,
        phone: req.body.phone,
        email: req.body.email
    });

    // Save Note in the database
    account.save()
    .then(data => {
        res.send(data);
    }).catch(err => {
        res.status(500).send({
            message: err.message || "Some error occurred while creating the Account."
        });
    });
};

exports.findAll = (req, res) => {
    Account.find()
    .then(account => {
        res.send(account);
    }).catch(err => {
        res.status(500).send({
            message: err.message || "Some error occurred while retrieving notes."
        });
    });
};

exports.findOne = (req, res) => {
  var accountId = req.params.accountId;
  Account.findOne({_id: accountId})
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
