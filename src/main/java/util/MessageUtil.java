package util;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.LinkedList;
import java.util.Queue;

public class MessageUtil {
    public static Queue<String> queue = new LinkedList<String>();
    public static Queue<String> sendqueue = new LinkedList<String>();
}
