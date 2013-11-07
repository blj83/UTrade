import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import freemarker.template.*;
import persist.*;
import except.*;
import model.ctrl.*;

// Servlet class CreateItem
//
// this class functions as the boundary class for the CreateItem use case
//
// doPost starts the execution of the CreateItem Use Case
//
//   parameters:
//
// minBid           Bid
// auctionEnd       Date
// name            string
// quantity         int
//category			Category
// attributes		Vector<AttributeValue>

public class CreateUser extends HttpServlet {
    
    static  String templateDir = "WEB-INF/templates";
    static  String resultTemplateName = "CreateItem-Result.ftl";
    
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
        
		PersistenceModuleImpl pm = PersistenceModule.createPersistenceModule();
		
        String s_minBid = null;
		String s_auctionEnd = null;
		String s_name = null;
		String s_quantity = null;
		String s_category = null;
		String s_attributeValue1=null, s_attributeValue2=null, s_attributeValue3=null,
		s_attributeValue4=null, s_attributeValue5=null;
		String s_attributeName1=null, s_attributeName2=null, s_attributeName3=null,
		s_attributeName4=null, s_attributeName5=null;
		Vector<AttributeValue> attributevalues = null;
		Vector<Attribute> attributes = null;
        
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
		s_category = req.getParameter( "category" );
		s_name = req.getParameter( "email" );
        s_minBid = req.getParameter( "mindBid" );
        s_auctionEnd = req.getParameter( "ln" );
		s_quantity = req.getParameter( "quantity" );
		s_attributeName1 = req.getParameter( "attributeName1" );
		s_attributeName2 = req.getParameter( "attributeName2" );
		s_attributeName3 = req.getParameter( "attributeName3" );
		s_attributeName4 = req.getParameter( "attributeName4" );
		s_attributeName5 = req.getParameter( "attributeName5" );
		s_attributeValue1 = req.getParameter( "attributeValue1" );
		s_attributeValue2 = req.getParameter( "attributeValue2" );
		s_attributeValue3 = req.getParameter( "attributeValue3" );
		s_attributeValue4 = req.getParameter( "attributeValue4" );
		s_attributeValue5 = req.getParameter( "attributeValue5" );
		
        
        if( s_category == null ) {
            UTradeException.error( cfg, toClient, "Unspecified Category" );
            return;
        }
        
        if( s_name == null ) {
            UTradeException.error( cfg, toClient, "Unspecified name" );
            return;
        }
        
        if( s_mindBid == null ) {
            UTradeException.error( cfg, toClient, "Unspecified minimum or start price" );
            return;
        }
        
        if( s_auctionEnd == null) {
            UTradeException.error( cfg, toClient, "Auction end date not set" );
            return;
        }
		
        if( s_quantity == null) {
            UTradeException.error( cfg, toClient, "Quantity not set" );
            return;
        }
		
        if( s_auctionEnd == null) {
            UTradeException.error( cfg, toClient, "Auction end date not set" );
            return;
        }
		
        if( s_attributeName1 == null || s_attributeName2 == null || s_attributeName3 == null || s_attributeName4 == null || s_attributeName5 == null){
			UTradeException.error( cfg, toClient, "An attribute name was not set" );
            return;
        }
		
		if( s_attributeValue1 == null || s_attributeValue2 == null || s_attributeValue3 == null || s_attributeValue4 == null || s_attributeValue5 == null) {
			UTradeException.error( cfg, toClient, "An attribute value was not set" );
			return;
		}
		
		//convert strings to proper values
		//Category category = pm.restoreCategory(name.getId()
		double minBid = Double.parseDouble(s_mindBid);
		Date auctionEnd = new Date(
		
		
		try {
			user_id = CtrlCreateItem.createItem(minBid, seller, auctionEnd, name, quantity, category,  );
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
