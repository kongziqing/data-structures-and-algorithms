package com.arr;
//代码功能，将原始数据转成稀疏数组，再将稀疏数组转成原始数据，
//要求1：将稀疏数组保存到磁盘上，比如map.txt
//要求2：恢复原来的数组时，读取map.txt进行恢复
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SparseArray {
    public static void main(String[] args) throws IOException {
        //创建一个原始的二维数组11*11
        //0；表示没有棋子，1表示黑子，2表示篮子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] =1;
        chessArr1[2][3] =2;
        //输出原始的二维数组
        for(int[] row:chessArr1){
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //将二维数组转稀疏数组
        //1.先遍历二维数组，得到非零数据的个数
        int sum = 0;
        for(int i=0;i<11;i++ ){
            for(int j = 0;j<11;j++){
                if(chessArr1[i][j]!=0){
                    sum++;
                }
            }
        }
        int sparseArr[][] = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        //遍历二维数组,将非零的值存放到sparseArr中
        int count = 0;//count用于记录是第几个非零数据
        for(int i = 0;i<11;i++){
            for(int j = 0;j<11;j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组的形式
        System.out.println();
        System.out.printf("得到稀疏数组为：");
        for(int i = 0;i<sparseArr.length;i++){
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);

        }
        System.out.println();

        //将稀疏数组恢复成原始的二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        for(int i =1;i<sparseArr.length;i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //输出恢复后的二维数组
        System.out.println();
        System.out.println("输出恢复后的二维数组");
        for(int[] row:chessArr2){
            for(int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        File file = new File("map.txt");//存放数组数据的文件
        FileWriter out = new FileWriter(file);//文件写入流

        //课后练习
        //要求1：在 前面的基础上，将稀疏数组保存到磁盘上，比如map.data
        //要求2：恢复原来的数组时，读取map.data进行恢复

        //将数组中的数据写入到文件中，每行各数据之间空格间隔
        for(int i = 0;i<sparseArr.length;i++){
            for(int j = 0;j<sparseArr[i].length;j++){
                out.write(sparseArr[i][j]+" ");
            }
            out.write("\r\n");
        }
        out.close();
        System.out.println("数据写入文件完成");

        //将map.txt文件读取
        System.out.println("读取文件中的数据，并转化为数组");
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            File in = new File("map.txt");
            InputStreamReader input = new InputStreamReader(new FileInputStream(in));
            BufferedReader bf = new BufferedReader(input);
            // 按行读取字符串
            String str;
            while ((str = bf.readLine()) != null) {
                arrayList.add(str);
            }
            bf.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对ArrayList中存储的字符串进行处理
        int length = arrayList.size();
        int width = arrayList.get(0).split(" ").length;
        int array[][] = new int[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                String s = arrayList.get(i).split(" ")[j];
                array[i][j] = Integer.parseInt(s);
            }
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

    }
}
