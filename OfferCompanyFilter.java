import java.util.Date;
import java.util.Set;


public class OfferCompanyFilter implements Filter{
	
		
		private Integer ID = null;
		private Double minValue = null;
		private Double maxValue = null;
		private Date minDate = null;
		private Date maxDate = null;
		
		public OfferCompanyFilter(Integer ID) {
			super();
			this.ID = ID;	
		}

		public OfferCompanyFilter(Integer ID,Double minValue, Double maxValue, Date minDate, Date maxDate) {
			super();
			this.ID = ID;
			this.minValue = minValue;
			this.maxValue = maxValue;
			this.minDate = minDate;
			this.maxDate = maxDate;
		}

		public OfferCompanyFilter(Integer ID, Double minValue, Double maxValue) {
			super();
			this.ID = ID;
			this.minValue = minValue;
			this.maxValue = maxValue;
		}

		public OfferCompanyFilter(Integer ID, Date minDate, Date maxDate) {
			super();
			this.ID = ID;
			this.minDate = minDate;
			this.maxDate = maxDate;
		}
		
		@Override
		public synchronized  Boolean accept(Event e) throws Throwable {
			Offer offer;
			
			if (e.getDestinationID() != ID) {
				return false;
			}
			
			try {
				if (!e.getType().equals(EventStrings.offerRead)) {
					return false;
				}
				offer = ((OfferReadEvent)e).getOffer();
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
