import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine()); //читаем сколько гоблинов в очереди
        ArrayList<Integer> queue = new ArrayList<>();
        while (n != 0) {
            String[] parts = reader.readLine().split(" ");

            if (parts[0].equals("+")) queue.add(Integer.parseInt(parts[1]));
            if (parts[0].equals("*")) {
                int middle = queue.size() / 2;
                if (queue.size() % 2 == 0) {
                    queue.add(middle, Integer.parseInt(parts[1]));
                } else queue.add(middle + 1, Integer.parseInt(parts[1]));

            }
            if (parts[0].equals("-")) {
                writer.write(queue.get(0).toString());
                writer.newLine();
                queue.remove(0);
            }
            n--;
        }

        reader.close();
        writer.close();
    }
}