const mongoose = require('mongoose');

const ArtworkSchema = mongoose.Schema({
    name: String,
    artist: String,
    account: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Account'
    },
    bid: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Bid'
    }
}, {
    timestamps: true
});

module.exports = mongoose.model('Artwork', ArtworkSchema);