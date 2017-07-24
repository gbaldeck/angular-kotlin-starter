package io.angular.sample.wrapper

import io.angular.sample.common.isNullOrUndefined
import io.angular.sample.common.jsObjectOf
import io.angular.sample.common.pop
import io.angular.sample.common.push
import io.angular.sample.external.HostBinding
import io.angular.sample.external.NgComponent
import kotlin.reflect.KFunction
import kotlin.reflect.KProperty

/**
 * Created by gbaldeck on 7/8/2017.
 */
abstract class Component<T : Any> {
  var initialized = false

  open var selector: String? = null
  open var templateUrl: String? = null
  open var template: String? = null
  open var styles: Array<String>? = null
  open var styleUrls: Array<String>? = null
  open var providers: Array<dynamic>? = null
  open var inputs: Array<String>? = null
  open var encapsulation: dynamic = null
  val injects: Array<Pair<dynamic, Boolean>> = arrayOf()
  val hostBindings: Array<Pair<String, String>> = arrayOf()

  protected object Component

  protected infix fun Component.selector(selector: String) {
    this@Component.selector = selector
  }

  protected infix fun Component.templateUrl(templateUrl: String) {
    this@Component.templateUrl = templateUrl
  }

  protected infix fun Component.template(template: dynamic) {
    this@Component.template = template
  }

  protected infix fun Component.encapsulation(encapsulation: dynamic) {
    this@Component.encapsulation = encapsulation
  }

  protected infix fun Component.style(style: String) {
    if (isNullOrUndefined(styles))
      styles = arrayOf(style)
    else
      styles!!.push(style)
  }

  protected infix fun Component.stylesheet(stylesheet: dynamic) {
    if (isNullOrUndefined(this@Component.styles))
      this@Component.styles = arrayOf(stylesheet.toString())
    else
      this@Component.styles!!.push(stylesheet.toString())
  }

  protected infix fun Component.styleUrl(styleUrl: String) {
    if (isNullOrUndefined(styleUrls))
      styleUrls = arrayOf(styleUrl)
    else
      styleUrls!!.push(styleUrl)
  }

  protected inline infix fun <reified T : Any> Component.provider(provider: Injectable<T>) {
    val instance = provider.initialize()
    if (isNullOrUndefined(providers))
      providers = arrayOf(instance)
    else
      providers!!.push(instance)
  }

  protected inline infix fun <reified T : Any> Component.inject(inject: Injectable<T>):InjectWrapper {
    val instance = inject.initialize() as Any
    injects.push( instance to false)
    return InjectWrapper
  }

  protected object InjectWrapper
  protected object optional
  protected infix fun InjectWrapper.make(optional: optional){
    val jsClass = injects.pop()!!.first as Any
    injects.push(jsClass to true)
  }

  protected infix fun Component.input(property: KProperty<*>): InputName {
    if (isNullOrUndefined(inputs))
      inputs = arrayOf(property.name)
    else
      inputs!!.push(property.name)

    return InputName()
  }

  protected inner class InputName {
    infix fun named(name: String) {
      with(inputs!!) {
        val propertyName = pop()
        push("$propertyName: $name")
      }
    }
  }

  protected infix fun Component.hostBinding(binding: String): HostBindingWrapper {
    return HostBindingWrapper(binding)
  }

  protected class HostBindingWrapper(val binding: String)

  protected infix fun HostBindingWrapper.on(property: KProperty<*>) {
    hostBindings.push(binding to property.name)
  }

  protected infix fun HostBindingWrapper.on(function: KFunction<*>) {
    hostBindings.push(binding to function.name)
  }

}

inline fun <reified T : Any> Component<T>.initialize(): dynamic {
  if (!initialized) {
    initialized = true
    val ngComponentConfig = jsObjectOf(
      this::selector.name to selector,
      this::templateUrl.name to templateUrl,
      this::template.name to template,
      this::styles.name to styles,
      this::styleUrls.name to styleUrls,
      this::providers.name to providers,
      this::encapsulation.name to encapsulation
    )

    if (hostBindings.isNotEmpty()) {
      hostBindings.forEach { (binding, name) ->
        HostBinding(binding)(T::class.js.asDynamic().prototype, name)
      }
    }
    NgComponent(ngComponentConfig)(T::class.js)
  }

  return T::class.js.asDynamic()
}