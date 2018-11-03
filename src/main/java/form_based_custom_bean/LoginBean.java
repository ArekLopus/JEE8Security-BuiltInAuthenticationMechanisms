package form_based_custom_bean;

import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

@FacesConfig(version=FacesConfig.Version.JSF_2_3)
@Named
@RequestScoped
public class LoginBean {

    @Inject
    SecurityContext securityContext;

    @Inject
    FacesContext facesContext;

    @NotNull
    private String username = "ed";

    @NotNull
    private String password = "ed";

    public void login() {
        
    	Credential credential = new UsernamePasswordCredential(username, new Password(password));
        
    	AuthenticationStatus status = securityContext.authenticate(
        		(HttpServletRequest) facesContext.getExternalContext().getRequest(),
        		(HttpServletResponse) facesContext.getExternalContext().getResponse(),
                AuthenticationParameters.withParams().credential(credential));
        
    	if (status.equals(AuthenticationStatus.SEND_CONTINUE)) {
            facesContext.responseComplete();
        } else if (status.equals(AuthenticationStatus.SEND_FAILURE)) {
            facesContext.addMessage(null, new FacesMessage(SEVERITY_ERROR, "Authentication failed", null));
        }
    }
    
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}