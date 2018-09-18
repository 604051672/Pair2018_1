import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
//����һ��SOPջ��
public class SopStacks {
    private LinkedList<String> list = new LinkedList<String>();
    Stack<String> s = new Stack<String>();
    
    public void push(String o){   // ��ջ
     list.addLast(o);
     
    }
    
    public String pop(){    // ��ջ,ָ��ջ��ɾ����Ԫ�ز�����
     return list.removeLast();
    }
    
    public String peek(){    // �鿴ջ��Ԫ��
     return list.getLast();
    }
    
    public boolean isEmpty(){   // �ж�ջ�Ƿ�Ϊ��
     return list.isEmpty();
    }
    
    public int getSize(){    // �õ�ջ�Ĵ�С
     return list.size();
    }

}
