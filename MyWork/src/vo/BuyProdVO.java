package vo;



public class BuyProdVO {
	private String buy_date;	//복합키
	private String buy_cost; 	
	private String buy_qty; 
	private String buy_prod;	//복합키, 외래키
	
	
	
	public String getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(String buy_date) {
		this.buy_date = buy_date;
	}
	public String getBuy_cost() {
		return buy_cost;
	}
	public void setBuy_cost(String buy_cost) {
		this.buy_cost = buy_cost;
	}
	public String getBuy_qty() {
		return buy_qty;
	}
	public void setBuy_qty(String buy_qty) {
		this.buy_qty = buy_qty;
	}
	public String getBuy_prod() {
		return buy_prod;
	}
	public void setBuy_prod(String buy_prod) {
		this.buy_prod = buy_prod;
	}
	
	
	
}
