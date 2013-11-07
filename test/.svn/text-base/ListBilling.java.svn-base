package test;

import java.util.*;
import model.entity.impl.Billing;
import persist.impl.PersistenceModuleImpl;
import persist.PersistenceModuleFactory;
import except.*;

public class ListBilling{

	public static void main(String args[]){
		
		PersistenceModule pm = new PersistenceModule();
		PersistenceModuleIml pml = pm.createPersistenceModuleImpl();
		
		Billing b = pml.restoreBilling(1); //id =1
		
		System.out.println("Address is: " + b.getAddressNumber());
		
		System.out.println("Street Name is: " + b.getStreetName());
		System.out.println("Zip Code is: " + b.getZipcode());
		System.out.println("Apartment Number is: " + b.getApartmentNumber());
		System.out.println("State is: " + b.getState());
		
		b.setAddressNumber("1234");
		b.setStreetName("Flapjack");
		b.setZipcode("30606");
		b.setApartmentNumber("3a");
		b.setState("Norway");
		
		System.out.println("Street Name is now: " + b.getStreetName());
		System.out.println("Zip Code is now: " + b.getZipcode());
		System.out.println("Apartment Number is now: " + b.getApartmentNumber());
		System.out.println("State is now: " + b.getState());
		
	}
}