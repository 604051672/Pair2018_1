
import java.util.ArrayList;
import java.util.List;
//����һ�����Ա���
public class LinearTable {
    private List<String> list=new ArrayList<String>();
    public void append(String str) {
		list.add(str);
    }
    public void append(int i,String str) {
		list.add(i,str);
    }
    //��������ӵ�����
    public List<String> getAll(){
    	return list;
    }
    //����������ȫ�����
    public String get(int index) {
    	return list.get(index);
    }
    //������е�indexλ������
    public int size() {
    	return list.size();
    }
    //���Ա�Ĵ�С
    public void remove() {
    	for(int i=0;i<=list.size();i++)
    		list.remove(i);
    }
    //ɾ�����Ա��е�����
}
