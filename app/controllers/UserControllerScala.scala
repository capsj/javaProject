package controllers


import com.avaje.ebean.Ebean
import com.google.inject.Inject
import model._
import play.api.libs.json.{Json}
import play.api.mvc.{Action, Controller}

/**
  * javaProject
  * Created by jeronimocarlos on 2/8/17.
  */

case class UserControllerScala @Inject() () extends Controller{

//  implicit val userWrites = new Writes[User] {
//    def writes(user: User) = Json.obj(
//      "id" -> user.getId,
//      "name" -> user.getName,
//      "email" -> user.getEmail,
//      "password" -> user.getPassword
//    )
//  }
//

//  def getUserById(id: Int) = Action{
//    val user: User = Ebean.find(classOf[User], id)
//    if(user != null) Ok(Json.obj("Status" -> "ok", "User" -> Json.toJson(user)))
//    else BadRequest(Json.obj("Status" -> "error", "msg" -> ("No user found for id: " + id)))
//  }
//
//  def getUserByEmail(email: String) = Action{
//    val user: User = Ebean.find(classOf[User]).where().eq("email", email).findUnique()
//    if(user != null) Ok(Json.obj("Status" -> "ok", "User" -> Json.toJson(user)))
//    else BadRequest(Json.obj("Status" -> "error", "msg" -> ("No user found for email: " + email)))
//  }

  def users() = Action{
    Ok(Json.toJson(User.all))
//    val users = Ebean.find(classOf[User]).findList()
//    if(!users.isEmpty) Ok(Json.obj("Status" -> "ok", "Users" -> Json.toJson(users)))
//    else BadRequest(Json.obj("Status" -> "error", "msg" -> "no users found"))
  }

//  def register() = Action{
//    Ok("")
//  }
//
//  def deleteUser() = Action{
//     Ok("")
//  }
//
//  def updateUser() = Action{
//    Ok("")
//  }
}
