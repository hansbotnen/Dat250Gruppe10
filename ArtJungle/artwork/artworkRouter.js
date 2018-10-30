const express = require('express');
const artwork = require('./artwork');
const artworkService = require('./ArtworkService');
const artworkRouter = express.Router();

// Get all artworks
artworkRouter.get('/', (req, res) => {
  console.log('Fetching all artworks');
  res.json(artworkService.getAll());
});

// Add new artwork
artworkRouter.post('/', (req, res) => {
  var name = req.body.name;
  var artist = req.body.artist;
  if(name == undefined || artist == undefined) {
    console.log('Cannot add artwork with name: ' + name + ', artist: ' + artist);
    res.json();
  }
  else{
    console.log('Adding artwork with name: ' + name + ', artist: ' + artist);
    res.json(artworkService.createArtwork(name,artist));
  }
});

// Get artwork from ID
artworkRouter.get('/:artworkId', (req,res) => {
  const artworkId = req.params.artworkId;
  console.log('Fetching artwork with id: ' + artworkId);
  res.json(artworkService.getById(artworkId));
});

module.exports = artworkRouter;
