package mimodek;

import processing.core.*; 
import processing.xml.*; 

public class Weather {

	private XMLElement xml;

	private static float _temperature;

	Weather() {
		update();
	}

	public float temperature() {
		return _temperature;
	}

	public boolean update() {
		try {
			xml = new XMLElement("http://rss.wunderground.com/auto/rss_full/global/stations/08221.xml?units=metric");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (xml != null) {
			XMLElement firstItem = xml.getChild(0).getChild(11).getChild(1);
			String content = firstItem.getContent();
			String[] conditionsA = processing.core.PApplet.split(content, ':');
			String[] conditionsB =  processing.core.PApplet.split(conditionsA[1], 'C');
			_temperature = PApplet.parseFloat (conditionsB[0]);
			return true;
		} else {
			return false;
		}
	}

}
