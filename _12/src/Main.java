import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

class Matrix{

    private int a[][];
    private boolean visited[];
    private Queue<Integer> queue1 = new LinkedList();
    private Queue<Integer> queue2 = new LinkedList();

    private int n;
    private boolean flag = false;
    private int s = 0;

    Matrix (int n){
        this.a = new int[n][n];
        this.visited = new boolean[n];
        this.n = n;
    }

    void fullMatrix(int i, int j, int value){
        a[i][j] = value;
    }

    void BFSUtil(int finish) {

            int v = queue1.poll();

            for (int j = 0; j < n; j++) {
                if ((a[v][j] == 1) && (v != j) && (!visited[j])) {
                    queue2.add(j);
                    visited[j] = true;
                }
            }
            if (queue2.contains(finish)) flag = true;
    }

    void BFS (int start, int finish) {

        queue1.add(start);
        visited[start] = true;
        BFSUtil(finish);

        while ((!flag) & (!queue2.isEmpty())) {
            queue1 = new LinkedList<>(queue2);
            queue2.clear();
            s++;
            while (!queue1.isEmpty()) {
                BFSUtil(finish);
            }
        }
    }

    void printAnswer() throws IOException{
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        if (flag) writer.write("" + (s+1));
        else writer.write("-1");
        writer.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        Matrix a = new Matrix(n);

        for (int i = 0; i < n; i++) {
            String parts[] = reader.readLine().split(" ");
            for (int j = 0; j < n; j++)
                a.fullMatrix(i, j, Integer.parseInt(parts[j]));
        }

        String parts[] = reader.readLine().split(" ");
        int start = Integer.parseInt(parts[0]) -1;
        int finish = Integer.parseInt(parts[1]) -1;

        if (start == finish) writer.write("0");
        else {
            a.BFS(start, finish);
            a.printAnswer();
        }

        reader.close();
        writer.close();
    }
}