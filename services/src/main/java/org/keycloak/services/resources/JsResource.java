package org.keycloak.services.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.InputStream;

/**
 * @author <a href="mailto:sthorger@redhat.com">Stian Thorgersen</a>
 */
@Path("/js")
public class JsResource {

    @GET
    @Path("/keycloak.js")
    @Produces("text/javascript")
    public Response getJs() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("keycloak.js");
        if (inputStream != null) {
            return Response.ok(inputStream).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
