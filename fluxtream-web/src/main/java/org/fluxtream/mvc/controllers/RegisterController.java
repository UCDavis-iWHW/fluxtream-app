package org.fluxtream.mvc.controllers;

import org.fluxtream.core.Configuration;
import org.fluxtream.core.aspects.FlxLogger;
import org.fluxtream.core.domain.Guest;
import org.fluxtream.core.services.GuestService;
import org.fluxtream.core.services.impl.ExistingEmailException;
import org.fluxtream.core.services.impl.UsernameAlreadyTakenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.fluxtream.core.utils.Utils.generateSecureRandomString;

@Controller
public class RegisterController {
	
	FlxLogger logger = FlxLogger.getLogger(RegisterController.class);

	@Qualifier("authenticationManager")
	AuthenticationManager authenticationManager;
	
	@Autowired
	GuestService guestService;

    @Autowired
    Configuration env;

    @RequestMapping("/mobile/oauth2/authorize")
    /**
     * A simple login form without register button
     */
    public String oauth2Authorize(ModelMap model) {
        model.addAttribute("release", env.get("release"));
        model.addAttribute("name", "The Upgrade");
        model.addAttribute("description", "The Upgrade brings together self trackers and personal data specialists in a unique platform to build the knowledge that will one day empower every human on earth to realize their full potential.");
        return "oauth2/Authorize";
    }


    @RequestMapping("/mobile/authenticate")
    /**
     * A simple login form without register button
     */
    public String authenticate(ModelMap model) {
        model.addAttribute("release", env.get("release"));
        return "mobile/authenticate";
    }

    @RequestMapping("/mobile/signIn")
    /**
     * Mobile landing page with login and register buttons
     */
    public String login(HttpServletRequest request,
                        ModelMap model) {
        String redirect_uri = request.getParameter("r");
        model.addAttribute("redirect_uri", redirect_uri);
        model.addAttribute("release", env.get("release"));
        return "mobile/signIn";
    }

    @RequestMapping("/mobile/register")
    public String mobileRegister(ModelMap model) {
        model.addAttribute("release", env.get("release"));
        return "mobile/register";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/createAccountForm")
	public ModelAndView createAccountForm(
            @RequestParam(value="isDeveloperAccount",required=false, defaultValue = "false") boolean isDeveloperAccount
    ) {
        ModelAndView mav = new ModelAndView("createAccount");
        mav.addObject("isDeveloperAccount", isDeveloperAccount);
        return mav;
	}
	
	@RequestMapping("/createAccount")
	public ModelAndView createAccount(
		@RequestParam("email") String email,
		@RequestParam("username") String username,
		@RequestParam("firstname") String firstname,
		@RequestParam("lastname") String lastname,
		@RequestParam("password1") String password,
		@RequestParam("password2") String password2,
//		@RequestParam("recaptchaChallenge") String challenge,
//		@RequestParam("recaptchaResponse") String uresponse,
		HttpServletRequest request) throws Exception, UsernameAlreadyTakenException, ExistingEmailException {
		email = email.trim();
		password = password.trim();
		password2 = password2.trim();
		username = username.trim();
		firstname = firstname.trim();
		lastname = lastname.trim();

		List<String> required = new ArrayList<String>();
		List<String> errors = new ArrayList<String>();
		if (email.equals("")) required.add("email");
        if (firstname.equals("")) required.add("firstname");
		if (username.equals("")) {
			required.add("username");
		} else if (guestService.getGuest(username)!=null) {
			errors.add("usernameTaken");
		}
		if (password.equals("")) required.add("password");
		if (password2.equals("")) required.add("password2");
		if (password.length()<8)
			errors.add("passwordTooShort");
		if (!password.equals(password2))
			errors.add("passwordsDontMatch");
        final Guest guestByEmail = guestService.getGuestByEmail(email);
        if (guestByEmail !=null && guestByEmail.getUserRoles().contains("ROLE_USER"))
			errors.add("userExists");
					
//		String remoteAddr = request.getRemoteAddr();
//        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
//        reCaptcha.setPrivateKey("6LeXl8QSAAAAADjPASFlMINNRVwtlpcvGugcr2RI");
//
//        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
//
//		if (!reCaptchaResponse.isValid())
//		errors.add("wrongCaptcha");
		
		if (errors.size()==0&&required.size()==0) {
			logger.info("action=register success=true username="+username + " email=" + email);
            final Guest guest = guestService.createGuest(username, firstname, lastname, password, email, Guest.RegistrationMethod.REGISTRATION_METHOD_FORM, null);
            final String autoLoginToken = generateSecureRandomString();
            guestService.setAutoLoginToken(guest.getId(), autoLoginToken);
			request.setAttribute("autoLoginToken", autoLoginToken);
			return new ModelAndView("accountCreationComplete");
		} else {
			logger.info("action=register errors=true");
            ModelAndView mav = new ModelAndView("createAccount");
			mav.addObject("email", email);
			mav.addObject("username", username);
			mav.addObject("firstname", firstname);
			mav.addObject("lastname", lastname);
			mav.addObject("errors", errors);
			mav.addObject("required", required);
			return mav;
		}
	}
	
}
