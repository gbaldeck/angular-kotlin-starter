package io.angular.sample.external

/**
 * Created by gbaldeck on 7/8/2017.
 */
external val ng: dynamic

@JsModule("@angular/platform-browser-dynamic")
external val AngularPlatformBrowserDynamic: dynamic
val platformBrowserDynamic: dynamic = AngularPlatformBrowserDynamic.platformBrowserDynamic

@JsModule("@angular/platform-browser")
external val AngularPlatformBrowser: dynamic
val BrowserModule = AngularPlatformBrowser.BrowserModule

@JsModule("@angular/common")
external val AngularCommon: dynamic
val CommonModule = AngularCommon.CommonModule

@JsModule("@angular/core")
external val AngularCore: dynamic
val enableProdMode = AngularCore.enableProdMode
val NgComponent = AngularCore.Component
val NgModule = AngularCore.NgModule
val NgInjectable = AngularCore.Injectable
val NgInject = AngularCore.Inject
val NgOptional = AngularCore.Optional
val CUSTOM_ELEMENTS_SCHEMA = AngularCore.CUSTOM_ELEMENTS_SCHEMA
val ViewEncapsulation = AngularCore.ViewEncapsulation
val HostBinding = AngularCore.HostBinding

//@JsModule("@angular/compiler")
//external val AngularCompiler: dynamic

@JsModule("@angular/forms")
external val AngularForms: dynamic
val FormsModule = AngularForms.FormsModule

@JsModule("@angular/http")
external val AngularHttp: dynamic

@JsModule("@angular/router")
external val AngularRouter: dynamic
val RouterModule = AngularRouter.RouterModule
val Routes = AngularRouter.Routes
val Router = AngularRouter.Router
val ActivatedRouteSnapshot = AngularRouter.ActivatedRouteSnapshot
val RouterStateSnapshot = AngularRouter.RouterStateSnapshot