
public class MathExamV2 {
	public static void math(int n,int grade) {
		System.out.println("n:"+n);
		System.out.println("grade:"+grade);
	}
	public static int[] input(String[] args,int[] str) {
		
		String regex="\\d*";
		if(args.length!=4) {//����������Ϊ���
			System.out.println("����Ĳ���ӦΪ4��");
			str[0]=0;
			return str;
		}
		if((!args[1].matches(regex))||(!args[3].matches(regex))) {//���������벻Ϊ����
			System.out.println("������꼶��������ӦΪ����");
			str[0]=0;
			return str;
			}
		if(!((args[0].equals("-n") && args[2].equals("-grade"))||(args[0].equals("-grade") && args[2].equals("-n")))) {
			System.out.println("���Ҳ���-n����-grade");
			str[0]=0;
			return str;
		}
		String s0=String.valueOf(args[1]);
		String s1=String.valueOf(args[3]);
		int grade=Integer.parseInt(s0);
		int n=Integer.parseInt(s1);
		
		if(args[0].equals("-n") && args[2].equals("-grade")) {
		n=Integer.parseInt(s0);
		grade=Integer.parseInt(s1);
		}
		
		
		if( grade>3 || grade<=0 ) {
			System.out.println("�����꼶Ӧ��һ�����꼶");
			str[0]=0;
			return str;
		}
		if(n<=0||n>1000)
		{
			System.out.println("������Ŀ����Ӧ����1~1000");
			str[0]=0;
			return str;
		}
		str[0]=1;
		str[1]=n;
		str[2]=grade;
		return str;
	}

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		int[] str= new int[3];
		input(args,str);
		if(str[0]==0){
			return ;
		}
		int n=str[1];
		int grade=str[2];
		math(n,grade);
	}

}
