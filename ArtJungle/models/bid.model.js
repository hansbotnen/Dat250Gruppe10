const mongoose = require('mongoose');

const BidSchema = mongoose.Schema({
    bidAmount: String,
}, {
    timestamps: true
});

module.exports = mongoose.model('Bid', BidSchema);