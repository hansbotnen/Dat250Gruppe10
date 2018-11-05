module.exports = (app) => {
  const artwork = require('../controllers/artwork.controller.js');
   app.post('/artworks', artwork.create);
   
   app.get('/artworks', artwork.findAll);

   app.get('/artworks/:artworkId', artwork.findOne);

   app.delete('/artworks/:artworkId', artwork.deleteOne);

   app.put('/artworks/:artworkId', artwork.updateOne);
}