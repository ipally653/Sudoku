
public class LinkList {
	Node head;
	Node tail;
	
	
	public class Node{
		int value;
		Node nextNode;
		
		public Node(int value)
		{
			this.value = value;
		}
		
		public void setValue(int value)
		{
			this.value = value;
		}
		public int getValue()
		{
			return value;
		}
		public void setNext(Node newNode)
		{
			nextNode = newNode;
		}
		public Node getNext()
		{
			try
			{
				return nextNode;
			}
			catch(NullPointerException e) {
				Node emptyNode = new Node(-1);
				return emptyNode;
			}
		}
		public void setNext(int value)
		{
			nextNode = new Node(value);
		}
		
		public boolean equals(Node newNode)
		{
			boolean result;
			if(this.getValue() == newNode.getValue())
				result = true;
			else
				result = false;
			return result;
		}
		
	}
	
	public LinkList()
	{
		head = new Node(1);
		Node temp = head;
		temp.setNext(2);
		temp = temp.getNext();
		temp.setNext(3);
		temp = temp.getNext();
		temp.setNext(4);
		temp = temp.getNext();
		temp.setNext(5);
		temp = temp.getNext();
		temp.setNext(6);
		temp = temp.getNext();
		temp.setNext(7);
		temp = temp.getNext();
		temp.setNext(8);
		temp = temp.getNext();
		temp.setNext(9);
		temp = temp.getNext();
		temp.setNext(10);
		tail = temp.getNext();
	}
	
	public void addNode(int value)
	{
		Node newNode = new Node(value);
		newNode.setNext(head);
		head = newNode;	
	}
	
	public Node removeNode(int index)
	{
		if(tail.equals(head))
			return tail;
		else if(index == 0)
		{
			head = head.getNext();
			return head;
		}
		else
		{	
			Node result = head;
			Node temp;
			for(int i = 0; i < index; i++)
			{
				if(i == (index - 1))
						temp = result;
				result = result.getNext();
			}
			temp.setNext(result.getNext());
			return result;	

		}
	}
	
	public int getValue(int index)
	{
		Node temp = head;
		for(int i = 0; i < index; i++)
		{
			temp = temp.getNext();
		}
		return temp.getValue();
	}
	
	public boolean containNumber(int value)
	{
		Node temp = head;
		boolean result = false;
		
		while(temp != tail && !result)
		{
			if(temp.getValue() == value)
			{
				result = true;
			}
		}
		return result;
	}
	
	public String toString()
	{
		String result = "";
		Node current = head;
		if(head != null)
		{
			result = result + current.getValue() + ": ";
			do
				{
				result = result + current.getNext().getValue() + ": ";
				if(current != tail)
					current = current.getNext();
				System.out.println(result);
				System.out.println(current.getValue());
			}while(current != tail);
		}	
		return result;
	}
	
}






























