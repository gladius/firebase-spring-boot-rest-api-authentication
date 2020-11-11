package io.thepro.apiservice.security.roles;

public interface SecurityRoleService {

	void addRole(String uid, String role) throws Exception;

	void removeRole(String uid, String role);

}
