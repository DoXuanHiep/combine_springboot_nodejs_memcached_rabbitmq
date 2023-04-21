const Post = require("../models/post")
const Title = require("../models/title")

const memcachedPost = require("../memcached/post")

exports.createPost = async(data) => {
    const content = data.content
    const title = await Title.findByPk(data.titleId)

    try {
        if (!title) {
            const error = new Error('Could not find');
            error.statusCode = 404;
            throw error
        }

        const post = new Post({
            content: content,
            titleId: title.id,
        })

        const result = await post.save()
    } catch(err) {
        if (!err.statusCode) {
            err.statusCode = 500
        }
        const status = err.statusCode;
        const message = err.message
        const data = err.data
        console.log(status, message, data)
    }
}

exports.updatePost = async(data) => {
    const postId = data.postId;
    const post = await Post.findByPk(postId);
    
    try {
        if (!post) {
            const error = new Error('Could not find');
            error.statusCode = 404;
            throw error
        }
        
        if (data.titleId != null) {
            theTitle = await Title.findByPk(data.titleId)
            if (!post) {
                const error = new Error('Could not find');
                error.statusCode = 404;
                throw error
            }
            post.titleId = theTitle.id
        } 

        if (data.content !=null) {
            post.content = data.content
        } 

        const result = await post.save()

        const listId = memcachedPost.listId
        if (listId.includes(postId)) {
            memcachedPost.setPostsFromDbToMemcached()
        }

    } catch(err) {
        if (!err.statusCode) {
            err.statusCode = 500
        }
        const status = err.statusCode;
        const message = err.message
        const data = err.data
        console.log(status, message, data)
    }

}

exports.likePost = async(data) => {
    const postId = data.postId;
    const post = await Post.findByPk(postId);

    try {
        if (!post) {
            const error = new Error('Could not find');
            error.statusCode = 404;
            throw error
        }
        post.amountLike += 1
        const result = await post.save()
    } catch(err) {
        if (!err.statusCode) {
            err.statusCode = 500
        }
        const status = err.statusCode;
        const message = err.message
        const data = err.data
        console.log(status, message, data)
    }
}

exports.getPosts = async() => {
    const offset = 0
    const amountPost = 10
    try {
        const posts = await Post.findAndCountAll({
            include: Title,
            limit: amountPost,
            offset: offset,
            order: [
                ["amount_like", "DESC"],
            ],
        })
        return posts.rows
    } catch (err) {
        if (!err.statusCode) {
            err.statusCode = 500;
        }
        const status = err.statusCode;
        const message = err.message
        const data = err.data
        console.log(status, message, data)
    }
}