package io.angular.sample.feature.admin.adminmenu

import io.angular.sample.external.require
import io.angular.sample.wrapper.Component

/**
 * Created by gbaldeck on 7/14/2017.
 */
class AdminMenuComponent{
  companion object: Component<AdminMenuComponent>() {
    init {
      Component template require("./feature/admin/adminmenu/admin-menu.component.html")
      Component stylesheet require("./feature/admin/adminmenu/admin-menu.component.scss")
    }
  }
}