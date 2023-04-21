const Sequelize = require('sequelize')

const sequelize = require('../ultil/database')

const Post = sequelize.define('post', {
    id: {
        field: 'id',
        type: Sequelize.INTEGER,
        autoIncrement: true,
        primaryKey: true
    },
    content: {
        field: 'content',
        type: Sequelize.STRING,
        allowNull: false,
    },
    amountLike: {
        field: 'amount_like',
        type: Sequelize.INTEGER,
        defaultValue: 0,
        allowNull: false,
    },
    titleId: {
        field: 'title_id',
        type: Sequelize.INTEGER,
        references: {
            model: 'title', // 'fathers' refers to table name
            key: 'id', // 'id' refers to column name in fathers table
        },
        allowNull: false
    }
}
)

module.exports = Post