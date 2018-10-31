const express = require('express');
const artwork = require('./artwork');
const artworkService = require('./ArtworkService');
const artworkRouter = express.Router();


artworkRouter.route('/')
  .get((req, res) => {
    console.log('Fetching all artworks');
    res.json(artworkService.getAll());
  })
  .post((req, res) =>{
    const name = req.body.name;
    const artist = req.body.artist;
    if(name == undefined || artist == undefined) {
      console.log('Cannot add artwork with name: ' + name + ', artist: ' + artist);
      res.json();
    }
    else{
      console.log('Adding artwork with name: ' + name + ', artist: ' + artist);
      res.json(artworkService.createArtwork(name,artist));
    }
  })

artworkRouter.route('/:artworkId')
  .get((req, res) => {
    const artworkId = req.params.artworkId;
    console.log('Fetching artwork with id: ' + artworkId);
    res.json(artworkService.getById(artworkId));
  })
  .delete((req, res) => {
    const artworkId = req.params.artworkId;
    console.log('Deleting artwork with id: ' + artworkId);
    res.json(artworkService.deleteById(artworkId));
  })
  .put((req, res) => {
    const artworkId = req.params.artworkId;
    console.log('Updating artwork with id: ' + artworkId);
    res.json(artworkService.updateById(artworkId, req.body));
  })

module.exports = artworkRouter;
