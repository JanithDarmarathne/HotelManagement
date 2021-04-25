package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.payment;
 
//@Path("/payment")
@Path("/payment")
public class paymentService {

	payment ProjectObj=new payment();
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
	public String insertProject(@FormParam("payID") String payID, 
			@FormParam("payDate") String payDate,
			@FormParam("payAmt") String payAmt,
			@FormParam("payDesc") String payDesc) {
		String output = ProjectObj.insertProject(payID, payDate,payAmt,payDesc);
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
		String payID = ProObject.get("payID").getAsString();
		String payDate = ProObject.get("payDate").getAsString();
		String payAmt = ProObject.get("payAmt").getAsString();
		String payDesc = ProObject.get("payDesc").getAsString();

		String output = ProjectObj.updateProject(payID, payDate, payAmt,payDesc);
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
		String pId = doc.select("payID").text();
		String output = ProjectObj.deleteProject(pId);
		return output;
	}
}

