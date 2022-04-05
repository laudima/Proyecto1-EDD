package edd.src.Estructuras;
import java.lang.Math;

public class Practica2 {

    /**
    * Método para simular los movimientos(minimos) de la torre de hanoi
    * 1. Sacamos los movimietos minimos con la formula conocida
    * 2. Agregamos todos los discos en la pila origen de mayor a menor
    * 3. Imprimimos el estado inicial
    * 4. Hacemos todos los movimientos en 2 casos
    *       Caso 1: la cantidad de discos es par
    *       Caso 2: la cantidad de discos es impar
    *
    * En ambos casos para mover el disco vemos el movimeinto modulo 3 y l
    * llamamos a la funcion mueve.
    *
    * @param cantidadDiscos - cantidad total de discos
    * @param origen - pila de origen
    * @param auxiliar - pila auxiliar
    * @param destino - pila de destino
    */
    public static void torresHanoi(int cantidadDiscos,Pila<Integer> origen, Pila<Integer> auxiliar, Pila<Integer> destino){

        //Es el número mínimo de movimientos es 2^n - 1 que haremos
        int total_movientos = (int)Math.pow(2, cantidadDiscos) -1;

        //Agregamos todos los discos al primer pilar (origen)
        for(int i = cantidadDiscos; i>0; i--){
            origen.push(i);
        }

        //Voltea la impresion de la pila para uqe se note el orden de los discos.
        StringBuilder pila = new StringBuilder();
        pila.append(origen.toString());
        pila.reverse();

        //Imprime el estado de inicio
        System.out.println("Tenemos " + cantidadDiscos + " discos");
        System.out.println();
        System.out.println("Movimiento 0");
        System.out.println("===========================================");;
        System.out.println("Pilar origen "+ "\t> " + pila);
        System.out.println("Pilar auxiliar "+ "\t> " + auxiliar.toString());
        System.out.println("Pilar final "+ "\t> " + destino.toString());
        System.out.println("===========================================");

        /*Mueve los discos para hacerlos llegar al pilar de destino.
        dependiendo del movimiento en módulo 3*/
        for(int i=1; i <= total_movientos; i++){

            int movimiento = i % 3;
            int ultimo_disco;

            /*Si la cantidad de discos es impar se intercambian los pilares
            de destino y de origen*/
            if(cantidadDiscos%2==0){
                switch(movimiento){
                    case 1:
                        mueve(origen,auxiliar);
                        break;
                    case 2:
                        mueve(origen, destino);
                        break;
                    case 0:
                        mueve(destino,auxiliar);
                        break;
                }
            }else{
                switch(movimiento){
                    case 1:
                        mueve(origen,destino);
                        break;
                    case 2:
                        mueve(origen, auxiliar);
                        break;
                    case 0:
                        mueve(auxiliar,destino);
                        break;

                }
            }

            /*Voltea el to String de las pilas para que se vea el orden de los
            discos (de mayor a menor)*/
            StringBuilder origen_pila = new StringBuilder();
            origen_pila.append(origen.toString());
            origen_pila.reverse();

            StringBuilder auxiliar_pila = new StringBuilder();
            auxiliar_pila.append(auxiliar.toString());
            auxiliar_pila.reverse();

            StringBuilder destino_pila = new StringBuilder();
            destino_pila.append(destino.toString());
            destino_pila.reverse();

            //Imprime cada movimiento
            System.out.println("Movimiento: " + i);
            System.out.println("Pilar origen" +"\t> " + origen_pila);
            System.out.println("Pilar auxiliar"+"\t> " + auxiliar_pila);
            System.out.println("Pilar final"+ "\t> " + destino_pila);
            System.out.println("=========================================");
        }
    }

    /**
    * Verifica que una pila no este vacía antes de hacer el movimiento,
    * y que se puedan hacer movimietos legales, es decir, no se puede poner
    * un disco grande sobre uno pequeño.
    * @param origen - pila de origen
    * @param destino - pila hacia donde se quieren mover
    */
    private static void mueve(Pila<Integer> origen, Pila<Integer> destino){

        int disco1;
        int disco2;

        /*Si l apila de origen es vacia el disco se mueve de destino -> origen
        si no se mueve de origen -> destino*/
        if(origen.isEmpty()){
            disco1 = destino.pop();
            origen.push(disco1);
        }else if(destino.isEmpty()){
            disco1 = origen.pop();
            destino.push(disco1);
        }else{
            /*Si las dos listas no estan vacías revisa quien es el disco más chico
            y lo cambia de pila */
            disco1 = origen.pop();
            disco2 = destino.pop();
            if(disco1 > disco2){
                origen.push(disco1);
                origen.push(disco2);
            }else{
                destino.push(disco2);
                destino.push(disco1);
            }
        }
    }

    /**
    * Método para imprimir los números en binario del 0 al N
    * Se utiliza el método binario(i) para obtener el número en binario de
    * cada uno de los números.
    */
    public static void binarioColas(int N){
        for(int i=0; i<=N; i++){
            ImprimeConcat(binario(i));
        }
    }

    /**
    * Método recursivo usando colas para obtener el número en binario de un número N.
    * @param número a convertir en binario
    * Tenemos 2 casos para N impar o par.
    * Caso par:
    *       Binario = Binario/2 + 0.
    * Caso impar:
    *       Binario = Binario/2 + 1.
    * Casos base = 0 y 1.
    * @param int N - número a convertir en binario.
    * @return Cola<Int> - número en binario.
    */
    private static Cola<Integer> binario(int N){
        Cola<Integer> binario = new Cola<Integer>();

        if(N==0){
            binario.push(0);
        }else if(N==1){
            binario.push(1);
        }else{
            if(N%2==0){
                binario = binario(N/2);
                binario.push(0);
            }else if(N%2==1){
                binario = binario(N/2);
                binario.push(1);
            }
        }
        return binario;
    }

    /**Método para imprmir de forma concatenada los elementos de una cola
    * @param Cola<Integer> cola - cola a imprimir. 
    */
    private static void ImprimeConcat(Cola<Integer> cola){
        String imprime_cola="";
        while(!(cola.isEmpty())){
            imprime_cola += cola.pop();
        }
        System.out.println(imprime_cola);
    }

    public static void main(String[] args) {
        // Escribe aqui tu codigo para probar los metodos anteriores.
        // No olvides comentar tu codigo y escribir tu nombre en el readme.

        /*Prueba de clase Pila y Cola*/

        Cola<Integer> numeros_cola = new Cola<Integer>();
        Pila<Integer> numeros_pila = new Pila<Integer>();
        for(int i=0; i<=10; i++){
            numeros_pila.push(i);
            numeros_cola.push(i);
        }

        System.out.println("---------------------------------------------");
        System.out.println("Test Push Pop");
        System.out.println();
        System.out.println("Push pila:"+ numeros_pila.toString());
        System.out.println("Push cola:"+ numeros_cola.toString());

        System.out.println("---------------------------------------------");

        Pila<Integer> numeros_copia1 = new Pila<Integer>();
        Cola<Integer> numeros_copia2 = new Cola<Integer>();

        numeros_copia1 = numeros_pila.clone();
        numeros_copia2 = numeros_cola.clone();

        System.out.println("Clone pila:" + numeros_copia1.toString());
        System.out.println("Clone cola:" + numeros_copia2.toString());

        System.out.println("---------------------------------------------");

        numeros_copia1.pop();
        numeros_copia2.pop();

        System.out.println("Clone pila:" + numeros_copia1.toString());
        System.out.println("Clone cola:" + numeros_copia2.toString());
        System.out.println("---------------------------------------------");


        /*Prueba binario*/
        int N=10;
        System.out.println("---------------------------------------------");
        System.out.println("Test Binario Cola con N = " + N );
        System.out.println();

        binarioColas(N);

        /*Prueba Torre de hanoi*/
        int discos =3;
        System.out.println("---------------------------------------------");
        System.out.println("Test Torre de Hanoi con " + discos + "discos");
        System.out.println();


        Pila<Integer> origen = new Pila<Integer>();
        Pila<Integer> auxiliar = new Pila<Integer>();
        Pila<Integer> destino = new Pila<Integer>();

        torresHanoi(discos,origen,auxiliar,destino);
    }

}
