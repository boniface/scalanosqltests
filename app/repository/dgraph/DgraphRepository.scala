package repository.dgraph

import domain.Person
import repository.Repository
import io.dgraph.DgraphClient
import io.dgraph.DgraphGrpc
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import java.util.Collections
import collection.JavaConverters._

class DgraphRepository extends Repository{



  val channel: ManagedChannel = ManagedChannelBuilder.forAddress("localhost", 9080).usePlaintext(true).build
  val stub: DgraphGrpc.DgraphStub = DgraphGrpc.newStub(channel)
  val dgraphClient: DgraphClient = new DgraphClient(Collections.singletonList(stub).asScala)


  override def create(person: Person): Person = ???

  override def update(person: Person): Person = ???

  override def delete(person: Person): Boolean = ???

  override def read(id: String): Person = ???

  override def readAll: List[Person] = ???
}
