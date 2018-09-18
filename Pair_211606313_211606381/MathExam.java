import java.util.regex.Pattern;

public class MathExam {
	private static String errorMessage = "����Ĳ�����ʽ�����밴�� -n ���� -grade ����   ���� -grade ����   -n ����  ��ʽ���롣";
	private static String grade;

	public static void main(String[] args) {
		if(judgmentParameter(args)) {
			System.out.println("Сѧ" + grade + "�꼶��ѧ����Ŀ�����ɣ���鿴out.txt�ļ�");
		}else {
			System.out.println(errorMessage);
		}
	}

	/**
	 * 
	 * @param args �û�����Ĳ���
	 * @return     ������Ҫ��ʱ���� true�����򷵻�false
	 */
	private static boolean judgmentParameter(String[] args) {
		if(args.length < 4 || args.length > 4) {
			return false;
		}else {
			// 1  �ж��Ƿ����� -n �� -grade ��ʶ
			if(!(("-n".equals(args[0]) && "-grade".equals(args[2]) )|| ("-grade".equals(args[0]) && "-n".equals(args[2])))) {
				return false;
			}
			
			// 2 ȥ�����ֲ�����ǰ��0
			args[1] = args[1].replaceFirst("^0*", "");
			args[3] = args[3].replaceFirst("^0*", "");
			
			// 3 �������������Ƿ�������
			Pattern pattern = Pattern.compile("[0-9]*");
			boolean matches = pattern.matcher(args["-n".equals(args[0]) ? 1 : 3]).matches();
			
			if (matches && args["-n".equals(args[0]) ? 3 : 1].length() > 4) {
				errorMessage = "��Ŀ�����������������У��������";
				return false;
			} else if (!matches) {
				errorMessage = "��Ŀ�������������������������У�����һ��������";
				return false;
			}
			
			// 4 �����꼶�����Ƿ���1~3
			Pattern compile = Pattern.compile("[1-3]?");
			boolean matches2 = compile.matcher(args["-n".equals(args[0]) ? 3 : 1]).matches();
			
			if (!matches2) {
				errorMessage = "Ŀǰֻ֧��1~3�꼶�����������У�����1~3�е�һ������";
				return false;
			}
		}
		return true;
	}
}
