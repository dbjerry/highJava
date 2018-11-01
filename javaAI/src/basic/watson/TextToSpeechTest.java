package basic.watson;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.watson.developer_cloud.http.ServiceCall;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.AudioFormat;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;

/*
 * IBM Text to Speech 서비스는 IBM의 음성 합성 기능을 사용하여
 * 다양한 언어, 방언 및 음성으로 텍스트를 자연스러운 발음으로
 * 합성하는 API를 제공한다.
 * 
 * 이 서빗느느 각 언어에 대해 남성, 여성 또는 둘 다를 지원한다.
 */
public class TextToSpeechTest {
	String USER_NAME = "f2ca0e6b-75f2-4b1c-8b42-b2cafe7e8f7d";
	String PASSWORD = "XSELj8h7gPIY";
	
	// TextToSpeech 서비스
	private TextToSpeech service;
	
	// 서비스를 설정하는 메서드
	public void setService() {
		service = new TextToSpeech();
		service.setUsernameAndPassword(USER_NAME, PASSWORD);
	}
	
	// service header 설정
	public void setHeader() {
		Map<String, String> headers = new HashMap<>();
		
		// true는 허용, false는 불허
		headers.put("X-Watson-Learning-Opt-Out", "false");
		
		service.setDefaultHeaders(headers);
	}
	
	// 음성 타입을 검색하는 메서드
	public void getVoice() {
		ServiceCall<List<Voice>> serviceCall = service.getVoices();
		
		List<Voice> voiceList = serviceCall.execute();
		
		// Watson에서 제공하는 음성 타입들 출력
		for(Voice voice : voiceList) {
			System.out.println(voice);
		}
	}
	
	// 서비스 실행
	public void executeService() {
		String text = "What do you do for freetime?";
		
		InputStream stream = service.synthesize(
								text, 
								Voice.EN_LISA,
								AudioFormat.WAV).execute();
		
		// 음성 데이터 재생 또는 저장
		try {
			InputStream in = WaveUtils.reWriteWaveHeader(stream);
			OutputStream os = new FileOutputStream("C:/1_ddit/A_TeachingMaterial/3.HignJava/other/javaAI/Data/test.wav");
			
			byte[] tmp = new byte[1024];
			int length = 0;
			while((length = in.read(tmp)) != -1) {
				os.write(tmp, 0, length);
			}
			
			os.close();
			in.close();
			stream.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		TextToSpeechTest test = new TextToSpeechTest();
		
		test.setService();
		test.setHeader();
		test.getVoice();
		test.executeService();
	}
	
}

