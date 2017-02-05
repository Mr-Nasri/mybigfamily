package business;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;

import model.RdfsModel;

public class SPARQLQueries {
	
	
	public static List<String> getMembersNameById(String familyId){
		
		ArrayList<String> members = new ArrayList<>();
		String queryString = "prefix f: <http://familytree/ns/>" +
						"prefix m: <http://familytree/member/ns/>" +
						"select ?name ?firstName where {" +
						 "<http://familytree/" + familyId + "> f:hasMember ?m." +
						 "?m m:hasFistName ?firstName." +
						 "?m m:hasLastName ?lastName." +
						 "BIND(CONCAT(?firstName, \" \" ,?lastName) AS ?name)" +
						"}";
		
		
		Model model = RdfsModel.getModel();
		  Query query = QueryFactory.create(queryString) ;
		  try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
		    ResultSet results = qexec.execSelect() ;
		    for ( ; results.hasNext() ; )
		    {
		      QuerySolution soln = results.nextSolution() ;
		      //RDFNode x = soln.get("m") ;       // Get a result variable by name.
		      //Person test = x.as(Person.c);
		      //Resource r = soln.getResource("VarR") ; // Get a result variable - must be a resource
		      Literal l = soln.getLiteral("name") ;   // Get a result variable - must be a literal
		      members.add(l.toString());
		      //System.out.println("SPARQL" + l.toString());
		    }
		  }
		  return members;
	}
	
public static List<Resource> getMembersById(String familyId){
		
		ArrayList<Resource> members = new ArrayList<>();
		String queryString = "prefix f: <http://familytree/ns/>" +
						"prefix m: <http://familytree/member/ns/>" +
						"select ?m where {" +
						 "<http://familytree/" + familyId + "> f:hasMember ?m" +
						"}";
		
		
		Model model = RdfsModel.getModel();
		  Query query = QueryFactory.create(queryString) ;
		  try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
		    ResultSet results = qexec.execSelect() ;
		    for ( ; results.hasNext() ; )
		    {
		      QuerySolution soln = results.nextSolution() ;
		      //RDFNode x = soln.get("m") ;       // Get a result variable by name.
		      //Person test = x.as(Person.c);
		      Resource r = soln.getResource("m") ; // Get a result variable - must be a resource
		      //Literal l = soln.getLiteral("name") ;   // Get a result variable - must be a literal
		      System.out.println(r.toString());
		      members.add(r);
		      //System.out.println("SPARQL" + l.toString());
		    }
		  }
		  return members;
	}
	
	
	
	public static List<Resource> searchMembersInCity(String familyId, String city){
		
		ArrayList<Resource> members = new ArrayList<>();
		String queryString = "prefix f: <http://familytree/ns/>" +
						"prefix m: <http://familytree/member/ns/>" +
						"select ?m where {" +
						 "<http://familytree/" + familyId + "> f:hasMember ?m." +
						 "?m m:liveIn \"" +  city + "\"" +
						"}";
		
		
		Model model = RdfsModel.getModel();
		  Query query = QueryFactory.create(queryString) ;
		  try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
		    ResultSet results = qexec.execSelect() ;
		    for ( ; results.hasNext() ; )
		    {
		      QuerySolution soln = results.nextSolution() ;
		      //RDFNode x = soln.get("m") ;       // Get a result variable by name.
		      //Person test = x.as(Person.c);
		      Resource r = soln.getResource("m") ; // Get a result variable - must be a resource
		      //Literal l = soln.getLiteral("name") ;   // Get a result variable - must be a literal
		      System.out.println(r.toString());
		      members.add(r);
		      //System.out.println("SPARQL" + l.toString());
		    }
		  }
		  return members;
	}

	public static List<Resource> findAnchestrByName(String name){
		
	ArrayList<Resource> members = new ArrayList<>();
	String queryString = "prefix f: <http://familytree/ns/>" +
					"prefix m: <http://familytree/member/ns/>" +
					"select ?m where {" +
					 "?m m:hasFistName \"" +  name + "\"" +
					"}";
	
	FileManager.get().addLocatorClassLoader(SPARQLQueries.class.getClassLoader());
    Model model= FileManager.get().loadModel("data/ged.rdf");
	
	  Query query = QueryFactory.create(queryString) ;
	  try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
	    ResultSet results = qexec.execSelect() ;
	    for ( ; results.hasNext() ; )
	    {
	      QuerySolution soln = results.nextSolution() ;
	      //RDFNode x = soln.get("m") ;       // Get a result variable by name.
	      //Person test = x.as(Person.c);
	      Resource r = soln.getResource("m") ; // Get a result variable - must be a resource
	      //Literal l = soln.getLiteral("name") ;   // Get a result variable - must be a literal
	      System.out.println(r.toString());
	      members.add(r);
	      //System.out.println("SPARQL" + l.toString());
	    }
	  }
	  return members;
    }

	public static List<Resource> searchAunts(String familyId, String member) {
		ArrayList<Resource> members = new ArrayList<>();
		String queryString = "prefix f: <http://familytree/ns/>" +
						"prefix m: <http://familytree/member/ns/>" +
						"select ?m where {" +
						 "<http://familytree/" + familyId + "> f:hasMember ?m." +
						 "<" +  member + "> m:hasAunt ?m" +
						"}";
		
		
		InfModel model = InferenceRules.getInfModel();
		  Query query = QueryFactory.create(queryString) ;
		  System.out.println("creating Query : " + queryString);
		  try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
		    ResultSet results = qexec.execSelect() ;
		    for ( ; results.hasNext() ; )
		    {
		      QuerySolution soln = results.nextSolution() ;
		      //RDFNode x = soln.get("m") ;       // Get a result variable by name.
		      //Person test = x.as(Person.c);
		      Resource r = soln.getResource("m") ; // Get a result variable - must be a resource
		      //Literal l = soln.getLiteral("name") ;   // Get a result variable - must be a literal
		      System.out.println(r.toString());
		      members.add(r);
		      //System.out.println("SPARQL" + l.toString());
		    }
		  }
		  return members;
	}
	
	public static List<Resource> searchUncles(String familyId, String member) {
		ArrayList<Resource> members = new ArrayList<>();
		String queryString = "prefix f: <http://familytree/ns/>" +
						"prefix m: <http://familytree/member/ns/>" +
						"select ?m where {" +
						 "<http://familytree/" + familyId + "> f:hasMember ?m." +
						 "<" +  member + "> m:hasUncle ?m" +
						"}";
		
		
		InfModel model = InferenceRules.getInfModel();
		  Query query = QueryFactory.create(queryString) ;
		  System.out.println("creating Query : " + queryString);
		  try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
		    ResultSet results = qexec.execSelect() ;
		    for ( ; results.hasNext() ; )
		    {
		      QuerySolution soln = results.nextSolution() ;
		      //RDFNode x = soln.get("m") ;       // Get a result variable by name.
		      //Person test = x.as(Person.c);
		      Resource r = soln.getResource("m") ; // Get a result variable - must be a resource
		      //Literal l = soln.getLiteral("name") ;   // Get a result variable - must be a literal
		      System.out.println(r.toString());
		      members.add(r);
		      //System.out.println("SPARQL" + l.toString());
		    }
		  }
		  return members;
	}
	
	public static List<Resource> searchCousins(String familyId, String member) {
		ArrayList<Resource> members = new ArrayList<>();
		String queryString = "prefix f: <http://familytree/ns/>" +
						"prefix m: <http://familytree/member/ns/>" +
						"select ?m where {" +
						 "<http://familytree/" + familyId + "> f:hasMember ?m." +
						 "<" +  member + "> m:hasCousin ?m" +
						"}";
		
		
		InfModel model = InferenceRules.getInfModel();
		  Query query = QueryFactory.create(queryString) ;
		  System.out.println("creating Query : " + queryString);
		  try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
		    ResultSet results = qexec.execSelect() ;
		    for ( ; results.hasNext() ; )
		    {
		      QuerySolution soln = results.nextSolution() ;
		      //RDFNode x = soln.get("m") ;       // Get a result variable by name.
		      //Person test = x.as(Person.c);
		      Resource r = soln.getResource("m") ; // Get a result variable - must be a resource
		      //Literal l = soln.getLiteral("name") ;   // Get a result variable - must be a literal
		      System.out.println(r.toString());
		      members.add(r);
		      //System.out.println("SPARQL" + l.toString());
		    }
		  }
		  return members;
	}
	
	public static List<Resource> searchGrandParents(String familyId, String member) {
		ArrayList<Resource> members = new ArrayList<>();
		String queryString = "prefix f: <http://familytree/ns/>" +
						"prefix m: <http://familytree/member/ns/>" +
						"select ?m where {" +
						 "<http://familytree/" + familyId + "> f:hasMember ?m." +
						 "<" +  member + "> m:hasGrandParent ?m" +
						"}";
		
		
		InfModel model = InferenceRules.getInfModel();
			System.out.println("creating Query : " + queryString);
		  Query query = QueryFactory.create(queryString) ;
		  try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
		    ResultSet results = qexec.execSelect() ;
		    for ( ; results.hasNext() ; )
		    {
		      QuerySolution soln = results.nextSolution() ;
		      //RDFNode x = soln.get("m") ;       // Get a result variable by name.
		      //Person test = x.as(Person.c);
		      Resource r = soln.getResource("m") ; // Get a result variable - must be a resource
		      //Literal l = soln.getLiteral("name") ;   // Get a result variable - must be a literal
		      System.out.println(r.toString());
		      members.add(r);
		      //System.out.println("SPARQL" + l.toString());
		    }
		  }
		  return members;
	}

}
