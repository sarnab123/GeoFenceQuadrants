package model;

public class Quadrants {
	
	Location leftTopLocation;
	Location rightBottomLocation;
	
	public void setData(Location leftTopLocation,Location rightBottomLocation)
	{
		this.leftTopLocation = leftTopLocation;
		this.rightBottomLocation = rightBottomLocation;
	}
	
	public Location getLeftTopLocation()
	{
		return this.leftTopLocation;
	}
	
	public Location rightBottomLocation()
	{
		return this.rightBottomLocation;
	}

}
