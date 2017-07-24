# Simple test
npm run compile-simple

cmp examples/simple/compiled-reference.js examples/simple/dist/build.js && echo 'Simple test passed' || exit 123
