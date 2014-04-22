package org.fluxtream.connectors.fluxtream_capture;

import com.google.gson.Gson;
import org.fluxtream.core.services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Chris Bartley (bartley@cmu.edu)
 */
@Controller()
@RequestMapping("/fluxtream_capture")
@Scope("request")
public class FluxtreamCaptureConnectorController {
    @Autowired
    GuestService guestService;

    Gson gson = new Gson();

    @RequestMapping(value = "/about")
    public ModelAndView about(final HttpServletRequest request) {
        return new ModelAndView("connectors/fluxtream_capture/about");
    }
}
