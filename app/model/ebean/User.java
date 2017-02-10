package model.ebean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.avaje.ebean.Model;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * javaProject
 * Created by jeronimocarlos on 2/6/17.
 */

@Entity
@Table(name = "User")
public class User extends Model {

    @Id
    @Expose
    private int id;
    @Expose
    private String email;
    @Expose
    private String name;
    @Expose
    private String password;

    public static Model.Finder<Integer, User> find = new Finder<>(User.class);

    public User() {}

    public User(@NotNull int id, String email, String name, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static List<User> users(){
        return find.all();
    }

}