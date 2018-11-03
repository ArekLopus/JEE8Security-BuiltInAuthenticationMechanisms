package authentication_mechanisms;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@BasicAuthenticationMechanismDefinition

//@FormAuthenticationMechanismDefinition(
//		  loginToContinue = @LoginToContinue(//useForwardToLogin=true,		//forward if true, redirect if false.
//				loginPage = "/form_based/login.html",
//				errorPage = "/form_based/login-error.html"))

//@CustomFormAuthenticationMechanismDefinition(
//		  loginToContinue = @LoginToContinue(
//				errorPage = "/form_based_custom/login-error.xhtml",
//				loginPage = "/form_based_custom/login.xhtml"))

// -uses SERVER FILE defined credentials ( No AuthenticationMechanism on!!! )
//http://jo:jo@localhost:8080/JEE8Security-BuiltInAuthenticationMechanisms/secured
// -Needs AuthenticationMechanism on
//http://mo:mo@localhost:8080/JEE8Security-BuiltInAuthenticationMechanisms/secured
//http://localhost:8080/JEE8Security-BuiltInAuthenticationMechanisms/secured

@WebServlet("/secured")
@SuppressWarnings("unused")
@ServletSecurity(
	httpMethodConstraints = @HttpMethodConstraint(value = "POST", emptyRoleSemantic = ServletSecurity.EmptyRoleSemantic.DENY),
	value = @HttpConstraint(rolesAllowed = "admin", transportGuarantee = ServletSecurity.TransportGuarantee.NONE)
)

public class ASecuredServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	SecurityContext sc;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Welcome To The Security Test Servlet!</h3>");
		out.println("<h4>'admin' role is needed to access this servlet.</h4>");

		if (sc.getCallerPrincipal() == null) {
			out.println("Principal is NULL!");
		} else {
			out.println("getCallerPrincipal().getName() -> " + sc.getCallerPrincipal().getName());
			out.println("<br/>isCallerInRole('admin') -> " + sc.isCallerInRole("admin"));
			out.println("<br/>isCallerInRole('user') -> " + sc.isCallerInRole("user"));
			out.println("<br/>isCallerInRole('foo') -> " + sc.isCallerInRole("foo"));
		}
	}
}