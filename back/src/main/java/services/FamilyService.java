package services;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.core.MediaType;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Family;
import model.Person;
import model.RdfsModel;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
@RequestMapping(value="/family")
public class FamilyService {

	@RequestMapping(value="/create", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public String testFramework(@RequestBody Family family) {
		System.out.println("GOT IT");
    	String familyID = UUID.randomUUID().toString();
    	String familyURI = "http://familytree/" + familyID;

    	family.setId(familyID);
    	Model model = RdfsModel.getModel();

    	// create the resource
    	Resource familyResource = model.createResource(familyURI);
    	Resource creatorResource = RdfsModel.addMember(family.getCreator());
    	familyResource.addProperty(RdfsModel.hasCreator, creatorResource);
    	familyResource.addProperty(RdfsModel.hasId, familyID);
    	familyResource.addProperty(RdfsModel.hasName, family.getName());
    	
    	model.write(System.out);
    	
    	return familyID;
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
