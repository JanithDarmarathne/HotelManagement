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
	public String insertProject(@FormParam("roomID") String room_ID, 
			@FormParam("roomName") String roomName,
			@FormParam("roomType") String roomType,
			@FormParam("bookingDate") String bookingDate) {
		String output = ProjectObj.insertProject(roomID, roomName, roomType, bookingDate);
		return output;

	}

	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateProject(String roomID) {
		// Convert the input string to a JSON object
		JsonObject ProObject = new JsonParser().parse(roomID).getAsJsonObject();

		// Read the values from the JSON object
		String roomID = ProObject.get("roomID").getAsString();
		String roomName = ProObject.get("roomName").getAsString();
		String roomType = ProObject.get("roomType").getAsString();
		String bookingDate = ProObject.get("bookingDate").getAsString();

		String output = ProjectObj.updateProject(roomID, roomName, roomType, bookingDate);
		return output;
	}

	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProject(String roomID) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(roomID, "", Parser.xmlParser());

		// Read the value from the element
		String roomID = doc.select("roomID").text();
		String output = ProjectObj.deleteProject(roomID);
		return output;
	}
}
