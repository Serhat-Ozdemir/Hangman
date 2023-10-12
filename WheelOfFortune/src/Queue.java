
public class Queue {
	int rear,front;

	Object[] elements;
	Queue(int capacity)
	{
		elements = new Object [capacity];
		rear = -1;
		front = 0;
	}
	boolean isEmpty()
	{
		return ( (elements[front] == null));
	}
	boolean isFull()
	{
		return ( (front == ( rear + 1) % elements.length &&	elements[front] != null && elements[rear] != null));
	}
	int Size()
	{
		if (elements[front] == null) {
			return 0;
			}
			else {
			if (rear >= front)
			return rear - front + 1;
			else
			return elements.length - (front - rear) + 1;
			}
			
	}
	void Enqueue(Object data)
	{
		if(isFull())
			System.out.println("Stack Overflow");
		else 
		{
			rear = (rear+1)%elements.length;
			elements[rear] = data;
		}		
	}
	Object Dequeue()
	{
		if(isEmpty()) {
			System.out.println("Stack is Empty");
			return null;
		}
		else {
			Object data = elements[front];
			elements[front] = null;
			front = (front + 1) % elements.length;
			return data;
		}
	}
	Object Peek() {
		if(isEmpty()) {
			System.out.println("Stack is Empty");
			return null;
		}
		else {
			return elements[front];
		}
	}

}
