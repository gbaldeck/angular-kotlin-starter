package io.angular.sample.feature.admin

import io.angular.sample.common.jsObjectOf
import io.angular.sample.external.CUSTOM_ELEMENTS_SCHEMA
import io.angular.sample.external.OnsenModule
import io.angular.sample.feature.admin.adminmenu.AdminMenuComponent
import io.angular.sample.feature.admin.login.LoginComponent
import io.angular.sample.feature.admin.service.UserService
import io.angular.sample.feature.admin.signup.SignUpComponent
import io.angular.sample.wrapper.Module
import io.angular.sample.wrapper.initialize

/**
 * Created by gbaldeck on 7/14/2017.
 */
class AdminModule {
  companion object : Module<AdminModule>() {
    init {
      Module declaration LoginComponent
      Module declaration SignUpComponent
      Module declaration AdminMenuComponent

      Module provider UserService

      Module importDynamic OnsenModule
      Module schema CUSTOM_ELEMENTS_SCHEMA
    }
  }
}