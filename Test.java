package recite.copy;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String infile = "G:\\study\\javaworks\\recite\\text\\chaoslist.csv";
		String outfile = "G:\\study\\javaworks\\recite\\text\\output.csv";
		WordList wl = new WordList(infile, outfile);
		
		testRead(wl);
		System.out.println("Testing shuffle");
		testRead(wl);
		
		System.out.println("Testing set forget");
		Word w = wl.next();
		w.forget();
		System.out.println(w.getFail());
	}
	
	static void testRead(WordList wl){
		System.out.println("Testing read word");
		int length = wl.wordlist.size();
		for (int i = 0; i < length; i++){
			Word w = wl.next();
			System.out.println(w.word+" ");
		}
	}

}
