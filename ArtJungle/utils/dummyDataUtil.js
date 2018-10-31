const account = require('../account/account');
const artwork = require('../artwork/artwork');
const bid = require('../bid/bid');


const accountDummies = [
                        new account('2721','Olav Thon'),
                        new account('a75b', 'Mikkel Mikkelsen')
                       ];

const artworkDummies = [
                        new artwork('y625','Skrik','Munch', '2721'),
                        new artwork('701a', 'Listhaug', 'AFK', '2721')
                       ];

const bidDummies = [
                    new bid('5352', 'a75b','701a',1000)
                   ];

module.exports = {
  accountDummies: accountDummies,
  artworkDummies: artworkDummies,
  bidDummies:bidDummies
}
