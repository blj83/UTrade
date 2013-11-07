import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import freemarker.template.*;

import except.*;
import model.ctrl.*;

// Servlet class MakePayment
//
// this class functions as the boundary class for the MakePayment use case
//
// doPost starts the execution of the MakePayment use case
//
// parameters:
//
// p_cardType   String
// p_secCode    String
// p_expDate    String
// p_cardNum    douStringble

public class CreateUser extends HttpServlet {
    
    static  String templateDir = "WEB-INF/templates";
    static  String resultTemplateName = "MakePayment-Result.ftl";
    
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
        
        String cardType;
        String secCode
        String expDate;
        String cardNum;
        
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
        cardType = req.getParameter( "cardType" );
        secCode = req.getParameter( "secCode" );
        expDate = req.getParameter( "expDate" );
        cardNum = req.getParameter( "cardNum" );
        
        if( cardType == null ) {
            UTradeException.error( cfg, toClient, "Unspecified card type" );
            return;
        }
        
        if( secCode == null ) {
            UTradeException.error( cfg, toClient, "Unspecified security code" );
            return;
        }
        
        if( expDate == null ) {
            UTradeException.error( cfg, toClient, "Unspecified expiration date" );
            return;
        }
        
        if( cardNum == null) {
            UTradeException.error( cfg, toClient, "Unspecified card number" );
            return;
        }
        
        try {
            user_id = CtrlCreateUser.createUser( cardType, secCode, expDate, cardNum);
        }
        catch ( Exception e ) {
            UTradeException.error( cfg, toClient, e );
            return;
        }
        
        // Setup the data-model
        root = new HashMap<String, String>();
        
        // Build the data-model
        root.put( "cardType", cardType);
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
