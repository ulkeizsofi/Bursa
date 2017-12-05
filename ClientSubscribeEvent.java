
public class ClientSubscribeEvent implements Event {
	
		private String type;
		private Company company;
		
		public ClientSubscribeEvent(String type, Company company) {
			this.type = type;
			this.company = company;
		}

		public String getType() {
			return type;
		}

		public Company getOffer() {
			return company;
		}
		
	
}
