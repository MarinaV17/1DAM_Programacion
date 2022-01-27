package ui;

import dao.DaoProducto;
import modelo.Producto;

import java.util.Scanner;

public class UIProductos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DaoProducto daoProducto = new DaoProducto();
        UIProductos uiProductos = new UIProductos();
        int opcion;
        System.out.println("Bienvenido administrador");
        System.out.println();

        do {
            opcion = uiProductos.mostrarMenu(sc);
            System.out.println();
            switch (opcion) {
                case 1:
                    //agregar productos
                    uiProductos.agregarProducto(sc, daoProducto);
                    break;
                case 2:
                    // modificar prod
                    uiProductos.modificarProducto(sc, daoProducto);
                    break;
                case 3:
                    // eliminar prod
                    uiProductos.eliminarProducto(sc, daoProducto);
                    break;
                case 4:
                    // mostrar productos
                    System.out.println(daoProducto);
                    System.out.println();
                    break;
                case 5:
                    // salir
                    System.out.println("Chau!");
                    break;
                default:
                    break;
            }
        } while (opcion != 5);
    }


    private int mostrarMenu(Scanner sc) {
        int opcion;
        do {
            System.out.println("Elija una opcion:");
            System.out.println("1 - Agregar Productos");
            System.out.println("2 - Modificar Productos");
            System.out.println("3 - Eliminar Productos");
            System.out.println("4 - Ver todos los productos");
            System.out.println("5 - Salir");
            opcion = sc.nextInt();
            sc.nextLine();
        } while (opcion < 1 || opcion > 5);
        return opcion;
    }

    private void agregarProducto(Scanner sc, DaoProducto dao) {
        System.out.println("AGREGAR PRODUCTOS");
        System.out.println();
        String nomProd;
        int stockProd;
        double precioProd;
        int opcion;
        do {
//            do {
                System.out.println("Ingrese nombre del producto:");
                nomProd = sc.nextLine();
            //} while (nomProd.equals(""));
            System.out.println();

            //do {
                System.out.println("Ingrese precio del producto:");
                precioProd = sc.nextDouble();
                System.out.println();
            //} while (precioProd < 0);

            //do {
                System.out.println("Ingrese stock del producto:");
                stockProd = sc.nextInt();
                sc.nextLine();
                System.out.println();
           // } while (stockProd < 0);


            Producto unProd = new Producto(nomProd, precioProd, stockProd);

            if (dao.agregarProducto(unProd)) {
                System.out.println("Se agrego " + unProd + " a la lista de productos disponibles");
            } else {
                System.out.println("El producto ya se encontraba en la lista de productos, por lo que no se agrego");
            }

            System.out.println();
            System.out.println("Ingrese 1 si quiere agregar otro producto o cualquier otro numero si quiere salir");
            opcion = sc.nextInt();
            sc.nextLine();
        } while (opcion == 1);
        System.out.println();
        System.out.println("Saliendo de agregar producto...");
        System.out.println();
    }

    private void eliminarProducto(Scanner sc, DaoProducto dao) {
        String nomProducto;
        System.out.println("ELIMINAR PRODUCTOS");
        System.out.println();
        int opcion;
        do {
            System.out.println("Ingrese el nombre del producto que desea eliminar: ");
            nomProducto = sc.nextLine();

            if (dao.eliminarProducto(nomProducto)) {
                System.out.println("Se elimino " + nomProducto + " de la lista de productos");
            } else {
                System.out.println("El producto " + nomProducto + "no se encontraba en la lista de productos, por lo que no se elimino");
            }
            System.out.println();
            System.out.println("Ingrese 1 si quiere continuar o cualquier otro numero si quiere salir");
            opcion = sc.nextInt();
            sc.nextLine();
        } while (opcion == 1);
        System.out.println();
        System.out.println("Saliendo de eliminar producto...");
        System.out.println();
    }

    private void modificarProducto(Scanner sc, DaoProducto daoProducto) {
        int opcionModi;
        do{
            System.out.println("MODIFICAR PRODUCTOS");
            System.out.println();
            System.out.println("Seleccione una opcion:");
            System.out.println();
            System.out.println("1 - Modificar un producto entero (nombre, stock y precio)");
            System.out.println("2 - Modificar el nombre de un producto");
            System.out.println("3 - Modificar el precio de un producto");
            System.out.println("4 - Modificar el stock de un producto");
            System.out.println("5 - Volver al inicio");

            opcionModi = sc.nextInt();
            sc.nextLine();
            System.out.println();
            switchModificar(sc, daoProducto, opcionModi);


        } while (opcionModi != 5);
        System.out.println();
        System.out.println("Saliendo de modificar producto...");
        System.out.println();
    }

    private void switchModificar(Scanner sc, DaoProducto daoProducto, int opcionModi) {
        String nomProdMod;
        if (opcionModi != 5){
                System.out.println("Ingrese el nombre del producto a modificar. Recuerde que tiene que estar previamente cargado en la lista de productos. Para volver ingrese 0");
                nomProdMod = sc.nextLine();
                //las variables precio y stock no son necesarias a la hora de buscar un producto, por lo que no se le piden al usuario
            if (!nomProdMod.equals("0")){
                switch (opcionModi){
                    case 1:
                        // pedir nuevo nombre
                        modificarProductoEntero(sc, daoProducto, nomProdMod);
                        break;
                    case 2:
                        // pedir nuevo nombre
                        modificarNombreProducto(sc, daoProducto, nomProdMod);
                        // cambiar solo nombre
                        break;
                    case 3:
                        // pedir nuevo precio
                        modificarPrecioProducto(sc, daoProducto, nomProdMod);
                        break;
                    case 4:
                        // pedir stock
                        modificarStockProducto(sc, daoProducto, nomProdMod);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void modificarStockProducto(Scanner sc, DaoProducto daoProducto, String nomProdMod) {
        int nuevoStockProd;
        do {
            System.out.println("Ingrese el nuevo stock que tendra el/la " + nomProdMod +": ");
            nuevoStockProd = sc.nextInt();
        }while (nuevoStockProd < 0);
        // cambiar solo el stock
        if (daoProducto.modificarProductoStock(nomProdMod, nuevoStockProd)){
            System.out.println("Se modifico el producto. Ahora es " + daoProducto.getProducto(nomProdMod));
        }
        else {
            System.out.println("No se encontro el producto en nuestra lista de productos o el stock ingresado es menor que 0. Intente nuevamente");
        }
    }

    private void modificarPrecioProducto(Scanner sc, DaoProducto daoProducto, String nomProdMod) {
        double nuevoPrecioProd;
        do{
            System.out.println("Ingrese el nuevo precio que tendra el/la " + nomProdMod +": ");
            nuevoPrecioProd = sc.nextDouble();
        }while (nuevoPrecioProd < 0);
        // cambiar solo el precio
        daoProducto.modificarProductoPrecio(nomProdMod, nuevoPrecioProd);
    }

    private void modificarNombreProducto(Scanner sc, DaoProducto daoProducto, String nomProdMod) {
        String nuevoNombreProd;
        do{
            System.out.println("Ingrese el nuevo nombre que tendra el/la " + nomProdMod +": ");
            nuevoNombreProd = sc.nextLine();
        }while (nuevoNombreProd.equals(""));
        daoProducto.modificarProductoNombre(nomProdMod, nuevoNombreProd);
    }

    private void modificarProductoEntero(Scanner sc, DaoProducto daoProducto, String nomProdMod) {
        double nuevoPrecioProd;
        int nuevoStockProd;
        String nuevoNombreProd;
        //do{
            System.out.println("Ingrese el nuevo nombre que tendra el/la " + nomProdMod +": ");
            nuevoNombreProd = sc.nextLine();
        //}while (nuevoNombreProd.equals(""));

        // pedir precio
        //do{
            System.out.println("Ingrese el nuevo precio que tendra el/la " + nomProdMod +": ");
            nuevoPrecioProd = sc.nextDouble();
        //}while (nuevoPrecioProd < 0);

        // pedir stock
        //do {
            System.out.println("Ingrese el nuevo stock que tendra el/la " + nomProdMod +": ");
            nuevoStockProd = sc.nextInt();
        //}while (nuevoStockProd < 0);

        // cambiar
        Producto prodNuevo = new Producto(nuevoNombreProd, nuevoPrecioProd, nuevoStockProd);
        daoProducto.modificarProducto(prodNuevo, nomProdMod);
    }

}
