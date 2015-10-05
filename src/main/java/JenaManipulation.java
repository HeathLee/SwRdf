import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.VCARD;

import java.io.FileOutputStream;
import java.io.IOException;


public class JenaManipulation {

    public static void main(String[] args) {
        String personURI = "http://someone/doge";
        String givenName = "Doge";
        String familyName = "Jenas";
        String fullName = givenName + " " + familyName;
        String mail = "mailto:doge@example.com";
        String friend = "Youcai Li";

        String inputPath = "src/main/resources/JenaManipulation.ttl";
        Model model = ModelFactory.createDefaultModel();
        model.read(inputPath, "TTL");

        model.createResource(personURI)
                .addProperty(VCARD.FN, fullName)
                .addProperty(VCARD.Given, givenName)
                .addProperty(VCARD.Family, familyName)
                .addProperty(FOAF.mbox, mail)
                .addProperty(FOAF.knows,
                        model.createResource()
                            .addProperty(FOAF.name, friend));


        String outFileInTTL = "src/main/resources/JenaManipulationOutput.ttl";
        String outFileInXML = "src/main/resources/JenaManipulationOutput.xml";
        FileOutputStream outputStreamWithTTL = null, outputStreamWithXML = null;
        try {
            outputStreamWithTTL = new FileOutputStream(outFileInTTL);
            outputStreamWithXML = new FileOutputStream(outFileInXML);
        } catch (IOException e) {
            System.out.println(e);
        }
        model.write(outputStreamWithTTL, "TTL");
        model.write(outputStreamWithXML, "RDF/XML");

    }
}