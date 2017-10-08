package recite.copy;

import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class WordList {
	private static final int THRESHOLD = 5;
	String infile;
	String outfile;
	List<Word> wordlist;
	int index;
	int remain;
	private boolean mark=false;
	boolean finalmark=false;
//	private static int MAX_WORDLIST = 3000;
	
	public WordList(String infile, String outfile){
		this.infile = infile;
		this.outfile = outfile;
		this.wordlist = this.read();
		this.remain = this.wordlist.size();
		this.shuffle();
	}
	
	private List<Word> read(){
		try {
			File file = new File(this.infile);
			InputStreamReader reader;
			reader = new InputStreamReader(new FileInputStream(file));
			BufferedReader br = new BufferedReader(reader);
			
			List<Word> wordlist = new ArrayList<Word>();
			String line = br.readLine();
			while(line != null){
				String[] parts = line.split(",");
				Word w = new Word(parts[0],parts[1], Integer.parseInt(parts[2].trim()));
				wordlist.add(w);
				line = br.readLine();
			}
			return wordlist;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void write(){
		try {
			File writename = new File(this.outfile);
			writename.createNewFile();
			BufferedWriter out=new BufferedWriter(new FileWriter(writename));
			for(Word w : this.wordlist){
				String word = w.getWord();
				String exp = String.join(Word.EXP_SPLIT, w.getExplanations());
				String fail = Integer.toString(w.getFail());
				String str = word+exp+fail;
				out.write(String.join(",", str)+"\n");
				out.close();
			}
			//out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Word next(){
		this.index++;
		if (this.index == this.wordlist.size()){
			if(this.mark==false){
				finalmark=false;
			}else{
				finalmark=true;
			}
			this.failmark(true);
			this.shuffle();
			this.index=0;
		}

		return this.wordlist.get(this.index);
	}
	
	
	public void pass(){
		this.wordlist.get(this.index).setFail(0);
		this.remain--;
	}
	
	public void failmark(boolean check){
		mark=check;
	}
	
	public boolean checkRemain(){
		return this.remain >= this.THRESHOLD;
	}
	
	private void shuffle(){
		Collections.shuffle(this.wordlist);
		this.index = 0;
	}
	
//	String[][] wordExplanation=new String[3000][3];
//	int[] wordCounts=new int[3000];
//	int wordsNum=0;
//	void read(){
//	//public static void main(String[] args) {
//    //String[][] wordExplanation=new String[3000][2];
//        String explanation;
//        int i;
//		try {
//			String pathname = "G:\\study\\javaworks\\recite\\text\\input.txt";
//			File filename = new File(pathname);
//			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
//			BufferedReader br = new BufferedReader(reader);
//			String line = new String();
//			
//			for (i=0;line != null;i++) {
//				line = br.readLine();
//				if(line!=null){
//					
//					wordExplanation[i][2]=line;
//					wordExplanation[i][0]=line.substring(line.indexOf("Q")+3, line.indexOf("#"));
//					
//					explanation=line.substring(line.indexOf("A")+3);
//				    wordExplanation[i][1]=explanation.replace("A: ","\n");
//				    wordCounts[i]=0;		    
//				}
//			}
//			wordsNum=i;
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
// 
//	}
//	void write() throws IOException{
//		int j;
//		File writename = new File("G:\\study\\javaworks\\recite\\text\\output.txt");
//		writename.createNewFile();
//		BufferedWriter out=new BufferedWriter(new FileWriter(writename));
//		for (j=0;j<wordsNum-1;j++){
//			out.write(this.wordExplanation[j][2]);
//			out.write("\r\n");
//		}
//		out.close();
//	}

}
