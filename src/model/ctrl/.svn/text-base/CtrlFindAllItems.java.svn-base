//
//
//
//
//

package model.ctrl;

import java.util.*;

import except.*;
import model.entity.*;
import persist.*;

public class CtrlFindAllItems {
    
    PersistenceModuleImpl   pm = null;
    Vector<Item>        sv = null;
    Iterator            iit = null;
    Item                i = null;
        
    sv = new Vector<Item>();
        
    pm = PersistenceModuleFactory.createPersistenceModule();
    
    /*public static Vector<Item> findBiddingItems(int ?) throws UTradeException{
        iit= pm.restoreBiddingItems(?);
        populate();
        return sv;
    }*/
    
    public static Vector<Item> findWonItems(long user_id) throws UTradeException{
    
        //retrieving all won items
        iit = pm.restoreWonItems(user_id);
        populate();
        return sv;
    }
    
    public static Vector<Item> findItems(long user_id) throws UTradeException{
        
        iit = pm.restoreItems(user_id);
        populate();
        return sv;
    }
    
    public static Vector<Item> findSoldItems(long user_id) throws UTradeException{
        iit = pm.restoreSoldItems(user_id);
        populate();
        return sv;
    }

   
    public static Vector<Item> fidCategoryItems(long category_id) throws UTradeException{
	iit = pm.restoreCategoryItems(category_id);
	populate();
	return sv;
    }

    public static void populate(){
        while(iit.hasNext()){
            i = (Item) iit.next();
            sv.add(i);
        }
    }
    
        
    
    
    
}
