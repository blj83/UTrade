
package model.ctrl;

import java.util.*;

import except.*;
import model.entity.*;
import persist.*;

public class CtrlFindAllAttributes {
    
        PersistenceModuleImpl   pm = null;
        Vector<Attribute>   sv = null;
        Iterator            ait = null;
        Attribute           a = null;
        
        sv = new Vector<Attribute>();
        pm = PersistenceModuleFactory.createPersistenceModule();
    
    public static Vector<Attribute> findAllAttributes(){     
        //retrieving all Attributes
        ait = pm.restoreAttributes();
	populate();
	return sv;
    }	
    
    public static Vector<Attribute> findAllCategoryAttributes(long c_id){
	ait = pm.restoreAttributes(c_id);
	populate();
	return sv;
    }

    
    public static void populate(){
        while(ait.hasNext()){
            a = (Attribute) ait.next();
            sv.add(a);
        }
    }
    
}
