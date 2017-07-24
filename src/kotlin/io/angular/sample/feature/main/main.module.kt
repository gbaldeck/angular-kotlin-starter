package io.angular.sample.feature.main

import io.angular.sample.external.CUSTOM_ELEMENTS_SCHEMA
import io.angular.sample.external.OnsenModule
import io.angular.sample.feature.main.calendar.CalendarComponent
import io.angular.sample.feature.main.home.HomeComponent
import io.angular.sample.feature.main.topbar.TopbarComponent
import io.angular.sample.wrapper.Module

/**
 * Created by gbaldeck on 7/14/2017.
 */
class MainModule{
  companion object: Module<MainModule>() {
    init {
      Module declaration TopbarComponent
      Module declaration HomeComponent
      Module declaration CalendarComponent
      Module declaration MainComponent

      Module entryComponent HomeComponent
      Module entryComponent CalendarComponent

      Module importDynamic OnsenModule
      Module schema CUSTOM_ELEMENTS_SCHEMA
    }
  }
}