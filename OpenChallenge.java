import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

class Word {
	private String engWord;
	private String korWord;
	
	public Word() {
		this.engWord = " ";
		this.korWord = " ";
	}
	
	public Word(String engWord, String korWord) {
		this.engWord = engWord;
		this.korWord = korWord;
	}
	
	public String getEngWord() { return this.engWord; }
	public String getKorWord() { return this.korWord; }
}

public class OpenChallenge {
	private Scanner scanner = new Scanner(System.in, "EUC-KR");
	private Vector<Word> word;
	
	public OpenChallenge() {
		word = new Vector<Word>();
		word.add(new Word("human", "사람"));
		word.add(new Word("spider", "거미"));
		word.add(new Word("alone", "혼자"));
		word.add(new Word("long", "길다"));
		word.add(new Word("ant", "거미"));
		word.add(new Word("breakthrough", "돌파구"));
		word.add(new Word("love", "사랑"));
		word.add(new Word("snow", "눈"));
		word.add(new Word("spring", "봄"));
		word.add(new Word("up", "위"));
		word.add(new Word("down", "아래"));
		word.add(new Word("summber", "여름"));
		word.add(new Word("hope", "희망"));
		word.add(new Word("you", "너"));
		word.add(new Word("we", "우리"));
		word.add(new Word("book", "책"));
		word.add(new Word("pencil", "연필"));
	}
	
	private int initExample(int example[], int answerIndex) {
		int chooseAns[] = { -1, -1, -1, -1};
		int index = 0;
		
		for(int i = 0; i < chooseAns.length; i++) {
			do {
				index = (int)(Math.random() * word.size());
			} while(index == answerIndex || exists(chooseAns, index)); 
			chooseAns[i] = index;
		}
		
		for(int j = 0; j < chooseAns.length; j++)
			example[j] = chooseAns[j];
		
		return (int)(Math.random() * chooseAns.length);
	}
	
	private boolean exists(int n[], int index) {
		for(int i=0; i<n.length; i++) {
			if(n[i] == index)
				return true;
		}
		return false;
	}
	
	public void start() {
		System.out.println("**** 영어 단어 테스트 프로그램 \"" + "명품단어" + "\" 입니다. ****"); 
		while(true) {
			System.out.print("단어 테스트:1, 단어 삽입:2. 종료:3>> ");
			try {
				int menu = scanner.nextInt();
				switch(menu) {
					case 1: wordQuiz(); break;					
					case 2: insertWords(); break;
					case 3: finish(); return;
					default :
						System.out.println("잘못 입력하였습니다.");
				}
			}
			catch(InputMismatchException e) { 
				scanner.next(); 
				System.out.println("숫자를 입력하세요 !!");
			}
			System.out.println(); 
		}
	}
	
	private void insertWords() {
		System.out.println("영어 단어에 그만을 입력하면 입력을 종료합니다.");
		while(true) {
			System.out.print("영어 한글 입력 >> ");
			String engWord = scanner.next(); 
	
			if(engWord.equals("그만")) 
				break;
			
			String korWord = scanner.next(); 
			word.add(new Word(engWord, korWord));
		}		
	}

	private void finish() {
		System.out.println("\"" + "명품단어"+ "\"를 종료합니다.");
		scanner.close();
	}
		
	private void wordQuiz() {
		System.out.println("현재 " + word.size() + "개의 단어가 들어 있습니다. -1을 입력하면 테스트를 종료합니다.");
		while(true) { 
			int answerIndex = (int)(Math.random() * word.size()); 
			String eng = word.get(answerIndex).getEngWord(); 
			
			int example[] = new int [4];
			
			int answerLoc = initExample(example, answerIndex); 
			example[answerLoc] = answerIndex; 

			System.out.println(eng + "?");
			
			for(int i=0; i<example.length; i++)
				System.out.print("(" + (i+1) + ")" + word.get(example[i]).getKorWord() + " "); 
			
			System.out.print(":>"); 
			try {
				int in = scanner.nextInt(); 
				if(in == -1) 
					break; 
				
				in--; 
				if(in == answerLoc)
					System.out.println("Excellent !!");
				else
					System.out.println("No. !!");
			}
			catch(InputMismatchException e) {
				scanner.next(); 
				System.out.println("숫자를 입력하세요 !!");
				
			}
		}		
	}
	
	public static void main(String[] args) {
		OpenChallenge openChallenge = new OpenChallenge();
		openChallenge.start();

	}

}
