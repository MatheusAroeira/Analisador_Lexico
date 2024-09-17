import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class app {

    public static Object[] arrayLinhas() throws FileNotFoundException {
        try(Scanner scanner = new Scanner(new File("codigo-fonte.txt"))){
            ArrayList<String> arrayLinha = new ArrayList<>();
            StringBuffer lexBuffer;
            String linha;

            //faz a leitura linha a linha e coloca num arrayList
            while (scanner.hasNextLine()) {
                linha = scanner.nextLine();
                arrayLinha.add(linha);
            }

            //remove quebra de linhas
            arrayLinha.removeIf(lex -> lex.isEmpty() || lex.isBlank());

            //remove tabulações
            for (String lex: arrayLinha) {
                lexBuffer = new StringBuffer();

                for (int i = 0; i <lex.length() ; i++) {
                    if(lex.charAt(i) != '\t'){
                        lexBuffer.append(lex.charAt(i));
                    }
                }

                arrayLinha.set(arrayLinha.indexOf(lex), lexBuffer.toString());
            }

            return arrayLinha.toArray();
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
