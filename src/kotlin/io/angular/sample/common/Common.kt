package io.angular.sample.common

/**
 * Created by gbaldeck on 7/8/2017.
 */
fun isNullOrUndefined(value: dynamic): Boolean = value === undefined || value === null

fun isNotNullOrUndefined(value: dynamic): Boolean = !isNullOrUndefined(value)

fun jsObjectOf(vararg pairs: Pair<String, dynamic>): dynamic {
  val obj: dynamic = Any()
  pairs.forEach {
    (key, value) ->
    obj[key] = value
  }
  return obj
}

fun jsObjectOf(map: Map<String, dynamic>): dynamic = jsObjectOf(*map.toList().toTypedArray())

fun <T> Array<T>.push(item: T) {
  this[size] = item
}

fun <T> Array<T>.pop(): T? {
  if(isEmpty())
    return null

  val thisVal = this
  val lastIndex = lastIndex
  val item = (js("thisVal.splice(lastIndex, 1)") as Array<T>)[0]
  return item;
}