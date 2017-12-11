
public class OfferReadEvent implements Event {
	private String type;
	private Integer clientID;
	private Integer companyID;
	private Offer offer;
	
	public OfferReadEvent(String type, Integer clientID, Integer companyId, Offer offer) {
		this.type = type;
		this.clientID = clientID;
		this.offer = offer;
		this.companyID = companyId;
	}
	
	@Override
	public String getType() {
		
		return type;
	}
	
	@Override
	public Integer getDestinationID() {
		return companyID;
	}
	
	public Offer getOffer() {
		return offer;
	}

	@Override
	public Integer getSourceID() {
		return clientID;
	}

}
