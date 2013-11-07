
package model.entity;

import java.util.*;

public interface User extends Entity{ 
   
   //getters
    public String getFirstName();
    public String getLastName();
    public String getEmail();
    public String getPassword();
    public boolean getIsAdmin();
    public Vector<Item> getBiddingItems();
    public Vector<Item> getWonItems();
    public Vector<Item> getSellingItems();
    public Vector<Item> getSoldItems();

    //setters
    public void setFirstName(String firstName);
    public void setLastName(String lastName);
    public void setEmail(String email);
    public void setPassword(String password);
    public void setIsAdmin(boolean isAdmin);
    public void setBiddingItems(Vector<Item> bidItems);
    public void setWonItems(Vector<Item> wonItems);
    public void setSellingItems(Vector<Item> sellItems);
    public void setSoldItems(Vector<Item> soldItems);


}
