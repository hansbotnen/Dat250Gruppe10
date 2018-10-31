const bid = require('./bid');
const createId = require('../utils/idUtil');
const dummies = require('../utils/dummyDataUtil');

class BidService{
  constructor() {
    this.bids = dummies.bidDummies;
  }

  addDummies(dummies) {
    this.bids = dummies;
  }

  idExists(id) {
    return this.getById(id) != undefined;
  }

  getAll() {
    return this.bids;
  }

  getById(bidId) {
    return this.bids.find(obj => obj.bidId = bidId);
  }

  createBid(bidderId, artworkId, bidAmount) {
    // TODO: validate input
    var newBid = new bid(createId(), bidderId, artworkId, bidAmount);
    this.bids.push(newBid);
    return newBid;
  }

  deleteById(bidId){
    var deleted = this.getById(bidId);
    this.bids = this.bids.filter(obj => obj.bidId != bidId);
    return deleted;
  }

  updateById(bidId, req) {
    // TODO: validate input
    var bid = this.deleteById(bidId);
    bid = bid.assign({}, bid, req);
    this.bids.push(bid);
    return bid;
  }

  getByBidderId(reqId){
    return this.bids.filter(obj => obj.bidderId == reqId);
  }

  getByArtworkId(reqId){
    return this.bids.filter(obj => obj.artworkId == reqId);
  }
}
module.exports = new BidService;
