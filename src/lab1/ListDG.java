package lab1;

import java.io.IOException;
import java.util.Scanner;
public class ListDG {
    // �ڽӱ��б��Ӧ������Ķ���
    private class ENode {
        int ivex;       // �ñ���ָ��Ķ����λ��
        ENode nextEdge; // ָ����һ������ָ��
    }

    // �ڽӱ��б�Ķ���
    private class VNode {
        char data;          // ������Ϣ
        ENode firstEdge;    // ָ���һ�������ö���Ļ�
    };

    private VNode[] mVexs,mVexs1;  // ��������


  
    
    /*
     * ����ͼ(�����ṩ�ľ���)
     *
     * ����˵����
     *     vexs  -- ��������
     *     edges -- ������
     */
    public ListDG(char[] vexs, char[][] edges) {
        
        // ��ʼ��"������"��"����"
        int vlen = vexs.length;
        int elen = edges.length;

        // ��ʼ��"����"
        mVexs = new VNode[vlen];
        for (int i = 0; i < mVexs.length; i++) {
            mVexs[i] = new VNode();
            mVexs[i].data = vexs[i];
            mVexs[i].firstEdge = null;
        }

        // ��ʼ��"��"
        for (int i = 0; i < elen; i++) {
            // ��ȡ�ߵ���ʼ����ͽ�������
            char c1 = edges[i][0];
            char c2 = edges[i][1];
            // ��ȡ�ߵ���ʼ����ͽ�������
            int p1 = getPosition(edges[i][0]);
            int p2 = getPosition(edges[i][1]);

            // ��ʼ��node1
            ENode node1 = new ENode();
            node1.ivex = p2;
            // ��node1���ӵ�"p1���������ĩβ"
            if(mVexs[p1].firstEdge == null)
              mVexs[p1].firstEdge = node1;
            else
                linkLast(mVexs[p1].firstEdge, node1);
        }
    }

    /*
     * ��node�ڵ����ӵ�list�����
     */
    private void linkLast(ENode list, ENode node) {
        ENode p = list;

        while(p.nextEdge!=null)
            p = p.nextEdge;
        p.nextEdge = node;
    }

    /*
     * ����chλ��
     */
    private int getPosition(char ch) {
        for(int i=0; i<mVexs.length; i++)
            if(mVexs[i].data==ch)
                return i;
        return -1;
    }

    /*
     * ��ȡһ�������ַ�
     */
    private char readChar() {
        char ch='0';

        do {
            try {
                ch = (char)System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while(!((ch>='a'&&ch<='z') || (ch>='A'&&ch<='Z')));

        return ch;
    }

    /*
     * ��ȡһ�������ַ�
     */
    private int readInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    /*
     * ��ӡ�������ͼ
     */
    public void print() {
        System.out.printf("List Graph:\n");
        for (int i = 0; i < mVexs.length; i++) {
            System.out.printf("%d(%c): ", i, mVexs[i].data);
            ENode node = mVexs[i].firstEdge;
            while (node != null) {
                System.out.printf("%d(%c) ", node.ivex, mVexs[node.ivex].data);
                node = node.nextEdge;
            }
            System.out.printf("\n");
        }
    }

    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        char[][] edges = new char[][]{
            {'A', 'B'}, 
            {'B', 'C'}, 
            {'B', 'E'}, 
            {'B', 'F'}, 
            {'C', 'E'}, 
            {'D', 'C'}, 
            {'E', 'B'}, 
            {'E', 'D'}, 
            {'F', 'G'}}; 
        ListDG pG;

        // �������е�"ͼ"
        pG = new ListDG(vexs, edges);

        pG.print();   // ��ӡͼ
    }
}
