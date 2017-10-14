import java.util.Random;

public class Floyd {
	private String [] str = {"to", "exchange", "strange", "nod", "worlds" ,"seek" ,"out" ,"life" ,"and" , "civilizations" };
	private int n = str.length;
	private String [] str_0 = new String[n];
	private int[][] in_matrix = new int [n][n];
	private int [][]out_matrix = new int [n][n];
	private int [] vis = {0,1,2,3,4,5,6,7,8,9};
	String s1;
	
	void turn()
	//void turn (String [] str ,int[][] in_matrix,int[][] out_matrix,int [] vis)
	 {
			int k=0;
			/**for(int i=0;i<str.length;i++)
			{
				for(int j=0;j<i;j++)
				{
					if(str[i].compareTo(str[j])==0)
					{
						vis[i]=vis[j];
					}
					else
					{
						vis[i]=k;k++;
					}
				}			
			}**/
			k=0;
			for(int i=0;i<str.length;i++)
			{
				if(vis[i]==k) 
				{
					str_0[k]=str[i];
					k++;
				}
			}
			
			
			for(int i=0;i<str.length-1;i++)
			{
				in_matrix[vis[i]][vis[i+1]]++;
				out_matrix[vis[i+1]][vis[i]]++;
			}
			in_matrix[0][5] = 1;
		    in_matrix[4][0] = 1;
		    in_matrix[6][3] = 1;
		    in_matrix[3][7] = 1;
		    in_matrix[8][3] = 1;
		    in_matrix[3][9] = 1;
		    in_matrix[4][5] = 0;
		    in_matrix[6][7] = 0;
		    in_matrix[8][9] = 0;
    }
	
    
	String calcShortestPath(String word1,String word2) {  
		 int m1 = 0, m2 = 0;   
		 int n = in_matrix.length; 
		    int[][] D = new int[n][n];//保存从i到j的最小路径值  
		    int[][] p = new int[n][n];//保存经过的中间节点  
		    // 找到对应序号
		    for(int i=0;i<str.length;i++)
			 {
				 String str0 =new String(str[i]);
				 if(str0.compareTo(word1)==0) m1=vis[i]; //找到初始起点
				 if(str0.compareTo(word2)==0) m2=vis[i]; //找到结尾节点
			 }
		    
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

		            //System.out.println("输出" + i + "到" + j + "最短路径：");  
		   String strN = word1;
          int k = p[m1][m2];  
		   if (k == -1) {  
		     System.out.println("没有最短路径");  
		   } else {  
		                //System.out.print(" " + k);  
		       String str0 = str_0[k];
		       strN = strN + "->" + str0;
		       while (k != m2) {  
		           k = p[k][m2];  
		           str0 = str_0[k];
		           strN = strN + "->" + str0;  
		       }  

		                //System.out.println(strN); 
		    } 
	        return strN;
     }
	String calcShortestPath(String word1) {  
		 int m1 = 0, m2 = 0;   
		 int n = in_matrix.length; 
		 String strr = "";
		    int[][] D = new int[n][n];//保存从i到j的最小路径值  
		    int[][] p = new int[n][n];//保存经过的中间节点  
		    // 找到对应序号
		    for(int i=0;i<str.length;i++)
			 {
				 String str0 =new String(str[i]);
				 if(str0.compareTo(word1)==0) m1=vis[i]; //找到初始起点
			 }
		    
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

			    strr = strr + strN + "\r\n";
			    strN = word1;
			    } }
	           }
	      return strr;

     }
	String randomWalk()
	{
        int n = str.length;
        int str_len = str.length;
		int [][] cp_matrix = new int[n][n];
		String strr;
		for(int i = 0;i<n;i++)
		{
			for (int j = 0;j<n;j++)
			{
				cp_matrix[i][j] = in_matrix[i][j];
			}
		}
		Random rand = new Random();
		int j = 5;
		//int j = rand.nextInt(str_len)%(str_len+1);
		strr = str[j];
		boolean flag =true ;
		while(flag)
		{
			for(int k=0;k<str_len;k++)
			{
				if (cp_matrix[j][k]!= 0)
				{
					if (cp_matrix[j][k] == 1)
					{strr = strr+ " "+ str[k];
					cp_matrix[j][k]= -1;
					j = k;
					k = -1;}
					else
					{
						strr = strr+ " "+ str[k];
						break;
					}
				}
			}
			flag = false;
		}
		return strr;
	}
	
	public static void main(String[] args) {
		Floyd c = new Floyd();
		c.turn();
		String s = c.randomWalk();
		System.out.println(s);
		/**System.out.println(s);
		System.out.println();
		s = c.calcShortestPath("new", "seek");
		System.out.println(s);
		s = c.calcShortestPath("new");
		System.out.println(s);**/
	}
	  
}
