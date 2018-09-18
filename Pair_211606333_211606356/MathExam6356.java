import java.io.File;
import java.util.*;
import java.text.*;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;

//import com.sun.org.apache.xml.internal.security.Init;

public class MathExam6356 {
	static int n;
	static int grade;
	static int[] a;
	static int[] b;
	static int[] c;
	static String str[];
	static String AS[];
	static Random fuhao = new Random();
	static Random num1 = new Random();
	static Random num2 = new Random();// ������ͬ��Random�������ɵ������������һ���ġ�
	private static int i;

	public static void main(String[] args) throws FileNotFoundException {
		init(args);

	}

	private static void init(String[] input) throws FileNotFoundException {
		if (input.length == 0) {
			// Ĭ�ϲ���
			n = 100;
			grade = 3;
		} else if (input.length == 4) {
			if (input[0].equals("-n") && input[2].equals("-grade")) {
				try {
					n = Integer.parseInt(input[1]);
					grade = Integer.parseInt(input[3]);
				} catch (NumberFormatException e) {
					System.out.println("��������������");
				}

			} else if (input[2].equals("-n") && input[0].equals("-grade")) {
				try {
					n = Integer.parseInt(input[3]);
					grade = Integer.parseInt(input[1]);
				} catch (NumberFormatException e) {
					System.out.println("��������������");
				}

			}
		} else {
			System.out.println("������Ĳ����������������У�");
			System.exit(0);
		}
		if (grade == 1) {
			gradeOne();
		} else if (grade == 2) {
			gradeTwo();
		} else if (grade == 3) {
			gradeThree();
		} else {
			System.out.println("Ŀǰֻ֧��1-3�꼶������������");
		}
		File file = new File("out.txt");
		PrintStream ps = new PrintStream(file);
		System.setOut(ps);// �Ѵ����Ĵ�ӡ���������ϵͳ��
		outPut();
	}

	private static void gradeThree() {

		str = new String[n];
		AS = new String[n];
		for (int i = 0; i < n; i++) {
			int n1 = num1.nextInt(2) + 3;// ������ŵĸ���
			a = new int[n1];// ���������
			b = new int[n1 + 1];// ���ֵĸ���
			String fuhao[] = { "+", "-", "��", "��" };
			String fuhao1[] = new String[4]; // �жϷ���
			for (int j = 0; j < n1; j++) {
				a[j] = num1.nextInt(4);
				fuhao1[j] = fuhao[a[j]];// �������n1�������
			}

			for (int k = 0; k < n1 + 1; k++) {
				b[k] = num2.nextInt(100) + 1;// ��������������������n+1������
			}
			String[] str2 = new String[n1 + 1];
			int[] flag = new int[n1 + 1];// �������0(,1�޷���,2)
			for (int j = 0; j < n1 + 1; j++) {
				str2[j] = Integer.toString(b[j]);
				flag[j] = 1;
			}
			int front = -2;
			String ss = null;// ��������һ����ʽ��������Ϊһ������
			for (int x = 0; x < n1; x++) {
				if (fuhao1[x].equals("+") || fuhao1[x].equals("-")) {
					int n2 = (int) (0 + Math.random() * (2 - 1 + 1));// �������0��1������������ɼӼ���������
					boolean tag = false;
					if (n2 == 0) {
						if (front == x - 1) {
							if ((flag[x - 1] == 1 || flag[x - 1] == 0) && flag[x - 1] != 2 && !tag) {
								str2[x - 1] = "(" + str2[x - 1];
								flag[x - 1] = 0;
							} else {
								tag = true;
							}
							if ((flag[x + 1] == 1 || flag[x + 1] == 2) && flag[x + 1] != 0 && !tag) {
								str2[x + 1] = str2[x + 1] + ")";
								flag[x + 1] = 2;
							} else {
								tag = true;
							}
						} else {
							if ((flag[x] == 1 || flag[x] == 0) && flag[x] != 2 && !tag) {
								str2[x] = "(" + str2[x];
								flag[x] = 0;
							} else {
								tag = true;
							}
							if ((flag[x + 1] == 1 || flag[x + 1] == 2) && flag[x + 1] != 0 && !tag) {
								str2[x + 1] = str2[x + 1] + ")";
								flag[x + 1] = 2;
							} else {
								tag = true;
							}
							front = x;
						}

					}
				}
			}
			ss = str2[0];
			for (int j = 0; j < n1; j++) {
				ss = ss + fuhao1[j] + str2[j + 1];
			}
			str[i] = ss;
		}
		List<String> infixorder = null;
		for (int i = 0; i < str.length; i++) {
			infixorder = toInfixExpression(str[i]);// ������ʽ
			List<String> suffix = parseSuffixExpression(infixorder);// ����
			AS[i] = str[i] + "=" + answer(suffix);
		}

	}

	private static void gradeTwo() {
		a = new int[n];
		b = new int[n];
		c = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = num1.nextInt(9); // 0-100�������
			b[i] = num2.nextInt(9);
			c[i] = fuhao.nextInt(2);// 0�ǳ˷�1�ǳ���
			if (c[i] == 1 || b[i] == 0) {// ������0�������
				b[i] = num2.nextInt(8) + 1;
			}
			if (c[i] == 0 || (a[i] == 0 && b[i] == 0)) {
				a[i] = num2.nextInt(8) + 1;
				b[i] = num2.nextInt(8) + 1;
			}
		}

	}

	public static void outPut() {
		for (int i = 0; i < n; i++) {
			if (grade == 1) {
				if (c[i] == 0) {
					System.out.println("(" + (i + 1) + ") " + a[i] + "+" + b[i] + " =");
				} else {
					System.out.println("(" + (i + 1) + ") " + a[i] + "-" + b[i] + " =");
				}
			} else if (grade == 2) {
				if (c[i] == 0) {
					System.out.println("(" + (i + 1) + ") " + a[i] + "*" + b[i] + " =");
				} else {
					System.out.println("(" + (i + 1) + ") " + a[i] + "/" + b[i] + " =");
				}
			} else {

				System.out.println("(" + (i + 1) + ") " + str[i]);

			}
		}
		System.out.println("--------------��׼��----------------");
		for (int i = 0; i < n; i++) {
			if (grade == 1) {
				if (c[i] == 0) {
					System.out.println("(" + (i + 1) + ") " + a[i] + "+" + b[i] + " =" + " " + (a[i] + b[i]));
				} else {
					System.out.println("(" + (i + 1) + ") " + a[i] + "-" + b[i] + " =" + " " + (a[i] - b[i]));
				}
			} else if (grade == 2) {
				if (c[i] == 0) {
					System.out.println("(" + (i + 1) + ") " + a[i] + "*" + b[i] + " =" + " " + (a[i] * b[i]));
				} else if (a[i] % b[i] == 0) {
					System.out.println("(" + (i + 1) + ") " + a[i] + "/" + b[i] + " =" + " " + (a[i] / b[i]));
				} else {
					System.out.println("(" + (i + 1) + ") " + a[i] + "/" + b[i] + " =" + " " + (a[i] / b[i]) + "..."
							+ (a[i] % b[i]));

				}
			} else {
				System.out.println("(" + (i + 1) + ") " + AS[i]);
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� HH:mm");
		String time = sdf.format(new Date());
		System.out.println("211606356 ���� 211606333��־�� " + time);// ���ѧ������ʱ��
	}

	public static void gradeOne() {
		a = new int[n];
		b = new int[n];
		c = new int[n];

		for (int i = 0; i < n; i++) {
			a[i] = num1.nextInt(100);// 0-100�������
			b[i] = num2.nextInt(100);
			c[i] = fuhao.nextInt(2);
		}
		for (int i = 0; i < n; i++) {
			if (c[i] == 0) {// ����ӷ�
				while (a[i] + b[i] > 100) {
					// �������
					a[i] = num1.nextInt(100);
					b[i] = num2.nextInt(100);
				}

			} else {// �������
				while (a[i] - b[i] < 0) {
					// �����С��0�������
					a[i] = num1.nextInt(100);
					b[i] = num2.nextInt(100);
				}
			}

		}
	}

	public static List<String> parseSuffixExpression(List<String> ls) {
		Stack<String> s1 = new Stack<String>();
		List<String> LS = new ArrayList<String>();
		for (String ssr : ls) {
			if (ssr.matches("\\d+")) {
				LS.add(ssr);
			} else if (ssr.equals("(")) {
				s1.push(ssr);
			} else if (ssr.equals(")")) {
				while (!s1.peek().equals("(")) {
					LS.add(s1.pop());
				}
				s1.pop();
			} else {
				Operation operation = new Operation();
				while (s1.size() != 0 && operation.getValue(s1.peek()) >= operation.getValue(ssr)) {
					LS.add(s1.pop());
				}
				s1.push(ssr);
			}
		}
		while (s1.size() != 0) {
			LS.add(s1.pop());
		}
		return LS;
	}

	public static List<String> toInfixExpression(String s) {
		List<String> infixorder = new ArrayList<String>();// �洢������ʽ
		int w = 0;
		String ssr;
		char c;
		do {
			if ((c = s.charAt(w)) < 48 || (c = s.charAt(w)) > 57) {
				infixorder.add("" + c);
				w++;
			} else {
				ssr = "";
				while (w < s.length() && (c = s.charAt(w)) >= 48 && (c = s.charAt(w)) <= 57) {
					ssr += c;
					w++;
				}
				infixorder.add(ssr);
			}

		} while (w < s.length());
		return infixorder;
	}

	public static int answer(List<String> last) {
		Stack<String> s = new Stack<String>();
		for (String ssr : last) {
			if (ssr.matches("\\d+")) {
				s.push(ssr);
			} else {
				int b = Integer.parseInt(s.pop());
				int a = Integer.parseInt(s.pop());
				int result = 0;
				if (ssr.equals("+")) {
					result = a + b;
				} else if (ssr.equals("-")) {
					result = a - b;
				} else if (ssr.equals("��")) {
					result = a * b;
				} else if (ssr.equals("��")) {
					result = a / b;
				}
				s.push("" + result);
			}
		}
		return Integer.parseInt(s.pop());
	}

	public static class Operation {// ���ȼ�
		private int ADDITION = 1;
		private int SUBTRACTION = 1;
		private int MULTIPLICATION = 2;
		private int DIVISION = 2;

		Operation() {

		}

		public int getValue(String operation) {
			int result;
			switch (operation) {
			case "+":
				result = ADDITION;
				break;
			case "-":
				result = SUBTRACTION;
				break;
			case "��":
				result = MULTIPLICATION;
				break;
			case "��":
				result = DIVISION;
				break;
			default:
				// System.out.println("�����ڸ������");
				result = 0;
			}
			return result;
		}
	}

}
