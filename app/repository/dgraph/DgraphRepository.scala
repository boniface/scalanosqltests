package repository.dgraph

import java.util

import domain.Person
import io.dgraph.DgraphProto.Operation
import io.dgraph.{DgraphClient, DgraphGrpc}
import io.grpc.{ManagedChannel, ManagedChannelBuilder}
import repository.Repository

import scala.collection.JavaConverters._

class DgraphRepository extends Repository {


  val channel: ManagedChannel = ManagedChannelBuilder
    .forAddress("localhost", 9080)
    .usePlaintext(true)
    .build
  val stub: DgraphGrpc.DgraphBlockingStub = DgraphGrpc.newBlockingStub(channel)
  val stubs: util.List[DgraphGrpc.DgraphBlockingStub] = List[DgraphGrpc.DgraphBlockingStub](stub).asJava
  val dgraphClient: DgraphClient = new DgraphClient(stubs)

  val schema = " id: string @index(exact) .\n" +
              " name: string   @index(int)  .\n"

  val op = Operation.newBuilder().setSchema(schema).build()
  dgraphClient.alter(op)


  override def create(person: Person): Person = ???

  override def update(person: Person): Person = ???

  override def delete(person: Person): Boolean = ???

  override def read(id: String): Person = ???

  override def readAll: List[Person] = ???

}
