package ui;

import ui.common.Constantes;

import java.util.Scanner;

public class MainClientes {
    public void inicioMenuClientes(){
        System.out.println("BIENVENIDO AL MENU DE LOS CLIENTES");
        Scanner sc = new Scanner(System.in);
        int opcion;
        UIClienteCompras uiClienteCompras = new UIClienteCompras();
        UIClienteTarjetas uiClienteTarjetas = new UIClienteTarjetas();


        System.out.println("Bienvenido Cliente");

        do {
            do {
                System.out.println("Elige una opción: \n " +
                        "1 - Comprar \n " +
                        "2 - Ver mis Compras previas \n " +
                        "3 - Administrar mis tarjetas \n " +
                        "4 - Salir");
                opcion = sc.nextInt();
            } while (opcion < 1 || opcion > 3);
            sc.nextLine();
            System.out.println();
            switch (opcion) {
                case 1:
                    //ir a la ui de compras
                    uiClienteCompras.inicioUICompras();
                    break;
                case 2:
                    // ver las comprar previas
                case 3:
                    //ir a la ui de tarjetas
                    uiClienteTarjetas.inicioUITarjetas();
                    break;
                case 4:
                    System.out.println(Constantes.CHAU);
                    break;
                default:
                    break;
            }
        } while (opcion != 3);
    }
}