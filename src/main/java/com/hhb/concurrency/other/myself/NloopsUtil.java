package com.hhb.concurrency.other.myself;

import java.util.Arrays;

/**
 * @author: huanghongbo
 * @Date: 2019-10-26 12:46
 * @Description:
 */
public class NloopsUtil {


    public static void main(String[] args) {
        int[] array = {2, 2, 2};
        int[] result = new int[array.length - 1];
        loop(array, array.length, result);
    }

    /**
     * 执行递归
     *
     * @param array
     * @param length
     * @param result
     */
    private static void loop(int[] array, int length, int[] result) {
        if (array != null && array.length > 0) {
            if (array.length > 1) {
                // 记录循环次数
                for (int i = 0; i < array[0]; i++) {
                    // 分别记录每一层需要打印的数据
                    result[length - array.length] = i;
                    //将目标一个数组第一层去掉，然后赋值给新数组，这样每层递归调用都会将第一个数据去掉。
                    int[] arr = Arrays.copyOfRange(array, 1, array.length);
                    // 递归调用，达到最后一个原数实现打印数据
                    loop(arr, length, result);
                }
            } else { // 打印数据
                for (int i = 0; i < array[0]; i++) {
                    for (int j = 0; j < result.length; j++) {
                        System.out.print(result[j] + " ");
                    }
                    System.out.println(i);
                }
                return;
            }
        }
        return;
    }

}
