package io.angular.sample.feature.admin

import io.angular.sample.external.require
import io.angular.sample.wrapper.Component

class AdminComponent{
  companion object: Component<AdminComponent>() {
    init {
      Component template require("./feature/admin/admin.component.html")
      Component stylesheet require("./feature/admin/admin.component.scss")
    }
  }
}