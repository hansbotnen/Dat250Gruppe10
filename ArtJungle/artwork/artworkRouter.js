const express = require('express');
const artwork = require('./artwork')
const artworkService = require('./ArtworkService')
const artworkRouter = express.Router();

artworkRouter.get('/', (req, res) => {
  res.json(artworkService.getAll());
});

module.exports = artworkRouter;
