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
        Tree tmp = null;
        for (int i = start; i < end - 1; i++) {
//            result.add(new Tree(null, lNambas.get(i), i, i, level));
            if (lNambas.get(i) != lNambas.get(i + 1)) {
                result.add(new Tree(null, lNambas.get(i), i, i, level));
            } else {
                if (tmp == null)
                    tmp = new Tree(null, null, i, i, level);
            }
        }
        return result;
    }
}

class Tree {
    Tree parent;
    List<String> leaves = new ArrayList<>();
    int start;
    int end;
    int level;

    public Tree(Tree t, String leaf, int start, int end, int level) {
        this.parent = t;
        if (leaf != null)leaves.add(leaf);
        this.start = start;
        this.end = end;
        this.level = level;
    }

    public Tree getParent() {
        return parent;
    }

    public void setParent(Tree parent) {
        this.parent = parent;
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

