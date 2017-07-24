var path = require('path');
var webpack = require('webpack');
var kotlinLoader = require.resolve('../../loader');

module.exports = {
  context: __dirname,
  devtool: 'source-map',
  entry: {
    main: './entry',
    vendor: ['kotlin']
  },
  output: {
    path: __dirname + '/dist',
    filename: 'build.js'
  },
  module: {
    rules: [
      {
        test: /\.kt$/,
        use: [
          {
            loader: kotlinLoader,
            options: {
              srcRoot: path.resolve(__dirname, './')
            }
          }
        ]
      }
    ]
  },
  plugins: [
    new webpack.optimize.CommonsChunkPlugin({
      name: 'vendor',
      filename: 'vendor.build.js'
    })
  ]
};
