package io.thepro.apiservice.security.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuth;

@RestController
@RequestMapping("role")
public class RoleController {

	@Autowired
	RoleService roleService;


	@Autowired
	FirebaseAuth firebaseAuth;


	@PutMapping("add")
	@IsSuper
	public void addRole(@RequestParam String uid, @RequestParam String role) throws Exception {
		roleService.addRole(uid, role);
	}

	@DeleteMapping("remove")
	@IsSuper
	public void removeRole(@RequestParam String uid, @RequestParam String role) {
		roleService.removeRole(uid, role);

	}


}
