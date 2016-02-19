package model;

public class Store {
	
	private String name;
	
	private Location storeLocation;
	
	public void setData(String name,Location storeLocation)
	{
		this.name = name;
		this.storeLocation = storeLocation;
	}
	
	public Location getLocation()
	{
		return this.storeLocation;
	}

}
