
package persist.impl;


import java.util.*;
import java.sql.*;

import persist.*;
import model.*;
import except.*;

public class PersistenceModuleImpl
implements PersistenceModule

{
	
	private Connection p_conn = null;
	
	private Statement  p_stmt = null;
	
	public PersistenceModuleImpl( String url, String user, String pass )
	
    throws UTradeException
	
	{
		try {
			
			// create the driver for MySQL
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			// establish the databse connection
			p_conn = DriverManager.getConnection( url, user, pass );
			if( p_conn == null )
				throw new UTradeException( "Could not establish connection with the database");
			
			// create a statement
			p_stmt = p_conn.createStatement();
			if( p_stmt == null )
				throw new UTradeException( "Could not create JDBC statement" );
			
		}
		catch( Exception e ) {	// just in case...
			e.printStackTrace();
		}
	}
	
	
	public Attribute restoreAttribute(long id) throws UTradeException{
		
		String sql = "select * from Attribute where Id = " + id;
		long a_id = 0;
		Category a_category = null;
		String name = null;
		
		int category = 0;
		
		try{
			
			//retrieve the Attribute Object
			if(p_stmt.execute(sql)){
				
				ResultSet r = p_stmt.getResultSet();
				
				while(r.next()){
					
					a_id = r.getLong(1);
					category = r.getInt(2);
					name = r.getString(3);
				}
			}
		}
		catch(Exception e){
			throw new UTradeException(e);
		}
		a_category = restoreCategory(category);
		return ModelFactory.createAttribute(a_id, a_category, name);
	}
	
	public Iterator restoreAttributes(long c_id) throws UTradeException{
		String sql = "select * from Attributes where Of_Category = " + c_id;
		
		try{
			if(p_stmt.execute(sql)){
				ResultSet r = p_stmt.getResultSet();
				return new AttributeIteratorImpl(r);
			}
		}
		catch(Exception e){
			throw new UTradeException(e);
		}
		throw new UTradeException("Could not restore peristent Attribute objects of Category " + c.getName());
	}
	
	public long storeAttribute(Attribute a) throws UTradeException{
		
		String sql = "insert into Attribute (Of_Category,Name) values (" + a.getOf_category.get_id() +
		", '" + name + "')";
		
		int inscnt = 0;
		long attribute_id = 0;
		
		try{
			inscnt = p_stmt.executeUpdate(sql);
			if(inscnt == 1){
				sql = "select last_insert_id()";
				if(p_stmt.execute(sql)){
					ResultSet r = p_stmt.getResultSet();
					while(r.next()){
						attribute_id = r.getLong(1);
						if(attribute_id > 0){
							a.set_id(attribute_id);
							a.set_isProxy();
							return attribute_id;
						}
					}
				}
			}
		}
		catch(Exception e){
			throw new UTradeException(e);
		}
		throw new UTradeException("Could not store Attribute Object");
	}
	
	public Iterator restoreAttributeValues(Item item) throws UTradeException{
		String sql = "select Value, Of_Attribute from Attribute_Values where Of_Item = " + item.getId();
		
		try{
			if(p_stmt.execute(sql)){
				ResultSet r = p_stmt.getResultSet();
				return new AttributeValuesIterator(item, r);
			}
		}
		catch(Exception e){
			throw new UTradeException("root cause" + e);
		}
		throw new UTradeException("Could not select Attribute VAlyes for Item");
	}
	
    public long storeAttributeValue(AttributeValue av) throws UTradeException{
		
        String sql = "insert into AttributeValues (Value,Of_Item,Of_Attribute) values ('" + av.get_Value() + "',"
		+ av.get_Of_Item.get_id() + "," + av.get_Of_Attribute.get_id() + ")";
		
        int inscnt = 0;
        long attribute_id = 0;
		
		try{
            inscnt = p_stmt.executeUpdate(sql);
            if(inscnt == 1){
                sql = "select last_insert_id()";
                if(p_stmt.execute(sql)){
                    ResultSet r = p_stmt.getResultSet();
                    while(r.next()){
                        attribute_id = r.getLong(1);
                        if(attribute_id > 0){
                            a.set_id(attribute_id);
                            a.set_isProxy();
                            return attribute_id;
                        }
                    }
                }
            }
    	}
        catch(Exception e){
            throw new UTradeException(e);
        }
        throw new UTradeException("Could not store AttributeValue Object");
    }
	
	public Bid restoreBid(long id) throws UTradeException{
		String sql = "select * from Bid where Id = " + id;
		long b_id = 0;
		User b_of_user = null;
		Item b_of_item = null;
		double b_value = 0;
		
		int user_id = 0;
		int item_id = 0;
		
		try{
			if( p_stmt.execute( sql ) ) { // statement returned a result
				
                // retrieve the result
                ResultSet r = p_stmt.getResultSet();
				
                // we will use only the first row!
                //
                while( r.next() ) {
					b_id = r.getLong(1);
					user_id = r.getInt(2);
					item_id = r.getInt(3);
					b_value = r.getString(4);
				}
				b_of_user = restoreUser(user_id);
				b_of_item = restoreItem(item_id);
				
				return ModelFactory.createBid(b_id,b_of_user,b_of_item,b_value);
			}
		}
		catch(Exception e){
			throw new UTradeException(e);
		}
		throw new UTradeException("Could not retrieve Bid object with id" + id);
	}
	
    public Iterator restoreBids(Item i) throws UTradeException{
        String sql = "select * from Bid where Of_Item = " + i.get_id();
		
        try{
            if(p_stmt.execute(sql)){
                ResultSet r = p_stmt.getResultSet();
                return new AttributeIteratorImpl(i,r);
            }
		}
		catch(Exception e){
			throw new UTradeException(e);
		}
		throw new UTradeException("Could not restore persistent Bid objects");
	}
	
    public Iterator restoreBids(User u) throws UTradeException{
        String sql = "select * from Bid where Of_User = " + u.get_id();

        try{
            if(p_stmt.execute(sql){
                ResultSet r = p_stmt.getResultSet();
                return new AttributeIteratorImpl(u,r);
            }
    	}
        catch(Exception e){
            throw new UTradeException(e);
        }
    	throw new UTradeException("Could not restore persistent	Bid objects");
    }
	
	public long storeBid(Bid b) throws UTradeException{
		
		String sql = "insert into Bid (Of_User,Of_Item,Amount) values (" + b.getOf_User + "," +
		b.getOf_Item() + ", '" + b.getValue() + "')";
		
		int inscnt = 0;
		long bid_id = 0;
		
		try{
			inscnt = p_stmt.executeUpdate(sql);
			if(inscnt == 1){
				sql = "select last_insert_id()";
				if(p_stmt.execute(sql)){
					ResultSet r = p_stmt.getResultSet();
					while(r.next()){
						bid_id = r.getLong(1);
						if(bid_id > 0){
							b.set_id(bid_id);
							b.set_isProxy();
							return bid_id;
						}
					}
				}
			}
		}
		catch(Exception e){
			throw new UTradeException(e);
		}
		throw new UTradeException("Could not store Bid Object");
	}
	
	public Billing restoreBilling(long id) throws UTradeException{
		String sql = "select * from Billing where id = " + id;
		long    b_id = 0;
		String  b_AddressNumber = null;
		String  b_StreetName = null;
		String  b_ZipCode = null;
		String  b_ApartmentNumber = null;
		String  b_State = null;
		
		try{
			if( p_stmt.execute( sql ) ) { // statement returned a result
				
				// retrieve the result
				ResultSet r = p_stmt.getResultSet();
				
				// we will use only the first row!
				//
				while( r.next() ) {
					b_id = getLong(1);
					b_AddressNumber = getString(2);
					b_StreetName = getString(3);
					b_ZipCode = getString(4);
					b_ApartmentNumber = getString(5);
					b_State = getString(6);
					
					return ModelFactory.createBilling(b_id,b_AddressNumber,b_StreetName,
													  b_ZipCode,b_AppartmentNumber,b_State);
				}
			}
		}
		catch(Exception e){
			throw new UTradeException(e);
		}
		throw new UTradeException("Could not retrieve persistent Billing object with id: " + id);
	}
	
	public long storeBilling(Billing b) throws UTradeException{
		
		String sql = "insert into Billing (Addr_No,Street,Zipcode,Appt_No,State) values ('" + b.getAddressNumber()
		+ "','" + b.getStreetName() + "','" + b.getZipcode + "','" + b.getAppartmentNumber + "','"
		+ b.getState() + "')";
		
		
		int inscnt = 0;
		long billing_id = 0;
		
		try{
			inscnt = p_stmt.executeUpdate(sql);
			if(inscnt == 1){
				sql = "select last_insert_id()";
				if(p_stmt.execute(sql)){
					ResultSet r = p_stmt.getResultSet();
					while(r.next()){
						billing_id = r.getLong(1);
						if(billing_id > 0){
							b.set_id(billing_id);
							b.set_isProxy();
							return billing_id;
						}
					}
				}
			}
		}
		catch(Exception e){
			throw new UTradeException(e);
		}
		throw new UTradeException("Could not store Billing Object");
	}
	
	
	public Bid restoreCategory(long id) throws UTradeException{
		
		String sql = "select * from Category where Id = " + id;
		long c_id = 0;
		Vector<Item> c_items = null;
		Vector<Attribute> c_attributes = null;
		double c_percent = 0;
		//Category c_parent = null;
		Vector<Category> c_children = null;
		String c_name = null;
		
		int parent_id = 0;
		
		try{
			if( p_stmt.execute( sql ) ) { // statement returned a result
				
				// retrieve the result
				ResultSet r = p_stmt.getResultSet();
				
				// we will use only the first row!
				//
				while( r.next() ) {
					c_id = r.getLong(1);
					c_name = r.getString(2);
					c_percent = r.getInt(3);
				}
				c_items = CtrlFindAllItems.findCategoryItems(c_id);
				c_attributes = CtrlFindAllAttributes.findAllCategoryAttributes(c_id);
				c_children = CtrlFindAllChildren(parent_id);
				
				return ModelFactory.createCategory(c_id,c_items,c_attributes,c_percent,c_children,c_name);
			}
		}
		catch(Exception e){
			throw new UTradeException(e);
		}
		throw new UTradeException("Could not retrieve Bid object with id" + id);
	}
	
	public Iterator restoreCategories() throws UTradeException{
		String sql = "Select * from Category";
		try{
			if(p_stmt.execute(sql)){
				ResultSet r = p_stmt.getResultSet();
				return new CategoryIteratorImpl(r);
			}
			
		} catch(Exception e){
			throw new UTradeException(e);
		}
		throw new UTradeException("Could not retrieve all persistent Category objects");
	}
	
	public long storeCategory(Category c) throws UTradeException{

        String sql = "insert into Category (Name,Percent_Charged) values ('" c.getName() + "'," +
			c.getPercentCharged() + ")";

        int inscnt = 0;
        long c_id = 0;

        try{
            inscnt = p_stmt.executeUpdate(sql);
            if(inscnt == 1){
                sql = "select last_insert_id()";
                if(p_stmt.execute(sql)){
                    ResultSet r = p_stmt.getResultSet();
                    while(r.next()){
                        c_id = r.getLong(1);
                        if(c_id > 0){
                            c.set_id(c_id);
                            c.set_isProxy();
                            return c_id;
                        }
                    }
                }
            }
        }
        catch(Exception e){
		    throw new UTradeException(e);
        }
        throw new UTradeException("Could not store Category Object");
    }
	
	public Iterator restoreCategoryItems(long c_id) throw UTradeException{
    	String sql = "Select * from Item where Of_category = " + c_id;     
    	try{
    	    if(p_stmt.execute(sql){
    	    	ResultSet r = p_stmt.getResultSet();
    	    	return new CategoryIteratorImpl(r);
    	    }
    	}
    	catch(Exception	e){
    	    throw new UTradeException(e);
    	}
    	throw new UTradeException("Could not retrieve all persistent Items objects belonging to Category " +
		c_id); 
    }


	public Iterator restoreChildrenCategories(long parent_id) throws UTradeException{
		String sql = "Select c.Id,c.Name,c.Percent_Charged from Category c,Category_Relationships r where
		r.Parent = " + parent_id + " and c.id = r.Child";
		
		try{
			if(p_stmt.execute(sql)){
				ResultSet r = p_stmt.getResultSet();
				return new CategoryIteratorImpl(r);
			}
		}
		catch(Exception e){
			throw new UTradeException(e);
		}
		throw new UTradeException("Could not retrieve all persistent Category objects children of Category " +
								  parent_id);
	}
	
	public Item restoreItem(long id) throws UTradeException{
		String sql = "Select * from Item where Id = " + id;
		long i_id = 0;
		User i_seller = null;
		Bid i_topbid = null;
		double i_minbid = 0;
		java.util.Date i_auction_end = null;
		String i_name = null;
		int i_quantity = 0;
		Vector<AttributeValue> i_attributes = null;
		Vector<Bid> i_bids = null;
		double i_finalPrice = 0;
		Category i_category = null;
		Boolean i_isActive = true;

		int user_id = 0;
		int b_id = 0;
		int c_id = 0;
	
		try{
			if(p_stmt.execute(sql)){
				ResultSet r = p_stmt.getResultSet();
				while(r.next()){
					i_id = r.getLong(1);
					user_id = r.getInt(2);
					b_id = r.getInt(3);
					i_minbid = r.getDouble(4);
					i_auction_end = r.getDate(5);
					i_name = r.getString(6);
					i_quantity = r.getInt(7);
					c_id = r.getInt(8);
					i_isActive = r.getBoolean(9);
				}
				i_seller = restoreUser(user_id);
				i_topbid = restoreBid(b_id);
				i_category = restoreCategory(c_id);
				i_attributes = CtrlFindAllAttributeValues.findAllAttributeValues(i_id);
				i_bids = CtrlFindAllBids.findAllItemBids(i_id);

				return ModelFactory.createItem(i_id,i_finalPrice,i_minbid,i_seller,i_topbid,i_auction_end,
					i_name,i_quantity,i_category,i_attributes,i_bids,i_isActive);
				
			}
		}
		catch(Exception e){
			throw new UTradeException(e);
		}
		throw new UTradeException("Could not restore Item + " id);
	}

    public Iterator restoreItems(long u_id) throw UTradeException{
        String sql = "Select * from Item where Of_User = " + u_id;
        try{
            if(p_stmt.execute(sql){
                ResultSet r = p_stmt.getResultSet();
                return new CategoryIteratorImpl(r);
            }
		}
		catch(Exception e){
            throw new UTradeException(e);
        }
		throw new UTradeException("Could not retrieve all persistent Items objects belonging to User " +
      	  u_id);
    }

	public long storeItem(Item i) throws UTradeException{

        String sql = "insert into Item (Of_User,Top_Bid,Min_Bid,Auction_End,Name,Quantity,Of_Category,Is_Active,
			Final_Price) values (" i.getSeller().get_id() + "," + i.getTopBid().get_id() + "," + i.getMinBid() + ","
			+ i.getAuctionEnd() + ",'" i.getName() + "'," + i.getQuantity + "," i.getCategory + "," i.getIsActive()
			+ "," + i.getFinalPrice() + ")";

        int inscnt = 0;
        long i_id = 0;

        try{
            inscnt = p_stmt.executeUpdate(sql);
            if(inscnt == 1){
                sql = "select last_insert_id()";
                if(p_stmt.execute(sql)){
                    ResultSet r = p_stmt.getResultSet();
                    while(r.next()){
                        i_id = r.getLong(1);
                        if(i_id > 0){
                            i.set_id(i_id);
                            i.set_isProxy();
                            return i_id;
                        }
                    }
                }
            }
        }
        catch(Exception e){
            throw new UTradeException(e);
        }
        throw new UTradeException("Could not store Item Object");
    }
	
	public Payment restorePayment(long id) throws UTradeException{
		String sql = "select * from Payment where Id = " + id);
		long p_id = 0;
		String p_cardType;
		String p_cardNumber;
		String p_securityCode;
		String p_expDate;
		
		try{
			if(p_stmt.execute(sql)){
				ResultSet r = p_stmt.getResultSet();
				while(r.next()){
					p_id = r.getLong(1);
					p_cardType = r.getString(2);
					p_securityCode = r.getString(3);
					p_expDate = r.getString(4);
					p_cardNumber = r.getString(5);
				}
				return ModelFactory.createPayment(p_id,p_cardType,p_securityCode,p_expDate,p_cardNumber);
			}
		}
		catch(Exception e){
			throw new UTradeException(e);
		}
		throws new UTradeException("Could not restore Payment object" + id);
	}
	
	public long storePayment(Payment p) throws UTradeException{
		String sql = "insert into Payment (Card_Type,Security_Code,Expiration_Date,Card_Number) values ('" +
			p.getCardType() + "','" + p.getSecurityCode() + "','" + p.getExpirationDate() + "','" +
			p.getCardNumber() + "')";
		
        int inscnt = 0;
        long p_id = 0;

        try{
            inscnt = p_stmt.executeUpdate(sql);
            if(inscnt == 1){
                sql = "select last_insert_id()";
                if(p_stmt.execute(sql)){
                    ResultSet r = p_stmt.getResultSet();
                    while(r.next()){
                        p_id = r.getLong(1);
                        if(p_id > 0){
                            p.set_id(p_id);
                            p.set_isProxy();
                            return p_id;
                        }
                    }
                }
            }
        }
        catch(Exception e){
            throw new UTradeException(e);
        }
        throw new UTradeException("Could not store Payment Object");
    }
	
	public User restoreUser(long id) throws UTradeException{
		String sql = "select * from user where Id = " + id;
		long u_id = 0;
		String u_firstName = null;
		String u_lastName = null;
		String u_email = null;
		String u_password = null;
		Boolean u_isAdmin = false;
		
		try{
			if(p_stmt.execute(sql)){
				ResultSet r = p_stmt.getResultSet();
				while(r.next()){
					u_id = r.getLong(1);
					u_firstName = r.getString(2);
					u_lastName = r.getString(3);
					u_email = r.getString(4);
					u_password = r.getString(5);
					u_isAdmin = r.getBoolean(6);
				}
				return ModelFactory.createUser(u_id,u_firstName,u_lastName,u_email,u_password,u_isAdmin);
			}
		}
		catch(Exception e){
			throw new UTradeException(e);
		}
		throw new UTradeException("Could not restore User object " + id);
	}	

    public long storeUser(User u) throws UTradeException{

		String sql = "insert into User (First_Name,Last_Name,Email,Password,Is_Admin) values ('"
			u.getFirstName + "','" + u.getLastName() + "','" + u.getEmail + "','" u.getPassword + "'," +
			u.getIsAdmin() + ")";
		
        int inscnt = 0;
        long u_id = 0;

        try{
            inscnt = p_stmt.executeUpdate(sql);
            if(inscnt == 1){
                sql = "select last_insert_id()";
                if(p_stmt.execute(sql)){
                    ResultSet r = p_stmt.getResultSet();
                    while(r.next()){
                        u_id = r.getLong(1);
                        if(u_id > 0){
                            u.set_id(u_id);
                            u.set_isProxy();
                            return u_id;
                        }
                    }
                }
            }
        }
        catch(Exception e){
            throw new UTradeException(e);
        }
        throw new UTradeException("Could not store Category Object");
    }
}
