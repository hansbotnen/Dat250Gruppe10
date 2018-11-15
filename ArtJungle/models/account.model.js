const mongoose = require('mongoose');

const AccountSchema = mongoose.Schema({
    name: String,
    phone: String,
    email: String,
    password: String,
    photo: String,
    bids: [{
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Bid'
    }],
    artworks: [{
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Artwork'
    }]
}, {
    timestamps: true
});

module.exports = mongoose.model('Account', AccountSchema);