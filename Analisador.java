import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Analisador {

    ArrayList<Object> tokens;
    ArrayList<Object> simbolos;
    private Object[] lexemas;
    private Object[] palavras_reservadas = {"int", "float", "char","boolean", "void", "if", "else", "for", "while", "scanf", "println", "main", "return"};
    private Object[] simbolos_especiais = {"(",")","[","]","{","}",";", ",", "="};
    private Object[] operadores_aritmeticos = {"+", "-","/", "*", "%"};
    private Object[] operadores_relacionais = {"<", ">", "<=", ">=", "==", "!=",};
    private Object[] operadores_logicos = {"&&", "||", "!"};

    public Analisador(Object[] lexemas) {
        leituraLinha leitor = new leituraLinha();
        this.lexemas = lexemas;
        tokens = new ArrayList<>();
        simbolos = new ArrayList<>();
    }

    public void analise() throws TokenDesconhecidoException{
        // Analisar cada lexema
        for (Object lex : this.lexemas) {

            if (isPalavraReservada(lex.toString())) {
                this.tokens.add(lex.toString());
            } else if (isSimboloEspecial(lex.toString())) {
                this.tokens.add(lex.toString());
            } else if(isOperadorAritmetico(lex.toString())){
                this.tokens.add(lex.toString()); 
            } else if(isOperadorLogico(lex.toString())){
                this.tokens.add(lex.toString());
            }else if(isOperadorRelacional(lex.toString())){
                this.tokens.add("COMP_" + lex.toString());
            }else if (isIntNUm(lex.toString())) {
                this.tokens.add("NUM_INT_" + lex.toString());
            } else if (isDecNum(lex.toString())) {
                this.tokens.add("NUM_DEC_" + lex.toString());
            } else if (isId(lex.toString())) {
                //verifica se o id ja esta na tabela de simbolos
                if(!simbolos.contains(lex.toString())){
                    simbolos.add(lex.toString());
                }
                this.tokens.add("ID_" + simbolos.indexOf(lex.toString()));

            } else if (isText(lex.toString())) {
                this.tokens.add("TEXTO_" + lex.toString());
            } else {
                throw new TokenDesconhecidoException(lex.toString());
            }
        }
    }

    private boolean isOperadorRelacional(String string){
        for (Object operador : this.operadores_relacionais) {
            if (operador.toString().equals(string)) {
                return true;
            }
        }
        return false;
    }

    private boolean isOperadorLogico(String string) {
        for (Object operador : this.operadores_logicos) {
            if (operador.toString().equals(string)) {
                return true;
            }
        }
        return false;
    }

    private boolean isOperadorAritmetico(String string) {
        for (Object operador : this.operadores_aritmeticos) {
            if (operador.toString().equals(string)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSimboloEspecial(String string) {
        for (Object simbolo : this.simbolos_especiais) {
            if (simbolo.toString().equals(string)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPalavraReservada(String string) {
        for (Object palavra : this.palavras_reservadas) {
            if (palavra.toString().equals(string)) {
                return true;
            }
        }
        return false;
    }

    private  boolean isIntNUm(String value){
        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    private boolean isDecNum(String value){
        Pattern pattern = Pattern.compile("^\\d+(\\.\\d+)?$");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    private boolean isId(String value){
        Pattern pattern = Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_]*$");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    private boolean isText(String value){
        Pattern pattern = Pattern.compile("^\".*\"$");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public Object[] getLista_de_tokens() {
        return  this.tokens.toArray();
    }

    public Object[] getTabela_de_simbolos() {
        return this.simbolos.toArray();
    }

}
