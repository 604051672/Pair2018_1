import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Arithmetic extends Operation{                                           // ����������
	

	
	public Arithmetic(String sPart) {
		setAnswerNumber(Integer.valueOf(arithmeticOne(sPart)));
	}
		
	public static  String arithmeticOne(String sPart) {                                     // �������һ����Ŀ   
		
		// (��������Ŀ��)ת���ַ������Ѷ���ַ��������������� 
		// �����ص�ǰ��Ŀ�Ĵ𰸸��� ������(FindBracket)
				
		final Matcher findNumber = Pattern.compile("(\\d+)").matcher(sPart);               // ��������ַ����е�����
		final Matcher findSymbol = Pattern.compile("[\\+\\-\\*\\/]").matcher(sPart);     //  ��������ַ����еķ���
			
		LinkedList<String> numberSet = new LinkedList<String>();                   // �����ֵļ���
		LinkedList<String> symbolSet = new LinkedList<String>();					 // ����ŵļ���
	
		while(findNumber.find()) {													// ��ʼ���ַ����е�����
			numberSet.add(findNumber.group());  									// ���ҵ������ִ������ּ�����
		}
																							
		while(findSymbol.find()) {    													// ��ʼ���ַ����еķ���
			symbolSet.add(findSymbol.group());										// ���ҵ��ķ��Ŵ��ڷ��ż�����
		}
																								
		multiplicationAndDivision(numberSet,symbolSet);							// �ȳ˳���Ӽ�������
		addAndSubtract(numberSet,symbolSet);									// ���üӼ����� 
				
		 System.out.println(" ���㣺" + answerNumber);
		return numberSet.get(0);                									// �õ��˵�ǰ��Ŀ�����մ�
				
	}
	
	public static void multiplicationAndDivision(LinkedList<String> numberSet, LinkedList<String> symbolSet) {
		
		for (int i = 0 ; i < symbolSet.size(); i++) {
		//(Ŀ��) �������ż��ϣ�����ֻ����*��/���ţ���Ϊ�ȳ˳���Ӽ���
		// ƥ����һ�����ţ����������ѽ���������ּ��ϣ�Ȼ��ӷ��ż�����ɾ���˷���

			if (symbolSet.get(i).equals("*") || symbolSet.get(i).equals("/")) { 				// ��������*��/ʱ

				firstNumber = Integer.valueOf(numberSet.get(i)); 						// �����ּ����е�ǰλ�õ���
				secondNumber = Integer.valueOf(numberSet.get(i + 1)); 					// ���ҵ��ڶ�����

				if (symbolSet.get(i).equals("*")) { 											// �жϵ�ǰ���ż��ϵķ��ţ���˷�
					answerNumber = firstNumber * secondNumber; 						// ������������answerNumber��
																																						
				} else if (symbolSet.get(i).equals("/")) { 									// �����
					answerNumber = firstNumber / secondNumber;
					
				}
				
				numberSet.set(i, String.valueOf(answerNumber)); 							// �����ּ��ϵĵ�i��λ�ã���Ӵ𰸣��Ḳ�ǵ�firstNumber
				numberSet.remove(i + 1); 													// ɾ�����ּ����е�secondNUmber
				symbolSet.remove(i); 														// ��ǰ�����Ѿ�������ɣ��ӷ��ż�����ɾȥ�˷��ţ����ٺ��ڱ������ϴ���
				i--; 																			// ��Ϊ��ǰλ�õķ����Ѿ�ɾ���ˣ����ϻ��Զ���ǰ���룬����Ҫ����һ�����ţ�����Ҫ��λ��-1
			}
		}
	}

	public static void addAndSubtract(LinkedList<String> numberSet, LinkedList<String> symbolSet) {
		
		for (int i = 0; i < symbolSet.size(); i++) { 										// ����Ӽ���ͬ��

			if (symbolSet.get(i).equals("+") || symbolSet.get(i).equals("-")) {
				
				firstNumber = Integer.valueOf(numberSet.get(i)); 					// �����ּ��ϵ���
				secondNumber = Integer.valueOf(numberSet.get(i + 1));

				if (symbolSet.get(i).equals("+")) { 										// ��ӷ�
					answerNumber = firstNumber + secondNumber;
																																	
				} else if (symbolSet.get(i).equals("-")) { 								// �����
					answerNumber = firstNumber - secondNumber;
																																											
				}
				
				numberSet.set(i, String.valueOf(answerNumber));
				numberSet.remove(i + 1); 												// ɾ����һ����
				symbolSet.remove(i);
				i--;
			}
		}
	}
	
}
