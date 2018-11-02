const express = require('express');
const accountRouter = express.Router();
const accountController = require('../controllers/account.controller.js');
module.exports = (app) => {
  const account = require('../controllers/account.controller.js');
   app.post('/account', account.create);

   app.get('/account', account.findAll);

   app.get('/account/:accountId', account.findOne);

   app.delete('/account/:accountId', account.deleteOne);

   app.put('/account/:accountId', account.updateOne);
   //app.get('/account/:accountId/artworks', artwork.getByOwnerId);
   //app.get('/account/:accountId/bids',bid.getByBidderId);
}
/*
accountRouter.route('/')
  .get(accountController.findAll)
  .post(accountController.create)
/*
accountRouter.route('/')
  .get((req, res) => {
    console.log('Fetching all accounts');
    res.json(accountService.getAll());
  })
  .post((req, res) =>{
    const name = req.body.name;
    console.log('Adding account with name: ' + name);
    res.json(accountService.createAccount(name));
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

  accountRouter.route('/:accountId/bids')
    .get((req, res) => {
      const accountId = req.params.accountId;
      console.log('Fetching all bids with bidder id: ' + accountId);
      res.json(bidService.getByBidderId(accountId));
    })

module.exports = accountRouter;
*/
