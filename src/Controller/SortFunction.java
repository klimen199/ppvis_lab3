package Controller;

import java.util.Arrays;

public class SortFunction {

    double getY(int elemNum){
        System.out.print(elemNum);
        int[] list = new int[elemNum];
        long allTime = 0;
        int repeat = 1000;

        for (int repeatNum = 0; repeatNum < repeat; repeatNum++){
            for (int i=0; i<elemNum; i++)
                list[i] = (int) (Math.random() * 100 + 1);
            long beginTime = System.nanoTime();
            sortMerge(list);
            long processingTime = System.nanoTime()-beginTime;
            allTime += processingTime;
        }
        double averageTime = (double) allTime/(repeat*1000); // time in microsec
        System.out.println("time 1 " + averageTime);
        return averageTime;
    }

    private int[] sortMerge(int[] arr) {
        int len = arr.length;
        if (len < 2) return arr;
        int middle = len / 2;
        return merge(sortMerge(Arrays.copyOfRange(arr, 0, middle)),
                sortMerge(Arrays.copyOfRange(arr, middle, len)));
    }
    private int[] merge(int[] arr_1, int[] arr_2) {
        int len_1 = arr_1.length, len_2 = arr_2.length;
        int a = 0, b = 0, len = len_1 + len_2; // a, b - счетчики в массивах
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            if (b < len_2 && a < len_1) {
                if (arr_1[a] > arr_2[b])
                    result[i] = arr_2[b++];
                else result[i] = arr_1[a++];
            } else if (b < len_2) {
                result[i] = arr_2[b++];
            } else {
                result[i] = arr_1[a++];
            }
        }
        return result;
    }
}
