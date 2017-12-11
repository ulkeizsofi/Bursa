
public class OfferEvent implements Event {
	private String type;
	private Offer oldOffer;
	private Offer newOffer;
	private Integer companyID;
	
	public OfferEvent(String type, Offer oldOffer, Offer newOffer, Integer companyID) {
		this.type = type;
		this.oldOffer = oldOffer;
		this.newOffer = newOffer;
		this.companyID = companyID;
	}

	public String getType() {
		return type;
	}

	public Offer getOldOffer() {
		return oldOffer;
	}
	

	public Offer getNewOffer() {
		return newOffer;
	}

	@Override
	public Integer getSourceID() {
		return companyID;
	}

	@Override
	public Integer getDestinationID() {
		return null;
	}
}
