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

public class CtrlFindAllBids {

    public static Vector<Bid> findAllBids() throws UTradeException{
        
        PersistenceModuleImpl   pm = null;
        Vector<Bid>         sv = null;
        Iterator            bit = null;
        Bid                 b = null;
        
        sv = new Vector<Bid>();
        
        pm = PersistenceModuleFactory.createPersistenceModule();

        //retrieving all Bids
        bit = pm.restoreBids();
        
        while(bit.hasNext()){
            b = (Bid) bit.next();
            sv.add(b);
        }
        
        return sv;
    }

}
