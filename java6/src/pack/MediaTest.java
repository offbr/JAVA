package pack;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class MediaTest {
	
	public void aa(){
		File bgm;
		AudioInputStream stream;
		//AudioInputStream : 오디오 형식, 길이가 지정된 입력 스트림, 바이트 단위 처리
		AudioFormat format;
		//AudioFormat : 사운드 스트림 내에서 데이터의 특정 배열을 지정
		DataLine.Info info;
		//DataLine : 미디어 관련 기능 제공
		Clip clip;
		//Clip : 사운드 재생 전에 로드되는 특수한 종료의 DataLine을 표현
		
		bgm = new File("c://work/sori/gun.wav");
		
		try {
			stream = AudioSystem.getAudioInputStream(bgm);
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip)AudioSystem.getLine(info);
			clip.open(stream);
			clip.start();
		} catch (Exception e) {
			System.out.println("aa err: " + e);
		}
	}
	
	public static void main(String[] args) {
		MediaTest test = new MediaTest();
		while(true){
			try {
				test.aa();
				Thread.sleep(3000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
