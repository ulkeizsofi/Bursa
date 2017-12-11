import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public final class OfferCustomerFilter implements Filter {
	
	private Integer ID = null;
	private Double minValue = null;
	private Double maxValue = null;
	private Date minDate = null;
	private Date maxDate = null;
	

	private Set<Integer> companies;
	
	public OfferCustomerFilter(Integer ID, Set<Integer> companies) {
		super();
		this.ID = ID;
		if (companies == null) {
			this.companies = new HashSet<>();
		}
		else {
			this.companies = companies;
		}	}

	public OfferCustomerFilter(Integer ID, Set<Integer> companies ,Double minValue, Double maxValue, Date minDate, Date maxDate) {
		super();
		this.ID = ID;
		if (companies == null) {
			this.companies = new HashSet<>();
		}
		else {
			this.companies = companies;
		}		this.minValue = minValue;
		this.maxValue = maxValue;
		this.minDate = minDate;
		this.maxDate = maxDate;
	}

	public OfferCustomerFilter(Integer ID, Set<Integer> companies ,Double minValue, Double maxValue) {
		super();
		this.ID = ID;
		if (companies == null) {
			this.companies = new HashSet<>();
		}
		else {
			this.companies = companies;
		}
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	public OfferCustomerFilter(Integer ID, Set<Integer> companies, Date minDate, Date maxDate) {
		super();
		this.ID = ID;
		if (companies == null) {
			this.companies = new HashSet<>();
		}
		else {
			this.companies = companies;
		}
		this.minDate = minDate;
		this.maxDate = maxDate;
	}

	@Override
	public synchronized Boolean accept(Event e) throws Throwable {
		Offer offer;
		
		if (e.getSourceID() == null) {
			return false;
		}
		
		if (!companies.contains(e.getSourceID())){
			return false;
		}
		
		try {
			if (e.getType().equals(EventStrings.newOffer) || e.getType().equals(EventStrings.modifyOffer)) {
				offer  = ((OfferEvent)e).getNewOffer();
			}
			else if(e.getType().equals(EventStrings.deleteOffer)) {
				offer  = ((OfferEvent)e).getOldOffer();
			}
			else {
				return false;
			}
			if (minValue != null && offer.getValue() < minValue) {
				return false;				
			}
			if (maxValue != null && offer.getValue() > maxValue) {
				return false;
			}
			if (minDate != null && offer.getDate().before(minDate)) {
				return false;
			}
			if (maxDate != null && offer.getDate().after(maxDate)) {
				return false;
			}
			return true;
		}
		catch (Exception except) {
			return false;
		}
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
	
	public synchronized void setMinValue(Double minValue) {
		this.minValue = minValue;
	}
	
	public synchronized void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}
	
	public synchronized void setMinDate(Date minDate) {
		this.minDate = minDate;
	}
	
	public synchronized void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}
	
}
