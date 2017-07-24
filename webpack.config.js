/**
 * Created by gbaldeck on 7/11/2017.
 */
var path = require('path');

module.exports = {
    entry: './src/index.js',
    output: {
        filename: 'js/bundle.js',
        path: path.resolve(__dirname, 'www')
    },
    devServer: {
        contentBase: './www'
    },
    module: {
        rules: [
            {
                test: /\.kt$/,
                use: [
                    {
                        loader: path.resolve(__dirname, './kotlin-loader/loader.js'),
                        options: {
                            srcRoot: path.resolve(__dirname, './src/kotlin')
                        }
                    }
                ]
            },
            {
                test: /\.html$/,
                use: [
                    {
                        loader: 'html-loader',
                        options: {
                            minimize: true
                        }
                    }
                ]
            },
            {
                test: /\.css$/,
                use: [
                    'style-loader',
                    'css-loader'
                ]
            },
            {
                test: /\.scss$/,
                include: [
                    path.resolve(__dirname, './src/kotlin')
                ],
                use: [
                    'css-loader',
                    'sass-loader'
                ]
            },
            {
                test: /\.(png|svg|jpg|gif)$/,
                use: [
                    'file-loader?[name].[ext]&outputPath=img/'
                ]
            },
            {
                test: /\.(woff|woff2|eot|ttf|otf)$/,
                use: [
                    'file-loader?[name].[ext]&outputPath=font/'
                ]
            }
        ]
    }
};