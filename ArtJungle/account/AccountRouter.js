const express = require('express');
const account = require('./account');
const accountService = require('./AccountService');
const accountRouter = express.Router();
const artworkService = require('../artwork/ArtworkService');


accountRouter.route('/')
  .get((req, res) => {
    console.log('Fetching all accounts');
    res.json(accountService.getAll());
  })
  .post((req, res) =>{
    const name = req.body.name;
    if(name == undefined) {
      console.log('Cannot add account with name: ' + name);
      res.json();
    }
    else{
      console.log('Adding account with name: ' + name);
      res.json(accountService.createAccount(name));
    }
  })

accountRouter.route('/:accountId')
  .get((req, res) => {
    const accountId = req.params.accountId;
    console.log('Fetching account with id: ' + accountId);
    res.json(accountService.getById(accountId));
  })
  .delete((req, res) => {
    const accountId = req.params.accountId;
    console.log('Deleting account with id: ' + accountId);
    res.json(accountService.deleteById(accountId));
  })
  .put((req, res) => {
    const accountId = req.params.accountId;
    console.log('Updating account with id: ' + accountId);
    res.json(accountService.updateById(accountId, req.body));
  })

  accountRouter.route('/:accountId/artworks')
    .get((req, res) => {
      const accountId = req.params.accountId;
      console.log('Fetching all artworks with owner id: '+ accountId);
      res.json(artworkService.getByOwnerId(accountId));
    })

module.exports = accountRouter;
