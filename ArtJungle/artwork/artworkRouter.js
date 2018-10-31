const express = require('express');
const artwork = require('./Artwork');
const artworkRouter = express.Router();
const artworks = [new artwork(1,'Skrik', 'Munch'), new artwork(2, 'Mona Lisa')];
