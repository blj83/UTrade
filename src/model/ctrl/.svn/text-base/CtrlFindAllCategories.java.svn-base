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

public class CtrlFindAllCategories {
    
    public static Vector<Category> findAllCategories() throws UTradeException{
        
        PersistenceModuleImpl   pm = null;
        Vector<Category>    sv = null;
        Iterator            cit = null;
        Category            c = null;
        
        sv = new Vector<Category>();
        
        pm = PersistenceModuleFactory.createPersistenceModule();
        
        //retrieving all Categories
        cit = pm.restoreCategories();
        
        while(cit.hasNext()){
            c = (Category) cit.next();
            sv.add(c);
        }
        
        return sv;
    }
    
}
