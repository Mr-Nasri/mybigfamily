package services;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.core.MediaType;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.StmtIterator;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import business.SPARQLQueries;
import model.Family;
import model.Person;
import model.RdfsModel;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
@RequestMapping(value="/family")
public class FamilyService {

	@RequestMapping(value="/create", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public String createFamily(@RequestBody Family family) {
		System.out.println("GOT IT");
    	String familyID = UUID.randomUUID().toString();
    	String familyURI = "http://familytree/" + familyID;

    	family.setId(familyID);
    	Model model = RdfsModel.getModel();

    	// create the resource
    	System.out.println(" 1 : " + model.toString());
    	System.out.println(" 2 : " + RdfsModel.getModel());
    	System.out.println(" 3 : " + familyURI);
    	System.out.println(" 4 : " + RdfsModel.family);
    	System.out.println(" 5 : " + family.getCreator());
    	Resource familyResource = model.createResource(familyURI, RdfsModel.family);
    	
    	Resource creatorResource = RdfsModel.createMember(family.getCreator());
    	familyResource.addProperty(RdfsModel.hasCreator, creatorResource);
    	familyResource.addProperty(RdfsModel.hasId, familyID);
    	familyResource.addProperty(RdfsModel.hasName, family.getName());
    	
    	RdfsModel.insertData();
    	//model.write(System.out);
    	
    	
    	return familyID;
    }
	
	@RequestMapping(value="/get/{id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public String getFamily(@PathVariable String id) {
		String familyURI = "http://familytree/" + id;
		System.out.println("uri :" + familyURI);
		Model model = RdfsModel.getModel();
		Resource familyResource = model.createResource(familyURI);
		System.out.println("resource" + familyResource);
		
		if(model.contains(familyResource,RdfsModel.hasId)){
			return "ok";
		}
		else{
			return "ko";
		}		
    }
	
	@RequestMapping(value="/members/get/{id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public List<String> getMembers(@PathVariable String id) {
		String familyURI = "http://familytree/" + id;
		Model model = RdfsModel.getModel();
		Resource familyResource = model.createResource(familyURI);
		
		if(model.contains(familyResource,RdfsModel.hasId)){
			System.out.println("yes");
			return SPARQLQueries.getMembersById(id);
		}
		else{
			System.out.println("no");
			return null;
		}		
    }
	
	@RequestMapping(value="/member/add/{id}", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public String addMember(@PathVariable String id, @RequestBody Person member) {
		String familyURI = "http://familytree/" + id;
		Model model = RdfsModel.getModel();
		Resource familyResource = model.createResource(familyURI);
		System.out.println("add member for family : " + familyURI);
		if(model.contains(familyResource,RdfsModel.hasId)){
			System.out.println(familyURI + " exists !");
			Resource memberResource = RdfsModel.createMember(member);
	    	familyResource.addProperty(RdfsModel.hasMember, memberResource);
	    	RdfsModel.insertData();
	    	return "ok";
		}
		else{
			System.out.println(familyURI + " does not exist !");
			return "";
		}		
    }
	
	@RequestMapping(value="/auth/{id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public List<String> authenticate(@PathVariable String id) {
		// check if id exists in the RDF (if family exists)
		// if exists
			//get from rdf all members of this family in the format (firstName + lastName)
			// return this list of Strings
		// else
			// return error message
		return null;
    }
	
	@RequestMapping(value="{id}/add", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public void addMember(@RequestBody Person person, @PathVariable String id) {
		// get family from rdf
		// get the member specified in person from family let's call it A
		// create the person
		// make the relation between person and A
		// update the RDF
		
    }
}
