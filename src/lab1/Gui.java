package lab1;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Gui {
	
	private JTextArea out_text ;    //输出文本区域
	private JTextField in_text;    //输入文本区域
    private graph G = new graph();	

    public static void main(String[] args) { 
    	Gui gui =new Gui(); 	
    	gui.prepareGUI();
    }
	
    private void prepareGUI(){        //构建图形界面
    	
        JFrame frame = new JFrame("实验一");

        frame.setSize(350, 450);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       //设置窗口尺寸

        JPanel panel = new JPanel();    
        
        frame.add(panel);
        panel.setLayout(null);
        
        JButton Button0 = new JButton("open");             //添加open按钮，用来选择文件
        Button0.setBounds(120, 20, 80, 25);
        Button0.setActionCommand("open");
        Button0.addActionListener(new ButtonClickListener() );
        panel.add(Button0);
        
        JLabel Label0 = new JLabel("输入");              //添加输入窗口

        Label0.setBounds(10,60,80,25);
        panel.add(Label0);

        in_text = new JTextField(20);
        in_text.setBounds(60,60,230,25);
        panel.add(in_text);

        JButton Button1 = new JButton("生成图");   //添加生成图按钮
        Button1.setBounds(40, 100, 100, 25);
        Button1.setActionCommand("生成图");
        Button1.addActionListener(new ButtonClickListener() );
        panel.add(Button1);
        
        JButton Button2 = new JButton("查询桥接词");  //添加查询桥接词按钮
        Button2.setBounds(180, 100, 100, 25);
        Button2.setActionCommand("查询桥接词");
        Button2.addActionListener(new ButtonClickListener() );
        panel.add(Button2);

        JButton Button3 = new JButton("生成新文本");   //添加生成新文本按钮
        Button3.setBounds(40, 140, 100, 25);
        Button3.setActionCommand("生成新文本");
        Button3.addActionListener(new ButtonClickListener() ); 
        panel.add(Button3);

        JButton Button4 = new JButton("最短路径");   //添加最短路径按钮
        Button4.setBounds(180, 140, 100, 25);
        Button4.setActionCommand("最短路径");
        Button4.addActionListener(new ButtonClickListener() ); 
        panel.add(Button4);
        
        JButton Button5 = new JButton("随机游走");   //添加随机游走按钮
        Button5.setBounds(40, 180, 100, 25);
        Button5.setActionCommand("随机游走");
        Button5.addActionListener(new ButtonClickListener() ); 
        panel.add(Button5);
 
        JButton Button6 = new JButton("clear");     //添加clear按钮
        Button6.setBounds(180, 180, 100, 25);
        Button6.setActionCommand("clear");
        Button6.addActionListener(new ButtonClickListener() ); 
        panel.add(Button6);
        
        out_text = new JTextArea();           //添加文本输出区域
       out_text.setLineWrap(true);
       out_text.setEditable(false);
       out_text.setWrapStyleWord(true);
        
        JScrollPane jsp= new JScrollPane(out_text);;   //添加滚动条
        jsp.setBounds(0,230,345,170);
        panel.add(jsp);
        
        frame.setVisible(true);
    }
    
    
  
    private class ButtonClickListener implements ActionListener{        //事件监听器
        public void actionPerformed(ActionEvent e) {
           String command = e.getActionCommand();  
           
           if( command.equals( "open" ))  {        	   //打开文件功能
        	   
        	   FileDialog fd=new FileDialog(new JFrame(),"选择文件",FileDialog.LOAD);  //生成打开文件窗口
				fd.setFile("*.txt");
				fd.setVisible(true);
				String filename = fd.getFile();         //取得文件名
				if (filename != null){
					G.filename=fd.getDirectory() + fd.getFile();     //取得文件完整路径
					//out_text.setText("文件打开成功！\n");  
					//G.readInFile();	   //读取文件
					out_text.setText("文件打开成功！\n"+G.readInFile());          
				}
        	   
           }
           else if( command.equals( "生成图" ) )  {
        	   if(G.filename==null)                     //未选择文件
        	   {
        		   out_text.setText("未选择文件！");
        	   }
        	   else
        	   {
         	   out_text.setText("生成图成功！图片以保存在桌面。");
        	   G.showDirectedGraph(G);       		    //展示图
        	   }

           }
           else if( command.equals( "查询桥接词" ) )  {
        	   if(G.filename==null)       //未选择文件
        	   {
        		   out_text.setText("未选择文件！");
        	   }
        	   else
        	   {
		    	   String [] strx=in_text.getText().replaceAll("[^a-zA-Z]", " ").toLowerCase().split("\\s+");    //分割最小化字符串    	   
		    	   if(strx.length!=2)        //如果长度不为2，输入错误
		    	   {
		    		   out_text.setText("输入错误！");
		    	   }
		    	   else      //输出桥接词
		    	   {
		    	   out_text.setText(G.queryBridgeWords(strx[0], strx[1]));		    		   
		    	   }

        	   }
           }
           else if( command.equals( "生成新文本" ) )  {
        	   if(G.filename==null)      //未选择文件
        	   {
        		   out_text.setText("未选择文件！");
        	   }
        	   else
        	   {
        		   String strx=in_text.getText();   
		    	   if((strx.split("\\s+").length < 2) && (strx.split("\\s+").length >0))       //单词数小于2，错误
		    	   {
		    		   out_text.setText("输入错误！");
		    	   }
		    	   else   
		    	   {		    		   
		    	      out_text.setText(G.generateNewText(strx));     //输出生成的新文本
		    	   }
        	   }
           }
           else if( command.equals( "最短路径" ) )  {
        	   String [] strx=in_text.getText().replaceAll("[^a-zA-Z]", " ").toLowerCase().split("\\s+");     //分割最小化字符串    
        	   if(G.filename==null)         //未选择文件
        	   {
        		   out_text.setText("未选择文件！");
        	   }
        	   else
        	   {
        		   if(strx.length>2 | in_text.getText()==null)    //单词大于2
        		   {
        			   out_text.setText("输入错误！");        			   
        		   }       		   
        		   else if(strx.length==2)           //2个单词的情况
        		   {
        			   out_text.setText(G.calcShortestPath(strx[0], strx[1]));
        		   }
        		   else if(strx.length==1)     //1个单词的情况
        		   {
        			   out_text.setText(G.calcShortestPath(strx[0]));
        		   }
        	   }
           }
           else if( command.equals( "随机游走" ) )  {
        	   if(G.filename==null)      //未选择文件
        	   {
        		   out_text.setText("未选择文件！");
        	   }
        	   else
        	   {
        		   out_text.setText(G.randomWalk());
        	   }
           }
           else  {                        //清除输入输出文本区域
        	   out_text.setText("");
        	   in_text.setText("");
           }      
        }     
     }

}
