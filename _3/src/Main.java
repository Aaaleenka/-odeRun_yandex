import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] parts = reader.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);

        int[][] a = new int[n][m];
        List<Character> way = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] part = reader.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                a[i][j] = Integer.parseInt(part[j]);
            }
        }

        for (int j = 1; j < m; j++) {
            a[0][j] = a[0][j] + a[0][j - 1];
        }
        for (int i = 1; i < n; i++) {
            a[i][0] = a[i][0] + a[i - 1][0];
        }

        for (int i = 1; i< n; i++){
            for (int j = 1; j < m; j++){
                int c = a[i][j] + a[i-1][j];
                int b = a[i][j] + a[i][j-1];
                if (c > b) {
                    a[i][j] = c;
                }
                else a[i][j] = b;
            }
        }

        int i = n-1;
        int j = m-1;

        while ((i != 0) || (j != 0)) {
            if (i == 0) {
                while (j != 0) {
                    j--;
                    way.add('R');
                }
            } else if (j == 0) {
                while (i != 0) {
                    i--;
                    way.add('D');
                }
            } else {

                if (a[i - 1][j] >= a[i][j - 1]) {
                    way.add('D');
                    i--;
                }
                else{
                    way.add('R');
                    j--;
                }
            }
        }

        writer.write(a[n-1][m-1] +"");
        writer.newLine();

        for (int k = way.size()-1; k>=0; k--){
            writer.write(way.get(k) + " ");
        }
        reader.close();
        writer.close();
    }
}