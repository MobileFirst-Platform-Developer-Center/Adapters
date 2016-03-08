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

@Path("/users")
public class JavaAdapterResource {

	/* Path for method: "<server address>/mfp/api/adapters/JavaAdapter/users/{first}/{middle}/{last}?age=value" */
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

}
