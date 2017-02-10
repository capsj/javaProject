package controllers;

import com.avaje.ebean.Ebean;
import model.ebean.User;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.addUser;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * javaProject
 * Created by jeronimocarlos on 2/6/17.
 */
public class UserController extends Controller{


    @Inject FormFactory formFactory;

    public Result addUser() {
        return ok(addUser.render());
    }

    public Result getUserById(int id){
        User user;
        user = Ebean.find(User.class, id);
        Map<String, Object> map = new HashMap<>();
        if (user == null) {
            map.put("Status", "error");
            map.put("msg", "No user with id " + id + " found");
            return badRequest(Json.toJson(map));
        } else {
            map.put("Status", "ok");
            map.put("User", user);
            return ok(Json.toJson(map));
        }

    }

    public Result getUserByEmail(String email){
        User user;
        user = Ebean.find(User.class).where().eq("email", email).findUnique();
        Map<String, Object> map = new HashMap<>();
        if(user != null) {
            map.put("Status", "ok");
            map.put("User", user);
            return ok(Json.toJson(map));
        }
        else{
            map.put("Status", "error");
            map.put("msg", "No user with email: " + email + " found.");
            return badRequest(Json.toJson(map));
        }

    }

    public Result register(){
        Form<User> form = formFactory.form(User.class).bindFromRequest();
        User user = form.get();
        Map<String, Object> map = new HashMap<>();
        Boolean taken = false;

        if(Ebean.find(User.class).where().eq("email", user.getEmail()).findUnique() != null ||
                Ebean.find(User.class).where().eq("name", user.getName()).findUnique() != null){
            taken = true;
        }
        if(!taken) {
            user.save();
            map.put("Status", "ok");
            map.put("user", user);
            return ok(Json.toJson(map));
        } else {
            map.put("Status", "error");
            map.put("msg", "Name or email already taken");
            return badRequest(Json.toJson(map));
        }
    }

    public Result deleteUser(int id){
        if(Ebean.find(User.class,id) != null) {
            Ebean.delete(Ebean.find(User.class, id));
            return ok("Status: ok \nUser " + id + " deleted");
        }
        return badRequest("Status: error \nUser " + id + " couldn't be deleted");
    }

    public Result getUsers(){
        List<User> users = User.find.all();
        Map<String, Object> map = new HashMap<>();
        if(!users.isEmpty()) {
            map.put("Status", "ok");
            map.put("Users", users);
            return ok(Json.toJson(map));
        }
        else {
            map.put("Status", "error");
            map.put("msg", "couldn't get users");
            return badRequest(Json.toJson(map));
        }
    }

    public Result updateUser(int id){
        User user;
        Map<String, Object> map = new HashMap<>();
        Boolean exists = false;

        user = Ebean.find(User.class, id);
        if(user != null){
            exists = true;
            Form<User> form = formFactory.form(User.class).bindFromRequest();
            User update = form.get();
            if(user.getEmail().equals(update.getEmail()) && update.getEmail().equals("")){
                user.setEmail(update.getEmail());
            }
            if(user.getName() != update.getName() && update.getName().equals("")){
                user.setName(update.getName());
            }
            if(user.getPassword() != update.getPassword() && update.getPassword().equals("")){
                user.setPassword(update.getPassword());
            }
            Ebean.save(user);
        }
        if(exists){
            map.put("Status", "ok");
            map.put("User", user);
            return ok(Json.toJson(map));
        }
        else {
            map.put("Status", "error");
            map.put("msg", "User couldn't be updated");
            return badRequest(Json.toJson(map));
        }
    }

}
