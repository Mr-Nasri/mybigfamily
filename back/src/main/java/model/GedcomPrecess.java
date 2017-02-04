package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.gedcom4j.comparators.IndividualByLastNameFirstNameComparator;
import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Gedcom;
import org.gedcom4j.model.Individual;
import org.gedcom4j.model.IndividualReference;
import org.gedcom4j.model.Submitter;
import org.gedcom4j.model.Family;
import org.gedcom4j.model.FamilyChild;
import org.gedcom4j.model.FamilySpouse;
import org.gedcom4j.parser.GedcomParser;
import org.gedcom4j.query.Finder;
import org.gedcom4j.relationship.AncestryCalculator;
import org.gedcom4j.relationship.Relationship;
import org.gedcom4j.relationship.RelationshipCalculator;
import org.gedcom4j.relationship.RelationshipName;
import org.gedcom4j.relationship.SimpleRelationship;

public class GedcomPrecess {

	
	public static void getFamiliesMembers(String filePath) throws IOException, GedcomParserException{

			GedcomParser gp = new GedcomParser();
			gp.load(filePath);
			
			Gedcom g = gp.getGedcom();
			
			RelationshipCalculator rc = new RelationshipCalculator();
			AncestryCalculator ac = new AncestryCalculator();
				
			// get the submitter of thegedcom file
			Submitter submitter = null;
			if (!g.getSubmitters().isEmpty()) {
			    submitter = g.getSubmitters().values().iterator().next();
			}
			
			/*for(Individual i : g.getIndividuals().values()){
				
				if(i.getFormattedName() != null){
					
					System.out.println("\nindividual Name :"+i.getFormattedName()+"\n");
				}
				if(i.getSex()!= null){
					
					System.out.println("\nindividual Sex :"+i.getSex()+"\n");
				}
				if(i.getAddress() != null){
					
					System.out.println("\nindividual Adress :"+i.getAddress().getAddr1().getValue()+"\n");
				}
				if(i.getSpouses() != null){
					
					System.out.println("\nindividual Spouse :"+i.getSpouses().toString()+"\n");
				}

			}*/			
		
			
			
			
			
			// get the husband and the wife in every family
			System.out.println("\n***FAMILY PROCESSING ***\n");
			
			Map<String, Person> personList = new HashMap<String, Person>();
			int k=1;
			
	    	Resource creatorResource = null;
	    	Resource wifeResource = null;
			
			
			for (Family fam : g.getFamilies().values()) {
				
				if(k < 100){
				
				model.Family familyModel = new model.Family();
				
				
				
				System.out.println("\n***get Xref of every family***\n");
			    if (fam.getXref() != null) {
			    	
			        System.out.println( "\nXref of the "+k+" family "+fam.getXref() );
			    }
			    
				System.out.println("\n***get the creator of the family***\n");
			    if (fam.getHusband() != null) {
			    	
			    	if(fam.getHusband().getIndividual() != null)
			    	{
			    		if(fam.getHusband().getIndividual().getNames().get(0).getBasic() != null && !fam.getHusband().getIndividual().getNames().get(0).getBasic().equals("")){
			    			model.Person person = new Person();
			    			familyModel.setName(fam.getHusband().getIndividual().getNames().get(0).getBasic().replaceAll("/", ""));
					    	person.setFirstName(fam.getHusband().getIndividual().getNames().get(0).getBasic().replaceAll("/", ""));
					    	person.setGender(fam.getHusband().getIndividual().getSex().getValue());
					    	creatorResource  = GedRdfsModel.createMember(person);
					    	personList.put("husband "+k, person);
					    	familyModel.setCreator(person);
					    	System.out.println("creator of the family Name : \n\n "+familyModel.getCreator().getFirstName());
			    		}
			    	}
			    }
			    System.out.println("\n***get the wife of the creator of the family***\n");
			    if (fam.getWife() != null) {
			    	if(fam.getWife().getIndividual() != null && fam.getHusband() != null)
			    	{
			    		if(fam.getWife().getIndividual().getNames().get(0).getBasic() != null && !fam.getWife().getIndividual().getNames().get(0).getBasic().equals("")){
			    	
					    	model.Person person = new Person();
					    	//familyModel.setName(fam.getHusband().getIndividual().getFormattedName());
					    	person.setFirstName(fam.getWife().getIndividual().getNames().get(0).getBasic().replaceAll("/", ""));
					    	person.setGender(fam.getWife().getIndividual().getSex().getValue().toString());
					    	person.setRelation("spouse");
					    	person.setRelative(fam.getHusband().getIndividual().getNames().get(0).getBasic().replaceAll("/", ""));
					    	wifeResource = GedRdfsModel.createMember(person);
					    	if(person != null){
					    	personList.put("wife "+k, person);
					    	}
					    	//familyModel.setCreator(person);
			    		}
			    	}
			    } 
				System.out.println("\n***get children in every family***\n");
				//List<Resource> resourceChild = new ArrayList<Resource>();
				try{
			    if (fam.getChildren() != null) {
			    	int i = 1;
			    	for(IndividualReference ir : fam.getChildren()){
			    		
			    		if(ir != null){
			    		if(ir.getIndividual() != null){
				    		if(ir.getIndividual().getFormattedName() != null && ir.getIndividual().getSex().getValue() != null){
				    			model.Person person = new Person();
				    			person.setFirstName(ir.getIndividual().getNames().get(0).getBasic().replaceAll("/", ""));
				    			person.setGender(ir.getIndividual().getSex().getValue());
				    			person.setRelation("parent");
				    			person.setRelative(personList.get("husband "+k).getFirstName());
				    			
				    			if(person != null){
				    			personList.put("child "+i+ " of "+k+"eme family ", person);
				    			}
				    		}
			    			//Resource childResource =  GedRdfsModel.createMember(personList.get("child "+i+ " of "+k+"eme family "));
			    			//resourceChild.add(childResource);	
			    		}}
			    		
			    		i++;
			    	}
			    	
			    }}catch(Exception e){
			    	e.getMessage();
			    }
			  
			 //   try{

			    			
			    System.out.println("GOT IT");
		    	String familyID = UUID.randomUUID().toString();
		    	String familyURI = "http://familytree/" + familyID;

		    	familyModel.setId(familyID);
		    	Model model = GedRdfsModel.getModel();

		    //	 create the resource
		    	Resource familyResource = model.createResource(familyURI, GedRdfsModel.family);

		    	
		    	familyResource.addProperty(GedRdfsModel.hasCreator, creatorResource);
		    	familyResource.addProperty(GedRdfsModel.hasMember, wifeResource);
		    	//for(Resource r : resourceChild){
		    	//familyResource.addProperty(GedRdfsModel.hasMember, r);
		    	//}
		    	familyResource.addProperty(GedRdfsModel.hasId, familyID);
		    	if(familyModel.getName() != null){
		    	familyResource.addProperty(GedRdfsModel.hasName, familyModel.getName());
		    	}
		    	
		    	GedRdfsModel.insertGedData();
		    	
			   // }catch(Exception e){
			   // 	e.getMessage();
			   // }
		    	k++;
			  /*  
			 // Only process families where we know both the husband and
	            // the wife
	            if (fam.getHusband() != null && fam.getWife() != null) {
	                // Get the nearest common ancestor(s), if any, between
	                // the husband and wife
	                Set<Individual> common = ac.getLowestCommonAncestors(fam.getHusband().getIndividual(), fam.getWife().getIndividual());
	 
	                if (!common.isEmpty()) {
	                    // We have a couple with common ancestors. Show
	                    // them.
	                    System.out.println(fam.getHusband().getIndividual().getFormattedName()
	                            + " and " + fam.getWife().getIndividual().getFormattedName()
	                            + " have " + common.size()
	                            + " common ancestor(s):");
	                    int commonAncNumber = 0;
	                    for (Individual i : common) {
	                        commonAncNumber++;
	                        System.out.println("\t" + commonAncNumber + ") "
	                                + i);
	                    }
	 
	                    System.out.println();
	                }
	            }
			    
			    
				System.out.println("\n***get husband and its wife in every family***\n");
			    if (fam.getHusband() != null && fam.getWife() != null) {
			        System.out.println("\n"+fam.getHusband().getIndividual().getNames().get(0).getBasic()
			                + " married to " + fam.getWife().getIndividual().getNames().get(0).getBasic()+"\n");
					/*for(Individual i : g.getIndividuals().values()){
						if(!(i.getFormattedName().equals(fam.getHusband().getIndividual().getFormattedName()))){
							
							rc.calculateRelationships(fam.getHusband().getIndividual(), i, true);
						
				            // .. and print them to stdout
				            System.out.println(i.getFormattedName());
				            for (Relationship r : rc.getRelationshipsFound()) {
				                System.out.println("\nthe Husband is related to "+i.getFormattedName()+" by the relation " + r+"\n");
				            }
						}
						
					}
			    }*/

			}}
			//return personList;
		
	}
	
	
	/**
	 * Get the "extended ancestry" of an individual. This is defined (for this method's purposes) as the individual's
	 * parents (and step-parents), recursively.
	 * 
	 * @param individual
	 *            the individual whose extended ancestry is desired
	 * @return the set of all ancestors for the individual, and all their spouses
	 * @throws GedcomParserException 
	 * @throws IOException 
	 */
	public static List<Person> getAnchestreByName(String filPath, String name, String familyName) throws IOException, GedcomParserException{
		
		List<Person> result = new ArrayList<Person>();
		RelationshipCalculator rc = new RelationshipCalculator();
		 
        // Load the GEDCOM
        GedcomParser gp = new GedcomParser();
        gp.load(filPath);
        Gedcom g = gp.getGedcom();
 
        // Find the starting person
        Finder f = new Finder(g);
        List<Individual> results = f.findByNameSoundsLike(familyName, name);

        for(Individual i : results){
        	Person person = new Person();

        	if(i.getFormattedName() != null){
        		person.setFirstName(i.getFormattedName());
        	}
        	if(i.getSex() != null){
        		person.setGender(i.getSex().getValue());
        	}
        	result.add(person);
        }
		
		
		return result;
	}
	
	public static List<String> getCoupleWithCommonAnchestor(String filPath) throws IOException, GedcomParserException{
		
		
		 AncestryCalculator ac = new AncestryCalculator();
		 
	        GedcomParser gp = new GedcomParser();
	        gp.load(filPath);
	        Gedcom g = gp.getGedcom();
	        List<String> results = new ArrayList<String>();
	        String result = "";
	 
	        for (Family f : g.getFamilies().values()) {
	            // Only process families where we know both the husband and
	            // the wife
	            if (f.getHusband() != null && f.getWife() != null) {
	                // Get the nearest common ancestor(s), if any, between
	                // the husband and wife
	                Set<Individual> common = ac.getLowestCommonAncestors(f
	                        .getHusband().getIndividual(), f.getWife().getIndividual());
	 
	                if (!common.isEmpty()) {
	                    // We have a couple with common ancestors. Show
	                    // them.
	                    result = f.getHusband().getIndividual().getFormattedName().replaceAll("/", "")
	                            + " and " + f.getWife().getIndividual().getFormattedName().replaceAll("/", "")
	                            + " have " + common.size()
	                            + " common ancestor(s):";
	                    int commonAncNumber = 0;
	                    for (Individual i : common) {
	                        commonAncNumber++;
	                        result += "" + commonAncNumber + ") "
	                                + i+"";
	                    }
	                    
	                 results.add(result);
	                }
	            }
	        }
	    
	  return results;
		
	}
	
	
	public Set<IndividualReference> getExtendedAncestry(Individual individual) {
	    Set<IndividualReference> result = new HashSet<IndividualReference>();

	    // Get every family this individual was a child of
	    for (FamilyChild fc : individual.getFamiliesWhereChild()) {
	        // Add father and all his wives
	        IndividualReference dad = fc.getFamily().getHusband();
	        if (dad != null && !result.contains(dad)) {
	            result.add(dad);
	            for (FamilySpouse fs : dad.getIndividual().getFamiliesWhereSpouse()) {
	                IndividualReference dadsWife = fs.getFamily().getWife();
	                if (dadsWife != null) {
	                    result.add(dadsWife);
	                    result.addAll(getExtendedAncestry(dadsWife.getIndividual() ) );
	                }
	            }
	            // And include his extended ancestry as well (recursively)
	            result.addAll(getExtendedAncestry(dad.getIndividual()));
	        }

	        // Add mother and all her husbands
	        IndividualReference mom = fc.getFamily().getWife();
	        if (mom != null && !result.contains(mom)) {
	        	//mom.getIndividual().getEvents(true).
	            result.add(mom);
	            for (FamilySpouse fs : mom.getIndividual().getFamiliesWhereSpouse()) {
	                IndividualReference momsHusband = fs.getFamily().getHusband();
	                if (momsHusband != null) {
	                    result.add(momsHusband);
	                    result.addAll(getExtendedAncestry(momsHusband.getIndividual()));
	                }
	            }
	            // And include her extended ancestry as well (recursively)
	            result.addAll(getExtendedAncestry(mom.getIndividual()));
	        }
	    }

	    return result;
	}
	
	
	/**
	 * Get the spouse of an individual in a family
	 * 
	 * @param fs
	 *            the family
	 * @param i
	 *            the individual to get the spouse for
	 * @return the spouse of the individual passed in
	 */
	private Individual getSpouse(FamilySpouse fs, Individual i) {
	    if (fs.getFamily().getHusband().getIndividual() == i) {
	        return fs.getFamily().getWife().getIndividual();
	    }
	    if (fs.getFamily().getWife().getIndividual() == i) {
	        return fs.getFamily().getHusband().getIndividual();
	    }
	    return null;
	}
	
	

	
}
