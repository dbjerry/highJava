package basic.watson;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.watson.developer_cloud.http.ServiceCall;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechModel;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.Transcript;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.BaseRecognizeCallback;

/*
 * IBM Watson의 Speech to Text 서비스는 IBM의 음성 인식 기능을
 * 응용 프로그램에 추가할 수 있는 API를 제공한다.
 * 
 * 이 서비스는 다양한 언어 및 오디오 형식의 음성을 빠르게 텍스트로
 * 변환한다.
 * 
 * 모든 응답 내용은 UTF-8인코딩의 JSON형식으로 반환한다.
 */
public class SpeechToTextTest {
	// Watson으로부터 부여받은 사용자 계정과 패스워드
	String USER_NAME = "d86e14ee-31b3-4478-bce7-32b242ca4ca5";
	String PASSWORD = "TmbVrDWaF7IR";
	
	// SpeechToText서비스 변수 선언
	private SpeechToText service;
	
	// 서비스 옵션
	private RecognizeOptions options;
	
	// 서비스 콜백
	private BaseRecognizeCallback callBack;
	
	// 서비스를 설정하는 메서드
	//	-->	IBM Watson에 등록한 사용자 계정과 패스워드로
	//		서비스에 접속한다.
	public void setService() {
		service = new SpeechToText();
		service.setUsernameAndPassword(USER_NAME, PASSWORD);
		
	}
	
	/*
	 * Service  Header 설정
	 * 	Watson은 기본적으로 서비스 사용에 로그를 남겨서
	 * 	서비스를 개선하는데 사용하고 있다.
	 * 	만약 Watson에서 서비스의 내용을 바꾸길 원하지 않는다면
	 * 	Header에 내용을 명시해주어야 한다. 
	 */
	public void setHeader() {
		Map<String, String> headers = new HashMap<>();
		
		// true는 허용, false는 불허
		headers.put("X-Watson-Learning-Opt-Out", "false");
		
		service.setDefaultHeaders(headers);
	}
	
	/*
	 * 서비스 모델을 검색하는 메서드 
	 * 	서비스에서 사용할 수 있는 언어 모델을 검색한다.
	 */
	public void getModel() {
		// 서비스 요청 인터페이스 ServiceCall 객체 구하기
		ServiceCall<List<SpeechModel>> serviceCall = service.getModels();
		
		// 서비스 요청을 실행해서 모델 리스트 얻기
		// 서비스콜을 이용
		List<SpeechModel> speechModelList = serviceCall.execute();
		
		// Watson에서 제공하는 모든 언어 모델
		for(SpeechModel model : speechModelList) {
			System.out.println(model);
		}
		
		System.out.println("─────────────────────────────");
		
		// 선택한 언어에서 해당하는 모델을 검색(내가 사용할 것)
		ServiceCall<SpeechModel> serviceCall_eng
				= service.getModel("en-US_NarrowbandModel");
		
		SpeechModel engModel = serviceCall_eng.execute();
		System.out.println(engModel);
	}

	// 서비스 옵션 설정
	public void setOption() {
		/*
		 * contentType - 컨텐츠 타입
		 * 		audio / basic
		 * 		audio / flac
		 * 		audio / mp3
		 * 		audio / mpeg
		 * 		audio / mulaw
		 * 		audio / ogg
		 * 		audio / wav
		 * 		audio / webm
		 * 
		 * interimResults	-->	중간 결과를 반환할지 여부
		 * 						true인 경우 임시 결과는 JSON형식의 SpeechRecognitionResults
		 * 						객체의 스트림으로 반환된다.
		 * 						- false(기본값)인 경우 최종 결과만 있는 단일
		 * 						SpeechRecognitionResults 객체가 반환된다.
		 * 
		 * maxAlternatives	-->	반환될 최대 개수 (기본값은 1개)
		 * 
		 * keywords	-->	오디오에서 발견할 수 있는 키워드 목록
		 * 				키워드는 최종 결과에서만 발견되며 최대 1000개의 키워드를 발견할 수 있다.
		 * 				키워드를 알아볼 필요가 없으면 매개변수를 생략하거나 빈 배열을 지정한다.
		 * 
		 * keywordsThreshold	-->	키워드 검색을 위한 신뢰값
		 * 							신뢰도가 임계값보다 크거나 같으면 단어가 키워드와
		 * 							일치하는 것으로 판단
		 * 							0 ~ 1 사이의 값으로 확률을 지정한다.
		 * 
		 */
		options = new RecognizeOptions.Builder()
				.model("en-US_NarrowbandModel")
				.contentType("audio/mp3")
				.interimResults(true)
				.maxAlternatives(3)
//				.keywords(new String[] {})
//				.keywordsThreshold(0.5)
				.build();
	}
	
	/*
	 * 서비스 실행 후 처리할 콜백 지정
	 * 		--> 서비스 실행 후에 처리할 내용을 지정해준다.
	 */
	public void setCallback() {
		callBack = new BaseRecognizeCallback() {
			
			// 문자 변환시 처리할 내용 지정
			@Override
			public void onTranscription(SpeechResults speechResults) {
				for(Transcript transcript : speechResults.getResults()) {
					String text = transcript.getAlternatives()
								.get(0).getTranscript();
					
					System.out.println(text);
				}
			}
			
			// 연결 종료시 처리할 내용 지정
			@Override
			public void onDisconnected() {
				System.exit(0);
			}
		};
	}
	
	/*
	 * 서비스 실행하는 메서드
	 * 		--> 설정해놓은 옵션과 서비스 실행 후 처리할 콜백을
	 * 			지정하여 서비스를 실행한다.
	 */
	public void executeService() {
		try {
			FileInputStream fis = new FileInputStream(
					getClass().getResource("speech.mp3").getPath()
				);
			
			service.recognizeUsingWebSocket(fis, options, callBack);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SpeechToTextTest test = new SpeechToTextTest();
		
		test.setService();
		test.setHeader();
		test.getModel();
		test.setOption();
		test.setCallback();
		test.executeService();
	}
}

