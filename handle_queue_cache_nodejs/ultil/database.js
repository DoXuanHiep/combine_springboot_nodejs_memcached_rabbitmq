const { Sequelize } = require('sequelize');
require('dotenv').config()

const sequelize = new Sequelize(process.env.DATABASE_TABLE, process.env.DATABASE_USER, process.env.DATABASE_PASSWORD, {
    host: process.env.DATABASE_IP,
    define: {
        freezeTableName: true,
        timestamps: false,
      },
    dialect: 'mysql',
});

module.exports = sequelize