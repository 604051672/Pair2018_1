import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindBracket {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ts();
	}

	public static void ts() {
		//
		String s1="(A+B)-(C+D)*(B+A)";  
		// ((A+B)/(C+D-B))*A
		//((A+B)*(C+D)-A)/B
		//(A+B)-(C+D)*(B+A)
		
		ArrayList<String> a1=new ArrayList<>();// ��ʼ��Ŀ���������������Ժ󷽱�ɾ�����ݣ�
		
		LinkedList<String> l1=new LinkedList<>();
		for(int i=0;i<s1.length();i++) {
			l1.add(String.valueOf(s1.charAt(i)));
		}
		
		/*
		 LinkedList<Integer> left=new LinkedList<>();
		ArrayDeque<Integer> right=new ArrayDeque<>();
		 */
		
		
		Matcher m=Pattern.compile("\\(").matcher(s1);
		Matcher m2=Pattern.compile("\\)").matcher(s1);
	
		int right=0; //�����ŵ�λ��
		int left=0; //�����ŵ�λ��
		
		for(int i=0;i<l1.size();i++) {
			if( l1.get(i).equals(")")) {
				right=i;
				for(int j=right-1;j>=0;j--) {
					if(l1.get(j).equals("(")) {
						l1.set(j, "!");
						left=j;
						break;
					}
				}
				
				for(int z=left;z<=right;z++) {
					System.out.print(l1.get(z));
					//l1.remove(z);						
				}
				System.out.println();
					
			}
		}
		
		
		
		/*
		while(m2.find()) {     //���������ţ���λ�󣬴ӵ�ǰλ������ɨ�裬�ҵ���һ��������
			
			                                                   //right.addLast(m2.start());
			right=m2.start();          //���ŵ�λ��
			for(int i=right-1;i>=0;i--) {
				if( (String.valueOf(s1.charAt(i))).equals("(") ) {
					left=i;      // ���һ��С��������λ�õ� �����ŵ�λ��
					break;
				}
			}
		
				System.out.println(s1.substring(left, right+1));
						
		}                                  //������
		*/
		
		
	}
}
