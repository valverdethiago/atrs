import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class AdSearch {

	public static final String PROVIDER_URL = "ldap://127.0.0.1:33389";
	public static final String DOMAIN = "springframework.org";
	public static final String USERNAME = "admin";
	public static final String PASSWORD = "admin";

	public static void main(String[] args) throws Exception {
		AdSearch adSearch = new AdSearch();
		adSearch.authenticate("admin", "admin");
	}

	public boolean authenticate(String user, String password) {

		Hashtable<String, String> ldapEnv = new Hashtable<String, String>();
		ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		ldapEnv.put(Context.PROVIDER_URL, PROVIDER_URL);
		ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
		ldapEnv.put(Context.SECURITY_PRINCIPAL, user);
		ldapEnv.put(Context.SECURITY_CREDENTIALS, password);

		try {
			DirContext ldapContext = new InitialDirContext(ldapEnv);
			ldapContext.close();
		} catch (NamingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}