package com.java285.second;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MathExamLv2 {
	private Object[] stack;
	private int size;// Ԫ�ظ���;

	public MathExamLv2() {// Ĭ�ϳ���Ϊ10;
		this(10);
	}

	public MathExamLv2(int len) {// Ҳ�����Լ����ó���,������;
		stack = new Object[len];
	}

	public int size() {// ����Ԫ�ظ���;
		return size;
	}

	public int capacity() {// �������鳤��,������;
		return stack.length;
	}

	public void push(Object o) {// ��ջ;
		size++;
		stack[size - 1] = o;
	}

	public boolean isEmpty() {// �п�;
		return size == 0;
	}

	public Object pop() {// ��ջ;
		if (isEmpty()) {// ����Ҫ�п�;
			throw new ArrayIndexOutOfBoundsException("����Ϊ��");
		}
		Object o = stack[--size];
		stack[size] = null;
		return o;
	}

	private static void gradeOne(int n) throws IOException, FileNotFoundException {
		int[] sum = new int[n + 1];// ����sum�������������
		char[] signSet = { '+', '-' };// ����sianSet�������������ַ���
		Random random = new Random();

		for (int i = 1; i <= n; i++) {
			String str = "(" + i + ")";
			int a = random.nextInt(100) + 1;
			int b = random.nextInt(100) + 1;
			int t = random.nextInt(signSet.length);
			char sign = signSet[t];

			switch (sign) {
			case '+':
				sum[i] = a + b;
				break;
			case '-':
				sum[i] = a - b;
				if (sum[i] < 0) {
					i = i - 1;
					continue;
				}
				break;
			default:
				;
			}
			str = str + " " + a + " " + sign + " " + b + " " + "=";
			out(str);
			if (i == n) {
				out("----------------��׼��----------------");
			}
		} // ���forѭ��������ȷ��Ҫд�뵽out.txt�е�str��ֵ��Ҳ����Ҫ������Ŀ

		BufferedReader br = in();

		for (int i = 1; i <= n; i++) {
			String str = br.readLine() + " " + sum[i];
			out(str);
			if (i == n) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy��-MM��-dd�� HH:mm");// �������ڸ�ʽ
				out("211406285 ��־��" + " " + df.format(new Date()));
			}
		} // ���forѭ��������ȷ��Ҫд�뵽out.txt�е�str��ֵ��Ҳ����Ҫ������Ŀ�Ĵ�
	}

	private static void gradeTwo(int n) throws IOException, FileNotFoundException {
		String[] sum = new String[n + 1];// ����sum�������������
		char[] signSet = { '*', '/' };// ����sianSet�������������ַ���
		Random random = new Random();

		for (int i = 1; i <= n; i++) {
			String str = "(" + i + ")";
			int a = random.nextInt(100) + 1;
			int b = random.nextInt(100) + 1;
			int t = random.nextInt(signSet.length);
			char sign = signSet[t];

			switch (sign) {
			case '*':
				sum[i] = String.valueOf(a * b);
				sign = '��';
				break;
			case '/':
				if (b == 0 && a > b) {
					i = i - 1;
					continue;
				}
				sum[i] = String.valueOf(a / b);
				if (a % b != 0) {
					sum[i] = sum[i] + "..." + String.valueOf(a % b);
				}
				sign = '��';
				break;
			default:
				;
			}
			str = str + " " + a + " " + sign + " " + b + " " + "=";
			out(str);
			if (i == n) {
				out("----------------��׼��----------------");
			}
		} // ���forѭ��������ȷ��Ҫд�뵽out.txt�е�str��ֵ��Ҳ����Ҫ������Ŀ

		BufferedReader br = in();

		for (int i = 1; i <= n; i++) {
			String str = br.readLine() + " " + sum[i];
			out(str);
			if (i == n) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy��-MM��-dd�� HH:mm");// �������ڸ�ʽ
				out("211406285 ��־��" + " " + df.format(new Date()));
			}
		} // ���forѭ��������ȷ��Ҫд�뵽out.txt�е�str��ֵ��Ҳ����Ҫ������Ŀ�Ĵ�
	}

	private static void gradeThree(int n) throws IOException, FileNotFoundException {
		String[] sum = new String[n + 1];// ����sum�������������
		char[] signSet = { '*', '/' };// ����sianSet�������������ַ���
		Random random = new Random();

		for (int i = 1; i <= n; i++) {
			String str = "(" + i + ")";
			int a = random.nextInt(100) + 1;
			int b = random.nextInt(100) + 1;
			int t = random.nextInt(signSet.length);
			char sign = signSet[t];

			switch (sign) {
			case '*':
				sum[i] = String.valueOf(a * b);
				sign = '��';
				break;
			case '/':
				if (b == 0 && a > b) {
					i = i - 1;
					continue;
				}
				sum[i] = String.valueOf(a / b);
				if (a % b != 0) {
					sum[i] = sum[i] + "..." + String.valueOf(a % b);
				}
				sign = '��';
				break;
			default:
				;
			}
			str = str + " " + a + " " + sign + " " + b + " " + "=";
			out(str);
			if (i == n) {
				out("----------------��׼��----------------");
			}
		} // ���forѭ��������ȷ��Ҫд�뵽out.txt�е�str��ֵ��Ҳ����Ҫ������Ŀ

		BufferedReader br = in();

		for (int i = 1; i <= n; i++) {
			String str = br.readLine() + " " + sum[i];
			out(str);
			if (i == n) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy��-MM��-dd�� HH:mm");// �������ڸ�ʽ
				out("211406285 ��־��" + " " + df.format(new Date()));
			}
		} // ���forѭ��������ȷ��Ҫд�뵽out.txt�е�str��ֵ��Ҳ����Ҫ������Ŀ�Ĵ�
	}

	private static BufferedReader in() throws FileNotFoundException {
		File file = new File("out.txt");
		InputStreamReader in = new InputStreamReader(new FileInputStream(file));// ����һ���������Ķ���
		BufferedReader br = new BufferedReader(in);// ����һ��������󣬰��ļ�����ת���ɼ������ʶ��Ķ���
		return br;
	}

	private static void out(String str) throws IOException {
		File file = new File("out.txt");// ����һ���ļ���������ָ��һ���ļ�λ��
		file.createNewFile();// ��ָ��λ�ô���һ���ļ�
		BufferedWriter br = new BufferedWriter(new FileWriter(file, true));// ����һ��������󣬰��ļ�����ת���ɼ������ʶ��Ķ���
		br.write(str + "\r\n");
		br.flush();
		br.close();
	}

	private static void wrongJudgement(String[] args, int n, int grade) throws IOException, FileNotFoundException {
		if (args.length < 4) {
			System.out.println("�밴�� -n n -grade grade ���� -grade grade -n n�ķ�ʽ����4��������");
			return;
		}

		switch (args[0]) {
		case "-n": {
			if (args[1].length() > 8) {
				System.out.println("�������Ŀ����̫���ˣ�");
			}
			n = Integer.parseInt(args[1]);
			break;
		}
		case "-grade": {
			grade = Integer.parseInt(args[1]);
			if (grade < 1 || grade > 3) {
				System.out.println("gradeֻ��ȡ1~3��");
			}
			break;
		}
		default:
			System.out.println("�밴�� -n n -grade grade ���� -grade grade -n n�ķ�ʽ����4��������");
			break;
		}

		switch (args[2]) {
		case "-n": {
			if (args[1].length() > 8) {
				System.out.println("�������Ŀ����̫���ˣ�");
			}
			n = Integer.parseInt(args[3]);
			break;
		}
		case "-grade": {
			grade = Integer.parseInt(args[3]);
			if (grade < 1 || grade > 3) {
				System.out.println("gradeֻ��ȡ1~3��");
			}
			break;
		}
		default:
			System.out.println("�밴�� -n n -grade grade ���� -grade grade -n n�ķ�ʽ����4��������");
			break;
		}
		if (n != -1 && grade != -1) {
			if (grade == 1) {
				gradeOne(n);
			}
			if (grade == 2) {
				gradeTwo(n);
			}
			if (grade == 3) {
				gradeThree(n);
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		int n = -1, grade = -1;
		// ͨ���ж�args�����鳤�����жϲ����ĸ����Ƿ��������
		wrongJudgement(args, n, grade);
	}
}
