package model.entity.impl;

import java.util.*;
import model.entity.*;

public class UserImpl implements User {
	
	//Entity Attributes
    private long    u_id;
    private boolean u_written;
    
    // user attr
    private String u_fn, u_ln, u_email, u_pass;
	private Vector<Item> u_biddingItems, u_wonItems, u_sellingItems, u_soldItems;
	private boolean u_isAdmin;
    
    // empty constructor
	public UserImpl(){
        this.u_id = 0;
        this.u_written = false;
        this.u_fn="Unknown";
        this.u_ln="Unknown";
        this.u_email="Unknown";
        this.u_pass="Unknown";
        this.u_isAdmin=false;
    	this.u_biddingItems = null;
        this.u_wonItems = null;
        this.u_sellingItems = null;
        this.u_soldItems = null;
	}
    
    // new simple user object
	public UserImpl(String fn, String ln, String email, String pass, boolean isAdmin, Vector<Item> biddingItems, Vector<Item> wonItems, Vector<Item> sellingItems, Vector<Item> soldItems){
		this.u_id = 0;
        this.u_written = false;
        this.u_fn=fn;
        this.u_ln=ln;
        this.u_email=email;
        this.u_pass=pass;
        this.u_isAdmin=isAdmin;
    	this.u_biddingItems = biddingItems;
        this.u_wonItems = wonItems;
        this.u_sellingItems = sellingItems;
        this.u_soldItems = soldItems;
	}
    
    // when retrieving from db
    public UserImpl(long id, String fn, String ln, String email, String pass, boolean isAdmin, Vector<Item> biddingItems, Vector<Item> wonItems, Vector<Item> sellingItems, Vector<Item> soldItems){
        this.u_id = id;
        this.u_written = false;
        this.u_fn=fn;
        this.u_ln=ln;
        this.u_email=email;
        this.u_pass=pass;
        this.u_isAdmin=isAdmin;
    	this.u_biddingItems = biddingItems;
        this.u_wonItems = wonItems;
        this.u_sellingItems = sellingItems;
        this.u_soldItems = soldItems;
    }
    
	//entity methods
    public boolean  get_isProxy()
    { return u_written; }
    
    public void  set_isProxy()
    { u_written = true; }
    
    public long  get_id()
    { return u_id; }
    
    public void  set_id( long id )
    { u_id = id; }
    
    // get
	public String getFirstName(){
		return this.u_fn;
	}
    
	public String getLastName(){
		return this.u_ln;
	}
    
	public String getEmail(){
		return this.u_email;
	}
    
	public String getPassword(){
		return this.u_pass;
	}
    
	public boolean getIsAdmin(){
		return this.u_isAdmin;
	}
    
	public Vector<Item> getBiddingItems(){
		return this.u_biddingItems;
	}
	
	public Vector<Item> getWonItems(){
		return this.u_wonItems;
	}
	
	public Vector<Item> getSellingItems(){
		return this.u_sellingItems;
	}
    
	public Vector<Item> getSoldItems(){
		return this.u_soldItems;
	}
    
	///////setters
	public void setFirstName(String fn){
		this.u_fn=fn;
	}
	
	public void setLastName(String ln){
		this.u_ln=ln;
	}
    
	public void setEmail(String email){
		this.u_email=email;
	}
    
	public void setPassword(String pass){
		this.u_pass=pass;
	}
    
	public void setIsAdmin(boolean isAdmin){
		this.u_isAdmin=isAdmin;
	}
    
	public void setBiddingItems(Vector<Item> biddingItems){
		this.u_biddingItems=biddingItems;
	}
    
	public void setWonItems(Vector<Item> wonItems){
		this.u_wonItems=wonItems;
	}
    
	public void setSellingItems(Vector<Item> sellingItems){
		this.u_sellingItems=sellingItems;
	}
    
	public void setSoldItems(Vector<Item> soldItems){
		this.u_soldItems=soldItems;
	}
}
