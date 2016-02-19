package model;

import java.util.List;

public class Quadrants {
	
	Location leftTopLocation;
	Location rightBottomLocation;
	
	List<Store> listOfStores;
	
	public void setData(Location leftTopLocation,Location rightBottomLocation)
	{
		this.leftTopLocation = leftTopLocation;
		this.rightBottomLocation = rightBottomLocation;
	}
	
	public Location getLeftTopLocation()
	{
		return this.leftTopLocation;
	}
	
	public void setStores(List<Store> stores)
	{
		this.listOfStores = stores;
	}
	
	public List<Store> getStores()
	{
		return this.listOfStores;
	}
	
	public Location getRightBottomLocation()
	{
		return this.rightBottomLocation;
	}

}
