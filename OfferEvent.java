
public class OfferEvent implements Event {
	private String type;
	private Offer offer;
	
	public OfferEvent(String type, Offer offer) {
		this.type = type;
		this.offer = offer;
	}

	public String getType() {
		return type;
	}

	public Offer getOffer() {
		return offer;
	}
	
}
