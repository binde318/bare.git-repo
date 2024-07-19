import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Algorithm {
    public static int findSmallestNonOccurringInteger(int[] array) {
        Set<Integer> numbers = new HashSet<>();
        for (int num : array) {
            numbers.add(num);
        }

        int smallestNonOccurring = 1;
        while (numbers.contains(smallestNonOccurring)) {
            smallestNonOccurring++;
        }

        return smallestNonOccurring;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of elements in the array:");
        int n = scanner.nextInt();

        int[] array = new int[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        scanner.close();

        int result = findSmallestNonOccurringInteger(array);
        System.out.println("The smallest non-occurring integer is: " + result);
    }
}


  /* Method: public static int findSmallestNonOccurringInteger(int[] array)
   Initialization:

  Set<Integer> numbers = new HashSet<>();: Creates a HashSet to store the elements of the array, allowing for O(1) average time complexity for checks and insertions.   Populating the Set:

   for (int num : array) { numbers.add(num); }: Iterates through the array and adds each element to the HashSet. This ensures that duplicate elements are ignored.
  Finding the Smallest Non-Occurring Integer:

 int smallestNonOccurring = 1;: Initializes the smallest non-occurring integer to 1.
  while (numbers.contains(smallestNonOccurring)) { smallestNonOccurring++; }: Checks if the current smallest non-occurring integer is in the HashSet. If it is, increments the integer and checks again, until it finds an integer that is not in the set.
   Return:  return smallestNonOccurring;: Returns the smallest non-occurring integer.
    Main Method: public static void main(String[] args)
   Reading Input:
    Scanner scanner = new Scanner(System.in);: Initializes a Scanner to read input from the console.
   System.out.println("Enter the number of elements in the array:");: Prompts the user to enter the number of elements in the array.
  int n = scanner.nextInt();: Reads the number of elements.
  int[] array = new int[n];: Initializes an array of size n.
            System.out.println("Enter the elements of the array:");: Prompts the user to enter the elements of the array.
         for (int i = 0; i < n; i++) { array[i] = scanner.nextInt(); }: Reads the elements of the array from the console.
 Closing Scanner:

           scanner.close();: Closes the Scanner to free up resources.
  Calling the Method and Printing the Result:    int result = findSmallestNonOccurringInteger(array);: Calls the findSmallestNonOccurringInteger method with the input array and stores the result.
            System.out.println("The smallest non-occurring integer is: " + result);: Prints the result.
   This code reads an array of integers from the user, finds the smallest positive integer that is not in the array, and prints the result.

   */

