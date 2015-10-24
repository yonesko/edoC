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
        //get overlaps
        for (int i = 0; i < lNambas.size() - 1; i++) {
            for (int j = i + 1; j < lNambas.size(); j++) {
                String overlap = leftOverlap(lNambas.get(i), lNambas.get(j));
                if (overlap.length() != 0 && !overlapHeap.contains(overlap))
                    overlapHeap.add(overlap);
            }
        }
        //sort overlaps by common
        Collections.sort(overlapHeap);
        int fn = 0;
        overlapFrst.add(new ArrayList<>());
        for (int i = 0; i < overlapHeap.size(); i++) {
            overlapFrst.get(fn).add(overlapHeap.get(i));
            if (i + 1 != overlapHeap.size()) {
                String overlap = leftOverlap(overlapHeap.get(i), overlapHeap.get(i + 1));
                if (overlap.length() == 0) {
                    fn++;
                    overlapFrst.add(new ArrayList<>());
                }
            }
        }
        //make tree
        System.out.println(overlapFrst);
        for (List<String> names : overlapFrst) {
            Tree root = new Tree(names.get(0));
            forest.add(root);
            String prefix = root.getName();
            Tree prev = root;
            for (int i = 1; i < names.size(); i++) {
                String curName = names.get(i);
                System.out.println(prefix + " " + curName);
                String cutCurName = cutPrefix(prefix, curName);
                String curOverlap = leftOverlap(curName, names.get(i - 1));

                if (curOverlap.equals(prefix)) {
                    root.addChild(prev = new Tree(cutCurName));
                }
                else {
                    prefix = curOverlap;
                    root = prev;
                    root.addChild(prev = new Tree(cutPrefix(prefix, curName)));
                }
            }
        }
        for (Tree tree : forest) {
            tree.show();
            System.out.println();
        }
        //append leaves


    }//main

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
    private List<Tree> children;

    Tree (String name) {
        this.name = name;
        children = new ArrayList<>();
    }
    void addChild(Tree c) {
        if (children == null)
            children = new ArrayList<>();
        children.add(c);
    }

    public String getName() {
        return name;
    }

    private void show(int indent) {
        char[] i = new char[indent];
        Arrays.fill(i, '\t');
        System.out.println(new String(i) + name);

        for (Tree child : children) {
            child.show(indent + 1);
        }
    }

    void show(){
        show(0);
    }
}
