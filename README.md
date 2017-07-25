# Angular-Kotlin Starter App

Not only is this a starter app, but also an instruction on how to use TypeScript/ESNext decorators with Kotlin. 

First, glance through this post on stack overflow so you get a better understanding of how decorators work:
 
 https://stackoverflow.com/questions/29775830/how-to-implement-a-typescript-decorator
 
Then look at the classes in the io.angular.sample.wrapper package to see how to use a
decorator in Kotlin.

To be more specific, look at line 141 of Component.kt to see the @Component decorator being used.
In this case it is renamed to NgComponent and is imported in the Angular.kt file in the io.angular.sample.external package.

Each class in the io.angular.sample.wrapper package uses their corresponding decorators in the initialize() extension function.

---

This app uses the following libraries together:

    - Angular 4
    - Kotlin
    - Onsenui Angular components
    - Webpack (with a modified version of huston007's kotlin-loader)
    
Note that the current output bundle size is huge, after taking it through minification and the new
Kotlin-js dead code elimination tool the size should be reduced dramatically.

https://discuss.kotlinlang.org/t/a-new-dead-code-elimination-tool-for-js/3777


To setup the project do:

    > npm install
    
    Also make sure the Kotlin compiler bin folder is on your path environment variable.
    The Kotlin compiler must be the same version of Kotlin that is in package.json.
    
    If you are using IntelliJ and want to use the compiler that comes with the Kotlin plugin
    just do a system wide search for 'kotlinc-js' and put the bin folder it finds on your path.
    
    For instance mine is C:\Users\gbaldeck\.IntelliJIdea2017.2\config\plugins\Kotlin\kotlinc\bin

To run the project do:

    > npm run build
    > npm run serve
    Navigate to localhost:8080
    
Then when you make changes do:

    > npm run build
    Refresh the browser