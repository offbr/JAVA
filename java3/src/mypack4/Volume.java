package mypack4;

public interface Volume { //인터페이스는 부모클래스로서의 기능만 있다
	void volumeUp(int level); //public abstract void volumeUp(int level); //interface는 public
	void volumeDown(int level); 
	
	//public void aaa(){} //일반메소드는 인터페이스에서 쓸수 없다 
}
