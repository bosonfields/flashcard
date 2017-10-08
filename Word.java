package recite.copy;

public class Word {
	public String word;
	public String[] explanations;
	static public String EXP_SPLIT = ":";
	private int fail;
	
	public Word(String word, String explanation, int fail){
		this.word = word;
		this.explanations = explanation.split(this.EXP_SPLIT);
		this.fail = fail;
	}
	
	public String displayExplanations(){
		return String.join("\n", this.explanations);
	}

	public void forget(){
		this.fail++;
	}
	// Getters and Setters

	public String getWord() {
		return word;
	}

	public String[] getExplanations() {
		return this.explanations;
	}

	public void setFail(int fail) {
		this.fail = fail;
	}

	public int getFail() {
		return fail;
	}
	
	
}
