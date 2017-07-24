package io.angular.sample.feature.main.calendar

import io.angular.sample.external.require
import io.angular.sample.wrapper.Component

/**
 * Created by gbaldeck on 7/13/2017.
 */
class CalendarComponent{
  companion object: Component<CalendarComponent>() {
    init {
      Component selector "ons-page"
      Component template require("./feature/main/calendar/calendar.component.html")
      Component stylesheet require("./feature/main/calendar/calendar.component.scss")
    }
  }
}