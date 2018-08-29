
public class LinkList {
	Node head;
	
	
	public class Node{
		int value;
		Node nextNode;
		boolean hasNext;
		
		public Node(int value)
		{
			this.value = value;
			hasNext = false;
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
			hasNext = true;
		}
		public Node getNext()
		{
			return nextNode;
		}
		public Node removeNode()
		{
			Node temp = this;
			while(temp.getNext().hasNext)
			{
				temp = temp.getNext();
			}
			Node result = temp.getNext();
			temp.setNext(null);
			return result;
		}
		
	}
	
	public LinkList()
	{
		head = new Node(0);
	}
	public void addNode(int value)
	{
		Node newNode = new Node(value);
		newNode.setNext(head);
		head = newNode;
	}
	public boolean containNumber(int num)
	{
		Node current = head;
		boolean contains = false;
		
		while(current.hasNext)
		{
			System.out.println(current.nextNode);
			if(current.getValue() == num)
				contains = true;
			else
				current = current.getNext();
		}	
		return contains;
	}
	
	
}






























