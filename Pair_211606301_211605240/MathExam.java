
public class MathExam {
	private static void operation(int n) {
		// TODO �Զ����ɵķ������
		int a=0,b=0,c=1,d=1;
		int g=0;
		int[] str= {a,b,c,d}; //abcd����������ȼ��� 0Ϊ+-��1Ϊ*/
		int[] answer = new int[n]; //��𰸵�����
		String[] word = new String[n]; //����Ŀ������
		for(int i=0;i<n;i++) {
			int f = 2+(int) (Math.random()*3); //�����ȡ������ĸ���
			int num = 1+(int) (Math.random()*1001);
			int num1 = 1+(int) (Math.random()*1001);
			word[i]="";
			answer[i]=0;
			
			for(int j=f;j>=2;j--) {
				g = (int)(Math.random()*(str.length)); //���ѡȡstr���鳤��
				int num2 =1+(int) (Math.random()*1001);
				if(g<=2) {
					if(g==1) {
						if(j==f) {
							word[i]=num+"+"+num1;
							answer[i] =num+num1;
						}
					word[i] = word[i]+"+"+num2;
					answer[i] = answer[i]+num2;
					}
					else {
						if(j==f) {
							word[i]=num+"-"+num1;
							answer[i] =Math.abs(num-num1);
						}
					word[i] = word[i]+"-"+num2;
					answer[i] = answer[i]-num2;
					}
				}
				else {
					if(g==3) {
						if(j==f) {
							word[i]=num+"x"+num1;
							answer[i] =num*num1;
						}
						word[i] = "("+word[i]+")"+"x"+num2;
						answer[i] = answer[i]*num2;
						j--;
					}
					else {
						if(j==f) {
							word[i]=num+"��"+num1;
							answer[i] =num/num1;
						}
						word[i] = "("+word[i]+")"+"��"+num2;
						answer[i] = answer[i]/num2;
						j--;
					}
				}
				
			}
			
		}
		

		for(int i=0;i<n;i++) {
		System.out.println(word[i]);
		}
		System.out.println();
		System.out.println("���Ǵ�3��"+answer[2]);
		System.out.println();
		for(int i=0;i<n;i++) {
			System.out.println(answer[i]);
			}
	}
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		int n = 10;
		operation(n);
		
	}

}
