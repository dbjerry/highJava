package vo;

/**
 * 물품 입고 정보를 관리하기 위한 VO
 * @author 김윤주
 * @since 2018.06.27
 * @version 1.0
 */
public class BuyProdVO {
	// PK : buy_date, buy_prod
	// FK : buy_prod → prod_id(PROD)
	private String buy_date; // 입고일
	private String buy_prod; // 물품 ID
	private String buy_qty; // 입고 물량
	private String buy_cost; // 입고 단가
	
	public String getBuy_date() {
		return buy_date;
	}
	
	public void setBuy_date(String buy_date) {
		this.buy_date = buy_date;
	}
	
	public String getBuy_prod() {
		return buy_prod;
	}
	
	public void setBuy_prod(String buy_prod) {
		this.buy_prod = buy_prod;
	}
	
	public String getBuy_qty() {
		return buy_qty;
	}
	
	public void setBuy_qty(String buy_qty) {
		this.buy_qty = buy_qty;
	}
	
	public String getBuy_cost() {
		return buy_cost;
	}
	
	public void setBuy_cost(String buy_cost) {
		this.buy_cost = buy_cost;
	}
}
