package services

import domain.Result

trait Service {
  def create (number: Long): Result
  def read:Result
  def update:Result
  def delete:Result
}

object CassandraService{
  def apply: Service = new CassandraService
}

object DgraphService{
  def apply: Service = new DgraphService
}

object RedisService{
  def apply: Service = new RedisService
}

object MongoDbService{
  def apply: Service = new MongoDbService
}


