import java.util.LinkedList;

public class FindBracket extends Operation{                            // ��ѽ��ѽ�����ţ�����

	static int sRight = 0; 											// ��¼�����ŵ�λ��
	static int sLeft = 0; 											// ��¼�����ŵ�λ��
	static String sPart = ""; 										// һ������Ŀ������������֮�����Ŀ
	static LinkedList<String> sInitialProblemSet;   					// ��ʼ��Ŀ���ϣ������������ţ��Ժ󷽱�ɾ�����ݣ�
	static String sProblem; 												// ���������������Ŀ���ַ���

	

	public  void findOne(String sProblem) {                              // �ҵ�һ��С���ţ�����
			
		LinkedList<String> sInitialProblemSet = new LinkedList<String>(); 		// ��ʼ��Ŀ���������������Ժ󷽱�ɾ�����ݣ�
				
		for(int i = 0; i < sProblem.length(); i++) {                    				// �ѽ��ܵ�����Ŀת�浽��ʼ��Ŀ�ļ�����
			sInitialProblemSet.add(String.valueOf(sProblem.charAt(i)));
		}
		
		for(int i = 0; i < sInitialProblemSet.size(); i++) { 						// �������ϣ��ҵ�һ��������
			
			
			if( sInitialProblemSet.get(i).equals(")")) {    					// ��ǰλ����������ʱ
					sRight = i;   												// ��¼�����ŵ�λ��
				
				for(int j = sRight-1; j >= 0; j--) {         						// �ӵ�ǰ��������ǰ�ҵ�һ��������
					
					if(sInitialProblemSet.get(j).equals("(")) {
						sInitialProblemSet.set(j, "!");						// �ı��־������
						sLeft = j;             									// ��¼������λ��
						break;
					}
				}
				
				for( int z = sLeft + 1; z < sRight; z++)							// ѭ���������ҳ���������֮�����
				{   						
					sPart = sPart + sInitialProblemSet.get(z);					// ���ҳ�������ƴ����������Ϊ��������Ŀ��һ����
					
				}
				
				@SuppressWarnings("unused")
				Arithmetic ar = new Arithmetic(sPart);						// ��sPart��Ϊ��������������������
																			
				sInitialProblemSet.set( sLeft, String.valueOf(getAnswerNumber()) );
																				
				for(int m = 1; m <= sRight-sLeft ; m++){
																
					sInitialProblemSet.remove(sLeft + 1);
					
				}
																				
				i = i - ( sRight - sLeft ); 								// ɾ����sRight-sLeft��Ԫ�أ�����i��λ��Ҳ�������ƶ�
						
										// ɾ��������i��λ��
				
			}//��ǰλ����������ʱ					
			
			sPart="";															// ��Ϊ��ƴ���ַ���������ÿ�ҵ�һ����������󣬾���Ҫ���ַ��������
		}// �ҵ�������
			
		for(int i = 0; i < sInitialProblemSet.size(); i++) {			// ����������ŵ�ʽ�Ӵ��������������
			sPart = sPart + sInitialProblemSet.get(i);
		}
		@SuppressWarnings("unused")
		Arithmetic arS = new Arithmetic(sPart);
																	
	}//findOne
	
	
}
