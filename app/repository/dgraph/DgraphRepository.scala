package repository.dgraph

import java.util
import java.util.Collections

import com.google.gson.Gson
import com.google.protobuf.ByteString
import domain.Person
import io.dgraph.DgraphProto.{Mutation, Operation}
import io.dgraph.{DgraphClient, DgraphGrpc}
import io.grpc.{ManagedChannel, ManagedChannelBuilder}
import repository.Repository

import scala.collection.JavaConverters._

class DgraphRepository extends Repository {


  val channel: ManagedChannel = ManagedChannelBuilder
    .forAddress("localhost", 9080)
    .usePlaintext(true)
    .build
 val  gson = new Gson()
  val stub: DgraphGrpc.DgraphBlockingStub = DgraphGrpc.newBlockingStub(channel)
  val stubs: util.List[DgraphGrpc.DgraphBlockingStub] = List[DgraphGrpc.DgraphBlockingStub](stub).asJava
  val dgraphClient: DgraphClient = new DgraphClient(stubs)

  val schema = " id: string @index(exact) .\n" +
              " name: string   @index(int)  .\n"

  val op = Operation.newBuilder().setSchema(schema).build()
  dgraphClient.alter(op)


  override def create(person: Person): Person = {
    val txn = dgraphClient.newTransaction()
    val  json = gson.toJson(person)
    val mu = Mutation.newBuilder()
      .setSetJson(ByteString.copyFromUtf8(json.toString))
      .build()
    txn.mutate(mu)
    txn.commit()
    txn.discard()
    person
  }

  override def update(person: Person): Person = {
    val updatedPerson = person.copy(name = person.name+ " Updated ")
    val txn = dgraphClient.newTransaction()
    val  json = gson.toJson(updatedPerson)
    val mu = Mutation.newBuilder()
      .setSetJson(ByteString.copyFromUtf8(json.toString))
      .build()
    txn.mutate(mu)
    txn.commit()
    txn.discard()
    updatedPerson

  }

  override def delete(person: Person): Boolean = ???

  override def read(id: String): Person = {
    val query = "query person($a: string) { person(func: eq(name, $a)) { name }}"
    val  results  = Collections.singletonMap("$a", "Alice")
    val res = dgraphClient.newTransaction().queryWithVars(query, results)
  }

  override def readAll: List[Person] = ???

}
