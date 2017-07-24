package io.angular.sample.feature.main.topbar

import io.angular.sample.external.require
import io.angular.sample.wrapper.Component

/**
 * Created by gbaldeck on 7/13/2017.
 */
class TopbarComponent{
  companion object: Component<TopbarComponent>() {
    init {
      Component selector "topbar-component"
      Component template require("./feature/main/topbar/topbar.component.html")
      Component stylesheet require("./feature/main/topbar/topbar.component.scss")
//      Component encapsulation ViewEncapsulation.None
      Component hostBinding "class" on TopbarComponent::hb_RequiredClass
    }
  }

  val title = "sample"
  val hb_RequiredClass = "toolbar"
}