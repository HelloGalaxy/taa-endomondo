package service;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.User;


@Path("/user")
public class UserManager {


private void init() {
	// TODO Auto-generated method stub

}
	
  // This method is called if HTML is request
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String sayHtmlHello() {
    return "<html> " + "<title>" + "Bonjour premier  test qvec jersy " + "</title>"
        + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
  }
  
  
 /*  @GET
  @Produces({ MediaType.APPLICATION_JSON })
  public Collection<User> getAllUser() {
      // return ;
  }*/
  
  
} 