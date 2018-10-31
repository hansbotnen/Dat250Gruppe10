const account = require('../account/account');
const artwork = require('../artwork/artwork');
const bid = require('../bid/bid');
const createId = require('./idUtil');

const accountDummies = [
                        new account('2721','Olav Thon'),
                        new account('a75b', 'Mikal Fuglestein')
                       ];
const artworkDummies = [
                        new artwork(createId(),'Skrik','Munch', '2721'),
                        new artwork('701a', 'Listhaug', 'AFK', '2721')
                       ];
const bidDummies = [
                    new bid(createId(), 'a75b','701a',3.50)
                   ];

module.exports = {
  accountDummies: accountDummies,
  artworkDummies: artworkDummies,
  bidDummies:bidDummies
}
