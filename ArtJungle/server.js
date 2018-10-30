const express = require('express');
const morgan = require('morgan');
const bodyParser = require('body-parser');
const artworkRouter = require('./artwork/ArtworkRouter');
const accountRouter = require('./account/accountRouter');
const bidRouter = require('./bid/bidRouter');
const app = express();
const port = 3000;


app.get('/', (req, res) => res.send('Hello World'));
app.listen(port, () => {
  console.log(`Listening on port ${port}`);
});
app.use(morgan('dev'));
app.use(bodyParser.json());
app.use('/artwork', artworkRouter);
app.use('/account', accountRouter);
app.use('/bid', bidRouter);
