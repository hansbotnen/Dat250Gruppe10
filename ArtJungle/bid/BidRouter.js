const express = require('express');
const bid = require('./bid');
const bidService = require('./bidService');
const bidRouter = express.Router();

bidRouter.route('/')
  .get((req, res) => {
    console.log('Fetching all bids');
    res.json(bidService.getAll());
  })
  .post((req, res) => {
    var bidderId = req.body.bidderId;
    var artworkId = req.body.artworkId;
    var bidAmount = req.body.bidAmount;
    res.json(bidService.createBid(bidderId,artworkId,bidAmount));
  })

bidRouter.route('/:bidId')
  .get((req, res) => {
    var bidId = req.params.bidId;
    console.log('Fetching bid with id: ' +bidId);
    res.json(bidService.getById(bidId));
  })
  .delete((req, res) => {
    var bidId = req.params.bidId;
    console.log('Deleting bid with id: ' + bidId);
    res.json(bidService.deleteById(bidId));
  })
  .put((req, res) => {
    var bidId = req.params.bidId;
    console.log('Updating bid with id: ' + bidId);
    res.json(bidService.updateById(bidId, req.body));
  })

module.exports = bidRouter;
