import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * A JUnit test class for testing SortedSet
 * @author TaJah
 *
 */
class SetTester {

	@Test
	void testGetCurrentSize() {
		SortedSet set = new SortedSet();			// Creating new empty set
		
		assertEquals(set.getCurrentSize(), 0);		// Empty set should have size 0
		set.add("a");
		assertEquals(set.getCurrentSize(), 1);		// Adding an element increments size by 1
		set.add("a");
		assertEquals(set.getCurrentSize(), 1);		// Failing to add element does not change size
		set.remove();
		assertEquals(set.getCurrentSize(), 0);		// Removing an element decrements size
	}

	@Test
	void testIsEmpty() {
		SortedSet set = new SortedSet();			// Creating new empty set
		
		assertTrue(set.isEmpty());					// Empty set is empty
		set.add("a");						
		assertFalse(set.isEmpty());					// After adding an element, set is not empty
		set.remove();
		assertTrue(set.isEmpty());					// After removing the element, set is empty
	}

	@Test
	void testAdd() {
		SortedSet set = new SortedSet();			// Creating new empty set
		
		assertTrue(set.add("c"));					// Add an element
		assertFalse(set.add("c"));					// Fail to add repeat element
		assertEquals(1, set.getCurrentSize());		// Adding element increments size, failing does not
		
		assertTrue(set.add("a"));					// Add an element before head
		assertEquals(2, set.getCurrentSize());		// Adding another element increments size
		
		assertTrue(set.add("b"));
		assertEquals(3, set.getCurrentSize());
		System.out.println(set);					// [a b c]
		set.clear();
		set.add("d");								// Add in descending order
		set.add("c");
		set.add("b");
		set.add("a");
	}

	@Test
	void testRemoveString() {
		SortedSet set = new SortedSet();			// Creating new empty set
		
		assertFalse(set.remove("a"));				// Removing element from empty set returns false
		set.add("a");
		set.add("b");
		set.add("c");
		
		assertTrue(set.remove("b"));				// Removing middle returns true
		assertFalse(set.contains("b"));				// Set no longer contains that element
		assertEquals(2, set.getCurrentSize());		// Size decremented after removing
		assertTrue(set.remove("a"));				// Removing first node
		assertEquals(1, set.getCurrentSize());		// Size decreased
		set.add("b");
		assertTrue(set.remove("c"));				// Removing last node
		assertFalse(set.contains("c"));
		assertTrue(set.contains("b"));
	}

	@Test
	void testRemove() {
		SortedSet set = new SortedSet();			// Creating new empty set
		assertEquals(set.remove(), null);			// Removing from empty set returns null
		set.add("a");
		set.add("b");
		set.add("c");
		assertEquals(set.remove(), "a");			// Removes first item
		assertEquals(set.toString(), "[b c]");
		assertEquals(set.remove(), "b");
		assertEquals(set.toString(), "[c]");
		assertEquals(set.remove(), "c");
		assertEquals(set.toString(), "[]");
	}

	@Test
	void testRemoveLast() {
		SortedSet set = new SortedSet();			// Creating new empty set
		assertNull(null, set.removeLast());			// Removing from empty set returns null
		set.add("a");
		set.add("b");
		set.add("c");
		assertEquals(set.removeLast(), "c");		// Removes last item
		assertEquals(set.toString(), "[a b]");
		assertEquals(set.removeLast(), "b");
		assertEquals(set.toString(), "[a]");
		assertEquals(set.removeLast(), "a");
		assertEquals(set.toString(), "[]");
		}

	@Test
	void testClear() {
		SortedSet set = new SortedSet();			// Creating new empty set
		set.clear();								// Clearing with no items
		assertEquals(0, set.getCurrentSize());		// empty
		set.add("a");
		set.add("b");
		set.add("c");
		System.out.println(set);
		set.clear();
		System.out.println(set + "empty");
	}

	@Test
	void testContains() {
		SortedSet set = new SortedSet();			// Creating new empty set
		
		assertFalse(set.contains("a"));				// Empty set contains no entries
		set.add("a");								// Add "a" to set
		assertTrue(set.contains("a"));				// Set contains "a"
		set.add("b");								// Add "b" to set
		assertTrue(set.contains("b"));				// Set contains "b"
		assertFalse(set.contains("c"));				// Set does not contain "c"
		set.remove("a");							// Remove "a" from set
		assertFalse(set.contains("a"));				// No longer contains "a"
	}

	@Test
	void testToString() {
		SortedSet set = new SortedSet();			// Creating new empty set
		set.add("a");
		set.add("b");
		set.add("c");
		assertEquals("[a b c]", set.toString());
		}

	@Test
	void testToArray() {
		SortedSet set = new SortedSet();
		String[] str = {"a", "b", "c", "d"};
		set.add("b");
		set.add("a");
		set.add("d");
		set.add("c");
		for (int i = 0; i < set.getCurrentSize(); i++) {
			assertEquals(str[i], set.toArray()[i]);
		}
	}

}
