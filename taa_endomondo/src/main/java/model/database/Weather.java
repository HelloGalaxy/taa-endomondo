package model.database;

import javax.persistence.*;

@Entity
public class Weather {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	// TODO: REF http://openweathermap.org/api get by json

	private String city;
	private int temperature;
	private int humidity;
	private float pressure;

	private float speedOfWind;
	private String directionOfWind;
	private String stateOfWtr; // from weather.value

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public float getPressure() {
		return pressure;
	}

	public void setPressure(float pressure) {
		this.pressure = pressure;
	}

	public float getSpeedOfWind() {
		return speedOfWind;
	}

	public void setSpeedOfWind(float speedOfWind) {
		this.speedOfWind = speedOfWind;
	}

	public String getDirectionOfWind() {
		return directionOfWind;
	}

	public void setDirectionOfWind(String directionOfWind) {
		this.directionOfWind = directionOfWind;
	}

	public String getStateOfWtr() {
		return stateOfWtr;
	}

	public void setStateOfWtr(String stateOfWtr) {
		this.stateOfWtr = stateOfWtr;
	}

	/*
	 * <current><city id="1799869" name="Nanning"><coord lon="108.316673"
	 * lat="22.816669"/><country>CN</country><sun rise="2013-10-08T22:41:11"
	 * set="2013-10-09T10:26:36"/></city><temperature value="25.822"
	 * min="25.822" max="25.822" unit="celsius"/><humidity value="64"
	 * unit="%"/><pressure value="1010.58" unit="hPa"/><wind><speed value="2.91"
	 * name="Light breeze"/><direction value="86.0022" code="E"
	 * name="East"/></wind><clouds value="80"
	 * name="broken clouds"/><precipitation mode="no"/><weather number="803"
	 * value="broken clouds" icon="04n"/><lastupdate
	 * value="2013-10-09T15:11:07"/></current>
	 */

}
