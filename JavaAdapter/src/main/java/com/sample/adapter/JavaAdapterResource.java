/**
* Copyright 2016 IBM Corp.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.sample.adapter;

import org.apache.wink.json4j.JSONException;
import org.apache.wink.json4j.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;


@Path("/users")
public class JavaAdapterResource {
	/*
	 * For more info on JAX-RS see https://jax-rs-spec.java.net/nonav/2.0-rev-a/apidocs/index.html
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
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{first}/{middle}/{last}")
	public JSONObject enterInfo(@PathParam("first") String first, @PathParam("middle") String middle, @PathParam("last") String last,
							@QueryParam("age") int age, @FormParam("height") String height, @HeaderParam("birthdate") String birthdate) throws JSONException {
		JSONObject results = new JSONObject();
		results.put("first",first);
		results.put("middle",middle);
		results.put("last",last);
		results.put("age",age);
		results.put("height",height);
		results.put("birthdate",birthdate);
		return results;
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
