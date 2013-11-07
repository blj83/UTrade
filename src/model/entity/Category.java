//
//
//

package model.entity;

import java.util.*;

public interface Category extends Entity{
    //getters
    public Vector<Item> getItems();
    public Vector<Attribute> getAttributes();
    public double getPercentCharged();
    public Category getParentCategory(); //do we need these, recursive?
    public Vector<Category> getChildrenCategory();
    public String getName();
    
    //setters
    public void setItems(Vector<Item> items);
    public void setAttributes(Vector<Attribute> attributes);
    public void setPercentCharged(double percent);
    public void setParentCategory(Category parent); //do we need these, recursive?
    public void setChildrenCategory(Vector<Category> children);
    public void setName(String name);

}
