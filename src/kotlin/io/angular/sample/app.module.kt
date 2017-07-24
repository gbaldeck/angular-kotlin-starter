package io.angular.sample

import io.angular.sample.external.BrowserModule
import io.angular.sample.external.CUSTOM_ELEMENTS_SCHEMA
import io.angular.sample.external.OnsenModule
import io.angular.sample.feature.admin.AdminModule
import io.angular.sample.feature.main.MainModule
import io.angular.sample.wrapper.Module

/**
 * Created by gbaldeck on 7/8/2017.
 */
class AppModule{
  companion object: Module<AppModule>() {
    init{
      Module import MainModule
      Module import AdminModule
      Module import AppRouting
      Module importDynamic BrowserModule
//      Module importDynamic DndModule.forRoot()

      Module declaration AppComponent
      Module bootstraper AppComponent
    }
  }
}