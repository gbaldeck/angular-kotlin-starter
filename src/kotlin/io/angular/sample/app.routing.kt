package io.angular.sample

import io.angular.sample.common.jsObjectOf
import io.angular.sample.external.RouterModule
import io.angular.sample.feature.admin.AdminComponent
import io.angular.sample.feature.admin.service.UserService
import io.angular.sample.feature.main.MainComponent
import io.angular.sample.wrapper.Module

/**
 * Created by gbaldeck on 7/14/2017.
 */
class AppRouting{
  companion object: Module<AppRouting>() {
    init {
      Module importDynamic RouterModule.forRoot(
        arrayOf(
          jsObjectOf("path" to "", "component" to MainComponent::class.js),// "canActivate" to arrayOf(UserService::class.js)),
          jsObjectOf("path" to "login", "component" to AdminComponent::class.js)
        )
      )
      Module exportDynamic RouterModule
    }
  }
}