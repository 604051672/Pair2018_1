import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Arithmetic {        //����������

	
	public static void test() {   
		//ת���ַ������Ѷ���ַ���������������
		String str="1*9-9+695/865";
		//56/8-6*8+6
		//35-69+10*5/8
		//8+9*8*8/6
		//1-2+3+4
		Matcher m=Pattern.compile("(\\d+)").matcher(str); //������
		Matcher m2=Pattern.compile("[\\+\\-\\*\\/]").matcher(str); // �ҷ���
		
		int firstNumber=0;
		int secondNumber=0;
		int answerNumber=0;
		
		LinkedList<String> li=new LinkedList<String>(); // ������
		LinkedList<String> li2=new LinkedList<String>();//�����


		int s;
		
		while(m.find()) {			
			li.add(m.group());  //�����ֻ��ߴ�����ڼ�����
		}
		System.out.println(li);
	
		while(m2.find()) {    //�����
			li2.add(m2.group());				
		}
		System.out.println(li2);
		
		for(int i=0;i<li2.size();i++) {
			
			if(li2.get(i).equals("*") || li2.get(i).equals("/")) {
				
				firstNumber=Integer.valueOf(li.get(i));               //�����ּ��ϵ���
				secondNumber=Integer.valueOf(li.get(i+1)); 
				
				if(li2.get(i).equals("*")) {           //��˷�
					answerNumber=firstNumber*secondNumber;
					
				}
				else if(li2.get(i).equals("/")) {      //�����
					answerNumber=firstNumber/secondNumber;
					
				}
				li.set(i, String.valueOf(answerNumber));
				li.remove(i+1);//ɾ����һ����
				li2.remove(i);
				i--;
			}
		}
		
		for(int i=0;i<li2.size();i++) {
			
			if(li2.get(i).equals("+") || li2.get(i).equals("-")) {
				
				firstNumber=Integer.valueOf(li.get(i));               //�����ּ��ϵ���
				secondNumber=Integer.valueOf(li.get(i+1)); 
				
				if(li2.get(i).equals("+")) {           //��ӷ�
					answerNumber=firstNumber+secondNumber;
					
				}
				else if(li2.get(i).equals("-")) {      //�����
					answerNumber=firstNumber-secondNumber;
					
				}
				li.set(i, String.valueOf(answerNumber));
				li.remove(i+1);//ɾ����һ����
				li2.remove(i);
				i--;
			}
		}
		System.out.println(li);
		System.out.println(li2);
		
		
	}
}
