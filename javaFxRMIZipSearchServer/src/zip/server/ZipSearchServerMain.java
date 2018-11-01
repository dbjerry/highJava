package zip.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import sun.font.CreatedFontTracker;
import zip.service.ZipSearchService;
import zip.service.ZipSearchServiceInf;

public class ZipSearchServerMain {
	public static void main(String[] args) {
		try {
			ZipSearchServiceInf zipService = new ZipSearchService();
			Registry reg = LocateRegistry.createRegistry(9988);
			reg.rebind("zipSearch", zipService);
			
			System.out.println("서버 준비 완료");
			
		} catch (RemoteException e) {
			// TODO: handle exception
		}
	}
}
