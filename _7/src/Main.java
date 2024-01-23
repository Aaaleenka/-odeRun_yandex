import java.io.*;
import java.util.Iterator;

class Matrix {

    private int[][] a;
    private boolean visited[];

    //заполнение матрицы графа
    Matrix (int n){
        this.a = new int[n][n];
        this.visited = new boolean[n];
    }

    void addVertices(int i, int j){
        this.a[i][j] = 1;
    }

    void DFS (int vertex){
        this.visited[vertex] = true;

        for (int j = 0; j < a.length; j ++ ){
            if ((a[vertex][j] == 1) && (vertex != j) && (!visited[j])) DFS(j);
        }
    }

    public boolean[] getVisited(){
        return visited;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String parts[] = reader.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);

        Matrix a = new Matrix(n);

        for (int i = 0; i < m; i++){
            String parts1[] = reader.readLine().split(" ");
            int ii = Integer.parseInt(parts1[0]) - 1;
            int jj = Integer.parseInt(parts1[1]) - 1;
            a.addVertices(ii,jj);
            a.addVertices(jj,ii);
        }

        a.DFS(0);
        boolean[] visited = a.getVisited();
        int k = 0;

        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) k++;
        }

        System.out.println(k);
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) System.out.print((i + 1) + " ");
        }

        reader.close();
        writer.close();
    }

}