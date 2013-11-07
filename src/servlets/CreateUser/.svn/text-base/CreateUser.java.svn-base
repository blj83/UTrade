import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import freemarker.template.*;

import except.*;
import model.ctrl.*;

// Servlet class CreateUser
//
// this class functions as the boundary class for the CreateUser use case
//
// doPost starts the execution of the CreateUser Use Case
//
//   parameters:
//
// fn               string
// ln               string
// email            string
// pass             string

public class CreateUser extends HttpServlet {
    
    static  String templateDir = "WEB-INF/templates";
    static  String resultTemplateName = "CreateUser-Result.ftl";
    
    private Configuration cfg;
    
    public void init()
    {
        // Prepare the FreeMarker configuration:
        // - Load templates from the WEB-INF/templates directory of the Web app.
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(getServletContext(), "WEB-INF/templates");
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        
        Map<String, String> root = null;
        Template resultTemplate = null;
        BufferedWriter toClient = null;
        long user_id = 0;
        
        String fn = null;
        String ln = null;
        String email = null;
        String password1 = null;
        String password2 = null;
        
        // Load templates from the WEB-INF/templates directory of the Web app.
        try {
            resultTemplate = cfg.getTemplate( resultTemplateName );
        }
        catch (IOException e) {
            throw new ServletException("Can't load template in: " + templateDir + ": " + e.toString());
        }
        
        // Prepare the HTTP response:
        // - Use the charset of template for the output
        // - Use text/html MIME-type
        toClient = new BufferedWriter(new OutputStreamWriter(res.getOutputStream(), resultTemplate.getEncoding()));
        
        res.setContentType("text/html; charset=" + resultTemplate.getEncoding());
        
        // Get the parameters
        fn = req.getParameter( "fn" );
        ln = req.getParameter( "ln" );
        email = req.getParameter( "email" );
        password1 = req.getParameter( "password1" );
        password2 = req.getParameter( "password2" );
        
        if( fn == null ) {
            UTradeException.error( cfg, toClient, "Unspecified first name" );
            return;
        }
        
        if( ln == null ) {
            UTradeException.error( cfg, toClient, "Unspecified last name" );
            return;
        }
        
        if( email == null ) {
            UTradeException.error( cfg, toClient, "Unspecified email" );
            return;
        }
        
        if( password1 != password2 || password1 == null) {
            UTradeException.error( cfg, toClient, "Passwords are not the same" );
            return;
        }
        
        try {
            user_id = CtrlCreateUser.createUser( fn, ln, email, password1);
        }
        catch ( Exception e ) {
            UTradeException.error( cfg, toClient, e );
            return;
        }
        
        // Setup the data-model
        root = new HashMap<String, String>();
        
        // Build the data-model
        root.put( "fn", fn );
        root.put( "ln", ln );
        root.put( "user_id", Long.toString( user_id ) );
        
        // Merge the data-model and the template
        try {
            resultTemplate.process( root, toClient );
            toClient.flush();
        }
        catch (TemplateException e) {
            throw new ServletException( "Error while processing FreeMarker template", e);
        }
        
        toClient.close();
    }
}
