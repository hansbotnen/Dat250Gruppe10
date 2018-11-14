const mongoose = require('mongoose');

var BidSchema = mongoose.Schema({
    bidAmount: String,
    account: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Account'
    },
    artwork: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Artwork'
    }
}, {
    timestamps: true
});

module.exports = mongoose.model('Bid', BidSchema);