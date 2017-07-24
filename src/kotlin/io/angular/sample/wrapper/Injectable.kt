package io.angular.sample.wrapper

import io.angular.sample.common.jsObjectOf
import io.angular.sample.external.NgInjectable

abstract class Injectable<T>{
  var initialized = false
}

inline fun <reified T : Any> Injectable<T>.initialize(): dynamic {
  if (!initialized) {
    initialized = true
    NgInjectable()(T::class.js)
  }

  return T::class.js.asDynamic()
}

