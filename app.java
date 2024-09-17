import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class app {

    public static Object[] arrayLinhas() throws FileNotFoundException {
        try(Scanner scanner = new Scanner(new File("codigo-fonte.txt"));){
            ArrayList<String> array = new ArrayList<String>();

            String linha;
            while (scanner.hasNextLine()) {
                linha = scanner.nextLine();
                array.add(linha);
            }
            String[] result = new String[array.size()];
            int cont = 0;
            for (String line: array) {
                result[cont] = line;
            }

            return array.toArray();
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("Arquivo não encontrado");
        }

    }

    public static void main(String[] args) throws FileNotFoundException {

        try {
            System.out.println(Arrays.toString(arrayLinhas()));
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("Arquivo não encontrado");
        }

    }

}
