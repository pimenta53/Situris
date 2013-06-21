package hirondelle.situris.pub.pontosReferencias;

import hirondelle.web4j.model.Id;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.security.SafeText;


public class CoordGPS {

	private SafeText latitude;
	private SafeText longitude;
	private SafeText altitude;
	private Id fIdCoord;
	
	public CoordGPS(SafeText la,SafeText lg,SafeText alt) throws ModelCtorException {latitude=la;longitude=lg;altitude=alt;}
	
	public CoordGPS(Id aIdCoord, SafeText la,SafeText lg,SafeText alt) throws ModelCtorException {
	  fIdCoord = aIdCoord;
	  latitude=la;
	  longitude=lg;
	  altitude=alt;
	}
	
	public SafeText getLatitude() {return latitude;}
	public SafeText getLongitude() {return longitude;}
	public SafeText getAltitude() {return altitude;}
	public Id getIdCoord(){return fIdCoord;}

}