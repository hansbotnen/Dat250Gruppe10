class Artwork{
    constructor(id, name, artist, ownerId){
      this.id=id;
      this.name=name;
      this.artist=artist;
      this.ownerId=ownerId;
      this.bids = 'http://localhost:3000/artwork/'+id+'/bids';
    }
}
module.exports = Artwork;
