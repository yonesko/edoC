package gleb;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader bfNambas;
    static List<String> lNambas;
    static List<Tree> forest;
    static List<List<String>> overlaps;

    public static void main(String[] args) throws IOException {
        bfNambas = new LineNumberReader(new FileReader("resources/fileservlet.txt"));
        lNambas = new ArrayList<>(Integer.parseInt(bfNambas.readLine()));
        overlaps = new ArrayList<>();
        forest = new ArrayList<>();
        {
            String s;
            while ((s = bfNambas.readLine()) != null)
                lNambas.add(s);
        }
        overlaps.add(lNambas);

        for (String lNamba : lNambas) {
            System.out.println(lNamba);
        }


        for (int k = 0;;k++) {
            List<String> lvlOverlaps = new ArrayList<>();
            for (int i = 0; i < overlaps.get(k).size() - 1; i++) {
                for (int j = i + 1; j < overlaps.get(k).size(); j++) {
                    String overlap = leftOverlap(overlaps.get(k).get(i), overlaps.get(k).get(j));
                    if (overlap.length() != 0 && !lvlOverlaps.contains(overlap))
                        lvlOverlaps.add(overlap);
                }
            }
            if (lvlOverlaps.size() == 0)
                break;
            overlaps.add(lvlOverlaps);
        }

        System.out.println(overlaps);

        for (int i = overlaps.size() - 1; i >= 0; i--) {
            List<String> curLvl = overlaps.get(i);
            List<String> prevLvl = i == overlaps.size() - 1 ? null : overlaps.get(i + 1);
            for (int j = 0; j < curLvl.size(); j++) {
                if (i == overlaps.size() - 1) {
                    forest.add(new Tree(null, curLvl.get(j)));
                    continue;
                }
                for (int k = 0; k < prevLvl.size(); k++) {
                    if (curLvl.get(j).startsWith(prevLvl.get(k)))
                        forest.add(new Tree(getTree(prevLvl.get(k)), curLvl.get(j)));
                    else
                        forest.add(new Tree(null, curLvl.get(j)));
                }
            }
        }

        for (Tree tree : forest) {
            System.out.println(tree);
        }

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
    static Tree getTree(String name) {
        for (Tree tree : forest) {
            if (tree.name.equals(name))
                return tree;
        }
        return null;
    }
}

class Tree {
    Tree parent;
    String name;

    public Tree(Tree parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " -> " + (parent == null ? null : parent.name);
    }
}

