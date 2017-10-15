package lab1;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Gui {  //guiͼ�ν���
	
	private JTextArea out_text ;    //����ı�����
	private JTextField in_text;    //�����ı�����
    private graph G = new graph();	

    public static void main(String[] args) { 
    	Gui gui =new Gui(); 	
    	gui.prepareGUI();
    }
	
    private void prepareGUI(){        //����ͼ�ν���
    	
        JFrame frame = new JFrame("ʵ��һ");

        frame.setSize(350, 450);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       //���ô��ڳߴ�

        JPanel panel = new JPanel();    
        
        frame.add(panel);
        panel.setLayout(null);
        
        JButton Button0 = new JButton("open");             //���open��ť������ѡ���ļ�
        Button0.setBounds(120, 20, 80, 25);
        Button0.setActionCommand("open");
        Button0.addActionListener(new ButtonClickListener() );
        panel.add(Button0);
        
        JLabel Label0 = new JLabel("����");              //������봰��

        Label0.setBounds(10,60,80,25);
        panel.add(Label0);

        in_text = new JTextField(20);
        in_text.setBounds(60,60,230,25);
        panel.add(in_text);

        JButton Button1 = new JButton("����ͼ");   //�������ͼ��ť
        Button1.setBounds(40, 100, 100, 25);
        Button1.setActionCommand("����ͼ");
        Button1.addActionListener(new ButtonClickListener() );
        panel.add(Button1);
        
        JButton Button2 = new JButton("��ѯ�ŽӴ�");  //��Ӳ�ѯ�ŽӴʰ�ť
        Button2.setBounds(180, 100, 100, 25);
        Button2.setActionCommand("��ѯ�ŽӴ�");
        Button2.addActionListener(new ButtonClickListener() );
        panel.add(Button2);

        JButton Button3 = new JButton("�������ı�");   //����������ı���ť
        Button3.setBounds(40, 140, 100, 25);
        Button3.setActionCommand("�������ı�");
        Button3.addActionListener(new ButtonClickListener() ); 
        panel.add(Button3);

        JButton Button4 = new JButton("���·��");   //������·����ť
        Button4.setBounds(180, 140, 100, 25);
        Button4.setActionCommand("���·��");
        Button4.addActionListener(new ButtonClickListener() ); 
        panel.add(Button4);
        
        JButton Button5 = new JButton("�������");   //���������߰�ť
        Button5.setBounds(40, 180, 100, 25);
        Button5.setActionCommand("�������");
        Button5.addActionListener(new ButtonClickListener() ); 
        panel.add(Button5);
 
        JButton Button6 = new JButton("clear");     //���clear��ť
        Button6.setBounds(180, 180, 100, 25);
        Button6.setActionCommand("clear");
        Button6.addActionListener(new ButtonClickListener() ); 
        panel.add(Button6);
        
        out_text = new JTextArea();           //����ı��������
       out_text.setLineWrap(true);
       out_text.setEditable(false);
       out_text.setWrapStyleWord(true);
        
        JScrollPane jsp= new JScrollPane(out_text);;   //��ӹ�����
        jsp.setBounds(0,230,345,170);
        panel.add(jsp);
        
        frame.setVisible(true);
    }
    
    
  
    private class ButtonClickListener implements ActionListener{        //�¼�������
        public void actionPerformed(ActionEvent e) {
           String command = e.getActionCommand();  
           
           if( command.equals( "open" ))  {        	   //���ļ�����
        	   
        	   FileDialog fd=new FileDialog(new JFrame(),"ѡ���ļ�",FileDialog.LOAD);  //���ɴ��ļ�����
				fd.setFile("*.txt");
				fd.setVisible(true);
				String filename = fd.getFile();         //ȡ���ļ���
				if (filename != null){
					G.filename=fd.getDirectory() + fd.getFile();     //ȡ���ļ�����·��
					//out_text.setText("�ļ��򿪳ɹ���\n");  
					//G.readInFile();	   //��ȡ�ļ�
					out_text.setText("�ļ��򿪳ɹ���\n"+G.readInFile());          
				}
        	   
           }
           else if( command.equals( "����ͼ" ) )  {
        	   if(G.filename==null)                     //δѡ���ļ�
        	   {
        		   out_text.setText("δѡ���ļ���");
        	   }
        	   else
        	   {
         	   out_text.setText("����ͼ�ɹ���ͼƬ�Ա��������档");
        	   G.showDirectedGraph(G);       		    //չʾͼ
        	   }

           }
           else if( command.equals( "��ѯ�ŽӴ�" ) )  {
        	   if(G.filename==null)       //δѡ���ļ�
        	   {
        		   out_text.setText("δѡ���ļ���");
        	   }
        	   else
        	   {
		    	   String [] strx=in_text.getText().replaceAll("[^a-zA-Z]", " ").toLowerCase().split("\\s+");    //�ָ���С���ַ���    	   
		    	   if(strx.length!=2)        //������Ȳ�Ϊ2���������
		    	   {
		    		   out_text.setText("�������");
		    	   }
		    	   else      //����ŽӴ�
		    	   {
		    	   out_text.setText(G.queryBridgeWords(strx[0], strx[1]));		    		   
		    	   }

        	   }
           }
           else if( command.equals( "�������ı�" ) )  {
        	   if(G.filename==null)      //δѡ���ļ�
        	   {
        		   out_text.setText("δѡ���ļ���");
        	   }
        	   else
        	   {
        		   String strx=in_text.getText();   
		    	   if((strx.split("\\s+").length < 2) && (strx.split("\\s+").length >0))       //������С��2������
		    	   {
		    		   out_text.setText("�������");
		    	   }
		    	   else   
		    	   {		    		   
		    	      out_text.setText(G.generateNewText(strx));     //������ɵ����ı�
		    	   }
        	   }
           }
           else if( command.equals( "���·��" ) )  {
        	   String [] strx=in_text.getText().replaceAll("[^a-zA-Z]", " ").toLowerCase().split("\\s+");     //�ָ���С���ַ���    
        	   if(G.filename==null)         //δѡ���ļ�
        	   {
        		   out_text.setText("δѡ���ļ���");
        	   }
        	   else
        	   {
        		   if(strx.length>2 | in_text.getText()==null)    //���ʴ���2
        		   {
        			   out_text.setText("�������");        			   
        		   }       		   
        		   else if(strx.length==2)           //2�����ʵ����
        		   {
        			   out_text.setText(G.calcShortestPath(strx[0], strx[1]));
        		   }
        		   else if(strx.length==1)     //1�����ʵ����
        		   {
        			   out_text.setText(G.calcShortestPath(strx[0]));
        		   }
        	   }
           }
           else if( command.equals( "�������" ) )  {
        	   if(G.filename==null)      //δѡ���ļ�
        	   {
        		   out_text.setText("δѡ���ļ���");
        	   }
        	   else
        	   {
        		   out_text.setText(G.randomWalk());
        	   }
           }
           else  {                        //�����������ı�����
        	   out_text.setText("");
        	   in_text.setText("");
           }      
        }     
     }

}
//testgui
