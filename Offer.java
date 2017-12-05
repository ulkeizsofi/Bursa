
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public final class Offer {
	private double value;
	private Date date;
	private AtomicInteger id;
	
	public Offer(int id, double value, Date date) {
		this.id = new AtomicInteger(id);
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
		return id.intValue();
	}
}
