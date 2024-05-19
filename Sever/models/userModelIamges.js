const mongoose = require('mongoose');

const userSchemaImage = new mongoose.Schema({
  userName: { type: String, required: true },
  email: { type: String, required: true, unique: true },
  password: { type: String, required: true },
  avatar: { type: Array }, 
  createdAt: { type: Date, default: Date.now }
});

const userImages = mongoose.model('userSchemaImage', userSchemaImage);

module.exports = userImages;