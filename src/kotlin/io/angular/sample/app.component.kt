package io.angular.sample

import io.angular.sample.external.onsNotification
import io.angular.sample.external.require
import io.angular.sample.feature.main.calendar.CalendarComponent
import io.angular.sample.feature.main.home.HomeComponent
import io.angular.sample.wrapper.Component

/**
 * Created by gbaldeck on 7/8/2017.
 */
class AppComponent {
  companion object : Component<AppComponent>() {
    init {
      Component selector "sample-app"
      Component template require("./app.component.html")
    }
  }

  val home = HomeComponent::class.js
  val calendar = CalendarComponent::class.js

  fun alert() {
    onsNotification.alert("Hello, world!");
  }
}