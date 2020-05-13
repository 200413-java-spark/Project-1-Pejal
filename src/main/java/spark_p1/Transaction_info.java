package spark_p1;

public class Transaction_info {
	private String transaction_date;
	private String product;
	private String price; 
	private String payment_type;
	private String name;
	private String city;
	private String state;
	private String country;
	private String account_create;
	private String last_login;
	private String latitude;
	private String longitude;
	
	//Getter
	public String getTranDate(){return this.transaction_date;}
	public String getProduct(){return this.product;}
	public String getPrice(){return this.price;}
	public String getPayType(){return this.payment_type;}
	public String getName() {return this.name;}
	public String getCity() {return this.city;}
	public String getState() {return this.state;}
	public String getCountry() {return this.country;}
	public String getCreate() {return this.account_create;}
	public String getLogin() {return this.last_login;}
	public String getLatitude() {return this.latitude;}
	public String getLongitude() {return this.longitude;}
	//Setter
	public void setTranDate(String a){ this.transaction_date=a;}
	public void setProduct(String a){ this.product=a;}
	public void setPrice(String a){ this.price=a;}
	public void setPayType(String a){this.payment_type=a;}
	public void setName(String a) {this.name=a;}
	public void setCity(String a) {this.city=a;}
	public void setState(String a) {this.state=a;}
	public void setCountry(String a) { this.country=a;}
	public void setCreate(String a) {this.account_create=a;}
	public void setLogin(String a) {this.last_login=a;}
	public void setLatitude(String a) {this.latitude=a;}
	public void setLongitude(String a) {this.longitude=a;}
	@Override
	public String toString()
	{
		return this.transaction_date+" "+
				this.product+" "+
				this.price+" "+
				this.payment_type+" "+
				this.name+" "+
				this.city+" "+
				this.state+" "+
				this.country+" "+
				this.account_create+" "+
				this.last_login+" "+
				this.latitude+" "+
				this.longitude;
	}

}
