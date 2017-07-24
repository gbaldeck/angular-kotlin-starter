const loaderUtils = require('loader-utils');
const kotlinCompiler = require('./compiler/kotlin-compiler');
const sourceMapResolve = require("source-map-resolve")
const fs = require('fs');
const path = require('path');

const SOURCE_MAP_RELATED_PATH = path.resolve('./', './compiler/file-name-doesnt-matter.js');

const dependencyCache = new Map();

function fillEmptySourcesContent(compileRes) {
    return new Promise((resolve, reject) => {
        sourceMapResolve.resolveSources(compileRes.sourceMap, SOURCE_MAP_RELATED_PATH, fs.readFile, (error, res) => {
            if (error) {
                return reject(error);
            }
            compileRes.sourceMap.sourcesContent = res.sourcesContent;

            resolve(compileRes);
        });
    });
}

function addAndCacheDependencies(entryFileName, pathes, addDependency) {
    pathes.forEach(addDependency);
    dependencyCache.set(entryFileName, pathes);
}

function addCachedDependencies(entryFileName, addDependency) {
    dependencyCache.get(entryFileName).forEach(addDependency);
}

module.exports = function (sourceCode) {
    this.cacheable();
    const addDependency = this.addDependency.bind(this);
    const options = loaderUtils.getOptions(this);

    const callback = this.async();

    if (!callback) {
        throw new Error('webpack-kotlin-loader currently only supports async mode.');
    }

    const filename = loaderUtils.getRemainingRequest(this);

    const sourcePathes = [filename, options.srcRoot].concat(options.srcRoots).filter(str => !!str);

    kotlinCompiler.compile(Object.assign({
        sources: sourcePathes,
        sourceMaps: true,
        moduleKind: 'commonjs',
        libraries: options.libraries || []
    }, options.compilerOptions))
        .then(fillEmptySourcesContent)
        .then(result => {
            addAndCacheDependencies(filename, result.sourceMap.sources, addDependency);
            return result;
        })
        .then(result => callback(null, result.compiledSource, result.sourceMap))
        .catch(err => {
            addCachedDependencies(filename, addDependency);
            callback(err);
        });
};