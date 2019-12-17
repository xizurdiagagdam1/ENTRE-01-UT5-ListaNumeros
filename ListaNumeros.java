import java.util.Arrays;
/**
 * La clase encapsula en un array
 * una lista de numeros
 * 
 * @author - 
 * 
 */

public class ListaNumeros 
{
    public static final int TAM_LISTA = 16;
    private int[] numeros;  
    private int pos;  

    /**
     * Constructor de la clase ListaNumeros 
     * Crea e inicializa adecuadamente los
     * atributos
     * 
     * @param n el tamaño máximo del array
     */
    public ListaNumeros(int n) 
    {
        if (n > TAM_LISTA || n < 0) {
            throw new IllegalArgumentException("Valor no permitido para tamaño lista");
        }else{
            numeros = new int[n];
        }
        pos = 0;
    }

    /**
     * @param numero el valor que se añade al final de numeros. No se hace nada si
     *               el array está completo o ya está el elemento
     * @return true si se ha podido añadir, false en otro caso
     * 
     * asumimos que numero es >= 0 (no hay que comprobarlo)
     */
    public boolean addElemento(int numero)
    {
        if(pos < numeros.length){
            numeros[pos] = numero;
            pos ++;
            return true;
        }
        return false;
    }

    /**
     * devuelve true si numeros está completo, false en otro caso Hazlo sin if
     */
    public boolean estaCompleta()
    {
        return pos < numeros.length;
    }

    /**
     * devuelve true si la lista está vacía, false en otro caso. Hazlo sin if
     */
    public boolean estaVacia() 
    {
        return pos==0;
    }

    /**
     * devuelve el nº de elementos realmente guardados en la lista
     */
    public int getTotalNumeros()
    {
        return pos;
    }

    /**
     * Vacía la lista
     */
    public void vaciarLista() 
    {
        for(int a = 0; a <= pos; a++){
            numeros[a] = 0;
        }
        pos = 0;
    }

    /**
     * @param numero el valor a buscar
     * @return true si se encuentra, false en otro caso
     */
    public boolean estaElemento(int numero) 
    {
        for(int a = 0; a < pos; a++){
            if(numeros[a] == numero){
                return true;
            }
        }
        return false;
    }

    /**
     * Representación textual de la lista de la forma indicada  (ver enunciado)
     * Si numeros = {14, 8, 13, 9, 11, 5, 3, 10, 7, 1}
     *  devuelve | 14 | 8 | 13 | 9 | 11 | 5 | 3 | 10 | 7 | 1 |
     * 
     * Si la lista está vacía devuelve | |
     */
    public String toString() 
    {
        String str = "";
        if(estaVacia()){
            System.out.printf("%s","||");
        }else{
            for(int a = 0; a < pos; a++){
                str += String.format("%s %d","|",numeros[a]);
            }
            str += String.format("%s","|");
        }
        return str;
    }

    /**
     * Mostrar en pantalla la lista
     */
    public void escribirLista() 
    {
        System.out.println(this.toString());
    }

    /**
     * a partir de un array de pares contador/valor crea y devuelve
     * un nuevo array resultado de expandir estos pares contador/valor
     *  
     *   Si numeros =  {3, 8, 4, 2, 0, 42, 5, 1}
     *                  |  |  |  |  |   |  |  | 
     *                  +--+  +--+  +---+  +--+ 
     *                  par    par    par   par 
     * 
     *  se devuelve: {8, 8, 8, 2, 2, 2, 2, 1, 1, 1, 1, 1}
     * (ver detalles en el enunciado)
     */
    public int[] expandir() {
        if(esImpar(numeros.length)){
            throw new RuntimeException("Nº impar de elementos en el array, añada uno más");
        }else{
            int elementos = 0;
            for(int a = 0; a < pos; a += 2){
                elementos += numeros[a]; 
            }
            int[]expandido = new int[elementos];
            int posicion = 0;
            for(int b = 0; b < pos; b += 2){
                for(int c = 0; c < numeros[b]; c++){
                    expandido[posicion] = numeros[b + 1];
                    posicion ++;
                }
            }
            return expandido;
        }
    }

    /**
     * @param valor el nº a analizar
     * @return true si valor es impar, false en otro caso
     */
    private static boolean esImpar(int valor) {
        return valor % 2 != 0;
    }

    /**
     *  Modifica la lista reorganizando los valores pares e impares, los pares se
     *  colocan al principio y los impares al final. Se mantiene el orden de ambos
     *  
     *  Se hará recorriendo una sola vez la lista y sin  usar ningún otro array auxiliar
     * 
     *  Si numeros = {3, 7, 4, 9, 2, 5, 8, 11, 13} 
     *  después de reorganizarParesImpares() quedaría {4, 2, 8, 3, 7, 9, 5, 11, 13}
     */
    public void reorganizarParesImpares() {
        int b = 0;
        for(int a = 0; a < pos; a++){
            if(!esImpar(numeros[a])){
                int cambio = numeros[b];
                numeros[b] = numeros[a];
                numeros[a] = cambio;
                b++;
            }
        }
    }

    /**
     *  Usando métodos de la clase Arrays haz una copia 
     *  de numeros al tamaño indicado por su longitud lógica
     *  Ordena esta copia
     *  Crea y devuelve un nuevo objeto ListaNumeros 
     *  que incluya los elementos del array ordenado
     */
    public ListaNumeros nuevaLista() {
        int[] copia = Arrays.copyOf(numeros, pos);
        Arrays.sort(copia);
        ListaNumeros lista = new ListaNumeros(copia.length);
        for(int a = 0; a < pos; a ++){
            lista.addElemento(copia[a]);
        }
        return lista;
    }

    /**
     * devuelve un array de 2 dimensiones de 4 filas y 4 columnas  
     * y guarda en este array los elementos de numeros tal como indica el enunciado
     * 
     *  Si numeros = {3, 7, 4, 9, 2, 5, 8, 11,0 13}
     *  el nuevo array tendrá { {3, 7, 4, 9},
     *                          {2, 5, 8, 11} ,
     *                          {13, 0, 0, 0} ,
     *                          {0, 0, 0, 0} }
     * 
     */
    public int[][] toArray2D() 
    {
        int[][] numeros2D = new int[4][4];
        int c = 0;
        for(int a = 0; a < 4; a++){
            for(int b = 0; b < 4; b++){
                numeros2D[a][b] = numeros[c];
                c++;
            }
        }
        return numeros2D;
    }

    /**
     * Punto de entrada a la aplicación
     * Contiene código para probar los métodos de ListaNumeros
     */
    public static void main(String[] args) 
    {
        ListaNumeros numeros = new ListaNumeros(10);
        numeros.addElemento(3);
        numeros.addElemento(8);
        numeros.addElemento(4);
        numeros.addElemento(2);
        numeros.addElemento(0);
        numeros.addElemento(42);
        numeros.addElemento(5);
        numeros.addElemento(1);

        System.out.println("Original: " + numeros.toString());
        int[] expandido = numeros.expandir();
        System.out.println("Expandido: " + Arrays.toString(expandido));
        
        ListaNumeros pares = new ListaNumeros(9);
        pares.addElemento(3);
        pares.addElemento(7);
        pares.addElemento(4);
        pares.addElemento(9);
        pares.addElemento(2);
        pares.addElemento(5);
        pares.addElemento(8);
        pares.addElemento(11);
        pares.addElemento(13);
        
        System.out.println("Original: " + pares.toString());
        
        pares.reorganizarParesImpares();
        
        System.out.println("Ordenado: " + pares.toString());
        
        ListaNumeros nueva = numeros.nuevaLista();
        
        System.out.println("Nueva Lista: " + nueva.toString());

    }
}
