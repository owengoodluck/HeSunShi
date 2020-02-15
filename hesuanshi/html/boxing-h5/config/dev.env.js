var merge = require('webpack-merge')
var prodEnv = require('./prod.env')
//API_ROOT: '"//127.0.0.1:81"'
module.exports = merge(prodEnv, {
	NODE_ENV: '"development"',
	API_ROOT: '"//127.0.0.1:82"'
})
