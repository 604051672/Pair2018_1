
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream.PutField;
import java.io.OutputStream;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.omg.CORBA.PUBLIC_MEMBER;


public class MathExam {
	/*
 * 	��ԭ�е�V2.0.0�汾�ϼ������꼶������������
 *     1.ʵ�������꼶��Ŀ�ʹ�������к�
 *     ps����δ�������Ϊ0�Ͳ�ֵΪ������bug��
 * 		coding �� GBK
 * 		MathExam_V2.0.7
 */	
	int firstNumber, secondNumber;		
	int symbol;	//��������ж�
	static int grade;
	static int count;		
	int result;		
	String operator_Add;
	
	//���ȳ����沨����������
	Stack<String> operators = new Stack<String>();	//�洢������
	Stack<String>  operand= new Stack<String>();	//�洢������
	private static StringBuilder postfixExpression=new StringBuilder(); //չʾ���ʽ
	Stack<Integer> postfixNumber = new Stack<Integer>();	//�����׺���ʽʱ�洢����
	
	String[] str_ArithmeticProblem = new String[10000];	//���������
	String[] str_MathAnswer = new String[10000];	//�����Ŀ�ͱ�׼��
	
	public MathExam(String[] args){
		inPut(args[0], args[1],args[2],args[3]);
		mathProblem(count);
		outPut();
		
	}

	

	private void inPut(String str0,String str1, String str2, String str3) {
		// TODO Auto-generated method stub
		boolean flag1 = true;		//�ж���Ŀ�꼶����
		boolean flag2 = true;
		
		Scanner in = new Scanner(System.in);
		String regex1 = "0*[1-9]{1}\\d{0,3}";		//������ʽ�ж��������Ϊ����������
		String regex2 = "0*[1-3]{1}{0}";
		Pattern pattern1 = Pattern.compile(regex1);		//���������������ʽ�ı������
		Pattern pattern2 = Pattern.compile(regex2);
		Matcher matcher1,matcher2;		//���������жϲ�����������ʽ�Ĳ�������
		
		while (true) {		
			try {
				if(str0.equals("-n") && str2.equals("-grade")){		//����ƥ������Ĳ������ͣ�args[0] �� args[2] ������
					matcher1 = pattern1.matcher(str1);		
					matcher2 = pattern2.matcher(str3);
					flag1 = matcher1.matches();
					flag2 = matcher2.matches();
					if(!flag1 || !flag2){
						throw new NumberFormatException();
					} else {
						count = Integer.valueOf(str1);
						grade = Integer.valueOf(str3);
					}
				}else if(str0.equals("-grade") && str2.equals("-n")){		//regex1��3�ı��ʽ��ƥ��ɹ�
					matcher1 = pattern1.matcher(str3);
					matcher2 = pattern2.matcher(str1);
					flag1 = matcher1.matches();		//����ƥ��淶��
					flag2 = matcher2.matches();		//�꼶ƥ��淶��
					if(!flag1 || !flag2){		//��������������������ʽ�淶�ͽ����쳣
						throw new NumberFormatException();		
					} else {	
						count = Integer.valueOf(str3);
						grade = Integer.valueOf(str1);
					}
				} else {
					System.out.println("��������������󣬼����˳�����");
					System.exit(0);
				}				
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println("������������Ϲ淶�������˳�����");
				System.exit(0);
			}			
			in.close();
			break;
		}
	}
	
	private void outPut() {
		// TODO Auto-generated method stub
		File file = new File("out.txt");
		
		if(!file.exists()){	//�ж��ļ��Ƿ����
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("�ļ�����ʱ���ִ���!");
			}
		}
		
		try {
			String str = "\r\n";
			byte[] bytes = str.getBytes();		//string����ת��Ϊ�ܱ�����ʶ��Ķ�������
			
			FileOutputStream fos = new FileOutputStream(file);	//�ļ�д����
			for (int i = 0; i < count; i++) {
				byte[] b_ArithmeticProblem = str_ArithmeticProblem[i].getBytes();
				fos.write(b_ArithmeticProblem);
				fos.write(bytes);
			}
			fos.write(bytes);
			fos.flush();	//ˢ���ڴ�
			for (int i = 0; i < count; i++) {
				byte[] b_MathAnswer = str_MathAnswer[i].getBytes();
				fos.write(b_MathAnswer);
				fos.write(bytes);
			}
			fos.flush();
			fos.close();	//�ر��ļ������
		
			System.out.print("-------  ���ι�����" + count + "��Сѧ"+ grade + "�꼶�����⣬���out.txt�ļ��鿴����    -------"); 	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("δ�ҵ�ָ���ļ�!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("�ļ�д������!");
		} 	
	}
	
	//�ж��Ƿ������
    private static boolean isOperator(String operator){
        if (operator.equals("+")||operator.equals("-")||operator.equals("��")||operator.equals("��")
                ||operator.equals("(")||operator.equals(")")) {
            return true;
        }
        return false;
    }
	
	//��������������ȼ�
    private static int priority(String s){
        if (s.equals("+") || s.equals("-")) {
        	return 1;
        } else if (s.equals("��") || s.equals("��")) {
        	return 2;
        } else if(s.equals("(") || s.equals(")")){
        	return 3;
        } else{
        	return 0;
        }
    }
    
    //����һ�����
    private static int Calculation(int n,int m,String operator){
    	int result = -4567;
    	if (operator.equals("+")) {
    		result = n + m;
    	} else if (operator.equals("-")) {
    		result = n - m;
    	} else if (operator.equals("��")) {
    		result = n * m;
    	} else if (operator.equals("��")) {
    		result = n / m;
    	}
    	return result;
    }


	//���ȳ��㷨����[��׺���ʽת��׺���ʽ]
	private void toPostfixExpression(String str_mix){
		int len = str_mix.length();
		char c,nextChar;
		String sc;
		for (int i = 0 ; i <= len-1 ; i++) {
			c = str_mix.charAt(i); 
			sc = String.valueOf(c);
			if(isOperator(sc))	//�ж��Ƿ��ǲ�����
			{
				if(operators.isEmpty()){	//�ж�Ϊ��ջ����ջ
					operators.push(sc);
				} else {
					if(priority(operators.peek()) < priority(sc) && !sc.equals(")")){	
						//ջ�����������ȼ�С�ڵ�ǰ���������ȼ��Ҳ�������Ϊ�����ţ���ջ
						operators.push(sc);
					} else if(priority(operators.peek()) >= priority(sc) && !sc.equals(")")){
						while(!operators.empty() && !operators.peek().equals("(")	//ջ��Ϊ�գ���ǰջ����������Ϊ������
								&& priority(operators.peek()) >= priority(sc)){		//���������ȼ�С�ڵ��ڵ�ǰջ�����������ȼ�
							do {
								operator_Add = operators.pop();
								postfixExpression.append(operator_Add);
								operand.push(operator_Add);
							} while (false);	}	// ջ����������������ʱֹͣѹջ
						operators.push(sc);		//����ֱ����ջ
					} else if(sc.equals(")")){	//��ǰɨ�赽�Ĳ�����Ϊ������(������ջ����)������ѹջ��ƥ�������������
						do {
							operator_Add = operators.pop();
							postfixExpression.append(operator_Add);
							operand.push(operator_Add);
						} while (!operators.peek().equals("("));
						operators.pop();	//����ջ�����ò�����������
					}
				}
			}else {	//�ǲ�����
				if(!sc.equals(" ")){
					postfixExpression.append(sc);
					operand.push(sc);					
				}
			}
		}
		while(!operators.empty()){	//�����ַ���ɨ����������ջ��Ϊ������ѹջ
			operator_Add = operators.pop();
			postfixExpression.append(operator_Add);
			operand.push(operator_Add);
		}
	}
	
	
	//�沨������
	private int reversePolish() {
		// TODO Auto-generated method stub
		char c;
		int len = postfixExpression.toString().length();
		for (int i = 0; i < len; i++) {
			c = postfixExpression.charAt(i);
			if(!isOperator(String.valueOf(c))){	//�жϷǲ���������ջ
				postfixNumber.push(Integer.parseInt(String.valueOf(c)));
			} else{
				int m = postfixNumber.pop();
				int n = postfixNumber.pop();
				String operator = String.valueOf(c);
				postfixNumber.push(Calculation(n, m, operator));	
			}
		}
		return postfixNumber.pop();
	}
	
	//����������
	private void mathProblem(int count) {	
		Random rnd = new Random();
		
		for (int i = 0; i < count; i++) {
			symbol = rnd.nextInt(2);
			firstNumber = (int)(Math.random()*20+1);
			secondNumber = (int)(Math.random()*20+1);
			
			if(grade == 1){
				switch (symbol) {
				case 0:
					add(firstNumber,secondNumber,i);
					break;
					
				case 1:
					sub(firstNumber,secondNumber,i);
					break;
					
				default:
					break;
				}
			} else if(grade == 2){
				switch (symbol) {
				case 0:
					mul(firstNumber,secondNumber,i);
					break;
					
				case 1:
					div(i);
					break;
					
				default:
					break;
				}
			}else{
				fourMixed();
			}	
		}
	}
	
	//�������㣺��������������ܹ�2-4��������
	private void fourMixed() {
		// TODO Auto-generated method stub
		int whereBrackets = (int)(Math.random());	//������������Ϊλ��
		int howManyNum = (int)(Math.random()*3+3);
		StringBuilder bf_sequence;
		for (int j = 0; j < count; j++) {
			int n1 = (int)(Math.random()*10);
			int n2 = (int)(Math.random()*10);
			int n3 = (int)(Math.random()*10);
			int n4 = (int)(Math.random()*10);
			int n5 = (int)(Math.random()*10);
			int c1 = (int)(Math.random()*4);
			int c2 = (int)(Math.random()*4);
			int c3 = (int)(Math.random()*4);
			int c4 = (int)(Math.random()*4);
			String[] cs = {"+","-","��","��"};
			if(howManyNum==3) {
			str_ArithmeticProblem[j] = n1 + " " + cs[c1] + " " + n2 + " " + cs[c2] + " " + n3;
			toPostfixExpression(str_ArithmeticProblem[j]);
			str_MathAnswer[j] ="( " + (j+1) + " ) " + n1 + " " + cs[c1] + " " + n2 + " " + cs[c2] + " " + n3 + " = " + reversePolish();
			} else if(howManyNum==4) {
				str_ArithmeticProblem[j] = n1 + " " + cs[c1] + " " + n2 + " " + cs[c2] + " " + n3 + " " + cs[c3] + " " + n4;
				toPostfixExpression(str_ArithmeticProblem[j]);
				str_MathAnswer[j] ="( " + (j+1) + " ) " + n1 + " " + cs[c1] + " " + n2 + " " + cs[c2] + " " + n3 + " " + cs[c3] + " " + n4 + " = " + reversePolish();
			}else if(howManyNum==5) {
				str_ArithmeticProblem[j] = n1 + " " + cs[c1] + " " + n2 + " " + cs[c2] + " " + n3 + " " + cs[c3] + " " + n4 + " " + cs[c4] + " " + n5;
				toPostfixExpression(str_ArithmeticProblem[j]);
				str_MathAnswer[j] ="( " + (j+1) + " ) " + n1 + " " + cs[c1] + " " + n2 + " " + cs[c2] + " " + n3 + " " + cs[c3] + " " + n4 + " " + cs[c4] + " " + n5 + " = " + reversePolish();
			}
			bf_sequence = new StringBuilder("( " + (j+1) + " ) ") ;	//����Ŀ������к�
			str_ArithmeticProblem[j] = bf_sequence.append(str_ArithmeticProblem[j]).toString();
		}
	}
	
/*
	 * �ӷ���
	 *  1.һ���꼶�ļӷ�������������20���ڡ�
	 * 
*/
	private void add(int n1, int n2,int i) {
		// TODO Auto-generated method stub
		result = n1 + n2;
		str_ArithmeticProblem[i] = "( " + (i+1) +" ) " + n1 + " + " + n2;
		str_MathAnswer[i] = "( " + (i+1) +" ) " + n1 + " + " + n2 + " = " + result;
	}
	
	/*
	 * ������
	 * 
	 * 1.һ���꼶����֮��Ӧ���ڴ���0��
	 * 2.�������ͼ�����20���ڡ�
*/
	private void sub(int n1, int n2,int i) {
		// TODO Auto-generated method stub
		if (n1 < n2) {		//��Ϊ������������ֵ
			int num;
			num = n1;
			n1 = n2;
			n2 = num;
		}
		result = n1 - n2;
		str_ArithmeticProblem[i] = "( " + (i+1) +" ) " + n1 + " - " + n2;
		str_MathAnswer[i] = "( " + (i+1) +" ) " + n1 + " - " + n2 + " = " + result;
	}
	
	/*
	 * �˷���
	 * 
	 * 1.һ���꼶�ĳ˷�����Ӧ����0-9���ڡ��žų˷�����
	 * 
*/
	private void mul(int n1, int n2,int i) {
		// TODO Auto-generated method stub
		if (n1 > 9) {
			n1 = (int)(Math.random()*10);
		} 
		if (n2 > 9) {
			n2 = (int)(Math.random()*10);
		}
		result = n1 * n2;
		str_ArithmeticProblem[i] = "( " + (i+1) +" ) " + n1 + " �� " + n2;
		str_MathAnswer[i] = "( " + (i+1) +" ) " + n1 + " �� " + n2 + " = " + result;
	}
	
	/*
	 * ������
	 * 
	 * 1.һ���꼶�ĳ�������Ӧ���ڡ��žų˷�����Χ���ڣ�
	 * 2.��ĸ����Ϊ��0����
	 * 
*/
	private void div(int i) {
		// TODO Auto-generated method stub
		while(true){
			int n1 = (int)(Math.random()*82);
			int n2 = (int)(Math.random()*81+1);
			if(n1 % n2 == 0){
				result = n1 / n2;
				str_ArithmeticProblem[i] = "( " + (i+1) +" ) " + n1 + " �� " + n2;
				str_MathAnswer[i] = "( " + (i+1) +" ) " + n1 + " �� " + n2 + " = " + result;
			}else if(n1 % n2 != 0){
				result = n1 / n2;
				str_ArithmeticProblem[i] = "( " + (i+1) +" ) " + n1 + " �� " + n2;
				str_MathAnswer[i] = "( " + (i+1) +" ) " + n1 + " �� " + n2 + " = " + result + "..." + (n1 % n2);
			}
			break;
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MathExam(args);	
	}

}
