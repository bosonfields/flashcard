package recite;

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
	
	//threshhold is a number of the remnant words, where words will not be removed until the all the remnant words can get remembered at one course
	private static final int THRESHOLD = 5;
	String infile;
	String outfile;
	List<Word> wordlist;
	int index;
	int remain;
	boolean finalMode = false;
	
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
				Word w = new Word(parts[0].trim(),parts[1], Boolean.valueOf(parts[2].trim()));
				if (!w.getFail()){
					wordlist.add(w);
				}
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
				String fail = (w.getFail()) ? "1":"0";
				String[] str = new String[]{word,exp,fail};
				out.write(String.join(",", str));
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Word next(){
		if (this.index == this.wordlist.size()){
			if (this.checkRemain()) {
				return null;
			}
			this.shuffle();
		}
		return this.wordlist.get(this.index);
	}
	
	
	public void pass(Word w){
		if (this.finalMode){
			this.index++;
			this.remain--;
			return;
		}
		if (!w.getFail()){
			this.wordlist.remove(w);
			this.remain--;
		}
	}

	public void forget(Word w){
		if (this.finalMode){
			this.shuffle();
			this.remain=this.THRESHOLD;
			return;
		}
		this.index++;
		w.forget();
	}
	
	public boolean checkRemain(){
		if (this.finalMode)
			return true;
		else if (this.remain <= this.THRESHOLD){
			this.finalMode = true;
			this.shuffle();
		}
		return false;
	}
	
	private void shuffle(){
		Collections.shuffle(this.wordlist);
		this.index = 0;
	}
}
