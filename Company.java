import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Company {
	private ConcurrentMap<Integer, Offer> offers = new ConcurrentHashMap<Integer, Offer>();
	
	
	//TODO: Do not return it
	public ConcurrentMap<Integer, Offer> getOffers() {
		return offers;
	}

	public void addOffer(Offer offer) throws InterruptedException {
		offers.put(offer.getID(), offer);
		
	}
	
	public void createOffer(int id, double value, Date date) throws InterruptedException {
		Offer offer = new Offer(id, value, date);
		addOffer(offer);
	}
	
	public void modifyOffer(int id, double value, Date date) throws InterruptedException {
		//TODO: avem nevoie?
		synchronized(offers) {
			if (offers.containsKey(id)) {
				offers.remove(id);
				createOffer(id, value, date);
			}
		}
	}
}
