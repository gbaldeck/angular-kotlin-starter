package io.angular.sample.feature.admin.signup

import io.angular.sample.external.require
import io.angular.sample.wrapper.Component

/**
 * Created by gbaldeck on 7/14/2017.
 */
class SignUpComponent{
  companion object: Component<SignUpComponent>() {
    init {
      Component template require("./feature/admin/signup/sign-up.component.html")
      Component stylesheet require("./feature/admin/signup/sign-up.component.scss")
    }
  }
}