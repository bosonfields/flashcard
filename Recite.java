package recite.copy;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;


public class Recite extends JFrame implements ActionListener{
	JButton jb1,jb2,jb3,jb4;
	JTextPane explanation;
	JPanel jp1,jp2,jp3,jp4;//面板  
    JLabel war,count,countNum;//标签  
	String infile = "G:\\study\\javaworks\\recite\\text\\chaoslist.csv";
	String outfile = "G:\\study\\javaworks\\recite\\text\\output.csv";
	WordList wl = new WordList(infile, outfile);
	Word w = wl.next();
	boolean choice=true;
//    words sb = new words();
//    int randomNum=0, forgetingNum=0, reciteCount=3, stringentRecit=1, threshold=4, stringentForget=threshold;
    
    
	public static void main(String[] args){    
		Recite win = new Recite();
	}
	public Recite(){
		    
		    jp1=new JPanel();
		    jp1.setBounds(100,50,1000,200);
	    	jp2=new JPanel();
	    	jp2.setBounds(100,200,1000,450);
	    	jp3=new JPanel();
	    	jp3.setBounds(450,680,300,30);
	    	jp4=new JPanel();
	    	jp4.setBounds(450,710,300,30);
	    	
	    	
	    	//words & explanation
	    	war=new JLabel();
	    	war.setFont(new java.awt.Font("Dialog",1,100));
	    	
	        explanation=new JTextPane();
	        explanation.setPreferredSize(new Dimension(1000,450));
	        explanation.setFont(new java.awt.Font("Dialog",1,30));
	        
	        
	        
	        count=new JLabel("剩余单词数: ");
	        count.setFont(new java.awt.Font("Dialog",1,20));
	        countNum=new JLabel();
	        countNum.setFont(new java.awt.Font("Dialog",1,20));
	        
	        //创建按钮  
	        
	        jb1=new JButton("start");
	        jb2=new JButton("forget");  
	        jb3=new JButton("next");
	        jb4=new JButton("export");
	        // adding listener
	        
	        jb1.addActionListener(this);
	        jb2.addActionListener(this);
	        
	        jb3.addActionListener(this);
	        jb3.addKeyListener(new KeyAdapter(){
	        	public void keyPressed(KeyEvent e){
	        		if(wl.checkRemain()){
	        			if(e.getKeyCode() == KeyEvent.VK_DOWN||e.getKeyCode() == KeyEvent.VK_Z){
	        				explanation.setText(w.displayExplanations());
	        				w.forget();
	    					choice=false;
	        			}else if(e.getKeyCode() == KeyEvent.VK_RIGHT||e.getKeyCode() == KeyEvent.VK_X){
	        				if(choice){
	        					explanation.setText(w.displayExplanations());
	        					choice=false;
	        					}else{
	        						wl.pass();
	        						w=wl.next();
	        						while(w.getFail()==0){
	        							w=wl.next();	
	        						}
	        					    war.setText(w.getWord());
	        					    explanation.setText(null);
	        					    choice=true;
	        					    String s = String.valueOf(wl.remain);
	        						countNum.setText(s);
	        					}
	        				}
	    		    }else{
	    		    	if(e.getKeyCode() == KeyEvent.VK_DOWN||e.getKeyCode() == KeyEvent.VK_Z){
	    		    		explanation.setText(w.displayExplanations());
	    					wl.failmark(false);
	    					choice=false;
	    				}else if(e.getKeyCode() == KeyEvent.VK_RIGHT||e.getKeyCode() == KeyEvent.VK_X){
	    					if(choice){
	    						explanation.setText(w.displayExplanations());
	    						choice=false;
	    						}else if(wl.finalmark){
	    						JOptionPane.showMessageDialog(null,"已完成记忆","提示消息",JOptionPane.WARNING_MESSAGE);  
	    						}else{
	    							w=wl.next();
	    							while(w.getFail()==0){
	    								w=wl.next();
	    							}
	    						    war.setText(w.getWord());
	    						    explanation.setText(null);
	    						    choice=true;
	    						}
	    				}
	   
	    		    }
	        			
	        	}
	      
	        });
	        jb4.addActionListener(this);

	        this.setLayout(new GridLayout(3,1));
	        
	        jp1.add(war);  
	        jp2.add(explanation);  
	         
	        jp3.add(jb1);  
	        jp3.add(jb2);
	        jp3.add(jb3);
	        jp3.add(jb4);

	        jp4.add(count);
	        jp4.add(countNum);
	        
	        this.add(jp1);  
	        this.add(jp2);   
	        this.add(jp3);
	        this.add(jp4);
	        

	        this.setLayout(null);
	        this.setTitle("I'm stupid");
	        this.setSize(1200,800);
	        this.setLocation(200,150);
	        this.setLocationRelativeTo(null);
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.setVisible(true);
	        this.setResizable(true);
      }
	 public void actionPerformed(ActionEvent e){
		 if(e.getActionCommand()=="start"){
			 String infile = "G:\\study\\javaworks\\recite\\text\\chaoslist.csv";
			 String outfile = "G:\\study\\javaworks\\recite\\text\\output.csv";
			  WordList wl = new WordList(infile, outfile);	
			  war.setText(wl.next().getWord());
			  String s = String.valueOf(wl.remain);
			  countNum.setText(s);
		 }else if(e.getActionCommand()=="export"){
				  System.out.println("W");
				  wl.write();
		
		 }else if(wl.checkRemain()){
    			if(e.getActionCommand()=="forget"){
    				explanation.setText(w.displayExplanations());
    				w.forget();
					choice=false;
				}else if(e.getActionCommand()=="next"){
					if(choice){
					explanation.setText(w.displayExplanations());
					choice=false;
					}else{
						wl.pass();
						w=wl.next();
						while(w.getFail()==0){
							w=wl.next();	
						}
					    war.setText(w.getWord());
					    explanation.setText(null);
					    choice=true;
					    String s = String.valueOf(wl.remain);
						countNum.setText(s);
					}
				}
		    }
    		else if(!wl.checkRemain()){
		    	if(e.getActionCommand()=="forget"){
					explanation.setText(w.displayExplanations());
					wl.failmark(false);
					choice=false;
				}else if(e.getActionCommand()=="next"){
					if(choice){
					explanation.setText(w.displayExplanations());
					choice=false;
					}else if(wl.finalmark){
					JOptionPane.showMessageDialog(null,"已完成记忆","提示消息",JOptionPane.WARNING_MESSAGE);  
					}else{
						w=wl.next();
						while(w.getFail()==0){
							w=wl.next();
						}
					    war.setText(w.getWord());
					    explanation.setText(null);
					    choice=true;
					}
				}
		    	
    		}
 		 }
}

    		 
//		 else if(forgetingNum>=threshold){
//				if(e.getActionCommand()=="forget"){
//					explanation.setText(sb.wordExplanation[randomNum][1]);
//					reciteCount=0;
//				}else if(e.getActionCommand()=="next"){
//				
//					if(reciteCount==3){
//					explanation.setText(sb.wordExplanation[randomNum][1]);
//					reciteCount=1;
//					}/*else if(reciteCount==1&&forgetingNum==0){
//					JOptionPane.showMessageDialog(null,"已完成记忆","提示消息",JOptionPane.WARNING_MESSAGE);  
//					}*/else{
//						exchange();
//					    rdm();
//					}
//				}
//		    }else if(forgetingNum<threshold){
//		    	if(e.getActionCommand()=="forget"){
//					explanation.setText(sb.wordExplanation[randomNum][1]);
//					stringentRecit=0;
//					reciteCount=0;
//				}else if(e.getActionCommand()=="next"){
//					if(reciteCount==3){
//					explanation.setText(sb.wordExplanation[randomNum][1]);
//					reciteCount=1;
//					}else if(reciteCount==1&&stringentForget==0&&stringentRecit==1){
//					JOptionPane.showMessageDialog(null,"已完成记忆","提示消息",JOptionPane.WARNING_MESSAGE);  
//					}else{
//						exchange();
//					    stringentrdm();
//					    System.out.println(forgetingNum+"\t"+reciteCount+"\t"+stringentForget+"\t"+stringentRecit);
//					}
//				}
	
//	 public void exchange(){
//		 String shiftWord;
//			String shiftExp;
//			String shiftWhole;
//			//int shiftCounts;
//			
//			shiftWord=sb.wordExplanation[randomNum][0];
//			shiftExp=sb.wordExplanation[randomNum][1];
//			shiftWhole=sb.wordExplanation[randomNum][2];
//			//shiftCounts=sb.wordCounts[randomNum];
//			
//			sb.wordExplanation[randomNum][0]=sb.wordExplanation[forgetingNum][0];
//			sb.wordExplanation[randomNum][1]=sb.wordExplanation[forgetingNum][1];
//			sb.wordExplanation[randomNum][2]=sb.wordExplanation[forgetingNum][2];
//			//sb.wordCounts[randomNum]=sb.wordCounts[forgetingNum];
//			
//			
//			sb.wordExplanation[forgetingNum][0]=shiftWord;
//			sb.wordExplanation[forgetingNum][1]=shiftExp;
//			sb.wordExplanation[forgetingNum][2]=shiftWhole;
//			//sb.wordCounts[forgetingNum]=shiftCounts;
//	 }
//	 public void rdm(){
//		    if(reciteCount==1){
//		    	String s = String.valueOf(forgetingNum);
//				countNum.setText(s);
//		    	forgetingNum=forgetingNum-1;
//				reciteCount=3;
//			}else{
//				reciteCount=3;
//			}
//			randomNum=(int)(Math.random()*forgetingNum);
//			war.setText(sb.wordExplanation[randomNum][0]);
//			//sb.wordCounts[randomNum]=0;
//			explanation.setText(null);
//	 }
//	 public void stringentrdm(){
//		 if(stringentForget>0){
//			 String s = String.valueOf(stringentForget);
//			 countNum.setText(s);
//			 stringentForget--;
//			 reciteCount=3;
//		 }else if(stringentForget==0&&stringentRecit==0){
//			 stringentForget=forgetingNum;
//			 stringentRecit=1;
//		 }
//		 randomNum=(int)(Math.random()*stringentForget);
//		 war.setText(sb.wordExplanation[randomNum][0]);
//			//sb.wordCounts[randomNum]=0;
//			explanation.setText(null);
//	 }
//}
