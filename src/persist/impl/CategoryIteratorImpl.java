//
//
//
//
//


package persist.impl;

import java.util.*;
import java.sql.*;
import persist.*;
import except.*;
import model.entity.*;
import model.ctrl.*;

public class CategoryIteratorImpl implements Iterator{
    
    // attributes
    ResultSet   c_rs = null;
    boolean     c_more = false;
    PersistenceModule 	pm = null;
	   
 
    //constructor
    public CategoryIteratorImpl (ResultSet rs) throws UTradeException {
        c_rs = rs;
        try {
            c_more = rs.next();
        }
        catch(Exception e){
            throw new UTradeException("Cannot create Item iterator; root cause: " + e);
        }
    }
    
    public boolean hasNext(){
        return c_more;
    }
    
    public Object next(){
        
        long q_id = 0;
        String q_name = null;
        double q_percent_charged =  0;
        Vector<Item> q_Items = null;
        Vector<Attribute> q_Attributes = null;
        Category q_ParentCategory = null;
        Vector<Category> q_ChildrenCategory = null;
        
	int category_id = 0;

        if (c_more){
            
            try{
                q_id = c_rs.getlLong(1);
                q_name = c_rs.getString(2);
                q_percent_charged = c_rs.getDouble(3);
                
                c_more = c_rs.next();
            }
            catch( Exception e ) {	// just in case...
                throw new NoSuchElementException( "No next Person object; root cause: " + e );
            }
            
	    pm = PersistenceModuleFactory.createPersistenceModule();
              
	  	
	    return ModelFactory.createCategory(q_id, q_Items, q_Attributes, q_percent_charged, q_ParentCategory, q_ChildrenCategory);
        }
        else{
            throw new NoSuchElementException( "No next Club object" );
        }
    }
    
    public void remove(){
        throw new UnsupportedOperationException();
    }
    
}
