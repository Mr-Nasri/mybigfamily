package business;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import model.RdfsModel;

public class SPARQLQueries {
	
	
	public static List<String> getMembersById(String familyId){
		
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

}
