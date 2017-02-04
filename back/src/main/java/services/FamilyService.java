package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.core.MediaType;

import org.apache.jena.rdf.model.InfModel;
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

import business.InferenceRules;
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
		InferenceRules.getInfModel();
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
	
	@RequestMapping(value="/members/getNames/{id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public List<String> getMembersName(@PathVariable String id) {
		String familyURI = "http://familytree/" + id;
		Model model = RdfsModel.getModel();
		Resource familyResource = model.createResource(familyURI);
		
		if(model.contains(familyResource,RdfsModel.hasId)){
			System.out.println("yes");
			return SPARQLQueries.getMembersNameById(id);
		}
		else{
			System.out.println("no");
			return null;
		}		
    }
	
	@RequestMapping(value="/members/getAll/{familyId}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public List<Person> getAllMembers(@PathVariable String familyId) {
		String familyURI = "http://familytree/" + familyId;
		Model model = RdfsModel.getModel();
		Resource familyResource = model.createResource(familyURI);
		
		if(model.contains(familyResource,RdfsModel.hasId)){
			System.out.println("search members for family : " + familyId);
			List<Resource> memberResources = SPARQLQueries.getMembersById(familyId);
			List<Person> members = new ArrayList<Person>();
			for(Resource r : memberResources){
				members.add(RdfsModel.createMember(r));
			}
			return members;
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
		System.out.println("relation exists : " + member.getRelation());
		System.out.println("gender exists : " + member.getGender());
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
	
	@RequestMapping(value="/searchInCity/{familyId}/{city}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public List<Person> searchInCity(@PathVariable String familyId, @PathVariable String city) {
		String familyURI = "http://familytree/" + familyId;
		Model model = RdfsModel.getModel();
		Resource familyResource = model.createResource(familyURI);
		
		if(model.contains(familyResource,RdfsModel.hasId)){
			System.out.println("search members in city for family : " + familyId);
			List<Resource> memberResources = SPARQLQueries.searchMembersInCity(familyId, city);
			List<Person> members = new ArrayList<Person>();
			for(Resource r : memberResources){
				members.add(RdfsModel.createMember(r));
			}
			return members;
		}
		else{
			System.out.println("cannot search members in city for family : " + familyId);
			return null;
		}		
    }
	
	@RequestMapping(value="/relatives/{familyId}/{member}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public HashMap<String,List<Person>> findRelatives(@PathVariable String familyId, @PathVariable String member) {
		String familyURI = "http://familytree/" + familyId;
		String memberURI = "http://familytree/person/" + member.replaceAll("\\s+","");
		InfModel infModel = InferenceRules.getInfModel();
		Resource familyResource = infModel.createResource(familyURI);
		
		if(infModel.contains(familyResource,RdfsModel.hasId)){
			System.out.println("InfModel : " + infModel);
			System.out.println("search relatives of : " + memberURI);
			
			HashMap<String,List<Person>> relatives = new HashMap<String,List<Person>>();
			
			List<Person> aunts = new ArrayList<Person>();
			List<Resource> auntResources = SPARQLQueries.searchAunts(familyId, memberURI);
			for(Resource r : auntResources){
				System.out.println("search aunts of : " + memberURI);
				aunts.add(RdfsModel.createMember(r));
			}
			relatives.put("Aunt", aunts);
			
			List<Person> uncles = new ArrayList<Person>();
			List<Resource> uncleResources = SPARQLQueries.searchUncles(familyId, memberURI);
			for(Resource r : uncleResources){
				System.out.println("search uncles of : " + memberURI);
				uncles.add(RdfsModel.createMember(r));
				System.out.println("size uncles : " + uncles.size());
			}
			System.out.println("size uncles 2: " + uncles.size());
			relatives.put("Uncle", uncles);
			System.out.println("uncles in relatives" + relatives.get("uncle"));
			
			List<Person> cousins = new ArrayList<Person>();
			List<Resource> cousinResources = SPARQLQueries.searchCousins(familyId, memberURI);
			for(Resource r : cousinResources){
				System.out.println("search cousins of : " + memberURI);
				cousins.add(RdfsModel.createMember(r));
			}
			relatives.put("Cousin", cousins);
			
			List<Person> grandParents = new ArrayList<Person>();
			List<Resource> grandParentResources = SPARQLQueries.searchGrandParents(familyId, memberURI);
			for(Resource r : grandParentResources){
				System.out.println("search grandParents of : " + memberURI);
				grandParents.add(RdfsModel.createMember(r));
			}
			System.out.println("GrandParentsize : " + grandParents.size());
			relatives.put("GrandParent", grandParents);
			//relatives
			System.out.println("size : " + relatives.size());
			return relatives;
		}
		else{
			System.out.println("cannot search members in city for family : " + familyId);
			return null;
		}		
    }
	
}
