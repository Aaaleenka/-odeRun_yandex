import java.io.*;
import java.util.*;

class Graph {

    //список ребер
    private LinkedList<Integer> vertices[];
    private int error = 0;
    private boolean visited[];
    private int colored[];

    Graph(int n) {
        vertices = new LinkedList[n];
        this.visited = new boolean[n];
        this.colored = new int[n];

        for (int i = 0; i < n; i++)
            vertices[i] = new LinkedList();
    }

    void addVertices(int i, int j) {
        vertices[i].add(j);
        vertices[j].add(i);
    }

    void DFS(int vertex, int color) {

        visited[vertex] = true;
        colored[vertex] = color;

        Iterator<Integer> ite = vertices[vertex].listIterator();

        while (ite.hasNext()) {
            int adj = ite.next();
            if ((!visited[adj]) && (error == 0)) DFS(adj, 3 - color);
            else if (colored[adj] == colored[vertex]) error = 1;
        }

    }

    public void printAnswer(int n) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < n; i++){
            if (!visited[i]) DFS(i, 1);
            if (error == 1) break;
        }

        if (error == 0) writer.write("YES");
        else  writer.write("NO");

        writer.close();
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String parts[] = reader.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);

        Graph a = new Graph(n);

        for (int i = 0; i < m; i++) {
            String parts1[] = reader.readLine().split(" ");
            a.addVertices(Integer.parseInt(parts1[0]) - 1, Integer.parseInt(parts1[1]) - 1);
        }

        a.printAnswer(n);
        reader.close();
    }
}