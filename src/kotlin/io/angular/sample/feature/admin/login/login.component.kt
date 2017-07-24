package io.angular.sample.feature.admin.login

import io.angular.sample.external.require
import io.angular.sample.wrapper.Component

/**
 * Created by gbaldeck on 7/14/2017.
 */
class LoginComponent{
  companion object: Component<LoginComponent>() {
    init {
      Component template require("./feature/admin/login/login.component.html")
      Component stylesheet require("./feature/admin/login/login.component.scss")
    }
  }
}