package recite;

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
	JPanel jp1,jp2,jp3,jp4;
    JLabel war,count,countNum;
	String infile = "chaoslist.csv";
	String outfile = "output.csv";
	WordList wl = new WordList(infile, outfile);
	Word w = wl.next();
	boolean finalMode = false;
	// boolean choice=true;
//    words sb = new words();
//    int randomNum=0, forgetingNum=0, reciteCount=3, stringentRecit=1, threshold=4, stringentForget=threshold;
    
    
	public static void main(String[] args){    
		Recite win = new Recite();
	}

	public Recite(){
		this.initGUI();      
    }

    private void initGUI(){

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
        
        
        
        count=new JLabel("Remain: ");
        count.setFont(new java.awt.Font("Dialog",1,20));
        countNum=new JLabel();
        countNum.setFont(new java.awt.Font("Dialog",1,20));
         
        
        jb1=new JButton("start");
        jb2=new JButton("forget");  
        jb3=new JButton("next");
        jb4=new JButton("export");
        // adding listener
        
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
    	jb4.addActionListener(this);

    	// jb3.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "next");
    	// jb3.getInputMap().put(KeyStroke.getKeyStroke("X"), "next");

    	// jb2.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "forget");
    	// jb2.getInputMap().put(KeyStroke.getKeyStroke("Z"), "forget");

    	jb3.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(!wl.checkRemain()){
					finalMode = true;
				}

				if(e.getKeyCode() == KeyEvent.VK_DOWN||e.getKeyCode() == KeyEvent.VK_Z){
					explanation.setText(w.displayExplanations());
					wl.forget(w);
				} else if(e.getKeyCode() == KeyEvent.VK_RIGHT||e.getKeyCode() == KeyEvent.VK_X){
					wl.pass(w);
					w = wl.next();
				    war.setText(w.getWord());
				    explanation.setText(null);
				    String s = String.valueOf(wl.remain);
					countNum.setText(s);
				}
			}
        });

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
			war.setText(this.w.getWord());
			countNum.setText(String.valueOf(wl.remain));
		}else if(e.getActionCommand()=="export"){
			// System.out.println("W");
			this.wl.write();
		}else if(e.getActionCommand()=="forget"){
			explanation.setText(w.displayExplanations());
			wl.forget(w);
		}else if(e.getActionCommand()=="next"){
			wl.pass(w);
			w = wl.next();

			if (w == null) {
				JOptionPane.showMessageDialog(null,"Game Over!","Confirm",JOptionPane.WARNING_MESSAGE);  
			}else {
			    war.setText(w.getWord());
			    explanation.setText(null);
			    String s = String.valueOf(wl.remain);
				countNum.setText(s);	
			}
		}
 	}
}
