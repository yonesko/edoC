package gleb;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader bfNambas;
    static List<String> lNambas;
    static List<Tree> forest;

    public static void main(String[] args) throws IOException {
        bfNambas = new LineNumberReader(new FileReader("resources/fileservlet2.txt"));
        lNambas = new ArrayList<>(Integer.parseInt(bfNambas.readLine()));
        {
            String s;
            while ((s = bfNambas.readLine()) != null)
                lNambas.add(s);
        }
        Collections.sort(lNambas);

        Queue<Tree> Q = new ArrayDeque<>();
        Q.addAll(scanLevel(0, 0, lNambas.size()));

        while (!Q.isEmpty()) {
            Tree c = Q.remove();
            Q.addAll(scanLevel(c.getLevel() + 1, c.getStart(), c.getEnd()));
        }


    }
    static List<Tree> scanLevel(int level, int start, int end) {
        List<Tree> result = new ArrayList<>();
        for (int i = start; i < end; i++) {

        }
        return result;
    }
}

class Tree {
    Tree t;
    List<String> leaves;
    int start;
    int end;
    int level;

    public Tree getT() {
        return t;
    }

    public void setT(Tree t) {
        this.t = t;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

