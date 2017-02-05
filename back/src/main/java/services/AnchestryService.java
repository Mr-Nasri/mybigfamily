package services;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.core.MediaType;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.gedcom4j.exception.GedcomParserException;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import business.SPARQLQueries;
import model.Family;
import model.GedRdfsModel;
import model.GedcomPrecess;
import model.Person;
import model.RdfsModel;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
@RequestMapping(value="/anchestry")
public class AnchestryService {
	
	@RequestMapping(value="/achestres", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public void getAnchestry() {
		
		//GedcomPrecess.intit("/home/user/todoWeb/mybigfamily/back/gedcom/gedr7420.ged");
		//Map<String, Person> result = null;
		try {

			    GedcomPrecess.getFamiliesMembers("geddata/royal92.ged");
		
		} catch (IOException | GedcomParserException e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
		}
			
		//return result;
    }
	
	@RequestMapping(value="/get/{name}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody List<Person> getAnchestryByName(@PathVariable String name){
		
		List<Resource> memberResources = SPARQLQueries.findAnchestrByName(name);
		List<Person> members = new ArrayList<Person>();
		for(Resource r : memberResources){
			if(r!=null){
			members.add(GedRdfsModel.createMember(r));
			}
		}
		return members;
	}
	
	@RequestMapping(value="/get/couples", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody List<String> getCouples(){
		
		
		List<String> data = new ArrayList<String>();
		
		try {

			data =  GedcomPrecess.getCoupleWithCommonAnchestor("geddata/royal92.ged");
		} catch (IOException | GedcomParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(data!=null){
			return data;
		}else{
			return null;
		}
	}

}
