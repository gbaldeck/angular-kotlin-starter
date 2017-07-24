package io.angular.sample.wrapper

import io.angular.sample.common.isNullOrUndefined
import io.angular.sample.common.jsObjectOf
import io.angular.sample.common.push
import io.angular.sample.external.NgModule

/**
 * Created by gbaldeck on 7/8/2017.
 */
abstract class Module<T : Any> {
  var initialized = false

  open var imports: Array<dynamic>? = null
  open var exports: Array<dynamic>? = null
  open var declarations: Array<dynamic>? = null
  open var providers: Array<dynamic>? = null
  open var bootstrap: Array<dynamic>? = null
  open var entryComponents: Array<dynamic>? = null
  open var schemas: Array<dynamic>? = null

  protected object Module

  protected inline infix fun <T : io.angular.sample.wrapper.Module<M>, reified M : Any>
    Module.import(import: T) {

    this.importDynamic(import.initialize())
  }

  protected infix fun Module.importDynamic(import: dynamic) {
    if (isNullOrUndefined(imports))
      imports = arrayOf(import)
    else
      imports!!.push(import)
  }

  protected inline infix fun <reified T : Any> Module.export(component: Component<T>) {
    this.exportDynamic(component.initialize())
  }

  protected infix fun Module.exportDynamic(export: dynamic) {
    if (isNullOrUndefined(exports))
      exports = arrayOf(export)
    else
      exports!!.push(export)
  }

  protected inline infix fun <reified T : Any> Module.declaration(component: Component<T>) {
    val instance = component.initialize()
    if (isNullOrUndefined(declarations))
      declarations = arrayOf(instance)
    else
      declarations!!.push(instance)
  }

  protected inline infix fun <reified T : Any> Module.provider(provider: Injectable<T>) {
    val instance = provider.initialize()
    if (isNullOrUndefined(providers))
      providers = arrayOf(instance)
    else
      providers!!.push(instance)
  }

  protected inline infix fun <reified T : Any> Module.bootstraper(component: Component<T>) {
    val instance = component.initialize()
    if (isNullOrUndefined(bootstrap))
      bootstrap = arrayOf(instance)
    else
      bootstrap!!.push(instance)
  }

  protected inline infix fun <reified T : Any> Module.entryComponent(component: Component<T>) {
    val instance = component.initialize()
    if (isNullOrUndefined(entryComponents))
      entryComponents = arrayOf(instance)
    else
      entryComponents!!.push(instance)
  }

  protected infix fun Module.schema(schema: dynamic) {
    if (isNullOrUndefined(schemas))
      schemas = arrayOf(schema)
    else
      schemas!!.push(schema)
  }
}

inline fun <reified T : Any> Module<T>.initialize(): dynamic {
  if (!initialized) {
    initialized = true
    val ngModuleConfig = jsObjectOf(
      this::imports.name to imports,
      this::declarations.name to declarations,
      this::providers.name to providers,
      this::entryComponents.name to entryComponents,
      this::bootstrap.name to bootstrap,
      this::schemas.name to schemas,
      this::exports.name to exports
    )

    NgModule(ngModuleConfig)(T::class.js)
  }

  return T::class.js.asDynamic()
}