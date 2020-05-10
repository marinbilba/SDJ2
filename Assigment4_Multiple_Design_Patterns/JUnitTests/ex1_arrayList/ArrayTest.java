import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.collection.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTest
{
  private ArrayList<String> array;

  @BeforeEach public void createArray()
  {
    array = new ArrayList<>();
  }

  /**
   * Check the add(T element) and add(int index, T element) methods.
   * Test was done in 2 parts:
   * 1. Elements A,B were added to the list
   * The correct array size and elements position was checked
   * 2. A third element C was added to the list at the specified index
   * Elements position was checked again
   */
  @Test @DisplayName("Add Element To Array") void addElementInArray()
  {
    array.add("A");
    array.add("B");
    //    1 Part
    assertAll(() -> assertEquals(2, array.size()),
        () -> assertEquals("A", array.get(0)),
        () -> assertEquals("B", array.get(1)));
    // 2 Part
    array.add(0, "C");
    assertEquals("C", array.get(0));
    assertEquals("A", array.get(1));
  }

  /**
   * Check IndexOutOfBoundsException
   */
  @Test @DisplayName("IndexOutOfBoundsException on add method") void indexOutOfBoundsExceptionOnAddMethod()
  {
    try
    {
      array.add(1000, "A");
    }
    catch (IndexOutOfBoundsException e)
    {
    }
  }

  /**
   * Test checks if the method returns true if this list contains the specified target element.
   */
  @Test @DisplayName("Contains method") void containsMethod()
  {
    array.add("A");
    assertTrue(array.contains("A"));
  }

  @Test @DisplayName("Get method") void getElementFromArray()
  {
    array.add("A");
    assertEquals("A", array.get(0));
  }

  /**
   * Check if the method throws IndexOutOfBoundsException
   * First we try to get a positive and negative element index from the array
   */
  @Test @DisplayName("IndexOutOfBoundsException on get method") void indexOutOfBoundsExceptionOnGetMethod()
  {
    try
    {
      array.get(2000);
    }
    catch (IndexOutOfBoundsException e)
    {
      // In the documentation is written that it throws an IndexOutOfBoundsException
      // but I checked the code, and there it is thrown an IllegalStateException
    }
    try
    {
      array.get(-1);
    }
    catch (IndexOutOfBoundsException e)
    {
      // In the documentation is written that it throws an IndexOutOfBoundsException
      // but I checked the code, and there it is thrown an IllegalStateException
    }
  }

  /**
   * Check the behavior of the list when adding a null element
   */
  @Test @DisplayName("Add null element to the array") public void testAddElementNull()
  {
    array.add(0, null);
  }

  /**
   * Check the indexOf(T element) if it returns the index of the specified element.
   */
  @Test @DisplayName("IndexOf method") void getIndexOfAnElementFromArray()
  {
    array.add("A");
    array.add("B");
    assertEquals(0, array.indexOf("A"));
    assertEquals(1, array.indexOf("B"));
    assertEquals(1, array.indexOf("D"),
        "user tries to pass an element that is not in the list");
  }

  /**
   * Check the isEmpty returns true if this list contains no elements and false if there are no elements
   */
  @Test @DisplayName("isEmpty method") void verifyIfTheArrayIsEmpty()
  {
    assertTrue(array.isEmpty());
    array.add("A");
    assertFalse(array.isEmpty());
  }

  /**
   * Check true if this list is full. Otherwise, false
   */
  @Test @DisplayName("isFull method") void verifyIfTheArrayIsFull()
  {
    assertFalse(array.isFull(),
        "according to documentation should always return false");
  }

  /**
   * Check if the method removes and returns the element at the specified index.
   */

  @Test @DisplayName("Remove element at Specified Index") void removeElementFromArrayAtSpecifiedIndex()
  {
    String object = "A";
    array.add(object);
    String object2 = array.remove(0);
    assertEquals(0, array.size());
    assertEquals(object, object2);
  }

  /**
   * Check if the method remove(int index) throws IndexOutOfBoundsException
   */
  @Test @DisplayName("Remove an element out of list range") void RemoveAnElementOutOfListRange()
  {
    try
    {
      array.remove(20);
    }
    catch (IndexOutOfBoundsException e)
    {
    }
  }

  /**
   * Check if the method removes and returns the specified element from this list.
   */

  @Test @DisplayName("Remove and return specified element") void removeAndReturnElementFromArray()
  {
    String object = "A";
    array.add(object);
    String object2 = array.remove("A");
    assertEquals(0, array.size());
    assertEquals(object, object2);
  }

  /**
   * Check if the method remove(T element) throws IllegalStateException
   */
  @Test @DisplayName("Remove not existing element throw IllegalStateException") void removeNonExistingElementFromArray()
  {
    try
    {
      array.remove("B");
    }
    catch (IllegalStateException e)
    {
    }
  }

  /**
   * Check if the array returns the number of elements in this list
   */
  @Test @DisplayName("Size method") void verifyArraySize()
  {
    assertEquals(0, array.size());
    array.add("A");
    assertEquals(1, array.size());
  }

  /**
   * Sets the element at the specified index
   */
  @Test @DisplayName("Set element on a specified index") void setElementOnASpecifiedIndex()
  {
    array.add(0, "A");
    array.add(1, "B");
    array.add(2, "C");

    array.set(1, "D");

    assertEquals("A", array.get(0));
    assertEquals("D", array.get(1));
    assertEquals("C", array.get(2));
  }

  /**
   * Check if it is possible to set an element that is not a part of a list,
   * this action should throw IndexOutOfBoundsException
   */
  @Test @DisplayName("Set an out of range element") void setAnOutOfRangeElement()
  {
    try
    {
      array.set(10, "A");
    }
    catch (IndexOutOfBoundsException e)
    {
    }
  }

  /**
   * Check the string representation of the list with elements
   * comma separated and enclosed in a set of curly braces.
   */
  @Test public void testToString()
  {
    String expected = "{}"; // put the expected value here
    assertEquals(expected, array.toString());

    String expected2 = "{A, B, C}";
    array.add("A");
    array.add("B");
    array.add("C");

    assertEquals(expected2, array.toString());
  }
}
