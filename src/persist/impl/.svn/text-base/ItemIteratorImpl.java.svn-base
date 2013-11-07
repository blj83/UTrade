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

public class ItemIteratorImpl implements Iterator{

    // attributes
    ResultSet   i_rs = null;
    boolean     i_more = false;
    PersistenceModule 	pm = null;    

    //constructor
    public ItemIteratorImpl (ResultSet rs) throws UTradeException {
            i_rs = rs;
            try {
                i_more = rs.next();
            }
            catch(Exception e){
                throw new UTradeException("Cannot create Item iterator; root cause: " + e);
            }
    }

    public boolean hasNext(){
        return i_more;
    }
    
    public Object next(){
        
        long q_id = 0;
        double q_finalPrice = 0;
        double q_minBid = 0;
        User q_seller = null;
        Bid q_topBid = null;
        java.util.Date q_auctionEnd = null;
        String q_name = null;
        int q_quantity = 0;
        Category q_category = null;
        Vector<AttributeValue> q_attributes = null;
        Vector<Bid> q_bids = null;
        boolean q_isActive = false;
        
	int user_id = 0;
	int bid_id = 0;
	int category_id = 0;

        if (i_more){
            
            try{
                q_id = i_rs.getlLong(1);
                user_id = i_rs.getInt(2);
                bid_id = i_rs.getInt(3);
                q_minBid = i_rs.getDouble(4);
                q_auctionEnd = i_rs.getDate(5);
                q_name = i_rs.getString(6);
                q_quantity = i_rs.getInt(7);
                category_id = i_rs.getInt(8);
                q_isActive = i_rs.getBoolean(9);
                
                i_more = i_rs.next();
            }
            catch( Exception e ) {	// just in case...
                throw new NoSuchElementException( "No next Person object; root cause: " + e );
            }
            
	    pm = PersistenceModuleFactory.createPersistenceModule();
	    q_seller = pm.restoreUser();
	    q_topBid = pm.restoreBid();
	    q_category = pm.restoreCategory();
	    		
            return ModelFactory.createItem(q_id,  q_finalPrice, q_minBid,q_seller, q_topBid, q_auctionEnd, q_name, q_quantity, q_category, q_attributes, q_bids, q_isActive);
        }
        else{
            throw new NoSuchElementException( "No next Club object" );
        }
    }
    
    public void remove(){
        throw new UnsupportedOperationException();
    }

}
