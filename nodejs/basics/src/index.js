const http = require('http');
const fs = require('fs');
const path = require('path');
const headers = {
  'Content-Type': 'text/html',
};

function getViewHtml(name, callback) {
  fs.readFile(path.join(__dirname, 'views', name), 'utf-8', (err, content) => {
    if (err) {
      throw err;
    }
    callback(content);
  });
}

const sever = http.createServer((req, res) => {
  if (req.method === 'GET') {
    res.writeHead(200, headers);

    if (req.url === '/') {
      getViewHtml('index.html', (content) => res.end(content));
    }
    if (req.url === '/about') {
      getViewHtml('about.html', (content) => res.end(content));
    }
  } else if (req.method === 'POST') {
    const body = [];
    let message;

    req.on('data', (data) => {
      body.push(Buffer.from(data));
    });

    req.on('end', () => {
      const match = /title=(.+)/.exec(body.toString());
      message = match[1];

      res.end(`
      <h1>Your message: ${message}</h1>
    `);
    });
  }
});

sever.listen(3000, () => {});
