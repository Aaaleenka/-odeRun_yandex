import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] a = new int[3];
        String[] parts = reader.readLine().split(" ");
        for (int i = 0; i < parts.length; i++){
            a[i] = Integer.parseInt(parts[i]);
        }
        Arrays.sort(a);
        System.out.println(a[1]);
        reader.close();
        writer.close();
    }
}