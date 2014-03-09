package org.fluxtream.mvc.controllers;

import javax.servlet.http.HttpServletResponse;
import org.fluxtream.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * User: candide
 * Date: 07/02/14
 * Time: 16:49
 */
@Controller
@RequestMapping(value = "/sreport")
public class SelfReportController {

    @Autowired
    Configuration env;

    @RequestMapping(value = { "/welcome", "", "/"})
    public ModelAndView index(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0);
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        if (auth != null && auth.isAuthenticated())
            return new ModelAndView("redirect:/sreport/home");
        ModelAndView mav = new ModelAndView("sreport/index");
        String release = env.get("release");
        if (release != null)
            mav.addObject("release", release);
        return mav;
    }

    @RequestMapping(value = "/home")
    public ModelAndView home(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0);
        ModelAndView mav = new ModelAndView("sreport/home");
        String release = env.get("release");
        if (release != null)
            mav.addObject("release", release);
        return mav;
    }

}
