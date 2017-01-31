package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class RdfsModel {

	private static final String DATA_FILE = "data/family.rdf";
	private static final String FAMILY_NAMESPACE = "http://familytree/ns/";
	private static final String MEMBER_NAMESPACE = "http://familytree/member/ns/";
	
	private static OntModel model;
	
	public static OntClass family;
	public static OntClass member;
	
	//************* PROPERTIES FOR FAMILY ***************
	//***************************************************
	public static Property hasId;
	public static OntProperty hasCreator;
	public static OntProperty hasName;
	public static OntProperty hasMember;
	
	
	//************* PROPERTIES FOR MEMBER ***************
	//***************************************************
	public static OntProperty hasFistName;
	public static OntProperty hasLastName;
	public static OntProperty hasBirthDate;
	public static OntProperty liveIn;
	public static OntProperty hasProfession;
	public static OntProperty hasPhone;
	public static OntProperty hasEmail;
	public static OntProperty hasGender;
	//public static OntProperty hasRelation;
	public static OntProperty hasRelative;
	
	
	public static OntProperty hasSibling;
	public static OntProperty hasParent;
	public static OntProperty hasChild;
	public static OntProperty hasSpouse;

	static {
		File f = new File(DATA_FILE);
		
		model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RDFS_INF);
		model.setNsPrefix("f", FAMILY_NAMESPACE);
		model.setNsPrefix("m", MEMBER_NAMESPACE);

		family = model.createClass( FAMILY_NAMESPACE + "Family" );
		member = model.createClass( MEMBER_NAMESPACE + "Member" );

		hasMember = model.createObjectProperty(FAMILY_NAMESPACE + "hasMember");
		hasCreator = model.createObjectProperty(FAMILY_NAMESPACE + "hasCreator");
		hasCreator.setSuperProperty(hasMember);
		hasName = model.createObjectProperty(FAMILY_NAMESPACE + "hasName");
		hasId = model.createProperty(FAMILY_NAMESPACE, "hasId");
		
		hasFistName = model.createObjectProperty(MEMBER_NAMESPACE + "hasFistName");
    	hasLastName = model.createObjectProperty(MEMBER_NAMESPACE + "hasLastName");
    	hasBirthDate = model.createObjectProperty(MEMBER_NAMESPACE + "hasBirthDate");
    	liveIn = model.createObjectProperty(MEMBER_NAMESPACE + "liveIn");
    	hasProfession = model.createObjectProperty(MEMBER_NAMESPACE + "hasProfession");
    	hasPhone = model.createObjectProperty(MEMBER_NAMESPACE + "hasPhone");
    	hasEmail = model.createObjectProperty(MEMBER_NAMESPACE + "hasEmail");
    	hasGender = model.createObjectProperty(MEMBER_NAMESPACE + "hasGender");
    	
    	
    	
    	
    	hasSibling = model.createObjectProperty(MEMBER_NAMESPACE + "hasSibling");
    	hasSpouse = model.createObjectProperty(MEMBER_NAMESPACE + "hasSpouse");
    	hasParent = model.createObjectProperty(MEMBER_NAMESPACE + "hasParent");
    	hasChild = model.createObjectProperty(MEMBER_NAMESPACE + "hasChild");
    	
    	hasParent.setInverseOf(hasChild);
    	hasSibling.setInverseOf(hasSibling);
    	
		if(f.exists() && !f.isDirectory() && f.length() != 0) { 
			//model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RDFS_INF);
			model.read(f.getAbsolutePath(), "RDF/XML");
		}
	}
	
	public static Model getModel() {
		return model;
	}

	public static Resource createMember(Person member) {
		String memberURI = "http://familytree/person/" + member.getFirstName().replaceAll("\\s+","") + member.getLastName().replaceAll("\\s+","");
		Resource memberResource = model.createResource(memberURI, RdfsModel.member);
		
		if(member.getFirstName() != null && !member.getFirstName().equals("")){
			memberResource.addProperty(hasFistName, member.getFirstName());
		}
		if(member.getLastName() != null && !member.getLastName().equals("")){
			memberResource.addProperty(hasLastName, member.getLastName());
		}
		if(member.getBirthDate() != null){
			memberResource.addProperty(hasBirthDate, member.getBirthDate().toString());
		}
		if(member.getCity() != null && !member.getCity().equals("")){
			memberResource.addProperty(liveIn, member.getCity());
		}
		if(member.getProfession() != null && !member.getProfession().equals("")){
			memberResource.addProperty(hasProfession, member.getProfession());
		}
		if(member.getPhone() != null && !member.getPhone().equals("")){
			memberResource.addProperty(hasPhone, member.getPhone());
		}
		if(member.getEmail() != null && !member.getEmail().equals("")){
			memberResource.addProperty(hasEmail, member.getEmail());
		}
		if(member.getGender() != null && !member.getGender().equals("")){
			memberResource.addProperty(hasGender, member.getGender());
		}
		if(member.getRelation() != null && !member.getRelation().equals("")
				&& member.getRelative() != null && !member.getRelative().equals("")){
			String relativeURI = "http://familytree/person/" + member.getRelative().replaceAll("\\s+","");
			Resource relativeResource = model.createResource(relativeURI);
			
//			if(model.contains(relativeResource,RdfsModel.hasId)){
				System.out.println("family contains " + relativeURI);
				switch(member.getRelation()){
				case "child":
					memberResource.addProperty(hasChild, relativeResource);
					break;
				case "parent":
					memberResource.addProperty(hasParent, relativeResource);
					break;
				case "sibling":
					memberResource.addProperty(hasSibling, relativeResource);
					break;
				case "spouse":
					memberResource.addProperty(hasSpouse, relativeResource);
					break;
			}
//			}
//			else{
//				System.out.println("family don't contain " + relativeURI);
//			}
			
		}
		return memberResource;
	}
	
	public static void insertData(){
		FileWriter fw = null;
		try {
			fw = new FileWriter(DATA_FILE, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedWriter bw = new BufferedWriter(fw);
		
		model.write(bw);
	}
	
}
