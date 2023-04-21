const Sequelize = require('sequelize')

const sequelize = require('../ultil/database')

const Title = sequelize.define('title', {
    id: {
        field: 'id',
        type: Sequelize.INTEGER,
        autoIncrement: true,
        primaryKey: true
    },
    genre: {
        field: 'genre',
        type: Sequelize.STRING,
        allowNull: false,
    }
}
)

module.exports = Title