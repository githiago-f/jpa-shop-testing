package com.medicines.vendor.application.controller.users;

import com.medicines.vendor.application.security.ApplicationUserDetails;
import com.medicines.vendor.application.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	final AuthenticationFacade authenticationFacade;

	@Autowired
	public UserController(AuthenticationFacade authenticationFacade) {
		this.authenticationFacade = authenticationFacade;
	}

	@GetMapping("/profile")
	public ApplicationUserDetails getProfile() {
		return (ApplicationUserDetails) authenticationFacade.getAuthentication()
			.getPrincipal();
	}
}
