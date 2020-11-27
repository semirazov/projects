import express from 'express';
import path from 'path';
const app: express.Application = express();

const PORT = process.env.PORT || 3000;

app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, '../views', 'index.html'));
});
app.get('/about', (req, res) => {
  res.sendFile(path.join(__dirname, '../views', 'about.html'));
});

app.listen(PORT, () => {
  console.log('Listening of port: ', PORT);
});
