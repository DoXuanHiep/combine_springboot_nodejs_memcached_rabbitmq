const post = require('./post')

const handleMemcached = () => {
    setInterval(post.setPostsFromDbToMemcached, 10000)
    // setInterval(() => {
    //     console.log(post.listId)
    // }, 5000)
    
}

module.exports = handleMemcached