const post = require('./post')

const handleMemcached = () => {
    setInterval(post.setPostsFromDbToMemcached, 5000)
    // setInterval(() => {
    //     console.log(post.listId)
    // }, 5000)
    
}

module.exports = handleMemcached