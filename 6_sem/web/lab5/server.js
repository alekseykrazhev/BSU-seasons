const express = require('express');
const app = express();
const path = require('path');

// serve index.html
app.get('/', function(req, res) {
  console.log('started server with return index.html');
  res.sendFile(path.join(__dirname, 'index.html'));
});

// download json file
app.get('/download', function(req, res) {
  res.download(path.join(__dirname, 'data', 'data.json'));
});

app.listen(3000, function() {
  console.log('Server started on port 3000');
});
