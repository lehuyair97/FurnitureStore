const mongoose = require('mongoose');

const userSchema = new mongoose.Schema({
  userName: { type: String, required: true },
  email: { type: String, required: true, unique: true },
  password: { type: String, required: true },
  avatar: { type: String }, 
  address:{type:String},
  phoneNumber:{type:Number},
  favorites:{
    type: Array,
    default: []
  },
  paymentMethod:{
    type: Array,
    default: []
  },
  createdAt: { type: Date, default: Date.now }
});

const User = mongoose.model('User', userSchema);

module.exports = User;