const artwork = require('./artwork');

class ArtworkService {
  constructor() {
    this.artworks = [new artwork(1,'Skrik','Munch'), new artwork(2, 'Listhaug', 'AFK')];
  }

  getAll() {
    return this.artworks;
  }

  getById(reqId) {
    return this.artworks.find(obj => ''+obj.id === reqId);
  }
}
module.exports = new ArtworkService();
