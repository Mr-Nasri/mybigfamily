package business;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;

import model.RdfsModel;

public class InferenceRules {
	private static final String RULES_FILE = "rules/rules.txt";
	private static InfModel infModel;
	
	static {
		Reasoner reasoner = new GenericRuleReasoner( Rule.rulesFromURL( RULES_FILE ) );
		
		infModel = ModelFactory.createInfModel( reasoner, RdfsModel.getModel() );
 
		/*StmtIterator it = infModel.listStatements();
		
		while ( it.hasNext() )
		{
			Statement stmt = it.nextStatement();
			
			Resource subject = stmt.getSubject();
			Property predicate = stmt.getPredicate();
			RDFNode object = stmt.getObject();
 
			System.out.println( subject.toString() + " " + predicate.toString() + " " + object.toString() );
		}*/
		
	}
	
	public static InfModel getInfModel() {
		Reasoner reasoner = new GenericRuleReasoner( Rule.rulesFromURL( RULES_FILE ) );
		
		infModel = ModelFactory.createInfModel( reasoner, RdfsModel.getModel() );
		return infModel;
	}
		
}
