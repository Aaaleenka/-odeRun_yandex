import java.io.*;
import java.util.*;

class Graph {

    //список ребер
    private LinkedList<Integer> vertices[];
    private boolean visited[];
    private boolean visitedAll[];
    private ArrayList<ArrayList<Integer>> components;
    private ArrayList<Integer> al;

    Graph(int n) {
        vertices = new LinkedList[n];
        visitedAll = new boolean[n];
        this.components = new ArrayList<>();

        for (int i = 0; i < n; i++)
            vertices[i] = new LinkedList();
    }

    void addVertices(int i, int j) {
        vertices[i].add(j);
        vertices[j].add(i);
    }

    void DFSUtil(int n) {

        for (int i = 0; i < n; i++){

            if (!visitedAll[i]) {
                this.visited = new boolean[n];
                this.al = new ArrayList<>();
                DFS(i);
                components.add(al);
            }

        }
    }

    void DFS(int vertex) {
        this.visited[vertex] = true;
        this.visitedAll[vertex] = true;
        al.add(vertex);

        Iterator<Integer> ite = vertices[vertex].listIterator();

        while (ite.hasNext()){
            int adj = ite.next();
            if (!visited[adj])  DFS(adj);
        }
    }

    public void printAnswer() throws IOException{
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        writer.write(""+components.size());
        writer.newLine();
        for (ArrayList<Integer> list : components)
        {
            writer.write(""+list.size());
            writer.newLine();
            for (Integer x : list){
                writer.write(""+(x + 1) + " ");
            }
            writer.newLine();
        }
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

        a.DFSUtil(n);
        a.printAnswer();

        reader.close();
    }
}