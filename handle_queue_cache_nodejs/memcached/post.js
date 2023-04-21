const Memcached = require('memcached')
const postController = require('../controller/post')

const memcached = new Memcached([`${process.env.MEMCACHED_IP}:11211`])

const listId = []

const setPostsFromDbToMemcached = async() => {
    const posts = await postController.getPosts()
    const data = posts.map((post) => {
        return {
            id: post.id,
            content: post.content,
            amountLike: post.amountLike,
            title: {
                titleId: post.titleId,
                genre: post.title.genre,
            }
        }
    })

    for (let i = 0; i < data.length; i++) {
        if (listId.length == data.length) {
            listId[i] = data[i].id
        } else {
            listId.push(data[i].id)
        }
        memcached.set(`post${i}`, JSON.stringify(data[i]), 120, function (err) { 
            console.log(err)
        });
    }
}

module.exports = {setPostsFromDbToMemcached, listId}
