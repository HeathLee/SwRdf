import org.apache.jena.query.*;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

/**
 * Created by heath on 15-9-8.
 */
public class RDFSReasoning {
    public static void main(String[] args) {

        Model model = ModelFactory.createDefaultModel();
        String path = "src/main/resources/tourism.ttl";
        try {
            model.read(path, "TTL");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        InfModel inf = ModelFactory.createRDFSModel(model);

        String sparql =
                "SELECT * WHERE {<http://example.org/inst/Museion> ?x ?Y .}";
        String sparql2 =
                "SELECT * WHERE {<http://example.org/inst/ChickenHut> ?x ?Y .}";

        Query query = QueryFactory.create(sparql);
        Query query2 = QueryFactory.create(sparql2);

        System.out.println("Solutions under Simple RDF reasoning:");

        System.out.print("Query statement： ");
        System.out.println(sparql);
        System.out.println();
        try (QueryExecution qe = QueryExecutionFactory.create(query, model)) {
            ResultSet res= qe.execSelect();
            res.forEachRemaining(System.out::println);
        }

        System.out.println();
        System.out.print("Query statement： ");
        System.out.println(sparql2);
        System.out.println();
        try (QueryExecution qe = QueryExecutionFactory.create(query2, model)) {
            ResultSet res= qe.execSelect();
            res.forEachRemaining(System.out::println);
        }

        System.out.println();
        System.out.print("Query statement： ");
        System.out.println(sparql);
        System.out.println();
        System.out.println("Solutions under RDFS reasoning:");
        try (QueryExecution qe = QueryExecutionFactory.create(query, inf)) {
            ResultSet res = qe.execSelect();
            res.forEachRemaining(System.out::println);
        }

        System.out.println();
        System.out.print("Query statement： ");
        System.out.println(sparql2);
        System.out.println();
        try (QueryExecution qe = QueryExecutionFactory.create(query2, inf)) {
            ResultSet res = qe.execSelect();
            res.forEachRemaining(System.out::println);
        }
    }
}
