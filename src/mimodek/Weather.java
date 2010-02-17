import processing.core.*; 
import processing.xml.*; 

import java.applet.*; 
import java.awt.*; 
import java.awt.image.*; 
import java.awt.event.*; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

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
			xml = new XMLElement(this, "http://rss.wunderground.com/auto/rss_full/global/stations/08221.xml?units=metric");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (xml != null) {
			int numKids = xml.getChildCount();
			XMLElement firstItem = xml.getChild(0).getChild(11).getChild(1);
			String name = firstItem.getName();
			String content = firstItem.getContent();
			String[] conditionsA = split(content, ':');
			String[] conditionsB = split(conditionsA[1], 'C');
			_temperature = PApplet.parseFloat (conditionsB[0]);
			return true;
		} else {
			return false;
		}
	}

}
