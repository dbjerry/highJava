/**
 * Sample Skeleton for 'ThreadTimer.fxml' Controller Class
 */

package basic.threadTimer;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ThreadTimerController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="label_timer"
    private Label label_timer; // Value injected by FXMLLoader
    
    @FXML // fx:id="btn_start"
    private Button btn_start; // Value injected by FXMLLoader

    @FXML // fx:id="btn_cancel"
    private Button btn_cancel; // Value injected by FXMLLoader

    private boolean stop;
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	assert label_timer != null : "fx:id=\"label_timer\" was not injected: check your FXML file 'ThreadTimer.fxml'.";
        assert btn_start != null : "fx:id=\"btn_start\" was not injected: check your FXML file 'ThreadTimer.fxml'.";
        assert btn_cancel != null : "fx:id=\"btn_cancel\" was not injected: check your FXML file 'ThreadTimer.fxml'.";

        btn_start.setOnAction(e -> {
        	stop = false;
        	
        	Thread thread = new Thread() {
        		//  익명구현체 만들듯이 만들면 된다.
        		@Override
        		public void run() {
					// TODO Auto-generated method stub
        			SimpleDateFormat sdf = 
        					new SimpleDateFormat("hh:mm:ss");
        			
        			while(!stop) {
        				String strTime = sdf.format(new Date());
        				
        				/*
        				 * 일반 쓰레드에서 JavaFx의 컨트롤 값을 변경시키면
        				 * JavaFx에서 컨트롤들을 처리하는 쓰레드와 충돌을 일으키는데
        				 * 이 충돌을 방지하기 위해서 Platform.runLater()를
        				 * 사용하여 컨트롤의 값을 변경해야 한다.
        				 */
        				
        				Platform.runLater(() -> {
        					label_timer.setText(strTime);
        				});
        				
        				try {
        					Thread.sleep(100);
        				}catch(InterruptedException ie) {
        					ie.printStackTrace();
        				}
        			}
				}
        	};
        	
        	thread.setDaemon(true);
        	thread.start();
        });
        
        
        btn_cancel.setOnAction(e -> {
        	stop = true;
        });
        
    }

}

