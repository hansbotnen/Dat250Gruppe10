const express = require('express');
const artwork = require('./artwork');
const artworkService = require('./ArtworkService');
const artworkRouter = express.Router();
const accountService = require('../account/AccountService');

function ownerIdIsValid(ownerId){
  return accountService.getById(ownerId) != undefined;
}
function artworkIdIsValid(artworkId){
  return artworkService.getById(artworkId) != undefined;
}

artworkRouter.route('/')
  .get((req, res) => {
    console.log('Fetching all artworks');
    res.json(artworkService.getAll());
  })
  .post((req, res) =>{
    const name = req.body.name;
    const artist = req.body.artist;
    const ownerId = req.body.ownerId;

    if(name == undefined || artist == undefined || !ownerIdIsValid(ownerId)) {
      console.log('Cannot add artwork with'
        + ' name: '    + name
        + ', artist: ' + artist
        + ', ownerId: '  + ownerId);
      res.json();
    }
    else{
      console.log('Adding artwork with'
        + ' name: '    + name
        + ', artist: ' + artist
        + ', ownerId: '  + ownerId);
        res.json(artworkService.createArtwork(name,artist, ownerId));
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
    const ownerId = req.body.ownerId;
    if(!artworkIdIsValid(artworkId)){
      console.log('Cannot update artwork because artworkId: ' + artworkId
        + ' is invalid');
      res.json();
    }
    else if(ownerId != undefined && !ownerIdIsValid(ownerId)) {
      console.log('Cannot update artwork because ownerId: ' + ownerId
        + ' is invalid');
      res.json();
    }
    else{
      console.log('Updating artwork with id: ' + artworkId);
      res.json(artworkService.updateById(artworkId, req.body));
    }
  })

module.exports = artworkRouter;
