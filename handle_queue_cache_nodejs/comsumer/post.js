const postController = require("../controller/post")

const exchange = 'post';
const keys = ['create_post', 'update_post', 'like_post'];


exports.createChannel = (error1, channel) => {
    if (error1) {
        throw error1;
      }
  
      channel.assertExchange(exchange, 'topic', {
        durable: true,
      });
  
      channel.assertQueue('', {
        exclusive: true,
      }, (error2, q) => {
        if (error2) {
          throw error2;
        }
        console.log('connect rabbitmq server successfully');
  
        keys.forEach((key) => {
          channel.bindQueue(q.queue, exchange, key);
        });

        channel.prefetch(2);
  
        channel.consume(q.queue, (msg) => {
          const receivedKey = msg.fields.routingKey;
          const data = JSON.parse(msg.content.toString());
          if (receivedKey == 'create_post') {
            postController.createPost(data);
          } else if (receivedKey == 'update_post') {
            postController.updatePost(data);
          } else if (receivedKey == 'like_post') {
            postController.likePost(data)
          }
          channel.ack(msg);
          // setTimeout(function() {
          //   console.log(" [x] Done");
          //   channel.ack(msg);
          // }, 5000);
        }, {
          noAck: false,
        });
      });
}