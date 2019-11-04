package io.github.chenfh5.sorters;

public class HeapSorter<T extends Comparable<T>> implements Sorter {

    @Override
    public void sort(Comparable[] list) {
        int n = list.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(list, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            Comparable temp = list[0];
            list[0] = list[i];
            list[i] = temp;

            // call max heapify on the reduced heap
            heapify(list, i, 0);
        }
    }

    private void heapify(Comparable[] list, int n, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && list[l].compareTo(list[largest]) > 0)
            largest = l;

        // If right child is larger than largest so far
        if (r < n && list[r].compareTo(list[largest]) > 0)
            largest = r;

        // If largest is not root
        if (largest != i) {
            Comparable temp = list[i];
            list[i] = list[largest];
            list[largest] = temp;

            // Recursively heapify the affected sub-tree
            heapify(list, n, largest);
        }
    }

}
