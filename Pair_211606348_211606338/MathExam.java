package com.it666.course1;

import java.util.Arrays;

public class MathExam {
	public static void main(String[] args) {
		//����2���ַ�������ƴ����Ŀ�ʹ�
		StringBuffer strbuf = new StringBuffer();
		StringBuffer strbuf1 = new StringBuffer();
		//random�������������Ŀ�еĲ�����
		int random = 0;
		//�������������������
		String[] str = { "+", "-", "X", "��" };
		//��������ɵ�2-4�����������ostr����
		String[] ostr = new String[4];
		
		for(int i=0;i<10;i++) {
			//������ɵ�2-4�������
			int operator = (int)(Math.random()*3)+2;
			int k = 0;
			for (int j = 0; j < operator; j++) {
				k = (int)(Math.random()*4);
				ostr[j]=str[k];
			}
			for (int j = 0; j < operator+1; j++) {
				random = (int)(Math.random()*1001);
				strbuf.append(random+" ");
				if(j<operator)
				strbuf.append(ostr[j]+" ");
			}strbuf.append("\n");
			//���³�ʼ������
			ostr = new String[4];
			
		}System.out.println(strbuf.toString());
		
	}
}
