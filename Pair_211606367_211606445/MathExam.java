
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MathExam6367 {
	static Scanner input = new Scanner(System.in);
	static String filename ="out.txt";
	static String[] str = {"+","-","x","��"};
	static List<String> Calculation_problem = new ArrayList<String>();
	static int number1 = 0,Symbol = 0,number2 = 0,Sum = 0,
			Remainder = 0,number = -1,grade = -1,Calculation_Num = 0;			
	static Random ranNum = new Random(101);
	static Random ranStr = new Random(4);
	static String word = null,check_message = null;
	static File file = new File(filename),parent = null;
	static OutputStream out = null;
	static String[] args= {" "," "};
	static Pattern pattern = Pattern.compile("[0-9]*");
	public static void main(String[] arg) {
			
			args[0] = arg[0];args[1] = arg[1];
			file_Initialization(file);//�ĵ���ʼ������
			Input_Message();//������Ϣ���
			//���ɺϸ����Ŀ
			for (int i = 1; i <= number; i++) 
			{
				number1 = ranNum.nextInt(100);
				number2 = ranNum.nextInt(100);
				iteration_part(grade);//���ɺ��ʵļӼ��˳�����
				if(str[Symbol].equals("+") && number1 + number2 > 100
					|| str[Symbol].equals("-") && number1 - number2 < 0
					|| str[Symbol].equals("x") && number1 * number2 > 100
					|| str[Symbol].equals("��") && number2 == 0
					)
				{	
					i--;
					continue;
				}
				else
				{
					if(str[Symbol].equals("+"))
						Sum = number1 + number2;
					else if(str[Symbol].equals("-"))
						Sum = number1 - number2;
					else if(str[Symbol].equals("x"))
						Sum = number1 * number2;
					else
					{	
						Sum = number1 / number2;
						Remainder = number1 % number2;
					}
					file_write_problem(str, number1, number2, Sum, Symbol, out, i);//����Ŀд���ĵ�
				}
			}
			if(number!=0)
				file_write_answer();//����д���ĵ�
	}

	private static void iteration_part(int grade) {
		Symbol = ranStr.nextInt(4);
		if(grade==1 && Symbol<=1)
			return;
		else if(grade==2 && Symbol<=3)
			return;
		else
			iteration_part(grade);
	}

	private static void Input_Message() {
		int j=1;
		while(number<0)
		{
			if(j==1)
			{
				check_message =args[0];
				if(!pattern.matcher(check_message).matches())
				{	
					System.out.print("������������Ϸ�������������������");
				}
			}
			check_message = input.nextLine();
			if(!pattern.matcher(check_message).matches())
			{	
				System.out.print("������������Ϸ�������������������");
				continue;
			}
			number = Integer.parseInt(check_message);
			if(number < 0)
				System.out.print("������������Ϸ�������������������");
			
		}
		j=1;
		while(grade!=1 && grade!=2)
		{
			if(j==1)
			{
				check_message =args[1];
				if(!pattern.matcher(check_message).matches())
				{	
					System.out.print("������꼶���Ϸ��������������꼶��");
				}
			}
			check_message = input.nextLine();
			if(!pattern.matcher(check_message).matches())
			{
				System.out.print("������꼶���Ϸ�!�����������꼶(1��2)��");
				continue;
			}
			grade = Integer.parseInt(check_message);
			if(grade!=1 && grade!=2)
				System.out.print("������꼶���Ϸ�!�����������꼶(1��2)��");
		}
	}
	
	private static void file_write_answer() {
		try {
			out.write("\r\n".getBytes());
			for (int i = 0; i < Calculation_Num; i++)
			{
				out.write(Calculation_problem.get(i).getBytes());
			}
		} 
		catch (IOException e) {
			System.out.println("������д��������ʱ�쳣��"+e.getMessage());
		}
	}

	private static void file_write_problem(String[] str, int number1, int number2, int Sum, int Symbol, OutputStream out, int i) {
		try {
			word ="("+Integer.toString(i)+") "
					+Integer.toString(number1)+" "+str[Symbol]+" "+Integer.toString(number2);
			out.write((word+"\r\n").getBytes());
			if(Remainder!=0)
			{
				Calculation_problem.add(word+" = "+Integer.toString(Sum)+"..."+Integer.toString(Remainder)+"\n");
				Calculation_Num++;
				Remainder = 0;
			}
			else
			{	
				Calculation_problem.add(word+" "+Integer.toString(Sum)+"\r\n");
				Calculation_Num++;
			}
		} 
		catch (IOException e) 
		{
			System.out.println("������out.write()ʱ�׳��쳣"+e.getMessage());
		}
	}

	private static void file_Initialization(File file) {
		if(!file.exists()) 
		{		parent = file.getParentFile();
				if(parent !=null && !parent.exists())
				{	parent.mkdir();//����Ŀ¼
					System.out.println("����Ŀ¼��"+parent);
				}
				try {
					file.createNewFile();
				} catch (IOException e) {
					System.out.println("������file.createNewFile()ʱ�׳��쳣"+e.getMessage());
				}
				System.out.println("�������ļ���"+file.getAbsolutePath());
		}//��ȡ����·��
		try {
			out = new FileOutputStream(file);
		} 
		catch (FileNotFoundException e) {
			System.out.println("������new FileOutputStream(file)ʱ�׳��쳣"+e.getMessage());
		}
	}

}
