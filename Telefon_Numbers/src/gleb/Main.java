package gleb;

import oracle.sql.CharacterSet;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader bfNambas;
    static List<String> lNambas;
    static List<Tree> forest;
    static List<String> overlapHeap;
    static List<List<String>> overlapFrst;

    public static void main(String[] args) throws IOException {
        bfNambas = new LineNumberReader(new FileReader("resources/fileservlet.txt"));
        lNambas = new ArrayList<>(Integer.parseInt(bfNambas.readLine()));
        forest = new ArrayList<>();
        overlapHeap = new ArrayList<>();
        overlapFrst = new ArrayList<>();
        {
            String s;
            while ((s = bfNambas.readLine()) != null)
                lNambas.add(s);
        }
        Collections.sort(lNambas);
        for (String lNamba : lNambas) {
            System.out.println(lNamba);
        }
        System.out.println();

        deep(null, 0, lNambas.size());

        for (Tree tree : forest) {
            System.out.println(tree);
        }
    }//main

    static void deep(Tree root, int start, int end) {
        System.out.println(root);
        int offset = getOffset(root);
        String overlap = lNambas.get(start).substring(offset);
        Tree tree = new Tree(overlap, root);
        if (root == null) forest.add(tree);

        for (int i = start + 1; i < end; i++) {
            String curNam = lNambas.get(i).substring(offset);
            String candOvlp = leftOverlap(overlap, curNam);

            if (candOvlp.length() == 0) {
                deep(tree, start, i);
            }
            if (candOvlp.length() < overlap.length()) {
                overlap = candOvlp;
                tree.setName(overlap);
            }
        }
    }
    static int getOffset(Tree t) {
        int result = 0;
        if (t != null)
            while ( t != null) {
                result += t.getName().length();
                t = t.getParent();
            }
        return result;
    }
    static String cutPrefix(String prefix, String s) {
            return s.substring(prefix.length());
    }
    static String cutPrefix(StringBuilder prefix, String s) {
        return cutPrefix(new String(prefix), s);
    }
    static Tree getTree(String name) {
        for (Tree tree : forest)
            if (tree.getName().equals(name))
                return tree;
        return null;
    }
    static String leftOverlap(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < Math.min(a.length(), b.length()); i++) {
            if (a.charAt(i) == b.charAt(i))
                result.append(a.charAt(i));
            else
                break;
        }
        return new String(result);
    }
}

class Tree {
    private String name;
    private Tree parent;

    public Tree getParent() {
        return parent;
    }

    Tree (String name, Tree parent) {
        this.name = name;
        this.parent = parent;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParent(Tree parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "name='" + name + '\'' +
                ", parent=" + (parent == null ? null : parent.name) +
                '}';
    }

    void show() {

    }
}
