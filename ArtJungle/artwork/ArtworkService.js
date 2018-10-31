const artwork = require('./artwork');
const createId = require('../utils/idUtil');
const dummies = require('../utils/dummyDataUtil');

class ArtworkService {
  constructor() {
    this.artworks = dummies.artworkDummies;
  }

  addDummies(dummies) {
    this.artworks = dummies;
  }

  idExists(id) {
    return this.getById(id) != undefined;
  }

  getAll() {
    return this.artworks;
  }

  getById(reqId) {
    return this.artworks.find(obj => obj.id == reqId);
  }

  createArtwork(name, artist, ownerId) {
    // TODO: validate input
    var newArtwork = new artwork(createId(), name, artist, ownerId);
    this.artworks.push(newArtwork);
    return newArtwork;
  }

  deleteById(reqId) {
    var deleted = this.getById(reqId);
    this.artworks = this.artworks.filter(obj => obj.id != reqId);
    return deleted;
  }

  updateById(reqId, req) {
    // TODO: validate input
    var artwork = this.deleteById(reqId);
    artwork = Object.assign({}, artwork, req);
    this.artworks.push(artwork);
    return artwork;
  }

  getByOwnerId(reqId){
    return this.artworks.filter(obj => obj.ownerId == reqId);
  }
}
module.exports = new ArtworkService();
