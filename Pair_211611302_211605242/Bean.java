
import java.util.Random;

public class Bean {
	private int count = 1; // ��ţ���ʼΪ1
	private String symbol = null; // ��Ŀ���������
	private int numberA = 0;
	private int numberB = 0;
	private int result = 0; // ������
	private int mod = 0; // ���������е�����
	private StringBuffer text1 = new StringBuffer(""); // ��Ŀ�� ��ʽ: A + B =
	private StringBuffer text2 = new StringBuffer(""); // �� ����ʽ: A + B = C
	private String txt = null; // txt = text1 + text2
	private StringBuffer topic=new StringBuffer(""); //���꼶3����Ŀ

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getNumberA() {
		return numberA;
	}

	public void setNumberA(int numberA) {
		this.numberA = numberA;
	}

	public int getNumberB() {
		return numberB;
	}

	public void setNumberB(int numberB) {
		this.numberB = numberB;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	
	public String getTxt() {
		return txt;
	}

	public int getMod() {
		return mod;
	}

	public void setMod(int mod) {
		this.mod = mod;
	}

	public StringBuffer getTopic() {
		return topic;
	}

	public void setTopic(StringBuffer topic) {
		this.topic = topic;
	}

}
