// ============================================================
//  EJ4 – Biblioteca Digital con BTree<Libro>
//
//  Clases incluidas:
//    · Libro       → implementa Comparable<Libro> por ISBN
//    · Biblioteca  → encapsula BTree<Libro>
//    · EJ4_Biblioteca → main con todas las pruebas
//
//  Archivo de entrada: biblioteca.txt
//    Línea 1 → orden del Árbol B
//    Siguientes → ISBN,título,autor,año
//
//  ¿Por qué Árbol B para una biblioteca digital?
//    1. Eficiencia I/O: cada nodo agrupa muchas claves → pocas
//       lecturas de disco para encontrar cualquier libro.
//    2. Búsqueda O(log n): árbol siempre balanceado; con orden 200
//       y 1 000 000 libros la altura máxima es ≤ 3 niveles.
//    3. Rangos eficientes: searchRange descarta subárboles enteros.
//    4. ISBN único garantizado: anti-duplicado integrado en insert().
//    5. Escalabilidad: igual principio que InnoDB (MySQL) y
//       PostgreSQL usan internamente árboles B+.
// ============================================================

import java.io.*;
import java.util.*;


// ════════════════════════════════════════
//  Clase Libro
// ════════════════════════════════════════
class Libro implements Comparable<Libro> {

    private long   isbn;
    private String titulo;
    private String autor;
    private int    anio;

    public Libro(long isbn, String titulo, String autor, int anio) {
        this.isbn   = isbn;
        this.titulo = titulo;
        this.autor  = autor;
        this.anio   = anio;
    }

    // Getters
    public long   getIsbn()   { return isbn;   }
    public String getTitulo() { return titulo; }
    public String getAutor()  { return autor;  }
    public int    getAnio()   { return anio;   }

    // Criterio de comparación: ISBN
    @Override
    public int compareTo(Libro other) {
        return Long.compare(this.isbn, other.isbn);
    }

    @Override
    public String toString() {
        return String.format("[ISBN: %d | %-35s | %-20s | %d]",
                isbn, titulo, autor, anio);
    }
}


// ════════════════════════════════════════
//  Clase Biblioteca
// ════════════════════════════════════════
class Biblioteca {

    private BTree<Libro> arbol;

    public Biblioteca(int orden) {
        this.arbol = new BTree<>(orden);
    }

    // ── Agregar un libro ─────────────────────────────────────
    public void agregar(Libro libro) {
        arbol.insert(libro);
    }

    // ── Eliminar libro por ISBN ──────────────────────────────
    public void eliminar(long isbn) {
        arbol.remove(new Libro(isbn, "", "", 0));
    }

    // ── Buscar libro por ISBN (con camino recorrido) ─────────
    public boolean buscar(long isbn) {
        Libro clave = new Libro(isbn, "", "", 0);
        System.out.println("Búsqueda de ISBN: " + isbn);
        return buscarConCamino(arbol.getRoot(), clave, 1);
    }

    private boolean buscarConCamino(BNode<Libro> current,
                                    Libro clave, int nivel) {
        if (current == null) {
            System.out.println("  → ISBN no encontrado en el árbol.");
            return false;
        }

        // Mostrar nodo visitado (camino recorrido)
        System.out.print("  Nivel " + nivel + " Nodo[" + current.idNode + "]{");
        for (int i = 0; i < current.count; i++) {
            System.out.print(current.keys.get(i).getIsbn());
            if (i < current.count - 1) System.out.print(", ");
        }
        System.out.println("}");

        int pos = 0;
        while (pos < current.count
                && clave.compareTo(current.keys.get(pos)) > 0) pos++;

        if (pos < current.count
                && clave.compareTo(current.keys.get(pos)) == 0) {
            System.out.println("  ✔ Encontrado en Nodo["
                    + current.idNode + "] posición " + pos);
            System.out.println("  " + current.keys.get(pos));
            return true;
        }

        return buscarConCamino(current.childs.get(pos), clave, nivel + 1);
    }

    // ── Cargar árbol desde archivo biblioteca.txt ────────────
    public void cargarDesdeArchivo(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            String primera = br.readLine();
            if (primera == null) { System.out.println("Archivo vacío."); return; }

            int orden = Integer.parseInt(primera.trim());
            this.arbol = new BTree<>(orden);
            System.out.println("Orden del Árbol B leído del archivo: " + orden);

            Set<Long> isbnVistos = new HashSet<>();
            String linea;
            int cargados = 0, duplicados = 0;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                String[] partes = linea.split(",");
                if (partes.length < 4) {
                    System.out.println("  Línea con formato inválido: " + linea);
                    continue;
                }

                long isbn = Long.parseLong(partes[0].trim());

                // Verificar ISBN duplicado
                if (!isbnVistos.add(isbn)) {
                    System.out.println("  ISBN duplicado ignorado: " + isbn);
                    duplicados++;
                    continue;
                }

                String titulo = partes[1].trim();
                String autor  = partes[2].trim();
                int    anio   = Integer.parseInt(partes[3].trim());

                arbol.insert(new Libro(isbn, titulo, autor, anio));
                cargados++;
            }

            System.out.println("Libros cargados: " + cargados
                    + " | Duplicados ignorados: " + duplicados);

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // ── Mostrar todos los libros ordenados por ISBN ──────────
    public void mostrarOrdenado() {
        System.out.println("Libros ordenados por ISBN (recorrido inorden):");
        arbol.inorder();
    }

    // ── Altura del árbol ─────────────────────────────────────
    public void mostrarAltura() {
        System.out.println("Altura del Árbol B: " + arbol.height());
    }

    // ── Total de libros almacenados ──────────────────────────
    public void mostrarTotal() {
        System.out.println("Total de libros: " + arbol.totalKeys());
    }

    // ── Visualizar árbol por niveles ─────────────────────────
    public void mostrarArbol() {
        System.out.println("Estructura del Árbol B (por niveles):");
        arbol.printTree();
    }
}


// ════════════════════════════════════════
//  Main – Pruebas EJ4
// ════════════════════════════════════════
public class EJ4_Biblioteca {

    public static void main(String[] args) {
        BNode.resetCounter();
        Biblioteca bib = new Biblioteca(4);   // orden por defecto

        // ── Cargar desde archivo ─────────────────────────────
        System.out.println("=== Cargando desde biblioteca.txt ===");
        bib.cargarDesdeArchivo("biblioteca.txt");

        System.out.println();
        bib.mostrarArbol();
        bib.mostrarAltura();
        bib.mostrarTotal();

        // ── Mostrar libros ordenados ─────────────────────────
        System.out.println("\n=== Libros ordenados por ISBN ===");
        bib.mostrarOrdenado();

        // ── Buscar ISBN existente ────────────────────────────
        System.out.println("\n=== Buscar ISBN existente: 9780596009205 ===");
        bib.buscar(9780596009205L);

        // ── Buscar ISBN no existente ─────────────────────────
        System.out.println("\n=== Buscar ISBN no existente: 1111111111111 ===");
        bib.buscar(1111111111111L);

        // ── Eliminar un libro ────────────────────────────────
        System.out.println("\n=== Eliminar ISBN: 9780201633610 (Design Patterns) ===");
        bib.eliminar(9780201633610L);
        bib.mostrarTotal();
        System.out.println();
        bib.mostrarOrdenado();

        // ── Agregar un libro nuevo ───────────────────────────
        System.out.println("\n=== Agregar nuevo libro ===");
        bib.agregar(new Libro(9780735619678L,
                "Code Complete", "Steve McConnell", 2004));
        bib.mostrarTotal();
        System.out.println();
        bib.mostrarOrdenado();

        // ── Justificación ────────────────────────────────────
        System.out.println("\n=== ¿Por qué Árbol B para bibliotecas digitales? ===");
        System.out.println(
            "1. Eficiencia I/O: cada nodo agrupa muchas claves, minimizando\n" +
            "   lecturas de disco (dominan el costo real en sistemas grandes).\n" +
            "2. Búsqueda O(log n): el árbol se mantiene siempre balanceado.\n" +
            "   Con orden 200 y 1 000 000 libros → altura ≤ 3 niveles.\n" +
            "3. Rangos eficientes: searchRange descarta subárboles enteros\n" +
            "   gracias al orden natural de las claves en cada nodo.\n" +
            "4. ISBN único garantizado: anti-duplicado integrado en insert(),\n" +
            "   equivalente a PRIMARY KEY en SQL.\n" +
            "5. Escalabilidad: fundamento de InnoDB (MySQL) y PostgreSQL\n" +
            "   que usan árboles B+ con el mismo principio."
        );
    }
}
