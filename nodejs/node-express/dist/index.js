"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const path_1 = __importDefault(require("path"));
const app = express_1.default();
const PORT = process.env.PORT || 3000;
app.get('/', (req, res) => {
    res.sendFile(path_1.default.join(__dirname, '../views', 'index.html'));
});
app.get('/about', (req, res) => {
    res.sendFile(path_1.default.join(__dirname, '../views', 'about.html'));
});
app.listen(PORT, () => {
    console.log('Listening of port: ', PORT);
});
//# sourceMappingURL=index.js.map