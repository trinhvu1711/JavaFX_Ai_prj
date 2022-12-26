package application;

import java.io.IOException;
import java.util.ArrayList;


import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class VoiceModel {
	private String voiceText;
	private StringBuffer log;
	Configuration config = new Configuration();
	LiveSpeechRecognizer rec;
	ArrayList<String> chatLog;

	public VoiceModel() {
		chatLog = new ArrayList<>();
		log = new StringBuffer();
		config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		config.setDictionaryPath("src\\1913.dic");
		config.setLanguageModelPath("src\\1913.lm");
		try {
			rec = new LiveSpeechRecognizer(config);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public VoiceModel(Configuration config) throws IOException {
		super();
		this.config = config;
		rec = new LiveSpeechRecognizer(config);
	}

	public Configuration getConfig() {
		return config;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}

	public LiveSpeechRecognizer getRec() {
		return rec;
	}

	public void setRec(LiveSpeechRecognizer rec) {
		this.rec = rec;
	}

	public ArrayList<String> getChatLog() {
		return chatLog;
	}

	public void setChatLog(ArrayList<String> chatLog) {
		this.chatLog = chatLog;
	}

	public String getVoiceText() {
		return voiceText;
	}

	public void setVoiceText(String voiceText) {
		this.voiceText = voiceText;
	}

	public StringBuffer getLog() {
		return log;
	}

	public void setLog(StringBuffer log) {
		this.log = log;
	}

	public String getVoice() {
		String result = null;
		while (true) {
			rec.startRecognition(true);
			SpeechResult speechResult = rec.getResult();
			if ((result= speechResult.getHypothesis()) != null) {
				result = speechResult.getHypothesis();
				setVoiceText(result);
				rec.stopRecognition();
				System.out.println("result"+result);
				break;
			}
		}
	
		return result;
	}

	
	public static void main(String[] args) {
//		System.out.println(new VoiceModel().getVoice());
		new VoiceModel().getVoice();
	}
}
