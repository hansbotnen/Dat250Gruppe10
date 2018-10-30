class Account {
  constructor(id, name){ // temp, add more later
    this.id = id;
    this.name = name;
    this.artworks = 'http://localhost:3000/account/'+id+'/artworks';
    this.bids = 'http://localhost:3000/account/'+id+'/bids';
  }
}
module.exports = Account;
