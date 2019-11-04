package io.github.chenfh5.sorters;

public class MergeSorter<T extends Comparable<T>> implements Sorter {

    @Override
    public void sort(Comparable[] list) {
        if (list != null) {
            int start = 0;
            for (int i = 0; i < list.length; i++) {
                if (list[i] == null) {
                    Comparable temp = list[i];
                    list[i] = list[start];
                    list[start] = temp;
                    start++;
                }
            }
            Comparable[] temp = new Comparable[list.length];
            mergerSort(list, start, list.length - 1, temp);

        }
    }

    private void mergerSort(Comparable[] list, int first, int last, Comparable[] temp) {
        if (first < last) {
            int mid = (first + last) / 2;
            mergerSort(list, first, mid, temp);
            mergerSort(list, mid + 1, last, temp);
            merge(list, first, mid, last, temp);
        }

    }

    private void merge(Comparable[] list, int first, int mid, int last, Comparable[] temp) {
        int current = 0;
        int i = first;
        int j = mid + 1;

        while (i <= mid && j <= last) {
            if (list[i].compareTo(list[j]) <= 0) {
                temp[current] = list[i];
                current++;
                i++;
            } else {
                temp[current] = list[j];
                current++;
                j++;
            }
        }
        while (i <= mid) {
            temp[current] = list[i];
            current++;
            i++;
        }
        while (j <= last) {
            temp[current] = list[j];
            current++;
            j++;
        }

        for (i = 0; i < current; i++) {
            list[first + i] = temp[i];
        }
    }

}
