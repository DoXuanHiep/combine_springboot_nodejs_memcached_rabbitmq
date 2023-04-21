const express = require("express")
const amqp = require('amqplib/callback_api');
const consumerPost = require('./comsumer/post')
const sequelize = require('./ultil/database')
const handleMemcached = require("./memcached/index")

//>>ENV
require('dotenv').config()

//DATABSE ASSCOCIATE
const Post = require('./models/post');
const Title = require("./models/title");

const app = express()

amqp.connect(`amqp://${process.env.RABBITMQ_USER}:${process.env.RABBITMQ_PASSWORD}@${process.env.RABBITMQ_IP}`, (error0, connection) => {
  if (error0) {
    throw error0;
  }
  connection.createChannel((error1, channel) => {
    consumerPost.createChannel(error1, channel)
  });
});

//setting asscociation for table
Title.hasMany(Post)
Post.belongsTo(Title)

//memcached
handleMemcached()

sequelize
    .authenticate()
    .then(() => {
      console.log("connect db successfully")
    })
    .catch (err => {
        console.log(err)
    })


