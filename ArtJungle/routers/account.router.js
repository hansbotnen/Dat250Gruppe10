module.exports = (app) => {
  const account = require('../controllers/account.controller.js');
   app.post('/accounts', account.create);

   app.get('/accounts', account.findAll);

   app.get('/accounts/:accountId', account.findOne);

   app.delete('/accounts/:accountId', account.deleteOne);

   app.post('/accounts/:accountId/update', account.updateOne);
   
   app.post('/accounts/authenticate', account.authenticate);
}
