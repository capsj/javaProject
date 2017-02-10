package model;


import org.junit.*;
import static org.junit.Assert.*;
import play.test.WithApplication;
import static play.test.Helpers.*;

/**
 * javaProject
 * Created by jeronimocarlos on 2/6/17.
 */
public class ModelsTest extends WithApplication{
    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
    }

    @Test
    public void createAndRetrieveUser() {
        new model.ebean.User("bob@gmail.com", "Bob", "secret").save();
        model.ebean.User bob = model.ebean.User.find.where().eq("email", "bob@gmail.com").findUnique();
        assertNotNull(bob);
        assertEquals("Bob", bob.name);
    }

    @Test
    public void tryAuthenticateUser() {
        new model.ebean.User("bob@gmail.com", "Bob", "secret").save();

        assertNotNull(model.ebean.User.authenticate("bob@gmail.com", "secret"));
        assertNull(model.ebean.User.authenticate("bob@gmail.com", "badpassword"));
        assertNull(model.ebean.User.authenticate("tom@gmail.com", "secret"));
    }
}
