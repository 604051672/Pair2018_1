package com.java285.second;

public class MathExamLv2 {
	private Object[] stack;
	private int size;// Ԫ�ظ���;

	public MathExamLv2() {// Ĭ�ϳ���Ϊ10;
		this(10);
	}

	public MathExamLv2(int len) {// Ҳ�����Լ����ó���,������;
		stack = new Object[len];
	}

	public int size() {// ����Ԫ�ظ���;
		return size;
	}

	public int capacity() {// �������鳤��,������;
		return stack.length;
	}

	public void push(Object o) {// ��ջ;
		size++;
		stack[size - 1] = o;
	}

	public boolean isEmpty() {// �п�;
		return size == 0;
	}

	public Object pop() {// ��ջ;
		if (isEmpty()) {// ����Ҫ�п�;
			throw new ArrayIndexOutOfBoundsException("����Ϊ��");
		}
		Object o = stack[--size];
		stack[size] = null;
		return o;
	}

	public static void main(String[] args) {
		StackTest Test = new StackTest();
	}
}
