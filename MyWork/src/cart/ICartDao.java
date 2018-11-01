package cart;

import java.util.List;

import vo.CartVO;

public interface ICartDao {
	public int insertCart(CartVO cv);
	
	public int deleteCart(String cart_no);
	
	public int updateCart(CartVO cv);
	
	public List<CartVO> getAllCart();
	
	public boolean getCart(String cart_no);
	
	public List<CartVO> searchCart(CartVO cv);
	
}
