import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//����һ��SOPջ��
public class SopStacks {
    private LinkedList<String> list = new LinkedList<String>();
    public void push(String o){   // ��ջ
     list.addFirst(o);
    }
    
    public String pop(){    // ��ջ,ָ��ջ��ɾ����Ԫ�ز�����
     return list.removeFirst();
    }
    
    public String peek(){    // �鿴ջ��Ԫ��
     return list.getFirst();
    }
    
    public boolean isEmpty(){   // �ж�ջ�Ƿ�Ϊ��
     return list.isEmpty();
    }
    
    public int getSize(){    // �õ�ջ�Ĵ�С
     return list.size();
    }

}
