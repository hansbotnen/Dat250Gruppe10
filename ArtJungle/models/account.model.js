const mongoose = require('mongoose');

const AccountSchema = mongoose.Schema({
    name: String,
    phone: String,
    email: String,
}, {
    timestamps: true
});

module.exports = mongoose.model('Account', AccountSchema);