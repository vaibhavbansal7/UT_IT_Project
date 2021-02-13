package com.knoldus.db

import com.knoldus.models.User
import scala.collection.immutable.HashMap

class UserReadDto {

  val vaibhavUser: User = User("Vaibhav","Bansal","knoldus","vaibhav.bansal@knoldus.com")
  val nitinUser: User = User("Nitin","Mittal","knoldus","nitin.mittal@knoldus.com")
  val users: HashMap[String, User] = HashMap("Vaibhav" -> vaibhavUser, "Nitin" -> nitinUser)

  def getUserByName(name: String): Option[User] = users.get(name)
}
