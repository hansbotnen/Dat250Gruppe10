const mongoose = require('mongoose');

const ArtworkSchema = mongoose.Schema({
    name: String,
    artist: String
}, {
    timestamps: true
});

module.exports = mongoose.model('Artwork', ArtworkSchema);