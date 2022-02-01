package ui;

import ui.common.Constantes;
import dao.DaoProductos;
import modelo.Producto;

import java.util.Scanner;

public class UIProductos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DaoProductos daoProductos = new DaoProductos();
        UIProductos uiProductos = new UIProductos();
        int opcion;
        System.out.println(Constantes.BIENVENIDO_ADMINISTRADOR);
        System.out.println();

        do {
            opcion = uiProductos.mostrarMenu(sc);
            System.out.println();
            switch (opcion) {
                case 1:
                    //agregar productos
                    uiProductos.agregarProducto(sc, daoProductos);
                    break;
                case 2:
                    // modificar prod
                    uiProductos.modificarProducto(sc, daoProductos);
                    break;
                case 3:
                    // eliminar prod
                    uiProductos.eliminarProducto(sc, daoProductos);
                    break;
                case 4:
                    // mostrar productos
                    System.out.println(daoProductos);
                    System.out.println();
                    break;
                case 5:
                    // salir
                    System.out.println(Constantes.CHAU);
                    break;
                default:
                    break;
            }
        } while (opcion != 5);
    }


    private int mostrarMenu(Scanner sc) {
        int opcion;
        do {
            System.out.println(Constantes.MENU_ADMINISTRADOR_PRODUCTOS);

            opcion = sc.nextInt();
            sc.nextLine();
        } while (opcion < 1 || opcion > 5);
        return opcion;
    }

    private void agregarProducto(Scanner sc, DaoProductos dao) {
        System.out.println(Constantes.AGREGAR_PRODUCTOS);
        System.out.println();
        String nomProd;
        int stockProd;
        double precioProd;
        int opcion;
        do {
            System.out.println(Constantes.INGRESE_NOMBRE_DEL_PRODUCTO);
            nomProd = sc.nextLine();
            System.out.println();

            System.out.println(Constantes.INGRESE_PRECIO_DEL_PRODUCTO);
            precioProd = sc.nextDouble();
            System.out.println();

            System.out.println(Constantes.INGRESE_STOCK_DEL_PRODUCTO);
            stockProd = sc.nextInt();
            sc.nextLine();
            System.out.println();


            Producto unProd = new Producto(nomProd, precioProd, stockProd);

            if (dao.agregarProducto(unProd)) {
                System.out.println(Constantes.SE_AGREGO + unProd + Constantes.A_LA_LISTA_DE_PRODUCTOS_DISPONIBLES);
            } else {
                System.out.println(Constantes.EL_PRODUCTO_YA_SE_ENCONTRABA_EN_LA_LISTA_DE_PRODUCTOS_O_ALGUNO_DE_SUS_CAMPOS_NO_ERAN_VALIDOS_INTENTE_NUEVAMENTE);
            }

            System.out.println();
            System.out.println(Constantes.INGRESE_1_SI_QUIERE_AGREGAR_OTRO_PRODUCTO_O_CUALQUIER_OTRO_NUMERO_SI_QUIERE_SALIR);
            opcion = sc.nextInt();
            sc.nextLine();
        } while (opcion == 1);
        System.out.println();
        System.out.println(Constantes.SALIENDO_DE_AGREGAR_PRODUCTO);
        System.out.println();
    }

    private void eliminarProducto(Scanner sc, DaoProductos dao) {
        String nomProducto;
        System.out.println(Constantes.ELIMINAR_PRODUCTOS);
        System.out.println();
        int opcion;
        do {
            System.out.println(Constantes.INGRESE_NOMBRE_DEL_PRODUCTO);
            nomProducto = sc.nextLine();

            if (dao.eliminarProducto(nomProducto)) {
                System.out.println("Se elimino " + nomProducto + Constantes.DE_LA_LISTA_DE_PRODUCTOS);
            } else {
                System.out.println(Constantes.EL_PRODUCTO + nomProducto + Constantes.NO_SE_ENCONTRABA_EN_LA_LISTA_DE_PRODUCTOS_POR_LO_QUE_NO_SE_ELIMINO);
            }
            System.out.println();
            System.out.println(Constantes.INGRESE_1_SI_QUIERE_ELIMINAR_OTRO_PRODUCTO_O_CUALQUIER_OTRO_NUMERO_SI_QUIERE_SALIR);
            opcion = sc.nextInt();
            sc.nextLine();
        } while (opcion == 1);
        System.out.println();
        System.out.println(Constantes.SALIENDO_DE_ELIMINAR_PRODUCTO);
        System.out.println();
    }

    private void modificarProducto(Scanner sc, DaoProductos daoProductos) {
        int opcionModi;
        do {
            System.out.println(Constantes.MENU_ADMINISTRADOR_MODIFICAR_PRODUCTOS);

            opcionModi = sc.nextInt();
            sc.nextLine();
            System.out.println();
            switchModificar(sc, daoProductos, opcionModi);


        } while (opcionModi != 5);
        System.out.println();
        System.out.println(Constantes.SALIENDO_DE_MODIFICAR_PRODUCTO);
        System.out.println();
    }

    private void switchModificar(Scanner sc, DaoProductos daoProductos, int opcionModi) {
        String nomProdMod;
        if (opcionModi != 5) {
            System.out.println("Ingrese el nombre del producto a modificar. Recuerde que tiene que estar previamente cargado en la lista de productos. Para volver ingrese 0");
            nomProdMod = sc.nextLine();
            //las variables precio y stock no son necesarias a la hora de buscar un producto, por lo que no se le piden al usuario
            if (!nomProdMod.equals("0")) {
                switch (opcionModi) {
                    case 1:
                        // pedir nuevo nombre
                        modificarProductoEntero(sc, daoProductos, nomProdMod);
                        break;
                    case 2:
                        // pedir nuevo nombre
                        modificarNombreProducto(sc, daoProductos, nomProdMod);
                        // cambiar solo nombre
                        break;
                    case 3:
                        // pedir nuevo precio
                        modificarPrecioProducto(sc, daoProductos, nomProdMod);
                        break;
                    case 4:
                        // pedir stock
                        modificarStockProducto(sc, daoProductos, nomProdMod);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void modificarStockProducto(Scanner sc, DaoProductos daoProductos, String nomProdMod) {
        int nuevoStockProd;
            System.out.println(Constantes.INGRESE_EL_NUEVO_STOCK_QUE_TENDRA_EL_LA + nomProdMod + Constantes.DOS_PUNTOS);
            nuevoStockProd = sc.nextInt();
        // cambiar solo el stock
        if (daoProductos.modificarProductoStock(nomProdMod, nuevoStockProd)) {
            System.out.println(Constantes.SE_MODIFICO_EL_PRODUCTO_AHORA_ES + daoProductos.getProducto(nomProdMod));
        } else {
            System.out.println(Constantes.NO_SE_ENCONTRO_EL_PRODUCTO_EN_NUESTRA_LISTA_DE_PRODUCTOS_O_EL_STOCK_INGRESADO_ES_MENOR_QUE_0_INTENTE_NUEVAMENTE);
        }
    }

    private void modificarPrecioProducto(Scanner sc, DaoProductos daoProductos, String nomProdMod) {
        double nuevoPrecioProd;
            System.out.println(Constantes.INGRESE_EL_NUEVO_PRECIO_QUE_TENDRA_EL_LA + nomProdMod + Constantes.DOS_PUNTOS);
            nuevoPrecioProd = sc.nextDouble();
        // cambiar solo el precio
        if (daoProductos.modificarProductoPrecio(nomProdMod, nuevoPrecioProd)) {
            System.out.println(Constantes.SE_MODIFICO_EL_PRODUCTO_AHORA_ES + daoProductos.getProducto(nomProdMod));
        } else {
            System.out.println(Constantes.NO_SE_ENCONTRO_EL_PRODUCTO_EN_NUESTRA_LISTA_DE_PRODUCTOS_O_EL_PRECIO_INGRESADO_ES_MENOR_QUE_0_INTENTE_NUEVAMENTE);
        }
    }

    private void modificarNombreProducto(Scanner sc, DaoProductos daoProductos, String nomProdMod) {
        String nuevoNombreProd;
            System.out.println(Constantes.INGRESE_EL_NUEVO_NOMBRE_QUE_TENDRA_EL_LA + nomProdMod + ": ");
            nuevoNombreProd = sc.nextLine();
        if (daoProductos.modificarProductoNombre(nomProdMod, nuevoNombreProd)){
            System.out.println(Constantes.SE_MODIFICO_EL_PRODUCTO_AHORA_ES + daoProductos.getProducto(nuevoNombreProd));
        }
        else {
            System.out.println(Constantes.NO_SE_ENCONTRO_EL_PRODUCTO_EN_NUESTRA_LISTA_DE_PRODUCTOS_O_EL_NOMBRE_NUEVO_NO_TIENE_UN_VALOR_INTENTE_NUEVAMENTE);
        }
    }

    private void modificarProductoEntero(Scanner sc, DaoProductos daoProductos, String nomProdMod) {
        double nuevoPrecioProd;
        int nuevoStockProd;
        String nuevoNombreProd;
        System.out.println(Constantes.INGRESE_EL_NUEVO_NOMBRE_QUE_TENDRA_EL_LA + nomProdMod + Constantes.DOS_PUNTOS);
        nuevoNombreProd = sc.nextLine();

        // pedir precio
        System.out.println(Constantes.INGRESE_EL_NUEVO_PRECIO_QUE_TENDRA_EL_LA + nomProdMod + Constantes.DOS_PUNTOS);
        nuevoPrecioProd = sc.nextDouble();
        sc.nextLine();

        // pedir stock
        System.out.println(Constantes.INGRESE_EL_NUEVO_STOCK_QUE_TENDRA_EL_LA + nomProdMod + Constantes.DOS_PUNTOS);
        nuevoStockProd = sc.nextInt();
        sc.nextLine();

        // cambiar
        Producto prodNuevo = new Producto(nuevoNombreProd, nuevoPrecioProd, nuevoStockProd);
        if (daoProductos.modificarProducto(prodNuevo, nomProdMod)) {
            System.out.println(Constantes.SE_MODIFICO_EL_PRODUCTO_AHORA_ES + daoProductos.getProducto(nuevoNombreProd));
        } else {
            System.out.println(Constantes.NO_SE_ENCONTRO_EL_PRODUCTO_EN_NUESTRA_LISTA_DE_PRODUCTOS_O_EL_ALGUNO_DE_LOS_CAMPOS_INGRESADOS_ES_INVALIDO_INTENTE_NUEVAMENTE);
        }
    }

}
