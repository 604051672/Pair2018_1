package com.java6369.lesson02;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MathExam_02{
	private static final String[] fuhao = {"+", "-"," * ", " / "};//����ʹ�õ��������
	static StringBuffer Topic = new StringBuffer(); //��ѭ���н����ַ���ƴ�ӣ�ƴ����Ŀ�ʹ�
	static StringBuffer Answer= new StringBuffer();
	private static int i;

public static void main(String[] args) throws IOException {//�׳��쳣
	int n = Integer.parseInt(args[0]);  //�������й����������
	int grade = Integer.parseInt(args[1]);//�����������Ŀ����
	if (args.length == 0) {
		//����������������һ�����������ڶ������꼶������Ĳ�����������
        throw new IllegalArgumentException("������������������꼶");
    }
	if(args.length == 1) {
		throw new IllegalArgumentException("��������2������");
	}
    String str = args[0];//����������ĵ�1������
    if(str.length()>=3) {
    	throw new IllegalArgumentException("����ֻ������2��");
    }
    for(i=0;(str.charAt(i) < '0' || str.charAt(i++) > '9') && i < str.length();i++) {
    	//����λ��Ϊi���ַ���0~9��ʾ���֣�����
    	throw new IllegalArgumentException("��Ҫ���������");
    }
    n = Integer.parseInt(args[0]);//�Ѻ���ת��Ϊint�ͽ�����ֵ����
    Exam369(n,grade);
    writeTo();
    System.out.println("��Ŀ����������鿴out.txt");//����out.txt
}
    private static void Exam369(int n,int grade) {
        int result = 0; 
        int num1,num2,sub;
        int yushu=0;
		for (int i = 1; i <= n ; i++) {
			 num1 = (int) (Math.random() * 101);
			 num2 = (int) (Math.random() * 101);
			 sub = (int) (Math.random() *2);
			String symbol = fuhao[sub];
			switch (symbol) {
			case "+":
				result = num1 + num2;
				break;
			case "-":
				if (num1 <num2) {
					int m = num1;
					num1 = m;
					m = num2;
				}
				result =num1 - num2;
				break;
			default:
				System.out.println("����");
			}
			int Choose = (grade == 1) ? 
			((int) (Math.random()*10))%2:((int) (Math.random() * 10)) % 4;
			String symbol2 = fuhao[Choose];
			
			switch (symbol2) {
			case " + ":
				result = num1 + num2;
				break;
			case " - ":
				result = num1 - num2;
				break;
			case " * ":
				result= num1 * num2;
				break;
			case " / ":
				while(num2 ==0) {
					num2 = (int) (Math.random() * 101);
				}
				result= num1 / num2;
				yushu = num1 % num2;
				break;
			default:
				break;
			}
			// ��¼��Ŀ���
			Topic.append("("+ i +") "+ num1 + " " + symbol + " " + num2 + "\r\n");
			if (symbol.equals(" / ")) {
				Answer.append("(" + i + ") " + num1 +  " " + symbol + " " + num2 + " = " + result
						+ (yushu != 0 ? ("..." + yushu) : "") + "\r\n");
			} else {
				Answer.append("(" + i + ") " + num1 +  " " + symbol + " "  + num2 + " = " + result + "\r\n");
			}
			
		}
    }
    public static void writeTo() throws IOException {//�׳��쳣
    	  File file= new File("out.txt");
		if (!file.exists()) {
			//�ж��Ƿ���ڴ�·��
			File parent = file.getParentFile();
			if (parent != null && !parent.exists()) {
				parent.mkdirs();
			}
			file.createNewFile();
		}
		OutputStream out = new FileOutputStream(file);
		byte[] question=Topic.toString().getBytes();
		byte[] answer=Answer.toString().getBytes();
		out.write(question);
		out.write(System.lineSeparator().getBytes());
		out.write(answer);
		out.close();
    }
}
