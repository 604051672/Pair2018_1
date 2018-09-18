package com.it666.course1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class MathExam {
	public static void main(String[] args) throws Exception {
		//�����������ĸ���bug
		if(!check(args))return;
		int m = 0;
		int l = 1;
		String str = null;
		// ����2���ַ�������ƴ����Ŀ�ʹ�
		StringBuffer strbuf = new StringBuffer();
		StringBuffer strbuf1 = new StringBuffer();
		StringBuffer strbuf2 = new StringBuffer();
		StringBuffer strbuf3 = new StringBuffer();
		// random�������������Ŀ�еĲ�����
		int random = 0;
		// int flag=5;
		// �������������������
		String[] strop = { "+", "-", "x", "��" };
		// ��������ɵ�2-4�����������ostr����
		String[] ostr = new String[4];

		for (int i = 0; i <= 100; i++) {
			// ������ɵ�2-3�������
			int operator = (int) (Math.random() * 3) + 2;
			int k = 0;
			// �������������ţ�������2�ֲ�ͬ������ţ�
			for (int j = 0; j < operator; j++) {
				k = (int) (Math.random() * 4);
				ostr[j] = strop[k];
				if (operator == 2 && j == 1) {
					// ������ֻ��2��������������2���������һ����ͬ
					while (ostr[0] == ostr[1]) {
						k = (int) (Math.random() * 4);
						ostr[0] = strop[k];
					}
				}
			}
			int flag1 = 1;
			int flag = 1;
			for (int j = 0; j < operator + 1; j++) {
				if (operator == 4)
					// ����������+-���Զ��������������
					if (j < operator && (ostr[j].equals("+") || ostr[j].equals("-")) && flag == 1) {
						strbuf.append("( ");
						flag = 0;// �ж��Ƿ����������0�����ѽ�����
						flag1 = 0;// �ж��Ƿ���Լ���������
					}
				random = (int) (Math.random() * 101);
				if (j == operator) {
					// ���һ���������ĩβ���ӿո�
					if (flag == 0 && flag1 == 1) {
						strbuf.append(random + " )");
						flag1 = 0;
						flag = 100;
					} else {
						strbuf.append(random);
						if (flag == 0) {
							// �����һ�����������������
							flag1 = 1;
						}
					}

				} else {
					if (flag == 0 && flag1 == 1) {
						strbuf.append(random + " )" + " ");
						flag1 = 0;
						flag = 100;
					} else {
						strbuf.append(random + " ");
						if (flag == 0) {
							// ����һ��ѭ�����п��Լ���������
							flag1 = 1;
						}
					}
				}
				if (j < operator)
					strbuf.append(ostr[j] + " ");

			}
			l = 1;
			// ���³�ʼ������
			ostr = new String[4];
			MathExam mathExam = new MathExam();
			mathExam.Init();
			str = strbuf.toString();

			char[] charArray = str.toCharArray();
			double shuntYardAlgo = mathExam.ShuntYardAlgo(charArray);
			String vf = String.valueOf(shuntYardAlgo);
			boolean matches = vf.matches("\\d+.[0]?");
			if (matches) {

				// strbuf2����ƴ����Ŀ
				strbuf2.append("(" + m + ")" + " " + strbuf + "\n");
				// strbuf1����ƴ�Ӵ�
				strbuf1.append("(" + m + ")" + " " + strbuf + " " + "=" + " " + (int) shuntYardAlgo + "\n");
				m++;
				l = 0;
			}
			strbuf = new StringBuffer();
			if (l != 0)
				i--;
		}
		strbuf2.append("\n");
		strbuf2.append(strbuf1);
		System.out.println(strbuf2.toString());
	}

	public static boolean check(String[] args) {
		// �жϲ����ĳ����Ƿ�Ϊ4
		if (args.length > 4 || args.length < 4)
			return false;

		// �ж��Ƿ���-n ��-grade�ı�ʶ��
		if (!(("-n").equals(args[0]) && "-grade".equals(args[2]) || ("-n").equals(args[2]) && "-grade".equals(args[0])))
			return false;

		// �ų�-n������000001�������Ƶ����
		char[] allChar = args[1].toCharArray();
		char[] allChar1 = args[3].toCharArray();
		int k = 0;
		while (allChar[k++] == '0');
		args[1] = new String(allChar, k - 1, allChar.length - k + 1);
		k = 0;
		while (allChar1[k++] == '0');
		args[3] = new String(allChar1, k - 1, allChar1.length - k + 1);

		// �����Ŀ��������
		boolean matches;
		if (args[0].equals("-n")) {
			matches = args[1].matches("[0-9]*");
		} else {
			matches = args[3].matches("[0-9]*");
		}

		boolean matches1;
		// ����꼶�����Ƿ���1-3
		if (args[0].equals("-garde")) {
			matches1 = args[1].matches("[1-3]?");
		} else {
			matches1 = args[3].matches("[1-3]?");
		}
		if (!matches1)
			return false;

		if (matches && (args[1].length() > 4 || args[3].length() > 4)) {
			System.out.println("��Ŀ����̫���ˣ��������룡��");
			return false;
		} else {
			System.out.println("������������");
			return false;
		}

	}

	/*
	 * �����ǵ��ȳ��㷨���沨�����ʽ��ʵ��
	 * 
	 */
	class Node {

		double val;
		char opt;

		Node(Double val, Character opt) {
			this.val = val;
			this.opt = opt;
		}
	}

	Node[] node = new Node[10005];
	Character[] str = new Character[10005];
	HashMap<Character, Integer> mp = new HashMap<>();

	void Init() {
		mp.put('(', 0);
		mp.put('-', 1);
		mp.put('+', 1);
		mp.put('x', 2);
		mp.put('��', 2);
		mp.put('^', 3);
	}

	// �沨�����ʽ�ļ���
	double CalPoland(Node node[], int n) throws Exception {
		Stack<Double> s = new Stack<>();
		for (int i = 0; i < n; i++) {
			if (node[i].opt == ' ')
				s.push(node[i].val);
			else {
				double a = s.peek();
				s.pop();
				double b = s.peek();
				s.pop();
				switch (node[i].opt) {
				case '+':
					s.push(b + a);
					break;
				case '-':
					s.push(b - a);
					break;
				case 'x':
					s.push(b * a);
					break;
				case '��':
					// if (Math.abs(a) < 1e-9)
					// throw new Exception();
					s.push(b / a);
					break;
				case '^':
					s.push(Math.pow(b, a));
					break;
				}
			}
		}
		return s.peek();
	}

	// ���ȳ��㷨
	double ShuntYardAlgo(char str[]) throws Exception {
		Stack<Character> oper = new Stack<>();

		// inNum��ǵ�ǰ�Ƿ������������
		boolean inNum = true;
		// hasDot����Ƿ��Ѿ�����С����
		boolean hasDot = true;

		int p = 0; // ɨ��ָ��λ��
		int cnt = 0; // ���Ż������ּ�����
		int sign = 1; // ��ʾ��������

		while (p < str.length) {
			if (Character.isDigit(str[p]) || str[p] == '.') {
				if (inNum) {
					double val = 0;
					double w = 1;
					if (str[p] == '.') {
						hasDot = true;
						val = 0;
					} else {
						hasDot = false;
						val = str[p] - '0';
					}
					p++;
					while (p < str.length && (Character.isDigit(str[p]) || str[p] == '.')) {
						if (str[p] == '.') {
							if (hasDot)
								throw new Exception();
							hasDot = true;
						} else {
							if (hasDot) {
								w *= 0.1;
								val += (str[p] - '0') * w;
							} else
								val = val * 10 + str[p] - '0';
						}
						p++;
					}
					p--;
					node[cnt++] = new Node(val * sign, ' ');
					sign = 1;
					inNum = false;
				} else
					throw new Exception();
			} else {
				switch (str[p]) {
				case '(':
					oper.push(str[p]);
					break;
				case ')':
					while (!oper.empty() && oper.peek() != '(') {
						node[cnt++] = new Node(0.0, oper.peek());
						oper.pop();
					}
					if (oper.empty())
						throw new Exception();
					oper.pop();
					break;
				case '+':
				case '-':
				case 'x':
				case '��':
				case '^':
					if (inNum) {
						if (str[p] != '+' && str[p] != '-')
							while (str[p] == '+' || str[p] == '-') {
								if (str[p] == '-')
									sign *= -1;
								p++;
							}
						p--;
					} else {
						while (!oper.empty() && mp.get(str[p]) <= mp.get(oper.peek())) {
							node[cnt++] = new Node(0.0, oper.peek());
							oper.pop();
						}
						oper.push(str[p]);
						inNum = true;
					}
					break;
				}
			}
			p++;
		}
		while (!oper.empty()) {
			node[cnt++] = new Node(0.0, oper.peek());
			oper.pop();
		}
		return CalPoland(node, cnt);
	}
}
