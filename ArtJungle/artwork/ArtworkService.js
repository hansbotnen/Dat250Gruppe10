const artwork = require('./artwork');
const createId = require('../utils/idUtil');

class ArtworkService {
  constructor() {
    this.artworks = [new artwork(createId(),'Skrik','Munch'), new artwork(createId(), 'Listhaug', 'AFK')];
  }

  getAll() {
    return this.artworks;
  }

  getById(reqId) {
    return this.artworks.find(obj => obj.id == reqId);
  }

  createArtwork(name, artist) {
    var newArtwork = new artwork(createId(), name, artist);
    this.artworks.push(newArtwork);
    return newArtwork;
  }

  deleteById(reqId) {
    var deleted = this.getById(reqId);
    this.artworks = this.artworks.filter(obj => obj.id != reqId);
    return deleted;
  }

  updateById(reqId, req) {
    var artwork = this.deleteById(reqId);
    artwork = Object.assign({}, artwork, req);
    this.artworks.push(artwork);
    return artwork;
  }
}
module.exports = new ArtworkService();
