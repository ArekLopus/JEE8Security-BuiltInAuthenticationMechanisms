package authentication_mechanisms;


import static java.util.Arrays.asList;
import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;

import java.util.HashSet;

import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

@DeclareRoles({"admin", "user", "foo"})
@ApplicationScoped
public class SimpleIdentityStore implements IdentityStore {

	public CredentialValidationResult validate(UsernamePasswordCredential usernamePasswordCredential) {

        if (usernamePasswordCredential.compareTo("aa", "aa")) {
            return new CredentialValidationResult("aa", new HashSet<>(asList("admin")));
        }
        
        if (usernamePasswordCredential. compareTo("ed", "ed")) {
            return new CredentialValidationResult("ed", new HashSet<>(asList("admin", "user")));
        }
        
        if (usernamePasswordCredential.compareTo("mo", "mo")) {
            return new CredentialValidationResult("mo", new HashSet<>(asList("admin", "user", "foo")));
        }
        
        if (usernamePasswordCredential.compareTo("bb", "bb")) {
            return new CredentialValidationResult("bb", new HashSet<>(asList("user")));
        }
        return INVALID_RESULT;
    }

	@Override
	public int priority() {
		return 20;
	}
	
}
