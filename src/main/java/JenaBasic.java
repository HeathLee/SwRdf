import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

/**
 * Created by heath on 15-9-4.
 */
public class JenaBasic {

    public static void main(String[] args) {
        Model model = ModelFactory.createDefaultModel();
        String path = "src/main/resources/JenaBasic.ttl";

        try {
            model.read(path, "TTL");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        model.write(System.out, "TTL");
    }
}
