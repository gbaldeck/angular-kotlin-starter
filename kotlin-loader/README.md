# kotlin-loader

### Kotlin webpack loader that allows importing Kotlin package into your JS

#### Please note that this loader is not production ready, it's still under development.

It uses kotlinc-js compiler to convert Kotlin code to JavaScript. Note that Kotlin codebase is not file-based, but package-based. So it's impossible to compile file-to-file at the moment, so you should import your kotlin entry point once.

See [build example](https://github.com/huston007/kotlin-loader/tree/master/examples/simple), [app example](https://github.com/huston007/kotlin-loader-example).

Usage:

```sh
npm i webpack-kotlin-loader --save-dev
```

Do not forget to install kotlin runtime:
```sh
npm i kotlin --save
```

Usage (for webpack 2):
`webpack.config.js`
```js
var path = require('path');
var webpack = require('webpack');

module.exports = {
  context: __dirname,
  devtool: 'source-map',
  entry: {
    main: './entry'
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
            loader: 'webpack-kotlin-loader',
            options: {
              srcRoot: path.resolve(__dirname, './')
            }
          }
        ]
      }
    ]
  }
};

```
Where `srcRoot` should be set to root directory which contains your kotlin sources.

Then you could import your kotlin entry point somewhere:
```js
require('./app/app.kt');
```
