const mongoose =  require('mongoose');
var express = require('express');
const app = express();

const uri = "mongodb+srv://lehuyair97:Lehuy1997@lehuy.d7b1k90.mongodb.net/FurnitureAPI?retryWrites=true&w=majority&appName=LeHuy";

mongoose.set('strictQuery',false);

const connect = mongoose.connect(uri)
.then(()=>{
    console.log("connected to mongoDB")
})
.catch((error)=>{
    console.log(error)
})
module.exports= {connect};
