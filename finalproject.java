import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class finalproject {

    // Linear Search Method
    public static int linearSearch(int[] array, int target) {
        int iterations = 0; // Variable to count the number of iterations

        for (int i = 0; i < array.length; i++) {
            iterations++; // Increment the iteration count
            if (array[i] == target) {
                System.out.println("Linear search iterations: " + iterations);
                return i;  // Returns index of found element
            }
        }

        System.out.println("Linear search iterations: " + iterations);
        return -1;  // Target not found
    }

    // Binary Search Method (Iterative)
    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        int iterations = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            iterations++;

            if (array[mid] == target) {
                System.out.println("Binary search iterations: " + iterations);
                return mid;
            }

            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println("Binary search iterations: " + iterations);
        return -1;  // Target not found
    }

    // Binary Search Method (Recursive)
    public static int binarySearchRecursive(int[] array, int target, int left, int right) {
        if (left > right) {
            return -1; // Base case: target not found
        }

        int mid = left + (right - left) / 2;

        if (array[mid] == target) {
            return mid; // Base case: target found
        } else if (array[mid] < target) {
            return binarySearchRecursive(array, target, mid + 1, right); // Recursive call on the right half
        } else {
            return binarySearchRecursive(array, target, left, mid - 1); // Recursive call on the left half
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter number of elements in array:");
            int n = scanner.nextInt();

            if (n <= 0) {
                System.out.println("Number of elements must be greater than zero.");
                return;
            }

            int[] array = new int[n];

            System.out.println("Enter elements:");
            for (int i = 0; i < n; i++) {
                array[i] = scanner.nextInt();
            }

            System.out.println("Enter target number to search:");
            int target = scanner.nextInt();

            // Linear Search
            long startTimeLinear = System.nanoTime();
            int linearResult = linearSearch(array, target);
            long endTimeLinear = System.nanoTime();
            long durationLinear = endTimeLinear - startTimeLinear;
            System.out.println((linearResult == -1) ? "Target not found by linear search." :
                    "Target found by linear search at index: " + linearResult);
            System.out.println("Time taken by linear search: " + durationLinear + " nanoseconds.");

            // Binary Search (Array must be sorted)
            Arrays.sort(array);
            long startTimeBinary = System.nanoTime();
            int binaryResult = binarySearch(array, target);
            long endTimeBinary = System.nanoTime();
            long durationBinary = endTimeBinary - startTimeBinary;
            System.out.println((binaryResult == -1) ? "Target not found by binary search." :
                    "Target found by binary search at index: " + binaryResult);
            System.out.println("Time taken by binary search: " + durationBinary + " nanoseconds.");

            // Binary Search Recursive
            long startTimeRecursive = System.nanoTime();
            int recursiveResult = binarySearchRecursive(array, target, 0, array.length - 1);
            long endTimeRecursive = System.nanoTime();
            long durationRecursive = endTimeRecursive - startTimeRecursive;
            System.out.println((recursiveResult == -1) ? "Target not found by recursive binary search." :
                    "Target found by recursive binary search at index: " + recursiveResult);
            System.out.println("Time taken by recursive binary search: " + durationRecursive + " nanoseconds.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid integers.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}