const express = require('express');
const artwork = require('./artwork')
const artworkService = require('./ArtworkService')
const artworkRouter = express.Router();

artworkRouter.get('/', (req, res) => {
  res.json(artworkService.getAll());
});
artworkRouter.get('/:artworkId', (req,res) => {
  const artworkId = req.params.artworkId;
  console.log("Fetching artwork with id: " + artworkId);
  res.json(artworkService.getById(artworkId));
});

module.exports = artworkRouter;
