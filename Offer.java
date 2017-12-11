
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public final class Offer {
	private double value;
	private Date date;
	private Integer id;
	
	public Offer(int id, double value, Date date) {
		this.id = id;
		this.value = value;
		this.date = (Date) date.clone();
	}
	
	public double getValue() {
		return value;
	}
	
	public Date getDate() {
		return (Date)date.clone();
	}
	
	public int getID() {
		return id;
	}
	public String toString() {
		return "Offer " + id + " with value " + value + "at" + date;
	}
}
