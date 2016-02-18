import java.util.ArrayList;
import java.util.List;

import model.Location;
import model.Quadrants;
import model.Store;

/**
 * 
 * The aim of this class is to categorize a list of stores into buckets based on
 * lat-lon quadrants.
 * 
 * What is a quadrant? Quadrant is a geo-block comprising of a top-left lat/long
 * and a bottom-right lat/long.
 * 
 * How are quadrants calculated? We will take the top-left most lat/long of USA.
 * According to
 * http://www.worldatlas.com/webimage/countrys/namerica/usstates/uslandst.htm -
 * the vertical length of USA is 2545.982 kms and the horizontal length is
 * 4313.042 kms. We plan to create a quadrant of horizontal length = 225 kms,
 * hence a quadrant would be - (leftLatLon, rightBottomLatLon), where
 * rightBottomLatLon = horizontal distance of 225 kms of leftLatLon and vertical
 * distance of 2545.982 of the calculated lat lon.
 * 
 * 
 * @author sarnab.poddar
 * 
 */
public class QuadrantPosition {

	public static void main(String[] args) {
		List<Quadrants> computedQuads = computeQuadrants();
		List<Store> stores = new ArrayList<>();
		bucketStoresIntoQuadrants(stores, computedQuads);
	}

	private static List<Quadrants> computeQuadrants() {

		// This location is left most top corner of USA.
		double startLat = 48.985534;
		double startLon = -125.647552;
		Location startTopLeftLocation = new Location(startLat, startLon);

		// Threshold horizontal distance of a quadrant in kms.
		double thresholdHorDistance = 225;

		// Measurements of USA in kms.
		double totalHorDistance = 4313.042;
		double totalVerDistance = 2545.982;

		List<Quadrants> listQuads = new ArrayList<>();

		for (int count = 0; count <= totalHorDistance / thresholdHorDistance; count++) {
			Quadrants quadrant = new Quadrants();

			// First Calculate horizontal lat lon of the thresholdHorDistance
			// from the left lat lon
			Location newCalculatedHorLoc = getNewValue(90,thresholdHorDistance, startTopLeftLocation.getLatitude(),
					startTopLeftLocation.getLongitude());

			// Then Calculate the vertical lat lon of the newly calculated
			// horizontal lat lon with threshold
			// vertical distance.
			Location newCalculatedVerLoc = null;
			if (null != newCalculatedHorLoc) {
				newCalculatedVerLoc = getNewValue(180, totalVerDistance,newCalculatedHorLoc.getLatitude(),
						newCalculatedHorLoc.getLongitude());
			}

			if (null != newCalculatedVerLoc && null != newCalculatedHorLoc) {
				// Hence a quadrant will comprise of the top left location and
				// bottom right location.
				quadrant.setData(startTopLeftLocation, newCalculatedVerLoc);

				System.out.println("Quadrant" + count + "--- ranges from "
						+ startTopLeftLocation.toString() + " to "
						+ newCalculatedVerLoc.toString());

				startTopLeftLocation = newCalculatedHorLoc;

				listQuads.add(quadrant);
			}

		}

		return listQuads;

	}

	/**
	 * 
	 * Loop through each store, and determine which quadrant that store falls
	 * in. store.lat > quad.leftTopLocation.latitude && store.lon <
	 * quad.rightBottomLocation.latitude AND store.lon <
	 * quad.leftTopLocation.longitude && store.lon >
	 * quad.rightBottomLocation.longitude
	 * 
	 * @param stores
	 * @param computedQuads
	 */
	private static void bucketStoresIntoQuadrants(List<Store> stores,
			List<Quadrants> computedQuads) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param brng
	 *            - Bearing - 90 in case of East, 180 in case of South, 270 in
	 *            case of West and 0 in case of North
	 * @param dist
	 *            - Distance to be calculated from the origin lat lon.
	 * @param latitude
	 *            - Origin Latitude.
	 * @param longitude
	 *            - Origin longitude
	 * @return
	 */
	private static Location getNewValue(double brng, double dist,
			double latitude, double longitude) {

		// Earth radius is 6371.
		dist = dist / 6371;

		brng = Math.toRadians(brng);

		latitude = Math.toRadians(latitude);
		longitude = Math.toRadians(longitude);

		double calculatedLat = Math.asin(Math.sin(latitude) * Math.cos(dist)
				+ Math.cos(latitude) * Math.sin(dist) * Math.cos(brng));

		double calculateLon = longitude
				+ Math.atan2(
						Math.sin(brng) * Math.sin(dist) * Math.cos(latitude),
						Math.cos(dist) - Math.sin(latitude)
								* Math.sin(calculatedLat));

		if (Double.isNaN(calculatedLat) || Double.isNaN(calculateLon))
			return null;

		Location newLocation = new Location(Math.toDegrees(calculatedLat),
				Math.toDegrees(calculateLon));

		return newLocation;
	}

}
