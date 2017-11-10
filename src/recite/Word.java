package recite;

public class Word {
	private String word;
	private String[] explanations;
	//EXP_SPLIT is a signal in the word's explanation, which can split deferent meaning for one word
	static public String EXP_SPLIT = "#";
	private boolean fail;
	
	public Word(String word, String explanation, boolean fail){
		this.word = word;
		this.explanations = explanation.split(this.EXP_SPLIT);
		this.fail = fail;
	}
	
	public String displayExplanations(){
		return String.join("\n", this.explanations);
	}

	public void forget(){
		this.fail = true;
	}
	
	public void remember(){
		this.fail=false;
	}

	// Getters and Setters

	public String getWord() {
		return word;
	}

	public String[] getExplanations() {
		return this.explanations;
	}
	
	public boolean getFail(){
		return this.fail;
	}
}
