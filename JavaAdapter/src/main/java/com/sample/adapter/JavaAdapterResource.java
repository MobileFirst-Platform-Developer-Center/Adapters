/*
 *    Licensed Materials - Property of IBM
 *    5725-I43 (C) Copyright IBM Corp. 2015. All Rights Reserved.
 *    US Government Users Restricted Rights - Use, duplication or
 *    disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
*/

package com.mycompany;

import java.util.List;
import java.util.logging.Logger;
import java.io.IOException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;


@Path("/users")
public class JavaAdapterResource {
	/*
	 * For more info on JAX-RS see https://jsr311.java.net/nonav/releases/1.1/index.html
	 */
		
	//Define logger (Standard java.util.Logger)
	static Logger logger = Logger.getLogger(JavaAdapterResource.class.getName());



	/* Path for method: "<server address>/(project name)/adapters/JavaAdapter/users" */
	@GET
	@Produces("application/xml")
	public String hello(){
		//log message to server log
		logger.info("Logging info message...");

		return "<html><title>Hello from the Java REST adapter</title><body>Hello from the Java REST adapter</body></html>";
	}
		
	/* Path for method: "<server address>/(project name)/adapters/JavaAdapter/users/{username}" */
	@GET
	@Path("/{username}")
	public String helloUser(@PathParam("username") String name){
		return "Hello " + name;
	}
	
	/* Path for method: "<server address>/(project name)/adapters/JavaAdapter/users/helloUserQuery?name=value" */
	@GET
	@Path("/helloUserQuery")
	public String helloUserQuery(@QueryParam("username") String name){
		return "Hello " + name;
	}
	

	
	/* Path for method: "<server address>/(project name)/adapters/JavaAdapter/users/{first}/{middle}/{last}?age=value" */
	@POST
	@Path("/{first}/{middle}/{last}")
	public String enterInfo(@PathParam("first") String first, @PathParam("middle") String middle, @PathParam("last") String last,
			@QueryParam("age") int age, @FormParam("height") String height, @HeaderParam("Date") String date){
		return first +" "+ middle + " " + last + "\n" +
				"Age: " + age + "\n" +
				"Height: " + height + "\n" +
				"Date: " + date;
	}
	
	/* Path for method: "<server address>/(project name)/adapters/JavaAdapter/users/newUsers" */
	@PUT
	@Path("/newUsers")
	public String newUsers(@FormParam("username") List<String> users){
		if(users!=null && users.size() != 0){
			String usersTemp = "";
			int index = 0;
			for (String user : users) {
				usersTemp += " " +user;
				if(index < users.size() - 1 && users.size() != 2) usersTemp += ",";
				if(++index == users.size() -1 && users.size() != 1) usersTemp += " and";
			}
			return "Hello" + usersTemp;
		}
		
		return "Hello";
	}
	

		
}
