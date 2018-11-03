package authentication_mechanisms;

//-Prior to Java EE 8, we’ve configured Authentication mechanisms declaratively through the web.xml file.
//-JEE 8 Security API has the new HttpAuthenticationMechanism interface as a replacement.
// Web applications can now configure Authentication mechanisms by providing implementations of this interface.
//-There are implementations for each of the three authentication methods defined by the Servlet spec:
// Basic HTTP authentication, form-based authentication, and custom form-based authentication.
//-It provides an annotation to trigger each implementation:
//	@BasicAuthenticationMechanismDefinition
//	@FormAuthenticationMechanismDefinition
//	@CustomFormAuthenrticationMechanismDefinition


//In general:
//1. Implementation of AuthenticationMechanism	- How to authenticate
//2. Implementation of IdentityStore			- Where look for credentials


//@ServletSecurity(@HttpConstraint(rolesAllowed = "admin"))		- To secure servlet access, instead of in web.xml


//To use Server configured file realm NO @BasicAuthenticationMechanismDefinition!!!

public class Info {}
