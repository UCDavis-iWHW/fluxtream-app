package org.fluxtream.connectors.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import org.fluxtream.auth.AuthHelper;
import org.fluxtream.domain.Guest;
import org.fluxtream.mvc.models.StatusModel;
import org.fluxtream.services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// This will actually appear under /api/fluxtream_capture/ because of the
// configuration for org.fluxtream.connectors.api in web.xml
@Path("/fluxtream_capture")
@Component("RESTFluxtreamCaptureApiController")
@Scope("request")
public class FluxtreamCaptureApiController {
    @Autowired
    GuestService guestService;

    Gson gson = new Gson();

    @GET
    @Path("/test")
    //@Produces({ MediaType.APPLICATION_JSON })
    @Produces({MediaType.TEXT_PLAIN})
    public String test() {
        long guestId;
        try {
            Guest guest = AuthHelper.getGuest();
            guestId = guest.getId();
        } catch (Throwable e) {
            return gson.toJson(new StatusModel(false,"Access Denied"));
        }

        String response = "Works! guestId is "+ Long.toString(guestId);
        StatusModel statusModel = new StatusModel(true,response);
        return gson.toJson(statusModel);
    }
}
