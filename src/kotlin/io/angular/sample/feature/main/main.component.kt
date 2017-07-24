package io.angular.sample.feature.main

import io.angular.sample.external.require
import io.angular.sample.feature.admin.service.UserService
import io.angular.sample.feature.main.calendar.CalendarComponent
import io.angular.sample.feature.main.home.HomeComponent
import io.angular.sample.wrapper.Component

class MainComponent{
  companion object: Component<MainComponent>() {
    init {
      Component template require("./feature/main/main.component.html")
      Component stylesheet require("./feature/main/main.component.scss")
      Component inject UserService make optional
    }
  }

  val home = HomeComponent::class.js
  val calendar = CalendarComponent::class.js
}