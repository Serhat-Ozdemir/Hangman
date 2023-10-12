
public class Stack {
	private int top;
	private Object[] elements;
	Stack(int capacity)
	{
		elements = new Object[capacity];
		top = -1;
	}
	boolean isEmpty()
	{
		return (top == -1);
	}
	boolean isFull()
	{
		return (top == elements.length-1);
	}
	int Size()
	{
		return top +1;
	}
	void Push(Object data)
	{
		if(isFull())
			System.out.println("Stack Overflow");
		else
		{
			top++;
			elements[top] = data;
			
		}
	}
	Object Pop()
	{
		if(isEmpty())
		{
			System.out.println("Stack is Empty");
			return null;
		}		
		else
		{
			Object data = elements[top];
			top--;
			return data;
		}
	}
	Object Peek()
	{
		if(isEmpty())
		{
			System.out.println("Stack is Empty");
			return null;
		}
		else
		{
			return elements[top];
		}
	}

}
