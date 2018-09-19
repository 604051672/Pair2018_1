/**
 * ʹ��Tab�����������ֶ��ո�
 * �����ַ������Ʋ�����120��
 * ������ע�ͣ�ÿ�����ܻس�һ�н���ע��
 * ���˹��������Ƿ���Ϲ淶
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class MathExam {
//	�����
	static char sign[]= {'+','-','*','/'};
//	�����Ŀ
	static List<String> list=new ArrayList<String>();	
//	��Ŵ�
	static List<Integer> answer=new ArrayList<Integer>();
//	������Ŀ
	static int N=0;
	static int M=1;
	static int O=1;
	static double sum1,sum2;
	
	static Random random=new Random();
	
	static List<String> RPN;
//	����һ��stack��¼�����
	static Stack<String> ops = new Stack<String>();
//	�������һ�³�ʼ��������
//	����һ��op�����ȼ�
	public static int priorityInfo(String symbol) {
	    HashMap<String, Integer> precedence = new HashMap<>();
	    precedence.put("(", 2);  
	    precedence.put(")", 2);
	    precedence.put("+", 0);  
	    precedence.put("-", 0);
	    precedence.put("*", 1);
	    precedence.put("/", 1);
	    return precedence.get(symbol);
	    }
	public static boolean isNumber(String s) {
	    if (s.equals("(") || s.equals(")") || s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
	        return false;
	    return true;
	}
	 
	public static double eval(String op, String val1, String val2) {
	    if (op.equals("+")) return Double.parseDouble(val1) + Double.parseDouble(val2);
	    if (op.equals("-")) return Double.parseDouble(val1) - Double.parseDouble(val2);
	    if (op.equals("/")) return Double.parseDouble(val1) / Double.parseDouble(val2);
	    if (op.equals("*")) return Double.parseDouble(val1) * Double.parseDouble(val2);
	 
	    throw new RuntimeException("Invalid operator");
	}
//	���潫��׺���ʽת��Ϊ��׺���ʽ
	/**
	 * ��׺���ʽ���ɺ�׺���ʽ
	 *
	 * @param in
	 */
	
	public static void fixPosition(String[] in) {
	    for (int i = 0; i < in.length; i++) {
	        if (isNumber(in[i])) {
	            //���������,ֱ�ӽ�������
	            RPN.add(in[i]);
	        } else {
	 
	            if (ops.isEmpty()) {
	                ops.push(in[i]);
	            } else if (in[i].equals("(")) {
	                ops.push(in[i]);
	            } else if (in[i].equals(")")) {
	                //���ų�ջ
	                bracketsPop();
	            } else {
	                //�����Ҫ��ӵ����ȼ�С�ڻ��ߵ���ջ�������ȼ� ��ջ�������ȼ���ջ �µ�Ԫ����ջ,����Ļ� �µ�Ԫ�ؽ�ջ
	                int newpriority = priorityInfo(in[i]);
	                int stackTopPriority = priorityInfo(ops.peek());
	                //��Ҫ�ų�ջ��Ԫ�ز�������
	                if (stackTopPriority != 2 && newpriority <= stackTopPriority) {
	                    RPN.add(ops.pop());
	                }
	                ops.push(in[i]);
	            }
	        }
	    }
	//forѭ��ִ����ϣ���op  stackʣ���op�Ƶ�list��
	    while (!ops.isEmpty())
	        RPN.add(ops.pop());
	}
	private static void bracketsPop() {
	 
	    while (!ops.peek().equals("(")) {
	        RPN.add(ops.pop());
	    }
	    //�����Ҫ�����ϲ��"("��ȥ��
	    ops.pop();
	}
	private static void calculate() {
	    for (int i = 0; i < RPN.size(); i++) {
	        if (!isNumber(RPN.get(i))) {
	            //��Ҫ��ǰ���������ֽ�������,��������
	        	try {
					double newTmp = eval(RPN.get(i), RPN.get(i - 2), RPN.get(i - 1));
		            RPN.remove(i);
		            RPN.remove(i - 1);
		            RPN.remove(i - 2);
		            RPN.add(i - 2, newTmp + "");
		            if (RPN.size() == 1) {
//		                System.out.println(RPN.get(0));
		                sum1=Double.parseDouble(RPN.get(0));
		                return;
		            }
				} catch (Exception e) {
					// TODO: handle exception
						sum1=Double.NEGATIVE_INFINITY;
					return;
				}
	            break;
	        }
	    }
	    calculate();
	}	

	
//	���ɲ����������
	public static int createNumber(int S) {
//		��Random��nextInt���������������
//		S��ʾ����������ķ�Χ
		return random.nextInt(S);
	}
//	������Ŀ
	public static void createProblem() {
//		����pro_num������Ŀ�ͷ��ϣ��������ַ�������list����
//		��ͨ���沨���㷨������Ŀ�Ľ��������answer����
//		ͨ��N�����Ƴ�����Ŀ
		String str;
		for(int i=0;i<N;i++) {
			while(true) {
				if(O>2 && createNumber(5)==0) {
					str=brackets();
				}else {
					str=nubrackets();
				}
				RPNcalculate(str);
				sum2=(int)sum1;
				if(sum1==sum2 && sum1>=0.0) {
					break;
				}
			}
			list.add(str);
			answer.add((int)sum1);
//			System.out.println(str+"= "+(int)sum1);
		}
		createFile("out");
		waterFile("out");
	}
	public static String brackets() {
		int i;
		int size=random.nextInt(1)+2;
		int start=random.nextInt(3);
		String str="";
		for(i=0;i<start+size+1;i++) {
			if(i==start) {
				str+="( ";
			}
			str=str+random.nextInt(100)+" ";
			if(i==start+size) {
				str+=") ";
			}
			if(i<=start || i>=start+size) {
				str=str+sign[random.nextInt(1)+2]+" ";
			}else {
				str=str+sign[random.nextInt(4)]+" ";
			}
			
		}
		str=str+random.nextInt(100)+" ";
		if(i==start+size) {
			str+=") ";
		}
		
//		System.out.println(str);
		return str;
	}
	public static String nubrackets() {
		int T=random.nextInt(3)+2;
		if(O<3) {
			T=1;
		}
		String str="";
		for(int i=0;i<T;i++) {
			str=str+random.nextInt(100)+" ";
			str=str+sign[random.nextInt(M)]+" ";
		}
		str=str+random.nextInt(100)+" ";
		
//		System.out.println(str);
		return str;
	}
	
	
//	�����ļ�
	public static void createFile(String name) {
		String filename=name+".txt";
		File file=new File(filename);
		if(!file.exists()) {
			System.out.println("�ļ������ɹ�");
		}else {
			System.out.println("�ļ��Ѵ���");
		}
	}
//	д���ļ�
	public static void waterFile(String name) {
		/* ���ļ�д������� */
		PrintStream pS=null;
		try {
			pS = new PrintStream(new FileOutputStream(name+".txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("�����ļ�ʧ��");
			e.printStackTrace();
		}
		/* ��Ŀ */
		for (int i = 0; i < N; i++) {
			pS.println("("+(i+1)+") "+list.get(i));
		}
		pS.println("");
		System.out.println("");
		/* ��׼�� */
		for (int i = 0; i < N; i++) {
			pS.println("("+(i+1)+") "+list.get(i)+" = "+answer.get(i));
		}
		pS.close();
	}
	
	
//	�����沨���㷨������
	public static void RPNcalculate(String str) {
		RPN = new ArrayList<String>();
		String[] RPNStr= str.split(" ");
		fixPosition(RPNStr);
		calculate();
	}
	public static void main(String[] args) {
		String num="0";
		String grade="1";
		for(int i=0;i<args.length;i++) {
			if(args[i].equals("-n")) {
				num=args[i+1];
			}
			if(args[i].equals("-grade")) {
				grade=args[i+1];
			}
		}
		if(!num.equals("0") && num.matches("0*[0-9]{0,3}") && grade.matches("[1-3]")) {
			N=Integer.parseInt(num);
			O=Integer.parseInt(grade);
			if(O==1) {
				M=2;
			}else {
				M=4;
			}
			createProblem();
		}else {
			System.out.println("���������Ҫ�������������Ŀ����");
		}
	}
}
