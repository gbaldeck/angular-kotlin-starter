package io.angular.sample.external

/**
 * Created by gbaldeck on 7/12/2017.
 */
//@JsModule("onsenui")
//external val Onsen: dynamic

@JsModule("ngx-onsenui")
external val NgxOnsen:dynamic
val OnsenModule = NgxOnsen.OnsenModule

val onsNotification = NgxOnsen.onsNotification

