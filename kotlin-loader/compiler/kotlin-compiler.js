const spawn = require('child_process').spawn;
const fs = require('fs');

const TMP_FILE_NAME = `${__dirname}/_compiled-tmp.js`;
const TMP_SOURCE_MAP_FILE_NAME = `${TMP_FILE_NAME}.map`;
const FILE_PROTO_PREFIX = 'file://';

const TURN_ON_RED_COLOR = '\033[31m';
const RESET_COLOR = '\033[0m';

function dropFilePrefixFromSourceUrls(sources) {
    return sources.map(path => {
        if (path.indexOf(FILE_PROTO_PREFIX) === 0) {
            return path.split(FILE_PROTO_PREFIX)[1];
        }
        return path;
    });
}

function onCompilationFinish() {
    return new Promise((resolve, reject) => {

        fs.readFile(TMP_SOURCE_MAP_FILE_NAME, (err, sourceMapBuffer) => {
            fs.readFile(TMP_FILE_NAME, (err, compiledSourceBuffer) => {
                if (err) {
                    return reject(err);
                }
                const compiledSource = compiledSourceBuffer.toString();
                const sourceMap = JSON.parse(sourceMapBuffer.toString());

                sourceMap.sources = dropFilePrefixFromSourceUrls(sourceMap.sources);

                resolve({sourceMap, compiledSource});
            });
        });
    });

}

function convertOptionsIntoArguments(options) {
    var argumentsList = [
        '-output',
        TMP_FILE_NAME,
        options.sourceMaps ? '-source-map' : null,
        options.noStdlib ? '-no-stdlib ' : null,
        options.metaInfo ? '-meta-info' : null,
        options.kjsm ? '-kjsm' : null,
        options.noWarn ? '-nowarn' : null,
        options.verbose ? '-verbose' : null
    ];

    if (options.main) {
        argumentsList = argumentsList.concat('-main', options.main);
    }

    if (options.moduleKind) {
        argumentsList = argumentsList.concat('-module-kind', options.moduleKind);
    }

    if (options.libraries && options.libraries.length) {
        argumentsList = argumentsList.concat('-libraries', options.libraries.join(','))
    }

    argumentsList = argumentsList.concat(options.sources)

    return argumentsList.filter(arg => !!arg);
}

function compile(options) {
    return new Promise((resolve, reject) => {
        var compilation = spawn(`kotlinc-js`,
            convertOptionsIntoArguments(options),
            {stdio: [process.stdin, process.stdout, 'pipe'], shell: true}
        );
        var hasErrors = false;
        var errors = '';
        var warnings = '';

        compilation.stderr.on('data', (data) => {
            if(data.toString().includes(": error:")){
                hasErrors = true;
                errors += data.toString();
            } else {
                warnings += data.toString();
            }

        });

        compilation.on('error', (err) => {
            hasErrors = true;
            errors += 'kotlin-js failed. do you have kotlin installed?';
            errors += JSON.stringify(err);
        });

        compilation.on('close', () => {
            console.warn(warnings);
            if (hasErrors === false) {
                resolve(onCompilationFinish());
            } else {
                console.error(TURN_ON_RED_COLOR, '\n kotlin-js compilation failed. \n', errors, RESET_COLOR);
                reject(errors);
            }
        });
    });
}

module.exports = {
    compile: compile
};