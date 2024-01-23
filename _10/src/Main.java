import java.io.*;
import java.util.*;

class Graph {

    //список ребер
    private LinkedList<Integer> vertices[];
    private boolean visited[];
    private Stack<Integer> stack = new Stack();
    boolean isCircle = false;


    Graph(int n) {
        vertices = new LinkedList[n];
        this.visited = new boolean[n];

        for (int i = 0; i < n; i++)
            vertices[i] = new LinkedList();
    }

    void addVertices(int i, int j) {
        vertices[i].add(j);
    }

    void circle(int start, int vertex, boolean[] newVisited) {

        if (!isCircle) {
            newVisited[vertex] = true;
            int i;
            Iterator<Integer> it = vertices[vertex].iterator();
            while (it.hasNext()) {
                i = it.next();
                if ((!isCircle) && (!newVisited[i])) circle(start, i, newVisited);
                else if (start == i) this.isCircle = true;
            }
        }
    }

    // Рекурсивная функция, используемая topologicalSort
    void topologicalSortUtil(int vertex, int n) {

        //  Помечаем текущий узел как посещенный
        visited[vertex] = true;
        Integer i;

        // Рекурсивно вызываем функцию для всех смежных вершин
        Iterator<Integer> it = vertices[vertex].iterator();
        while (it.hasNext()) {
            i = it.next();
            if (!visited[i]) topologicalSortUtil(i, n);
                //проверяем есть ли цикл
            else {
                boolean newVisited[] = new boolean[n];
                circle(i,i, newVisited);
            }
        }
        stack.push(vertex);
    }

    // Функция для поиска топологической сортировки.
    void topologicalSort(int n) {

        for (int i = 0; i < n; i++)
            if ((!isCircle) && (visited[i] == false)) topologicalSortUtil(i, n);

    }

    public void printAnswer() throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        if (isCircle) writer.write("-1");
        else {
            while (stack.empty() == false)
                writer.write((stack.pop() + 1)+ " ");
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

        a.topologicalSort(n);
        a.printAnswer();
        reader.close();
    }
}