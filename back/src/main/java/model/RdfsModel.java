package model;

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

	private static final String FAMILY_NAMESPACE = "http://familytree/ns/";
	private static final String MEMBER_NAMESPACE = "http://familytree/member/ns/";
	
	private static OntModel model;
	
	OntClass family;
	OntClass member;
	
	//************* PROPERTIES FOR FAMILY ***************
	//***************************************************
	public static Property hasId;
	public static OntProperty hasCreator;
	public static OntProperty hasName;
	
	
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
	
	public static OntProperty hasSibling;
	public static OntProperty hasParent;
	public static OntProperty hasChild;
	public static OntProperty hasSpouse;

	static {
		model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RDFS_INF);

		OntClass family = model.createClass( FAMILY_NAMESPACE + "Family" );
		OntClass member = model.createClass( MEMBER_NAMESPACE + "Member" );

		hasCreator = model.createObjectProperty(FAMILY_NAMESPACE + "hasCreator");
		hasName = model.createObjectProperty(FAMILY_NAMESPACE + "hasName");
		//hasCreator.addRange(res);
		hasId = model.createProperty(MEMBER_NAMESPACE, "hasId");
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
    	

	}
	
	public static Model getModel() {
		return model;
	}

	public static Resource addMember(Person member) {
		String memberURI = "http://familytree/person/" + member.getFirstName() + member.getLastName();
		Resource memberResource = model.createResource(memberURI);
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
		return memberResource;
	}
	
}
