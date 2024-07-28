package util;

public class LettersManager {

    
    public static String escape(String s){
        String res = s;
        res = res.replaceAll("<", "&lt;");
        res = res.replaceAll(">", "&gt;");
        res = res.replaceAll("\"", "&quot;");
        return res;
    }

    public static String sortableWord(String word){
        String res = word;
        res = res.replace("İ", "I");
        res = res.replace("Á", "A");
        res = res.replace("ñ", "n");
        res = res.replace("í", "i");
        res = res.replace("é", "e");
        res = res.replace("ü", "u");
        res = res.replace("ğ", "g");
        res = res.replace("á", "a");
        res = res.replace("ı", "i");
        res = res.replace("ã", "a");
        res = res.replace(" ", "");
        res = res.toLowerCase();
        return res;
    }
    
}
