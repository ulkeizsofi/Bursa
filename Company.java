import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Company implements Listener{
	private Integer id;
	private ConcurrentMap<Integer, Offer> offers = new ConcurrentHashMap<Integer, Offer>();
	private Dispatcher dispatcher;
	private OfferCompanyFilter offerFilter;
	
	
	public Company(Integer id, Dispatcher dispatcher) {
		this.id = id;
		this.dispatcher = dispatcher;
		this.offerFilter = new OfferCompanyFilter(id);
	}
	
	@Override
	public Integer getID() {
		return id;
	}

	public void addOffer(Offer offer) throws Throwable {
		offers.put(offer.getID(), offer);
		dispatcher.sendEvent(new OfferEvent(EventStrings.newOffer, null, offer, this.id));
	}
	
	public void createOffer(int id, double value, Date date) throws Throwable {
		Offer offer = new Offer(id, value, date);
		addOffer(offer);
	}
	
	public void modifyOffer(int id, double value, Date date) throws Throwable {
			if (offers.containsKey(id)) {
				Offer oldOffer = offers.get(id);
				Offer newOffer = new Offer(id, value, date);
				offers.put(id, newOffer);
				offers.remove(id);
				dispatcher.sendEvent(new OfferEvent(EventStrings.modifyOffer, oldOffer, newOffer, this.id));
			
		}
	}
	
	public void removeOffer(int id) throws InterruptedException {
		Offer offerToDelete = offers.get(id);
		offers.remove(id);
		dispatcher.sendEvent(new OfferEvent(EventStrings.deleteOffer, offerToDelete, null, this.id));
	}

	@Override
	public void onEvent(Event e) throws Throwable {
		System.out.print("COMPANY " + this.id + " GOT EVENT ");
		if (e.getType().equals(EventStrings.offerRead)) {
			OfferReadEvent offerReadEvent = (OfferReadEvent)e;
			System.out.println("Customer read " + offerReadEvent.getOffer() + " by " + offerReadEvent.getSourceID());
		}
	}
	
	@Override
	public void registerToEvent(String type) {
		dispatcher.registerListener(type, offerFilter, this);
	}
	

	public void setFilter(Double minValue, Double maxValue) {
		offerFilter.setMinValue(minValue);
		offerFilter.setMaxValue(maxValue);
	}
}
