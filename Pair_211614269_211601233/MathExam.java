
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

	public static boolean isNum(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static void mathOne(int n) {
		// ����ŵ���Ŀ
		String prob;
		// ������ŵ���Ŀ
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
				prob1 = a + " " + "+" + " " + b;
				prob = "(" + i + ")" + " " + a + " " + "+" + " " + b;

				ans = prob + " " + "=" + " " + Calculate.ans(prob1);
			} else {
				prob1 = a + " " + "-" + " " + b;
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
				prob1 = a + " " + "*" + " " + b;
				prob = "(" + i + ")" + " " + a + " " + "*" + " " + b;
				ans = prob + " " + "=" + " " + Calculate.ans(prob1);
			} else {
				prob = "(" + i + ")" + " " + a + " " + "/" + " " + b;
				if (a % b != 0) {
					ans = "(" + i + ")" + " " + a + " " + "/" + " " + b + " " + "=" + " " + (a / b) + "..." + (a % b);
				} else {
					prob1 = a + " " + "/" + " " + b;
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
		System.out.println("���꼶");
		System.out.println(n + "��");
	}

	public static void main(String[] args) {
		if (args.length != 4) {
			System.out.println("�������");
		} else {
			if (args[0].equals("-n") && args[2].equals("-grade") || args[0].equals("-grade") && args[2].equals("-n")) {
				if (args[0].equals("-n")) {
					// �޶�����Ϊ0-999
					if (isNum(args[1]) && args[1].length() <= 3 && isNum(args[3]) && args[3].length() == 1
							&& Integer.parseInt(args[3]) > 0 && Integer.parseInt(args[3]) <= 3) {
						if (args[3].equals("1")) {
							mathOne(Integer.parseInt(args[1]));
						} else if (args[3].equals("2")) {
							mathTwo(Integer.parseInt(args[1]));
						} else {
							mathThr(Integer.parseInt(args[1]));
						}
					} else {
						System.out.println("�������");
					}
				} else {
					if (isNum(args[3]) && args[3].length() <= 3 && isNum(args[1]) && args[1].length() == 1
							&& Integer.parseInt(args[1]) > 0 && Integer.parseInt(args[1]) <= 3) {
						if (args[1].equals("1")) {
							mathOne(Integer.parseInt(args[3]));
						} else if (args[1].equals("2")) {
							mathTwo(Integer.parseInt(args[3]));
						} else {
							mathThr(Integer.parseInt(args[3]));
						}
					} else {
						System.out.println("�������");
					}
				}
			} else {
				System.out.println("�������");
			}
		}

	}
}
