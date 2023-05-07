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
		word.add(new Word("human", "���"));
		word.add(new Word("spider", "�Ź�"));
		word.add(new Word("alone", "ȥ��"));
		word.add(new Word("long", "���"));
		word.add(new Word("ant", "�Ź�"));
		word.add(new Word("breakthrough", "���ı�"));
		word.add(new Word("love", "���"));
		word.add(new Word("snow", "��"));
		word.add(new Word("spring", "��"));
		word.add(new Word("up", "��"));
		word.add(new Word("down", "�Ʒ�"));
		word.add(new Word("summber", "����"));
		word.add(new Word("hope", "���"));
		word.add(new Word("you", "��"));
		word.add(new Word("we", "�츮"));
		word.add(new Word("book", "å"));
		word.add(new Word("pencil", "����"));
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
		System.out.println("**** ���� �ܾ� �׽�Ʈ ���α׷� \"" + "��ǰ�ܾ�" + "\" �Դϴ�. ****"); 
		while(true) {
			System.out.print("�ܾ� �׽�Ʈ:1, �ܾ� ����:2. ����:3>> ");
			try {
				int menu = scanner.nextInt();
				switch(menu) {
					case 1: wordQuiz(); break;					
					case 2: insertWords(); break;
					case 3: finish(); return;
					default :
						System.out.println("�߸� �Է��Ͽ����ϴ�.");
				}
			}
			catch(InputMismatchException e) { 
				scanner.next(); 
				System.out.println("���ڸ� �Է��ϼ��� !!");
			}
			System.out.println(); 
		}
	}
	
	private void insertWords() {
		System.out.println("���� �ܾ �׸��� �Է��ϸ� �Է��� �����մϴ�.");
		while(true) {
			System.out.print("���� �ѱ� �Է� >> ");
			String engWord = scanner.next(); 
	
			if(engWord.equals("�׸�")) 
				break;
			
			String korWord = scanner.next(); 
			word.add(new Word(engWord, korWord));
		}		
	}

	private void finish() {
		System.out.println("\"" + "��ǰ�ܾ�"+ "\"�� �����մϴ�.");
		scanner.close();
	}
		
	private void wordQuiz() {
		System.out.println("���� " + word.size() + "���� �ܾ ��� �ֽ��ϴ�. -1�� �Է��ϸ� �׽�Ʈ�� �����մϴ�.");
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
				System.out.println("���ڸ� �Է��ϼ��� !!");
				
			}
		}		
	}
	
	public static void main(String[] args) {
		OpenChallenge openChallenge = new OpenChallenge();
		openChallenge.start();

	}

}
