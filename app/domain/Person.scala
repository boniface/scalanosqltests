package domain

import play.api.libs.json.Json

case class Person(id:String, name:String)

object Person {
  implicit val formatJson = Json.format[Person]

}
