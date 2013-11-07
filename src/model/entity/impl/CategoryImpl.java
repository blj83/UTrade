//
//
//
//
//

package model.entity.impl;

import model.entity.*;
import java.util.*;

public class CategoryImpl implements Category{
    
    //Entity Attributes
    private long        c_id;
    private boolean     c_written;

    //Category Attributes
    private Vector<Item>    c_Items;
    private Vector<Attribute> c_Attributes;
    private double          c_PercentCharged;
    private Category        c_ParentCategory;
    private Vector<Category>  c_ChildrenCategory;
	private String c_Name;
    
    //Constructors
    //Empty constructor
    public CategoryImpl(){
        c_id = 0;
        c_written = false;
        c_Items = null;
        c_Attributes = null;
        c_PercentCharged = 0;
        c_ParentCategory = null;
        c_ChildrenCategory = null;
    }
    
    //Created by the admin via the form
    public CategoryImpl(Vector<Item> items, Vector<Attribute> attributes,
                        double percentCharged, Category parentCategory,
                        Vector<Category> childrenCategory){
        
        c_id = 0;
        c_written = false;
        c_Items = items;
        c_Attributes = attributes;
        c_PercentCharged = percentCharged;
        c_ParentCategory = parentCategory;
        c_ChildrenCategory = childrenCategory;
    }
    
    //Retrieving the information from the datbase
    public CategoryImpl(long id, Vector<Item> items, Vector<Attribute> attributes,
                        double percentCharged, Category parentCategory,
                        Vector<Category> childrenCategory){
        c_id = id;
        c_written = true;
        c_Items = items;
        c_Attributes = attributes;
        c_PercentCharged = percentCharged;
        c_ParentCategory = parentCategory;
        c_ChildrenCategory = childrenCategory;
        
    }
    
    //Entity methods
    public boolean  get_isProxy()
    { return c_written; }
    
    public void  set_isProxy()
    { c_written = true; }
    
    public long  get_id()
    { return c_id; }
    
    public void  set_id( long id )
    { c_id = id; }

    
    //getters
    public Vector<Item> getItems(){return c_Items;}
    public Vector<Attribute> getAttributes(){return c_Attributes;}
    public double getPercentCharged(){return c_PercentCharged;}
    public Category getParentCategory(){return c_ParentCategory;}
    public Vector<Category> getChildrenCategory(){return c_ChildrenCategory;}
	public String getName(){return c_Name;}
    
    //setters
    public void setItems(Vector<Item> items){c_Items = items;}
    public void setAttributes(Vector<Attribute> attributes){c_Attributes = attributes;}
    public void setPercentCharged(double percent){c_PercentCharged = percent;}
    public void setParentCategory(Category parent){c_ParentCategory = parent;}
    public void setChildrenCategory(Vector<Category> children){c_ChildrenCategory = children;}
	public void setName(String name){c_Name = name;}

}
