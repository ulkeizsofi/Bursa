import java.util.Date;

public class SetFilterEvent implements Event {
	private String type;
	private double minValue;
	private double maxValue;
	private Date minDate;
	private Date maxDate;
	
	public SetFilterEvent(String type, double minValue, double maxValue, Date minDate, Date maxDate) {
		this.type = type;
		this.minValue = minValue;
		this.maxValue =maxValue;
		this.minDate = (Date) minDate.clone();
		this.maxDate = (Date) maxDate.clone();
	}
	
	public String getType() {
		return type;
	}
	 
	public double getMinValue() {
		return minValue;
	}
	
	public double getMaxValue() {
		return maxValue;
	}
	
	public Date getMinDate() {
		return minDate;
	}
	
	public Date getMaxDate() {
		return maxDate;
	}
}
