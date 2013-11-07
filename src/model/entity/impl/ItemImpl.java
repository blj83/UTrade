//
//
//Class: itemImpl
//
//Ben Johnson
//
//

package model.entity.impl;

import model.entity.*;
import java.util.*;

//Implementation of the Item class
public class ItemImpl implements Item{
    
    //Entity Attributes
    private long    i_id;
    private boolean i_written;
    //Item Attributes
    private double  i_FinalPrice, i_MinBid;
    private User    i_Seller;
    private Bid     i_TopBid;
    private Date    i_AuctionEnd;
    private String  i_Name;
    private int     i_Quantity;
    private Category    i_Category;
    private Vector<AttributeValue> i_Attributes;
    private Vector<Bid> i_Bids;
    private boolean i_IsActive;
    
    //Empty constructor
    public ItemImpl(){
        i_id = 0;
        i_written = false;
        i_FinalPrice = 0;
        i_MinBid = 0;
        i_Seller = null;
        i_TopBid = null;
        i_AuctionEnd = new Date(0);
        i_Name = "Unknown";
        i_Quantity = 0;
        i_Category = null ;
        i_Attributes = null;
        i_Bids = null;
        i_IsActive = false;
    }
    
    //When creating a new Item object
    public ItemImpl(
                    double finalPrice,
                    double minBid,
                    User seller,
                    Bid topBid,
                    Date auctionEnd,
                    String name,
                    int quantity,
                    Category category,
                    Vector<AttributeValue> attributes,
                    Vector<Bid> bids){
        
        
        i_id = 0;
        i_written = false;
        i_FinalPrice = finalPrice;
        i_MinBid = minBid;
        i_Seller = seller;
        i_TopBid = topBid;
        i_AuctionEnd = auctionEnd;
        i_Name = name;
        i_Quantity = quantity;
        i_Category = category;
        i_Attributes = attributes;
        i_Bids = bids;
        i_IsActive = true;
    }
    
    //When retrieving from the database
    public ItemImpl(long id,
                    double finalPrice,
                    double minBid,
                    User seller,
                    Bid topBid,
                    Date auctionEnd,
                    String name,
                    int quantity,
                    Category category,
                    Vector<AttributeValue> attributes,
                    Vector<Bid> bids,
                    boolean isActive){
        i_id = id;
        i_written = false;
        i_FinalPrice = finalPrice;
        i_MinBid = minBid;
        i_Seller = seller;
        i_TopBid = topBid;
        i_AuctionEnd = auctionEnd;
        i_Name = name;
        i_Quantity = quantity;
        i_Category = category;
        i_Attributes = attributes;
        i_Bids = bids;
        i_IsActive = isActive;
    }
    
    //entity methods
    public boolean  get_isProxy()
    { return i_written; }
    
    public void  set_isProxy()
    { i_written = true; }
    
    public long  get_id()
    { return i_id; }
    
    public void  set_id( long id )
    { i_id = id; }
    
    //Item methods
    //Getters
    public User getSeller(){return i_Seller;}
    public Bid getTopBid() {return i_TopBid;}
    public double getMinBid() {return i_MinBid;}
    public Date getAuctionEnd() {return i_AuctionEnd;}
    public String getName() {return i_Name;}
    public int getQuantity() {return i_Quantity;}
    public Vector<AttributeValue> getAttributes() {return i_Attributes;}
    public Vector<Bid> getBids(){return i_Bids;}
    public double getFinalPrice() {return i_FinalPrice;}
    public Category getCategory() {return i_Category;}
    public boolean getIsActive() {return i_IsActive;}
    
    //setters
    public void setSeller(User seller) {i_Seller = seller;}
    public void setTopBid(Bid bid) {i_TopBid = bid;}
    public void setMinBid(double minBid) {i_MinBid = minBid;}
    public void setAuctionEnd(Date endingDate) {i_AuctionEnd = endingDate;}
    public void setName(String name) {i_Name = name;}
    public void setQuantity(int quantity) {i_Quantity = quantity;}
    public void setAttributes(Vector<AttributeValue> attributes) {i_Attributes = attributes;}
    public void setBids(Vector<Bid> bids) {i_Bids = bids;}
    public void setFinalPrice(double price) {i_FinalPrice = price;}
    public void setCategory(Category category) {i_Category = category;}
    public void setIsActive(boolean isActive) {i_IsActive = isActive;}
    
}
