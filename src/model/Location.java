package model;

public class Location {
	
		private double latitude;
		private double longtitude;
		
		public Location(double lat,double lon)
		{
			this.latitude = lat;
			this.longtitude = lon;
		}
		
		public double getLatitude()
		{
			return this.latitude;
		}
		
		public double getLongitude()
		{
			return this.longtitude;
		}
		
		@Override
		public String toString() {
		return "Latitude== "+getLatitude()+" Longitude== "+getLongitude() ;
		}
}
