module.exports = (app) => {
    const bid = require('../controllers/bid.controller.js');
     app.get('/bids', bid.findAll);
  
     app.get('/bids/:bidId', bid.findOne);
  
     app.delete('/bids/:bidId', bid.deleteOne);
  
     app.put('/bids/:bidId', bid.updateOne);
  }