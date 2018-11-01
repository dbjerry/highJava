package cart;

import java.util.List;

import vo.CartVO;

public class ICartServiceImpl implements ICartService{
	private static ICartServiceImpl cartService;
	private ICartDaoImpl cartDao;
	
	private ICartServiceImpl(){
		cartDao = ICartDaoImpl.getInstance();
		
	}
	
	public static ICartServiceImpl getInstance(){
		if(cartService == null){
			cartService = new ICartServiceImpl();
		}
		return cartService;
	}

	@Override
	public int insertCart(CartVO cv) {
		return cartDao.insertCart(cv);
	}

	@Override
	public int deleteCart(String cart_no) {
		return cartDao.deleteCart(cart_no);
	}

	@Override
	public int updateCart(CartVO cv) {
		return cartDao.updateCart(cv);
	}

	@Override
	public List<CartVO> getAllCart() {
		return cartDao.getAllCart();
	}

	@Override
	public boolean getCart(String cart_no) {
		return cartDao.getCart(cart_no);
	}

	@Override
	public List<CartVO> searchCart(CartVO cv) {
		return cartDao.searchCart(cv);
	}
	
	
	
	
	
	
}
