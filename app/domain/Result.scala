package domain

import java.time.LocalDateTime

import play.api.libs.json.Json

case class Result(start:LocalDateTime, end:LocalDateTime, duration:Long, objects:Long)

object Result{
  implicit val formatRes = Json.format[Result]
}
