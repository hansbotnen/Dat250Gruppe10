const bid = require('./bid');
const createId = require('../utils/idUtil');

class BidService{
  constructor() {
    this.bids = [new bid(createId(), 'a75b','701a',3.50)];
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
