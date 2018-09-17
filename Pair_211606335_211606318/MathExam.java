
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MathExam {
	/*
 * 	��ԭ�е�V2.0.0�汾�ϼ������꼶������������
 *     1.ɾ��ԭ�еĹ��캯���Լ����캯�������أ�
 *     2.input���������˶��´����"-n"��"-grade"������ƥ���жϣ� 
 *     3.�޸Ķ��꼶��Ŀ���ɣ�ֻ��Գ˳���ѵ�����������Ӽ����㡣
 * 		coding �� GBK
 * 		MathExam_V2.0.1
 */	
	int firstNumber, secondNumber;		
	int symbol;	//��������ж�
	static int grade;
	static int count;		
	int result;		
	
	String[] str_ArithmeticProblem = new String[10000];	//���������
	String[] str_MathAnswer = new String[10000];	//�����Ŀ�ͱ�׼��
	
	public MathExam(String args0,String args1, String args2, String args3){
		inPut(args0, args1,args2,args3);
		mathProblem(count);
		outPut();
	}

	private void inPut(String str0,String str1, String str2, String str3) {
		// TODO Auto-generated method stub
		boolean flag1 = true;		//�ж���Ŀ�꼶����
		boolean flag2 = true;
		
		Scanner in = new Scanner(System.in);
		String regex1 = "0*[1-9]{1}\\d{0,3}";		//������ʽ�ж��������Ϊ����������
		String regex2 = "0*[1-2]{1}{0}";
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
			}
			
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
		str_ArithmeticProblem[i] = "( " + (i+1) +" ) " + n1 + " x " + n2;
		str_MathAnswer[i] = "( " + (i+1) +" ) " + n1 + " x " + n2 + " = " + result;
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
		new MathExam(args[0], args[1],args[2],args[3]);	
	}

}
