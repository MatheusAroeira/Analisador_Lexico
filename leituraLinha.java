import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
// import java.util.Arrays;

public class leituraLinha {

    public Object[] arrayLinhas() throws FileNotFoundException {
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

            //remove comentarios em linha
            arrayLinha.removeIf(lexema -> lexema.charAt(0) == '/' && lexema.charAt(1) == '/');

            //remove comentarios em bloco
            for (int i = 0; i < arrayLinha.size(); i++) {
                String lex = arrayLinha.get(i);
                if(lex.charAt(0) == '/'){
                    for (int j = i; j < arrayLinha.size(); j++) {
                        String lexRemove = arrayLinha.get(i);
                        if(lexRemove.charAt(lexRemove.length() - 1) == '/'){
                            arrayLinha.remove(i);
                            break;
                        }
                        arrayLinha.remove(i);
                    }
                }
            }

            return arrayLinha.toArray();
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("Arquivo não encontrado");
        }

    }

    public Object[] getLexemas() throws FileNotFoundException{
        try {
            ArrayList<String> lexList = new ArrayList<>();
            Object[] arrayLinhas = this.arrayLinhas();
            StringBuffer lexBuffer;

            //separa os lexemas
            for (Object linhas : arrayLinhas) {
                lexBuffer = new StringBuffer();
                for (int i = 0; i < linhas.toString().length(); i++) {
                    char c = linhas.toString().charAt(i);
                    if(c == '\"'){
                        lexBuffer.append(c);
                       do {
                            i++;
                            c = linhas.toString().charAt(i);
                            lexBuffer.append(c);
                        } while(c != '\"');
                        lexList.add(lexBuffer.toString());
                    }else if (c == ' ') {
                        lexList.add(lexBuffer.toString());
                        lexBuffer.setLength(0);
                    }else if(c == '(' || c == ')' || c == '{' || c == '}' || c == '[' || c == ']' || c ==';' || c == ',' || c == '=' || c == '<' || c == '>' || c == '+' || c == '-' || c == '/' || c == '%' || c == ':'){
                        lexList.add(lexBuffer.toString());
                        lexBuffer.setLength(0);
                        lexBuffer.append(c);
                        lexList.add(lexBuffer.toString());
                        lexBuffer.setLength(0);
                    } else {
                        lexBuffer.append(c);
                    }

                }
                lexList.removeIf(lex -> lex.isBlank() || lex.isEmpty());
            }
            
            return lexList.toArray();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Arquivo não encontrado");
        }
    }

    // public static void main(String[] args) throws FileNotFoundException {

    //     try {
    //         System.out.println(Arrays.toString(getLexemas()));
    //     } catch (FileNotFoundException e){
    //         throw new FileNotFoundException("Arquivo não encontrado");
    //     }

    // }

}
