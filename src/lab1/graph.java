package lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class graph {
	private int[][] in_matrix,out_matrix;  //出度表，入度表
	private int [] vis;		
	private String [] str ; //store split word	 	 
	private String [] str_0; //store word number	 
	private String [] strlist0; // store brige word		
	String filename;
	
	  public String readInFile(){     //读取文件
		File file = new File(filename); 
		String wordsStr = "";
		Scanner in;                     //按行读取字符串，并分割好
		try {
			in = new Scanner(file);
			while(in.hasNextLine()){
				String str = in.nextLine();
				wordsStr = wordsStr.concat(replaceStr(str));
			}		
			
			str =new String[wordSplit(wordsStr).length];    //根据长度new一个String
			str=wordSplit(wordsStr);                  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		creategraph ();     //创建图
		return wordsStr;
	}	

	private void creategraph ()
	 {

			vis = new int[str.length];          //记录
			Set<String> set = new HashSet<>(Arrays.asList(str));
			str_0=new String [set.size()];
			in_matrix=new int[set.size()][set.size()];
			out_matrix=new int[set.size()][set.size()];			
			//初始化
			for(int i=0;i<str_0.length;i++)
			{
				//vis[i]=0;
				for(int j=0;j<str_0.length;j++)
				{
					in_matrix[i][j]=0;
					out_matrix[i][j]=0;					
				}
			}
			int k=0;	

			for(int i=0;i<str.length;i++)        //给每个单词标号，按出现顺序升序标号，用VIS数组标识。
			{
				int flag=0;
				for(int j=0;j<i;j++)
				{
					if(str[i].compareTo(str[j])==0)
					{
						vis[i]=vis[j];flag=1;
						continue;
					}
				}		
				if(flag==0) {vis[i]=k;k++;}
			}
			k=0;
			for(int i=0;i<str.length;i++)     //踢出str数组中重复元素，并不改变原有元素顺序
			{
				if(vis[i]==k) 
				{
					str_0[k]=str[i];
					k++;
				}
			}	
			
			
			for(int i=0;i<str.length-1;i++)       //给二维数组赋值。
			{
				in_matrix[vis[i]][vis[i+1]]++;
				out_matrix[vis[i+1]][vis[i]]++;
			}
		}

	private String [] queryBridgeWords_0(String word1, String word2)      //查询桥接词，返回桥接词字符数组
	{
		 int m = -1,n = -1,flag=0;          //初始化
		 
		 int[] index = new int[str.length];         //记录桥接词的标号
		 
		 String [] list;           //桥接词字符数组
		 
		 for(int i=0;i<str.length;i++)         //寻找单词的标号
		 {
			 String str0 =new String(str[i]);
			 if(str0.compareTo(word1)==0) m=vis[i];
			 if(str0.compareTo(word2)==0) n=vis[i];
		 }

		 if(m==-1 | n==-1)           //单词不在图中
		 {
			return list=new String[0];
		 }		 
		 
		 for(int i=0;i<in_matrix.length;i++)          //记录桥接词的下标和桥接词的总数
		 {
				 if(out_matrix[n][i]!=0 && in_matrix[m][i]!=0)
				 {
					 index[flag]=i;
					 flag++;					 
				 }			 
		 }
		 
		 if(flag==0 )           //没有桥接词
		 {
			 return list=new String[0];
		 }
		 else 
		 {                //将桥接词添加进数组
			 list = new String[flag];
			 for(int i=0;i<flag;i++)
			 {

				String str0 = str_0[index[i]];				 
				 list[i] = str0;				 
			 }	
			 return list;
		 }	
		 
	}
	
	 public String queryBridgeWords(String word1, String word2)    //查询桥接词
	 {
		 int m = -1,n = -1,flag=0;  //初始化
		 
		 int[] index = new int[str.length];   //记录桥接词的标号
		 for(int i=0;i<str.length;i++)        //寻找单词的标号
		 {
			 String str0 =new String(str[i]);
			 if(str0.compareTo(word1)==0) m=vis[i];
			 if(str0.compareTo(word2)==0) n=vis[i];
		 }
		 if(m==-1 && n!=-1)         //Word1不在图中
		 {
			 return "No \""+ word1+"\" in the graph!";
		 }
		 else if(n==-1 && m!=-1)    //Word2不在图中
		 {
			 return "No \""+ word2+"\" in the graph!";
		 }
		 else if(n==-1 && m==-1)    //Word1 ，Word2不在图中
		 {
			 return "No \""+ word1+"\" and \""+ word2+"\" in the graph!";			 
		 }
		 
		 for(int i=0;i<in_matrix.length;i++)
		 {
				 if(out_matrix[n][i]!=0 && in_matrix[m][i]!=0)         //记录桥接词的下标和桥接词的总数
				 {
					 index[flag]=i;
					 flag++;					 
				 }			 
		 }
		 
		 if(flag==0)   //没有桥接词
		 {
			 return "No bridge words from \""+word1+"\" to \""+word2+"\"!";
		 }
		 else  
		 {
			 String strr = new String("The bridge words from \""+word1+"\" to \""+word2+"\" are:");
			 strlist0 =new String[flag];
			 for(int i=0;i<flag;i++)
			 {

				String str0 = str_0[index[i]];   //得到桥接词
				 if(flag==1)           //只有一个桥接词
				 {
					 strr= strr+str0+".";
				 }
				 else if(i!=flag-1)         //不是最后一个桥接词
				 {
					 strr= strr+str0+",";
				 }
				 else                //最后一个桥接词
				 {
					 strr=strr+"and "+str0+".";
				 }
				 
				 strlist0[i] = str0;
				 
				 continue;
			 }
			 return strr;			 
		 }		 
	 }
	 
	private static String[] wordSplit(String str) {   //分割字符串
		return str.split("\\s+");
	}	 
	private static String replaceStr(String str){      //全部转为小写
		return str.replaceAll("[^a-zA-Z]", " ").toLowerCase();
	}	
	
	public  String generateNewText(String inputText)     //生成新文本
	 {
		 String [] strlist = wordSplit(inputText);   //分割字符串得到一个个字符
		 
		 String str = new String();   //存储新文本
		 
		 for(int i=0;i<strlist.length-1;i++)    
		 {
			 str = str+strlist[i]+" ";
			 //根据queryBridgeWords_0函数得到桥接词列表
			strlist0=queryBridgeWords_0(strlist[i].replaceAll("[^a-zA-Z]", " ").toLowerCase(),strlist[i+1].replaceAll("[^a-zA-Z]", " ").toLowerCase());
			 
			 if(strlist0.length!=0)  //存在桥接词，随机选择一个
			 {
				 final double d = Math.random();
				 final int k = (int)(d*strlist0.length);
				 String str1= strlist0[k];
				 str = str+str1+" ";				 
			 }		 
		 }
		 str+=strlist[strlist.length-1];
		 return str;
		 
	 }
	
	 public void showDirectedGraph(graph G)          //展示图
	 {
	    GraphViz gViz=new GraphViz("C:\\Users\\Administrator\\Desktop","D:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe");//设置好路径
        gViz.start_graph();
        
        for(int i=0;i<str_0.length;i++)    //扫描每个点对，如果存在边就将其写入dot文件
        {
        	for(int j=0;j<str_0.length;j++)
        	{
        		if(in_matrix[i][j]!=0)
        		{
        			gViz.addln(str_0[i]+"->"+str_0[j]+"[label=\"" +String.valueOf( in_matrix[i][j]) + "\"];");	//按dot语法写入dot文件
        		}
        	}
        }
               
        gViz.end_graph();
        try {
            gViz.run();
        } catch (Exception e) {
            e.printStackTrace();
        }		 
	        
	 }

	 public String calcShortestPath(String word1, String word2)
	 {
		 int m1 = -1, m2 = -1;   
		 int n = in_matrix.length; 
		    int[][] D = new int[n][n];  //保存从i到j的最小路径值  
		    int[][] p = new int[n][n];  //保存经过的中间节点 
	
		    for(int i=0;i<str.length;i++)
			 {
				 String str0 =new String(str[i]);
				 if(str0.compareTo(word1)==0) m1=vis[i];  //找到初始起点
				 if(str0.compareTo(word2)==0) m2=vis[i];   //找到结尾节点
			 }
		    if(m1==-1 && m2==-1)
		    {
		    	return "字符 \""+word1+"\" and \""+word2 +"\" 不存在！";
		    }
		    else if(m1==-1 && m2!=-1)
		    {
		    	return "字符 \""+word1 +"\" 不存在！";
		    }
		    else if(m1!=-1 && m2==-1)
		    {
		    	return "字符 \""+word2 +"\" 不存在！";
		    }
		    
		    for (int i = 0; i < n; i++) {
		        for (int j = 0; j < n; j++) {  
		            if (in_matrix[i][j] != 0) {  
		                p[i][j] = j;        //先将图中节点连接
		            } else {  
		                p[i][j] = -1;  
		            }  
		            if (in_matrix[i][j]!=0)
		            {	
		            	D[i][j] = in_matrix[i][j];       //将边的权值付值
		            }
		            else
		            {
		            	D[i][j] = 100;
		            }
		        }  
		    }  
		 
		    for (int x = 0; x < n; x++) {           //进行Floyd算法
		        for (int i = 0; i < n; i++) {  
		            for (int j = 0; j < n; j++) {  
		                if (D[i][j] > D[i][x] + D[x][j]) {  
		                    D[i][j] = D[i][x] + D[x][j];  
		                    p[i][j] = p[i][x];  
		                }  
		            }  
		        }  
		    }  
		 // 将结果进行输出 
		   String strN = word1;
           int k = p[m1][m2];  
		   if (k == -1) {  

			   return "没有最短路径！";
		     
		   } else {  //将结果字符串末端不断添加
		       String str0 = str_0[k];
		       strN = strN + "->" + str0;
		       while (k != m2) {  
		           k = p[k][m2];  
		           str0 = str_0[k];
		           strN = strN + "->" + str0;  
		       }  
		       String strA = String.valueOf(D[m1][m2]);
		       strN = strN + "   路径长度：" + strA;      //路径输出，并将其长度添加在末尾
		    } 
	        return strN;		 
	 }
	 
	 
	 String calcShortestPath(String word1) {  
		 int m1 = -1, m2 = 0;   
		 String strr = "";
		 int n = in_matrix.length; 
		    int[][] D = new int[n][n];//保存从i到j的最小路径值  
		    int[][] p = new int[n][n];//保存经过的中间节点  
		    // 找到对应序号
		    for(int i=0;i<str.length;i++)
			 {
				 String str0 =new String(str[i]);
				 if(str0.compareTo(word1)==0) m1=vis[i];
			 }
		    if (m1 == -1) return "字符 \""+word1 +"\" 不存在！";
		    for (int i = 0; i < n; i++) {//初始化D，p  
		        for (int j = 0; j < n; j++) {  
		            if (in_matrix[i][j] != 0) {  
		                p[i][j] = j;  
		            } else {  
		                p[i][j] = -1;  
		            }  
		            if (in_matrix[i][j]!=0)
		            {	
		            	D[i][j] = in_matrix[i][j];  
		            }
		            else
		            {
		            	D[i][j] = 100;
		            }
		        }  
		    }  
		 
		    for (int x = 0; x < n; x++) {//进行Floyd算法，从0到n-1所有可能进行遍历  
		        for (int i = 0; i < n; i++) {  
		            for (int j = 0; j < n; j++) {  
		                if (D[i][j] > D[i][x] + D[x][j]) {  
		                    D[i][j] = D[i][x] + D[x][j];  
		                    p[i][j] = p[i][x];  
		                }  
		            }  
		        }  
		    }  
		    // 下面对获得的结果进行展示  
		    String strN = word1;
		       for(m2 = 0;m2 <n;m2++)
	           {        
		    	   if (m2 != m1)
		    	   {strr = strr + "输出" + word1 + "到" + str_0[m2] + "的最短路径：\r\n" ;  
			       int k = p[m1][m2];  
			       if (k == -1) 
			       {  
			            strr = strr + "没有最短路径\r\n";  
			       }   
			       else {  
			                //System.out.print(" " + k);  
			       String str0 = str_0[k];
			       strN = strN + "->" + str0;
			       while (k != m2) {  
			           k = p[k][m2];  
			           str0 = str_0[k];
			           strN = strN + "->" + str0;  
			       }  
			       String strA = String.valueOf(D[m1][m2]);
			       strN = strN + "   路径长度：" + strA;
			    strr = strr + strN + "\r\n";
			    strN = word1;
			    } }
	           }
		       return strr;
     }
	 public String randomWalk()
	 {
	       int n = str_0.length;
	        int str_len = str_0.length;
			int [][] cp_matrix = new int[n][n];        //新建数组，用于记录游走情况；
			String str;
			for(int i = 0;i<n;i++)
			{
				for (int j = 0;j<n;j++)
				{
					cp_matrix[i][j] = in_matrix[i][j];
				}
			}
			//随机数的产生
			Random rand = new Random();
			int j = rand.nextInt(str_len)%(str_len+1);
			str = str_0[j];
			boolean flag =true ;
			//把游走路线存储到字符串中
			while(flag)
			{
				for(int k=0;k<str_len;k++)
				{
					if (cp_matrix[j][k]!= 0)
					{
						if (cp_matrix[j][k] != -1)
						{str = str+ " "+ str_0[k];
						cp_matrix[j][k]= -1;           //把访问过的边值修改为 -1
						j = k;
						k = -1;}
						else
						{
							str = str+ " "+ str_0[k];
							break;
						}
					}
				}
				flag = false;    //结束路线生成
			}
			return str;		 
	 }

	
}
