
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathExam6360 {
	
	String Date[] = new String[100];
	String QT[] = new String[1000];				//����ȫ�ֱ������������
	String QT_1[] = new String[1000];
	String ORDER[] = new String[1000];			//��������ŵ����顣
	String AS[] = new String[1000];				//����ȫ�ֱ������������ʹ�
	String SymB[] = {"+","-","��","��"};
	String NL ="\r\n";							//����ȫ�ֱ���
	byte[] question;							//����ȫ�ֱ���
	byte[] newline = NL.getBytes();				//����ȫ�ֱ���
	byte[] answer;								//����ȫ�ֱ���
	byte[] order;
	byte[] date;

	
	
	 public static int Level(String operation){  						//���ȼ��жϡ�
	     int result;
	     switch (operation){
	         case "+":
	             result=1;
	             break;
	         case "-":
	             result=1;
	             break;
	         case "��":
	             result=2;
	             break;
	         case "��":
	             result=2;
	             break;
	         default:
//	             System.out.println("�����ڸ������");
	             result=0;
	     }
	     return result;
	 }
	 
	 
	public MathExam6360(String args[]) {											//���캯��
		// TODO Auto-generated constructor stub
		boolean B;
		if(args[0].equals("-n") && args[2].equals("-grade"))  						//�жϲ����������꼶��˳��
		{
			B =judge(args[1],args[3]);												//�жϲ����ĸ�ʽ�Լ���С�Ƿ����
			if(B==true) {	
				
				calculate(Integer.parseInt(args[1]), Integer.parseInt(args[3]));	//�����꼶�������ʽ��
				TxT(Integer.parseInt(args[1]));										//�����ı�������ʽ��д���ı�����		
			}else 
				{System.out.println("�������û���������ˡ�");}
			
		}
		else if(args[0].equals("-grade") && args[2].equals("-n")) {
			
			B =judge(args[3],args[1]);												//�жϲ����ĸ�ʽ�Լ���С�Ƿ����
			if(B==true) {	
				
				calculate(Integer.parseInt(args[3]), Integer.parseInt(args[1]));	//�����꼶�������ʽ��
				TxT(Integer.parseInt(args[3]));										//�����ı�������ʽ��д���ı�����
						
			}else 
				{System.out.println("�������û���������ˡ�");}
			
		}
		else
			{System.out.println("�������û���������ˡ�");}
		
	}
	

	private  void TxT(int count) {
		
		DateFormat dt = DateFormat.getDateTimeInstance(); 							//��ȡ��ǰʱ��
		Date[0] = dt.format(new Date());
		
		
		File file = new File("out6343.txt");  										//�����ı�
		if(!file.exists())															//�ж�TXT�Ƿ����
		{
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("out6343.txt�������");
		}
		try {
			FileOutputStream fos = new FileOutputStream(file);
			for(int i=0;i<count;i++) {
				ORDER[i]="("+(i+1)+") ";
				question = QT[i].getBytes();
				order = ORDER[i].getBytes();
				fos.write(order);
				fos.write(question);
				fos.write(newline);
				
			}
			
			
			for(int i=0;i<count;i++){
				ORDER[i]="("+(i+1)+") ";
				order = ORDER[i].getBytes();
				answer = AS[i].getBytes();
				fos.write(newline);
				fos.write(order);
				fos.write(answer);
				
			}
			date =Date[0].getBytes();
			fos.write(newline);
			fos.write(date);
			fos.flush();
			fos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private  boolean judge(String count ,String grade) {    //�жϲ�����ʽ�Ƿ���ȷ�ķ�����
		String Regex="[1-9]{1}[0-9]{0,2}";   				//������ʽ��������Ĳ����޶�����������Χ�ڣ�ͬʱ��������ޡ�
		String Regex2="[1-3]{1}{0}";        				//������ʽ��������Ĳ����޶�����������Χ�ڣ�ͬʱ��������ޡ�
		
		
		Pattern p =Pattern.compile(Regex);
		Pattern p2 =Pattern.compile(Regex2);
		
		
		Matcher M=p.matcher(count);         				//�����ж�
		Matcher M2=p2.matcher(grade);			
		
		
		return M.matches() && M2.matches();   				//����boolean���͵�ֵ
		
	}
	
	private  void calculate(int count,int grade){
		
		if(grade == 1){ 										
			calculate_1(count);
		}
		
		
		if(grade == 2) {
			calculate_2(count);
		}
		
		if(grade == 3) {
		
			calculate_3(count);
		
		}
	}
	
	private void  calculate_1(int count) {					//һ�꼶����
		for(int i=1;i<=count;) {
			int a=(int)(Math.random()*10+1);				//�����һ������
			int b=(int)(Math.random()*10+1);				//����ڶ�������
			int symbol=(int)(Math.random()*2);				//������š�
		
			if(symbol==0) {
				QT[i-1]=a + " + " + b;
				AS[i-1]=a + " + " + b +" = " +(a+b) ;
			}
			else if(symbol==1) {
				if(a<b)
					{continue;}
				QT[i-1]=a + " - " + b;	
				AS[i-1]=a + " - " + b +" = " +(a-b) ;
			}
			i++;
			}
	}
	
	private void  calculate_2(int count) {					//���꼶����
		for(int i=1;i<=count;) {
			
			int a=(int)(Math.random()*100);					//�����һ������
			int b=(int)(Math.random()*100);					//����ڶ�������
			int symbol=(int)(Math.random()*4);				//������š�
		
			if(symbol==0) {
				QT[i-1]=a + " + " + b;
				AS[i-1]=a + " + " + b +" = " + (a+b) ;
			}
			else if(symbol==1) {
				if(a<b)
					{continue;}
				QT[i-1]=a + " - " + b;	
				AS[i-1]=a + " - " + b +" = " +(a-b) ;
			}
			else if(symbol==2) {
				QT[i-1]=a%10 + " �� " + b%10;
				AS[i-1]=a%10 + " �� " + b%10 +" = " +((a%10)*(b%10)) ;
			}
			else if(symbol==3) {
				if(b%10==0) 
					{continue;}
				
				QT[i-1]=a%10 + " �� " + b%10;
				
				if(((a%10)%(b%10))==0) {
					
					AS[i-1]=a%10 + " �� " + b%10 +" = " +((a%10)/(b%10)) ;
				}
			
				else if(((a%10)%(b%10))!=0) {
					AS[i-1]=a%10 + " �� " + b%10 +" = " +((a%10)/(b%10)) +" ������ " + ((a%10)%(b%10));
				}
			
			}
		i++;
		}	
	}
	
	private void  calculate_3(int count) {
	     int i=0;
	    while(i<count){
	    	  	
	    	  	int symbol_number=(int)(Math.random()*5+2);				   	//����������֣������жϷ��Ÿ���
	    	  	int number_1=(int)(Math.random()*1000);		 				//������ɵ�һ������
	  			int number_2=(int)(Math.random()*1000+1);				 	//������ɵڶ�������
	  			int number_3=(int)(Math.random()*1000+1);					//������ɵ���������
	  			int symbol_1=(int)(Math.random()*4);						//������ɵ�һ������
	  			int symbol_2=(int)(Math.random()*4);						//������ɵڶ�������
	  			
	  			if(symbol_number == 2) {
	  				if(symbol_1==symbol_2)									//��֤����������������š�
	  					{continue;}
	  				if(Level(SymB[symbol_1])<Level(SymB[symbol_2])) {		//�Ƚϵ�һ�����ź͵ڶ������ŵ����ȼ�����������š�
			  			QT_1[i]="("+number_1+SymB[symbol_1]+number_2+")"+SymB[symbol_2]+number_3;
			  			QT[i]="( "+number_1+" "+SymB[symbol_1]+" "+ number_2 +" ) "+SymB[symbol_2] +" " +number_3;
	  				}
	  				else if(Level(SymB[symbol_1])>Level(SymB[symbol_2]))  {
			  			QT_1[i]=+number_1+SymB[symbol_1]+"(" + number_2 +SymB[symbol_2]+number_3+")";
			  			QT[i]=number_1+" "+SymB[symbol_1]+" ( "+ number_2 +" "+SymB[symbol_2]  +number_3+" ) ";
	  				}
	  				else {
	  					QT_1[i]=+number_1+SymB[symbol_1]+number_2+SymB[symbol_2]+number_3;
			  			QT[i]=number_1+" "+SymB[symbol_1]+" "+ number_2 +" "+SymB[symbol_2] +" "+number_3;
	  				}
		  		}
	  			//�ж������Ϊ����֮�󣬿�ʼ�жϷ������ȼ�������ʽ����������š�
	  			
	  			
	  			else if(symbol_number == 3) {	
	  				if(symbol_1==symbol_2)		//��֤ʽ���������������������
	  				{continue;}		
	  				int number_4=(int)(Math.random()*1000+1);
	  				int symbol_3=(int)(Math.random()*4);
	  				if(Level(SymB[symbol_1])<Level(SymB[symbol_2])) {
			  			QT_1[i]="("+number_1+SymB[symbol_1]+number_2+")"+SymB[symbol_2]+number_3+SymB[symbol_3]+number_4;
			  			QT[i]="( "+number_1+" "+SymB[symbol_1]+" "+ number_2 +" ) "+SymB[symbol_2] +" " +number_3+" "+SymB[symbol_3]+" "+number_4;
	  				}
	  				else if(Level(SymB[symbol_1])>Level(SymB[symbol_2]))  {
			  			QT_1[i]=+number_1+SymB[symbol_1]+"(" + number_2 +SymB[symbol_2]+number_3+")"+SymB[symbol_3]+number_4;
			  			QT[i]=number_1+" "+SymB[symbol_1]+" ( "+ number_2 +" "+SymB[symbol_2]  +number_3+" ) "+SymB[symbol_3]+" "+number_4;
	  				}
	  				else if(Level(SymB[symbol_2])>Level(SymB[symbol_3]))  {
			  			QT_1[i]=+number_1+SymB[symbol_1]+ number_2 +SymB[symbol_2]+"("+number_3 +SymB[symbol_3]+number_4+")";
			  			QT[i]=number_1+" "+SymB[symbol_1]+" "+ number_2 +" "+SymB[symbol_2] +" ( " +number_3+" "+SymB[symbol_3]+" "+number_4+" ) ";
	  				}
	  				else {
	  					QT_1[i]=+number_1+SymB[symbol_1]+number_2+SymB[symbol_2]+number_3+SymB[symbol_3]+number_4;
			  			QT[i]=number_1+" "+SymB[symbol_1]+" "+ number_2 +" "+SymB[symbol_2] +" "+number_3+" "+SymB[symbol_3]+" "+number_4;
	  				}
		  		}
	  		//�ж������Ϊ����֮�󣬿�ʼ�жϷ������ȼ�������ʽ����������š�
	  			
	  			
	  			else if(symbol_number == 4) {
	  				if(symbol_1==symbol_2)								//��֤ʽ�������������������
	  				{continue;}
	  				int number_4=(int)(Math.random()*1000+1);
	  				int number_5=(int)(Math.random()*1000+1);
	  				int symbol_3=(int)(Math.random()*4);
	  				int symbol_4=(int)(Math.random()*4);
	  				
	  				if(Level(SymB[symbol_1])<Level(SymB[symbol_2])) {
			  			QT_1[i]="("+number_1+SymB[symbol_1]+number_2+")"+SymB[symbol_2]+number_3+SymB[symbol_3]+number_4+SymB[symbol_4]+number_5;
			  			QT[i]="( "+number_1+" "+SymB[symbol_1]+" "+ number_2 +" ) "+SymB[symbol_2] +" " +number_3+" "+SymB[symbol_3]+" "+number_4+" "+SymB[symbol_4]+" "+number_5;
	  				}
	  				
	  				
	  				else if(Level(SymB[symbol_1])>Level(SymB[symbol_2])) {
			  			QT_1[i]=number_1+SymB[symbol_1]+"("+number_2+SymB[symbol_2]+number_3+")"+SymB[symbol_3]+number_4+SymB[symbol_4]+number_5;
			  			QT[i]=number_1+" "+SymB[symbol_1]+" ( "+ number_2 +" "+SymB[symbol_2]+" "+number_3+" ) "+SymB[symbol_3]+" "+number_4+" "+SymB[symbol_4]+" "+number_5;
	  				}
	  				
	  				
	  				else if(Level(SymB[symbol_2])>Level(SymB[symbol_3])) {
			  			QT_1[i]=number_1+SymB[symbol_1]+number_2+SymB[symbol_2]+"("+number_3+SymB[symbol_3]+number_4+")"+SymB[symbol_4]+number_5;
			  			QT[i]=number_1+" "+SymB[symbol_1]+" "+ number_2 + " "+SymB[symbol_2]+" ( "+number_3+" "+SymB[symbol_3]+" "+number_4+" ) "+" "+SymB[symbol_4]+" "+number_5;
	  				}
	  				
	  				
	  				else if(Level(SymB[symbol_3])>Level(SymB[symbol_4])) {
			  			QT_1[i]=number_1+SymB[symbol_1]+number_2+SymB[symbol_2]+number_3+SymB[symbol_3]+"("+number_4+SymB[symbol_4]+number_5+")";
			  			QT[i]=number_1+" "+SymB[symbol_1]+" "+ number_2 + " "+SymB[symbol_2]+" "+number_3+" "+SymB[symbol_3]+" ( "+number_4+" "+SymB[symbol_4]+" "+number_5+" )";
	  				}
	  				
	  				
	  				else {
			  			QT_1[i]=number_1+SymB[symbol_1]+ number_2+SymB[symbol_2]+number_3+SymB[symbol_3]+number_4+SymB[symbol_4]+number_5;
			  			QT[i]=number_1+" "+SymB[symbol_1]+" "+ number_2 +" "+SymB[symbol_2] +" " +number_3+" "+SymB[symbol_3] +" "+number_4+" "+SymB[symbol_4]+" "+number_5;
	  				}
	  			}
	  		//�ж������Ϊ�ĸ�֮�󣬿�ʼ�жϷ������ȼ�������ʽ����������š�
	  			
	  			else {continue;}		//��symbol_number���������Ҫ������ʱ����������ѭ�������¿�ʼ�µ�ѭ������Ҫ��ֹ���ֿ�ָ�롣
	  			
	  			
	  			 List<String> rec= toInfixExpression(QT_1[i]);			//����������ʽ��
	  			 AS[i]=QT[i]+" = "+reckon(rec);
	  			 
	  			 
	  			 if(reckon(rec)<0 || reckon(rec)>10000)  //�����ֵĽ����СΪ�������ߴ���10000����������ѭ������������ʽ�ӡ�
	  			 {continue;}
	  			 i++;
	  			 
	      	}
	     
	    }
	
	
	  public  List<String> parseSuffixExpression(List<String> ls) {   //�沨�����ʽ
	        Stack<String> s1=new Stack<String>();
	        List<String> LS = new ArrayList<String>();
	        for (String str : ls) {
	            if (str.matches("\\d+")) {
	                LS.add(str);
	            }
	            else if(str.equals("(")) {
	            	s1.push(str);
	            }
	            else if (str.equals(")")) {
	            	while(!s1.peek().equals("(")) {
	            		LS.add(s1.pop());
	            	}
	            	s1.pop();
	            }
	            else {
	                while (s1.size() != 0 && Level(s1.peek()) >= Level(str)) {
	                    LS.add(s1.pop());
	                }
	                s1.push(str);
	            }
	        }
	        while (s1.size() != 0) {
	            LS.add(s1.pop());
	        }
	        return LS;
	    }
	  
	
	 public List<String> toInfixExpression(String s) {
	        List<String> ls = new ArrayList<String>();//�洢������ʽ
	        int w = 0;
	        String str;
	        char c;

	        do {
	            if ((c = s.charAt(w)) < 48 || (c = s.charAt(w)) > 57) {
	                ls.add("" + c);
	               w++;
	                
	            } else {
	                str = "";
	                while (w < s.length() && (c = s.charAt(w)) >= 48
	                        && (c = s.charAt(w)) <= 57) {
	                    str += c;
	                    w++;
	                }
	                ls.add(str);
	            }

	        } while (w < s.length());
	        List<String> LS=parseSuffixExpression(ls);
	        return LS;
	    }
	
	 public  int reckon(List<String> ls) {     //���ڼ������Ա����ʽ�ӡ�
	        Stack<String> s=new Stack<String>();
	        for (String str : ls) {
	            if (str.matches("\\d+")) {
	                s.push(str);
	            } else {
	                int b = Integer.parseInt(s.pop());
	                int a = Integer.parseInt(s.pop());
	                int result=0;
	                if (str.equals("+")) {
	                    result = a + b;
	                } else if (str.equals("-")) {
	                    result = a - b;
	                } else if (str.equals("��")) {
	                    result = a * b;
	                } else if (str.equals("��")) {
	                    result = a / b;
	                }
	                s.push("" + result);
	            }
	        }
	        return Integer.parseInt(s.pop());
	    }
	
	public static void main(String args[]) {
		new	MathExam6360(args);

	}

}
