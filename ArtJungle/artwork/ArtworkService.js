const artwork = require('./artwork');

class ArtworkService {
  constructor() {
    this.artworks = [new artwork(1,'Skrik','Munch'), new artwork(2, 'Listhaug', 'AFK')];
  }

  getAll() {
    return this.artworks;
  }

  getById(reqId) {
    return this.artworks.find(obj => obj.id == reqId);
  }

  createArtwork(name, artist){
    var newArtwork = new artwork(this.artworks.length+1, name, artist);
    this.artworks.push(newArtwork);
    return newArtwork;
  }
}
module.exports = new ArtworkService();
