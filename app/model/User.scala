package model

import ebean.{User => EUser}
import scala.collection.JavaConversions._

/**
  * javaProject
  * Created by jeronimocarlos on 2/9/17.
  */
case class User(id: Int, email: String, name: String, password: String) {

  def toEbean = new EUser(id, email, name, password)

}
  case class UserObject(id: Int, email: String, name: String, password: String)

  object User {
    def all = EUser.users() map User.apply toList

    def apply(eUser: EUser): User = {
      new User(eUser.getId, eUser.getEmail, eUser.getName, eUser.getPassword)
    }
  }
