package io.angular.sample.wrapper

import io.angular.sample.external.enableProdMode
import io.angular.sample.external.platformBrowserDynamic
import io.angular.sample.external.process

/**
 * Created by gbaldeck on 7/9/2017.
 */
object Angular
inline infix fun <reified T: Any> Angular.bootstrap(module: Module<T>) {
  if(process.env.ENV == "production"){
    enableProdMode()
  }

  platformBrowserDynamic().bootstrapModule(module.initialize())
}