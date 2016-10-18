package exs;

abstract public class PolyAnimal {
	protected  int move = 10;
	
	public PolyAnimal() {
		System.out.println("동물 집합!");
	}
	
	public int getMove() {
		return move;
	}
	
	abstract public void disp();
	
}
