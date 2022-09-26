package com.redhat.consulting.testdistributedcache;

import com.redhat.consulting.testdistributedcache.models.User;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyNamingStrategy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import java.util.Locale;

@Path("/hello-world")
public class HelloResource {
	
	private static final Logger LOG = Logger.getLogger(HelloResource.class);
	@Inject HttpServletRequest request;
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public User hello(User postData) {
		
		JsonbConfig jsonbConfig = new JsonbConfig()
				                          .withPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE_WITH_UNDERSCORES)
				                          .withFormatting(true);
		
		Jsonb jsonb = JsonbBuilder.newBuilder().withConfig(jsonbConfig).build();
		
		
	  // Do logic
		System.out.println("Request received");
		
		HttpSession session = request.getSession(true);
		
		String userJson = (String) session.getAttribute("user");
		
		User cachedUser = null;
		if (userJson != null) {
			cachedUser = jsonb.fromJson(userJson, User.class);
			postData.counter++;
		} else {
			LOG.warnf("No cached session object: %s.", postData.login.username);
			cachedUser = postData;
		}
		
		String jsonValue = jsonb.toJson(postData);
		request.getSession().setAttribute("user", jsonValue);
		return postData;
	}
	
	@GET
	@Produces("text/plain")
	public String hello() {
		return "Hello World";
	}
}