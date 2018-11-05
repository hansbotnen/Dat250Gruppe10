const mongoose = require('mongoose');

var BidSchema = mongoose.Schema({
    bidAmount: String,
    account: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Account'
    }
}, {
    timestamps: true
});

module.exports = mongoose.model('Bid', BidSchema);