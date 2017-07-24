package io.angular.sample.feature.admin.service

import io.angular.sample.wrapper.Injectable

class UserService{
  companion object: Injectable<UserService>()

  val userLoggedIn = false
}