package cart;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.CartVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class ICartDaoImpl implements ICartDao{
	private static ICartDaoImpl cartDao;
	private SqlMapClient smc;
	
	
	
	private ICartDaoImpl(){
		Reader rd;
		try {
			rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			
		} catch (IOException e) {
			System.out.println("SqlMapClient객체 생성 실패");
			e.printStackTrace();
		}
	}
	
	public static ICartDaoImpl getInstance(){
		if(cartDao == null){
			cartDao = new ICartDaoImpl();
		}
		return cartDao;
	}

	@Override
	public int insertCart(CartVO cv) {
		int cnt = 0;
		try {
			Object obj = smc.insert("cart.insertCart",cv);
			if(obj == null){
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return cnt;
	}

	@Override
	public int deleteCart(String cart_no) {
		int cnt = 0;
		try {
			cnt = smc.delete("cart.deleteCart", cart_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int updateCart(CartVO cv) {
		int cnt = 0;
		
		try {
			cnt = smc.update("cart.updateCart", cv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<CartVO> getAllCart() {
		List<CartVO> cartList = new ArrayList<CartVO>();
		
		try {
			cartList = smc.queryForList("cart.getCartAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cartList;
	}

	@Override
	public boolean getCart(String cart_no) {
		boolean result = false;
		Object obj = null;
		try {
			obj = smc.queryForObject("cart.getCart", cart_no);
			if(obj == null){
				result = false;
			}else{
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<CartVO> searchCart(CartVO cv) {
		List<CartVO> cartList = new ArrayList<CartVO>();
		try {
			cartList = smc.queryForList("cart.searchCart", cv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cartList;
		
	}
	
}
