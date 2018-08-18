package repository

import domain.Person

trait Repository {

  def create(person: Person): Person

  def update(person: Person): Person

  def delete(person: Person): Boolean

  def read(id: String): Person

  def readAll: List[Person]
}


