
public class MathExam {
	//�ӷ�
	private static void add(int i,StringBuffer word,int[] answer) {
		// TODO �Զ����ɵķ������
		int num1 = (int) (Math.random()*999);
		System.out.println("���Ǽӷ�1��"+num1);
		word.insert(word.length()," + "+num1);
		answer[i] = answer[i]+num1;
	}
	//����
	private static void sub(int i,StringBuffer word, int[] answer) {
		// TODO �Զ����ɵķ������
		int num1 = (int) (Math.random()*(answer[i]-1));
		System.out.println("���Ǽ���1��"+num1);
		word.insert(word.length(),"-"+num1);
		answer[i] = answer[i]-num1;
	}
	//�˷�
	private static void mul(int i,StringBuffer word, int[] answer) {
		// TODO �Զ����ɵķ������
		int num1 = (int) (Math.random()*98);
		System.out.println("���ǳ˷�1��"+num1);
			word.insert(word.length(), "x"+num1);
			answer[i]= answer[i]*num1;
	}
	//����
	private static void div(int i,int j,int f, StringBuffer word, int[] answer) {
		// TODO �Զ����ɵķ�����
		int test = (int)(Math.random());
		if((test==1)||(answer[i]==0)) {
			int num1 = 1+(int)(Math.random()*98);  //����
			System.out.println("���ǳ���1��"+num1);
			int rem=answer[i]%num1;
			if(rem>0) {
				word.insert(word.length(), "+"+rem);
				word.insert(word.length(),"��"+num1);
				answer[i]=(answer[i]+rem)/num1;
			}else {
			word.insert(word.length(),"��"+num1);
			answer[i]=answer[i]/num1;}
		}
		else if(test==0) {
			int num1 = (int)(Math.random()*(1000/answer[i]));  //������
			System.out.println("���ǳ���2��"+num1*answer[i]);
			word.insert(0,(num1*answer[i])+"��");
			answer[i] = num1;
		}
		
	}
	private static void operation(int n) {
		// TODO �Զ����ɵķ������
		//str���鴢����������ĵȼ��ͷ��ţ�answer�������ʽ�Ӵ𰸣�word���鴢����Ŀ��f�����������ĸ���;num������ĵ�һ����;g��ѡ���g�������;i�ǵ�i���⣻j�ǵ�ǰ��������
		int level=1;//���õȼ�
		int[] str= {0,0,1,1}; // 0Ϊ+-��1Ϊ*/
		int[] answer = new int[n]; //��𰸵�����
		//����Ŀ������
		for(int i=0;i<n;i++) {//��n����Ŀ��ѭ��
			int f = 2+(int) (Math.random()*3); //f:�����ȡ������ĸ���
			int g = (int)(Math.random()*(str.length-1));//��һ�������
			if(g==2) {
				answer[i]= (int) (Math.random()*98);
			}else {
				answer[i] = (int) (Math.random()*998);
			}//��һ�������
			StringBuffer word= new StringBuffer(answer[i]+"");
			for(int j=0;j<f;j++) {
				if(level<str[g] && j>f-2)break;//����Ҫ�����ŵ�����������������
				if(level<str[g]) {
					//������
					word.insert(0, "(");
					word.insert(word.length(), ")");
					j++;
				}
				level=str[g];
				 //���ѡ�����
				if(g==0) {	
					add(i,word, answer);
				}
				else if(g==1) {
				
					sub(i,word, answer);
					}
				else if(g==2) {
					
					div(i,j,f,word, answer);
					
				}
				else {
					mul(i,word, answer);
				}
				g = (int)(Math.random()*(str.length));
				if(answer[i]>=100) {
				g = (int)(Math.random()*(str.length-1));
				}
					
				
				
			}
			System.out.println(word);
			System.out.println(answer[i]);
		}
	}
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		int n = 1;
		operation(n);
		
	}

}
