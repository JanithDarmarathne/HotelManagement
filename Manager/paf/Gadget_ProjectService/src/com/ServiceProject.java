package com;

import model.Project;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Project")
public class ServiceProject {
	Project ProjectObj = new Project();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProject() {
		return ProjectObj.readProject();
	}

	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertProject(@FormParam("loginId") String loginId, 
			@FormParam("loginUser") String loginUser,
			@FormParam("loginPass") String loginPass,
			@FormParam("loginEmail") String loginEmail) {
		String output = ProjectObj.insertProject(loginid, loginUser, loginPass, loginEmail);
		return output;

	}

	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateProject(String productData) {
		// Convert the input string to a JSON object
		JsonObject ProObject = new JsonParser().parse(productData).getAsJsonObject();

		// Read the values from the JSON object
		String loginId = ProObject.get("loginId").getAsString();
		String loginUser = ProObject.get("loginUser").getAsString();
		String loginPass = ProObject.get("loginPass").getAsString();
		String loginEmail = ProObject.get("loginEmail").getAsString();

		String output = ProjectObj.updateProject(loginId, loginUser, loginPass, loginEmail);
		return output;
	}

	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProject(String productData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(productData, "", Parser.xmlParser());

		// Read the value from the element
		String logId = doc.select("loginId").text();
		String output = ProjectObj.deleteProject(logId);
		return output;
	}
}
