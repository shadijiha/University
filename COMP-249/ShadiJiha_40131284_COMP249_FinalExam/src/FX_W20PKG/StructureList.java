/**
 * @author Shadi Jiha
 * @Date April 22nd, 2020
 */

package FX_W20PKG;

public class StructureList implements Cloneable {
	
	private StructureNode head;

	public StructureList() {
		head = null;
	}
	
	public void addToStart(Structure s)	{
		StructureNode node = new StructureNode(s, head);
		head = node;
	}
	
	public void addToEnd(Structure s)	{
		if (head == null)	{
			addToStart(s);
			return;
		}
		StructureNode last = get(size() - 1);		
		last.setNext(new StructureNode(s, null));		
	}
	
	public void append(StructureList list)	{
		
		StructureNode position = list.head;
		
		while(position != null)	{
			this.addToEnd(position.getsObj());
			position = position.getNext();
		}
		
		list.nullfy();		
	}
	
	public Object clone()	{
		try	{
			StructureList copy = (StructureList) super.clone();
			if (head == null)
				copy.head = null;
			else	{
				
				StructureNode position = head;
				StructureNode newHead = null;
				StructureNode end = null;
				
				newHead = new StructureNode(position.getsObj(), null);
				end = newHead;
				position = position.getNext();
				
				while(position != null)	{
					end.setNext(new StructureNode(position.getsObj(), null));
					end = end.getNext();
					position = position.getNext();
				}				
				
				copy.head = newHead;
			}
			
			return copy;
			
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
	public void showListContents()	{
		
		StructureNode position = head;
		
		while(position != null)	{
			System.out.println(position.getsObj() + " ===> ");
			position = position.getNext();
		}
		
		System.out.println("X");		
	}
	
	public int size()	{
		int count = 0;
		StructureNode position = head;
		while (position != null)	{
			count++;
			position = position.getNext();
		}
		return count;
	}
	
	public StructureNode get(int index)	{
		
		index = index < 0 ? 0 : index;
		
		StructureNode position = head;
		int count = 0;
		
		while(position != null)	{
			if (count == index)
				return position;
			position = position.getNext();
			count++;
		}
		return null;
	}
	
	private void nullfy()	{
		this.head = null;
	}
	
	public class StructureNode	{
		private Structure sObj;
		private StructureNode next;
		
		/**
		 * @param sObj
		 * @param next
		 */
		public StructureNode(Structure sObj, StructureNode next) {
			super();
			this.sObj = sObj;
			this.next = next;
		}

		/**
		 * @return the sObj
		 */
		public Structure getsObj() {
			return sObj;
		}

		/**
		 * @param sObj the sObj to set
		 */
		public void setsObj(Structure sObj) {
			this.sObj = sObj;
		}

		/**
		 * @return the next
		 */
		public StructureNode getNext() {
			return next;
		}

		/**
		 * @param next the next to set
		 */
		public void setNext(StructureNode next) {
			this.next = next;
		}
	}

}
