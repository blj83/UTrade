//
//
//
//

package model.ctrl;



import java.util.*;

import except.*;
import model.entity.*;
import persist.*;


public class CtrlFindAllAttributeValues {
    
    public static Vector<AttributeValue> findAllAttributeValues(long item_id) throws ClubsException{
        
        PersistenceModuleImpl 	pm = null;
        Vector<Club>	sv  = null;
        Iterator 		ait = null;
        Club     		a = null;
        
        sv = new Vector<AttributeValue>();
        
        pm = PersistenceModuleFactory.createPersistenceModule();
        
        // retrieve all AV objects
        //
        ait = pm.restoreAttributeValues(item_id);
        
        while( ait.hasNext() ) {
            
            a = (AttributeValue) ait.next();
            sv.add( a );
            
        }
        
        return sv;
    }
}
