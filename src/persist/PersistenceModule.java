package persist;


import java.util.*;
import java.sql.*;
import model.entity.*;
import except.*;

public interface PersistenceModule {
	
	public Item 	 	restoreItem( long id ) throws UTradeException;
	public long		storeItem(Item item) throws UTradeException;
	
	public User		restoreUser(long id) throws UTradeException;
	public long		storeUser(User user) throws UTradeException;
	
	public Bid		restoreBid(long id) throws UTradeException;
	public Iterator restoreBids(Item i) throws UTradeException;
	public long		storeBid(Bid bid) throws UTradeException;
	
	public Category 	restoryCategory(long id) throws UTradeException;
	public Iterator restoreCategories() throws UTradeException;
	public long		storeCategory(Category c) throws UTradeException;
	
	public Billing	restoreBilling(long id) throws UTradeException;
	public long		storeBilling(Billing b) throws UTradeException;
	
	public Attribute	restoreAttribute(long id) throws UTradeException;
	public Iterator restoreAttributes(long c_id) throws UTradeException;
	public long 		storeAttribute(Attribute a) throws UTradeException;
	
	public Payment	restorePayment(long id) throws UTradeException;
	public long		storePayment(Payment p) throws UTradeException;
	
	public Iterator restoreAttributeValues(Item item) throws UTradeException;
	public long storeAttributeValue(AttributeValue av) throws UTradeException;
	
	
};
