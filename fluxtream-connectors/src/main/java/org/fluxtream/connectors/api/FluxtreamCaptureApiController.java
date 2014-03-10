package org.fluxtream.connectors.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import org.fluxtream.auth.AuthHelper;
import org.fluxtream.domain.Guest;
import org.fluxtream.mvc.models.StatusModel;
import org.fluxtream.services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// This will actually appear under /api/fluxtream_capture/ because of the
// configuration in
@RequestMapping("/fluxtream_capture")
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

        String response = "Works!";
        //StatusModel statusModel = new StatusModel(true,response);
        //return gson.toJson(statusModel);

        response = "{\"result\":\"OK\",\"message\":\"Works!\"}";
        return(response);
    }
}
