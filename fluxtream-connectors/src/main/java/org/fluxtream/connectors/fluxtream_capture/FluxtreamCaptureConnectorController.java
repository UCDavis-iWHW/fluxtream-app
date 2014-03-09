package org.fluxtream.connectors.fluxtream_capture;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;
import org.fluxtream.mvc.models.CalendarModel;
import org.fluxtream.services.GuestService;
import org.fluxtream.services.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.fluxtream.auth.AuthHelper;
import org.fluxtream.domain.Guest;
import org.fluxtream.mvc.models.StatusModel;
import com.google.gson.Gson;

/**
 * @author Chris Bartley (bartley@cmu.edu)
 */
@Controller()
@RequestMapping("/fluxtream_capture")
public class FluxtreamCaptureConnectorController {
    @Autowired
    GuestService guestService;

    Gson gson = new Gson();

    @RequestMapping(value = "/about")
    public ModelAndView about(final HttpServletRequest request) {
        return new ModelAndView("connectors/fluxtream_capture/about");
    }

    @RequestMapping(value = "/test")
    public String test(@QueryParam("arg") String arg) {
        long guestId;
        try {
            Guest guest = AuthHelper.getGuest();
            guestId = guest.getId();
        } catch (Throwable e) {
            return gson.toJson(new StatusModel(false,"Access Denied"));
        }

        String response = "Works!";
        StatusModel statusModel = new StatusModel(true,response);
        return gson.toJson(statusModel);
    }
}
