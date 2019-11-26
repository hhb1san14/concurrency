package com.hhb.concurrency.other.myself;

/**
 * @author: huanghongbo
 * @Date: 2019-10-25 16:31
 * @Description:
 */
public class NodeSort {


    public static void main(String[] args) {

        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();
        node1.setA(1);
        node2.setA(2);
        node3.setA(3);
        node4.setA(4);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);

        Node h = node1;
        while (null != h) {
            System.out.print(h.getA() + " ");
            h = h.getNext();
        }
        Node s = sort(node1);

        while (s != null) {
            System.out.print(s.getA() + " ");
            s = s.getNext();
        }

        for (int i = 0; i < 100; i++) {
            continue;
        }


        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] ints = twoSum(nums, target);
        for (int i = 0; i < ints.length; i++) {
            System.err.println(ints[i]);
        }
    }

    /**
     * 1 2 3 4
     *
     * @param node
     * @return
     */
    public static Node sort(Node node) {
        Node head = node;
        if (head == null) {
            return head;
        }
        Node pre = head; // 1
        Node cur = head.getNext();// 2
        Node tmp;
        while (cur != null) {
            tmp = cur.getNext();
            cur.setNext(pre);
            pre = cur;
            cur = tmp;
        }
        head.setNext(null);
        return pre;
    }


    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (j == i) {
                    continue;
                }

                if (nums[i] + nums[j] == target) {
                    result[0] = nums[i];
                    result[1] = nums[j];
                    return result;
                }
            }
        }
        return result;
    }


}
