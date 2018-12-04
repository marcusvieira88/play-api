package controllers;

import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import datasources.Couchbase;
import play.mvc.*;

import views.html.*;

import javax.inject.Inject;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    @Inject
    private Couchbase couchbase;

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {

        JsonObject user = JsonObject.empty()
                .put("firstname", "Marcus")
                .put("lastname", "Vieira")
                .put("job", "developer")
                .put("age", 29);

        JsonDocument doc = JsonDocument.create("marcus", user);
        JsonDocument response = couchbase.getBucket().upsert(doc);

        System.out.println("responseInsert="+response);

        JsonDocument loaded = couchbase.getBucket().get("marcus");
        System.out.println("responseloaded="+loaded);
        if (loaded == null) {
            System.err.println("Document not found!");
        } else {
            loaded.content().put("age", 30);
            JsonDocument updated = couchbase.getBucket().replace(loaded);
            System.out.println("Updated: " + updated.id());
        }

        return ok(index.render("Data Updated."));
    }

}
