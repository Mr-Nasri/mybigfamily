package services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.core.MediaType;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.VCARD;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import model.Family;
import model.RdfsModel;

@RestController
@EnableAutoConfiguration
@RequestMapping(value="")
public class TestService {

    @RequestMapping("/hello")
    String hello() {
        return "Hello Achraf";
    }
    
    @RequestMapping(value="/test", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public List<String> getStrings() {
        List<String> tests = new ArrayList<>();
        tests.add("test1");
        tests.add("test2");
        tests.add("test3");
        return tests;
    }
    
    @RequestMapping(value="/create/{name}/{creator}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public void testFramework(@PathVariable String name, @PathVariable String creator) {
    	// some definitions
    	
    	
    	String familyID = UUID.randomUUID().toString();
    	String familyURI    = "http://familytree/" + familyID;
    	
    	String creatorURI    = "http://familytree/person/" + creator;

    	// create an empty Model
    	Model model = RdfsModel.getModel();

    	// create the resource
    	Resource familyResource = model.createResource(familyURI);
    	Resource creatorResource = model.createResource(creatorURI);
    	
    	// add the property
    	familyResource.addProperty(RdfsModel.hasCreator, creatorResource);
    	creatorResource.addProperty(RdfsModel.hasFistName, "achraf");
    	creatorResource.addProperty(RdfsModel.hasLastName, "nasri");
    	model.write(System.out);
    }
}
