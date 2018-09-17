package com.java.Math;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class MathExam {
	static int a;
	static int b;
	static int sym;

	public static boolean checkOne() {
		a = (int) (0 + Math.random() * (100 - 0 + 1));
		b = (int) (0 + Math.random() * (100 - 0 + 1));
		// symΪ1��Ϊ��,2��Ϊ��
		sym = (int) (1 + Math.random() * (2 - 1 + 1));
		if (sym == 1) {
			if ((a + b) > 100) {
				return false;
			} else {
				return true;
			}
		} else {
			if ((a - b) < 0) {
				return false;
			} else {
				return true;
			}
		}
	}

	public static boolean checkTwo() {
		// symΪ1��Ϊ��,2��Ϊ��
		sym = (int) (1 + Math.random() * (2 - 1 + 1));
		if (sym == 1) {
			a = (int) (0 + Math.random() * (9 - 0 + 1));
			b = (int) (0 + Math.random() * (9 - 0 + 1));
			return true;
		} else {
			a = (int) (1 + Math.random() * (99 - 1 + 1));
			b = (int) (1 + Math.random() * (9 - 1 + 1));
			if (a < b) {
				return false;
			} else {
				if (a / b >= 10) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean checkThr() {
		// symΪ1��Ϊ��,2��Ϊ��
		if (sym == 1) {
			a = (int) (0 + Math.random() * (9 - 0 + 1));
			b = (int) (0 + Math.random() * (9 - 0 + 1));
			return true;
		} else {
			a = (int) (1 + Math.random() * (99 - 1 + 1));
			b = (int) (1 + Math.random() * (9 - 1 + 1));
			if (a < b) {
				return false;
			} else {
				if (a / b >= 10) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isNum(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static void mathOne(int n) {
		//����ŵ���Ŀ
		String prob;
		//������ŵ���Ŀ
		String prob1;
		String ans;
		ArrayList<String> probs = new ArrayList<String>();
		ArrayList<String> anss = new ArrayList<String>();
		boolean end = false;
		for (int i = 1; i <= n; i++) {
			do {
				end = checkOne();
			} while (!end);
			// symΪ1��Ϊ��,2��Ϊ��
			if (sym == 1) {
				prob1=a + " " + "+" + " " + b;
				prob = "(" + i + ")" + " " + a + " " + "+" + " " + b;
				
				ans = prob + " " + "=" + " " + Calculate.ans(prob1);
			} else {
				prob1=a + " " + "-" + " " + b;
				prob = "(" + i + ")" + " " + a + " " + "-" + " " + b;
				ans = prob + " " + "=" + " " + Calculate.ans(prob1);
			}
			probs.add(prob);
			anss.add(ans);

		}

		File file = new File("out.txt");
		if (!file.exists()) {
			File parent = file.getParentFile();
			if (parent != null && !parent.exists()) {
				parent.mkdirs();
			}
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("�ļ�����ʧ��,������");
			}
		}
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] data;
			for (String str : probs) {
				str += "\r\n";
				data = str.getBytes();
				try {
					out.write(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("д�����!");
				}
			}
			String fg = "-----------------��׼��-----------------" + "\r\n";
			data = fg.getBytes();
			try {
				out.write(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("д�����!");
			}
			for (String str : anss) {
				str += "\r\n";
				data = str.getBytes();
				try {
					out.write(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("д�����!");
				}
			}
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("io����");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("�Ҳ����ļ�,������");
		}
	}

	public static void mathTwo(int n) {
		String prob;
		String prob1;
		String ans;
		ArrayList<String> probs = new ArrayList<String>();
		ArrayList<String> anss = new ArrayList<String>();
		boolean end = false;
		for (int i = 1; i <= n; i++) {
			// ����
			do {
				end = checkTwo();
			} while (!end);
			if (sym == 1) {
				prob1=a + " " + "*" + " " + b;
				prob = "(" + i + ")" + " " + a + " " + "*" + " " + b;
				ans = prob + " " + "=" + " " + Calculate.ans(prob1);
			} else {
				prob = "(" + i + ")" + " " + a + " " + "/" + " " + b;
				if (a % b != 0) {
					ans = "(" + i + ")" + " " + a + " " + "/" + " " + b + " " + "=" + " " + (a / b) + "..." + (a % b);
				} else {
					prob1=a + " " + "/" + " " + b;
					ans = prob + " " + "=" + " " + Calculate.ans(prob1);
				}
			}
			probs.add(prob);
			anss.add(ans);
		}

		File file = new File("out.txt");
		if (!file.exists()) {
			File parent = file.getParentFile();
			if (parent != null && !parent.exists()) {
				parent.mkdirs();
			}
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("�ļ�����ʧ��,������");
			}
		}
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] data;
			for (String str : probs) {
				str += "\r\n";
				data = str.getBytes();
				try {
					out.write(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("д�����!");
				}
			}
			String fg = "-----------------��׼��-----------------" + "\r\n";
			data = fg.getBytes();
			try {
				out.write(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("д�����!");
			}
			for (String str : anss) {
				str += "\r\n";
				data = str.getBytes();
				try {
					out.write(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("д�����!");
				}
			}
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("io����");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("�Ҳ����ļ�,������");
		}
	}
	
	public static void mathThr(int n) {
		String prob;
		String ans;
		ArrayList<String> probs = new ArrayList<String>();
		ArrayList<String> anss = new ArrayList<String>();
		boolean end = false;
		for (int i = 1; i <= n; i++) {
			// 
			sym = (int) (1 + Math.random() * (2 - 1 + 1));
			do {
				end = checkTwo();
			} while (!end);
			if (sym == 1) {
				prob = "(" + i + ")" + " " + a + " " + "*" + " " + b;
				ans = "(" + i + ")" + " " + a + " " + "*" + " " + b + " " + "=" + " " + (a * b);
			} else {
				prob = "(" + i + ")" + " " + a + " " + "/" + " " + b;
				if (a % b != 0) {
					ans = "(" + i + ")" + " " + a + " " + "/" + " " + b + " " + "=" + " " + (a / b) + "..." + (a % b);
				} else {
					ans = "(" + i + ")" + " " + a + " " + "/" + " " + b + " " + "=" + " " + (a / b);
				}
			}
			probs.add(prob);
			anss.add(ans);
		}

		File file = new File("out.txt");
		if (!file.exists()) {
			File parent = file.getParentFile();
			if (parent != null && !parent.exists()) {
				parent.mkdirs();
			}
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("�ļ�����ʧ��,������");
			}
		}
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] data;
			for (String str : probs) {
				str += "\r\n";
				data = str.getBytes();
				try {
					out.write(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("д�����!");
				}
			}
			String fg = "-----------------��׼��-----------------" + "\r\n";
			data = fg.getBytes();
			try {
				out.write(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("д�����!");
			}
			for (String str : anss) {
				str += "\r\n";
				data = str.getBytes();
				try {
					out.write(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("д�����!");
				}
			}
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("io����");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("�Ҳ����ļ�,������");
		}
	}

	public static void main(String[] args) {
		if (args.length != 1 && args.length != 2) {
			System.out.println("����Ĳ�����������ȷ,������");
		} else if (args.length == 1) {
			String str1 = args[0];
			if (isNum(str1)) {
				if (str1.length() >= 5) {
					System.out.println("������������,������");
				} else {
					mathOne(Integer.parseInt(str1));
				}
			}
		} else if (args.length == 2) {

			String str1 = args[0];
			String str2 = args[1];
			if (isNum(str1)) {
				if (str1.length() >= 5) {
					System.out.println("������������,������");
				} else {
					if (!str2.equals("1") && !str2.equals("2")) {
						System.out.println("������꼶����,������");
					} else {
						if (str2 .equals("1")) {
							mathOne(Integer.parseInt(str1));
						} else {
							mathTwo(Integer.parseInt(str1));
						}
					}

				}
			} else {
				System.out.println("��������,����������");
			}
		}
	}
}
