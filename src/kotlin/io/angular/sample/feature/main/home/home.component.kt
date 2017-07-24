package io.angular.sample.feature.main.home

import io.angular.sample.external.require
import io.angular.sample.wrapper.Component

/**
 * Created by gbaldeck on 7/13/2017.
 */
class HomeComponent{
  companion object: Component<HomeComponent>() {
    init {
      Component selector "ons-page"
      Component template require("./feature/main/home/home.component.html")
      Component stylesheet require("./feature/main/home/home.component.scss")
    }
  }
}