import java.util.ArrayList;
import java.util.HashMap;

public class Customer  implements Listener{
	private Integer id;
	private HashMap<Integer, ArrayList<Offer>> companies = new HashMap<>();
	private Dispatcher dispatcher;
	private OfferCustomerFilter offerFilter;
	
	public Customer(Integer id, Dispatcher dispatcher) {
		this.id = id;
		this.dispatcher = dispatcher;
		this.offerFilter = new OfferCustomerFilter(id, companies.keySet());
	}
	
	@Override
	public void onEvent(Event e) {
		System.out.print("CUSTOMER " + this.id + " GOT EVENT ");
		if (e.getType().equals(EventStrings.newOffer)) {
			OfferEvent offerEvent = (OfferEvent)e;
			companies.get(offerEvent.getSourceID()).add(offerEvent.getNewOffer());
			System.out.println("New:" + offerEvent.getNewOffer());
		}
		
		if (e.getType().equals(EventStrings.modifyOffer)) {
			OfferEvent offerEvent = (OfferEvent)e;
			companies.get(offerEvent.getSourceID()).remove(offerEvent.getOldOffer());
			companies.get(offerEvent.getSourceID()).add(offerEvent.getNewOffer());
			System.out.println("Modified from " + offerEvent.getOldOffer() + " to " + offerEvent.getNewOffer());
		}
		
		if (e.getType().equals(EventStrings.deleteOffer)) {
			OfferEvent offerEvent = (OfferEvent)e;
			companies.get(offerEvent.getSourceID()).remove(offerEvent.getOldOffer());
			System.out.println("Deleted: " + offerEvent.getOldOffer());
		}
	}
	
	public void subscribeToCompany(Integer idCompany) throws Throwable {
		synchronized(offerFilter){
			if (companies.get(idCompany) != null) {
				return;
			}
			companies.put(idCompany, new ArrayList<>());
		}
	}

	@Override
	public Integer getID() {
		return id;
	}
	
	@Override
	public void registerToEvent(String type) {
		dispatcher.registerListener(type, offerFilter, this);
	}
	public void registerToOfferEvents(){
		registerToEvent(EventStrings.newOffer);
		registerToEvent(EventStrings.modifyOffer);
		registerToEvent(EventStrings.deleteOffer);
	}
	
	public void setFilter(Double minValue, Double maxValue) {
		offerFilter.setMinValue(minValue);
		offerFilter.setMaxValue(maxValue);
	}
}
