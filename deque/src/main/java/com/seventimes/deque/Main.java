package com.seventimes.deque;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7));

        Iterator<Integer> iterator1 = list.iterator();

        System.out.println(iterator1.next());

        list.remove(2);

        Iterator<Integer> iterator2 = list.iterator();

        System.out.println(iterator2.next());
        list.remove(1);

        System.out.println(iterator1.next());
    }

}
