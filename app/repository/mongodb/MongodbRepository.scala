package repository.mongodb


import domain.Person
import org.mongodb.scala._
import repository.Repository
import scala.collection.JavaConverters._

class MongodbRepository extends Repository{

  val settings: MongoClientSettings = MongoClientSettings.builder()
    .applyToClusterSettings(b => b.hosts(List(new ServerAddress("localhost")).asJava))
    .build()
  val mongoClient: MongoClient = MongoClient(settings)

  val database: MongoDatabase = mongoClient.getDatabase("db")
  val collection: MongoCollection[Document] = database.getCollection("person")

  override def create(person: Person): Person = {
    val doc: Document = Document("_id" -> person.id, "name" -> person.name)
    val resr = collection.insertOne(doc)

  }

  override def update(person: Person): Person = ???

  override def delete(person: Person): Boolean = ???

  override def read(id: String): Person = ???

  override def readAll: List[Person] = ???
}
