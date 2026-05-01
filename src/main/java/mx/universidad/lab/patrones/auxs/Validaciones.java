package auxs;

public class Validaciones {
    
    public static boolean validarEntero(int num, int min, int max)
    {
        if(num < min || num > max)
            return false;
        return true;
    }

    public static boolean validarCodigo(String codigo) 
    {
        return codigo.matches("\\d{3}");
    }

    public static boolean validarLinea(String autor) 
    {
        return autor.matches("[a-zA-Z ]+");
    }

    public static boolean validarPalabra(String palabra)
    {
        return palabra.matches("[a-zA-Z]+");
    }


}
