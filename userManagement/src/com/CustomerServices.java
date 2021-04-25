package com;
import model.Customer; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Customer") 
public class CustomerServices 
{ 
 Customer customerObj = new Customer(); 
@GET
@Path("/") 
@Produces(MediaType.TEXT_HTML) 
public String readCustomer() 
 { 
 return customerObj.readCustomer(); 
 }


@POST
@Path("/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertCustomer(
 @FormParam("customerID")String customerID,
 @FormParam("customerIdentity") String customerIdentity, 
 @FormParam("customerName") String customerName, 
 @FormParam("customerEmail") String customerEmail, 
 @FormParam("customerAddress") String customerAddress) 
{ 
 String output = customerObj.insertCustomer(customerID,customerIdentity, customerName, customerEmail, customerAddress); 
return output; 
}



@PUT
@Path("/") 
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.TEXT_PLAIN) 
public String updateCustomer(String customerData) 
{ 
//Convert the input string to a JSON object 
 JsonObject customerObject = new JsonParser().parse(customerData).getAsJsonObject(); 
//Read the values from the JSON object
 String customerID = customerObject.get("customerID").getAsString(); 
 String customerIdentity = customerObject.get("customerIdentity").getAsString(); 
 String customerName = customerObject.get("customerName").getAsString(); 
 String customerEmail = customerObject.get("customerEmail").getAsString(); 
 String customerAddress = customerObject.get("customerAddress").getAsString(); 
 String output = customerObj.updateCustomer(customerID, customerIdentity, customerName, customerEmail, customerAddress); 
return output; 
}

@DELETE
@Path("/") 
@Consumes(MediaType.APPLICATION_XML) 
@Produces(MediaType.TEXT_PLAIN) 
public String deleteCustomer(String customerData) 
{ 
//Convert the input string to an XML document
 Document doc = Jsoup.parse(customerData, "", Parser.xmlParser()); 
 
//Read the value from the element <itemID>
 String customerID = doc.select("customerID").text(); 
 String output = customerObj.deleteCustomer(customerID); 
return output; 
}

}
