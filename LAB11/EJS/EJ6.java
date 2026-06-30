/*
 * EJERCICIO 6: Caso real - sistema de autenticacion de usuarios (cache de sesiones)
 *
 * SessionCache simula el cache de sesiones de un sistema web usando una tabla
 * hash con encadenamiento. La clave es el token de sesion y el valor es un
 * objeto Session con los datos del usuario y su tiempo de expiracion.
 *
 * IMPORTANTE: no se usa LinkedList ni ninguna clase de java.util.
 * Cada bucket de la tabla es una lista enlazada propia (clase NodoSesion
 * con puntero "siguiente"), implementada manualmente.
 *
 * Se usa token.hashCode() como funcion hash, reducido al tamano de la tabla.
 */
public class EJ6 {

    // Representa una sesion activa de un usuario
    static class Session {
        String token;
        String username;
        String role;
        long expiresAt; // timestamp Unix en milisegundos

        Session(String token, String username, String role, long expiresAt) {
            this.token = token;
            this.username = username;
            this.role = role;
            this.expiresAt = expiresAt;
        }

        public String toString() {
            return "Session{user=" + username + ", role=" + role + ", expiresAt=" + expiresAt + "}";
        }
    }

    // Nodo de la lista enlazada propia: guarda una Session y un puntero al siguiente nodo
    static class NodoSesion {
        Session sesion;
        NodoSesion siguiente;

        NodoSesion(Session sesion) {
            this.sesion = sesion;
            this.siguiente = null;
        }
    }

    // Lista enlazada simple hecha a mano para cada bucket de la tabla
    static class ListaSesiones {
        NodoSesion cabeza;

        void agregar(Session s) {
            NodoSesion nuevo = new NodoSesion(s);
            if (cabeza == null) {
                cabeza = nuevo;
            } else {
                NodoSesion actual = cabeza;
                while (actual.siguiente != null) {
                    actual = actual.siguiente;
                }
                actual.siguiente = nuevo;
            }
        }

        // Busca una sesion por token (sin importar si expiro o no)
        Session buscarPorToken(String token) {
            NodoSesion actual = cabeza;
            while (actual != null) {
                if (actual.sesion.token.equals(token)) {
                    return actual.sesion;
                }
                actual = actual.siguiente;
            }
            return null;
        }

        // Elimina la sesion que tenga el token indicado. Retorna true si elimino algo.
        boolean eliminarPorToken(String token) {
            if (cabeza == null) return false;

            if (cabeza.sesion.token.equals(token)) {
                cabeza = cabeza.siguiente;
                return true;
            }

            NodoSesion anterior = cabeza;
            NodoSesion actual = cabeza.siguiente;
            while (actual != null) {
                if (actual.sesion.token.equals(token)) {
                    anterior.siguiente = actual.siguiente;
                    return true;
                }
                anterior = actual;
                actual = actual.siguiente;
            }
            return false;
        }

        // Elimina todas las sesiones cuyo expiresAt sea menor al tiempo dado.
        // Retorna cuantos nodos se eliminaron.
        int eliminarExpiradas(long ahora) {
            int eliminados = 0;

            // Eliminamos nodos expirados al inicio de la lista
            while (cabeza != null && cabeza.sesion.expiresAt < ahora) {
                cabeza = cabeza.siguiente;
                eliminados++;
            }

            if (cabeza == null) return eliminados;

            // Recorremos el resto de la lista buscando nodos expirados
            NodoSesion anterior = cabeza;
            NodoSesion actual = cabeza.siguiente;
            while (actual != null) {
                if (actual.sesion.expiresAt < ahora) {
                    anterior.siguiente = actual.siguiente;
                    eliminados++;
                } else {
                    anterior = actual;
                }
                actual = actual.siguiente;
            }
            return eliminados;
        }

        int contar() {
            int total = 0;
            NodoSesion actual = cabeza;
            while (actual != null) {
                total++;
                actual = actual.siguiente;
            }
            return total;
        }
    }

    static class SessionCache {

        static final int TAMANO = 16;

        ListaSesiones[] tabla = new ListaSesiones[TAMANO];

        SessionCache() {
            for (int i = 0; i < TAMANO; i++) {
                tabla[i] = new ListaSesiones();
            }
        }

        // Convierte el hashCode del token (que puede ser negativo) en un indice valido
        int indiceDe(String token) {
            int h = token.hashCode();
            return Math.abs(h) % TAMANO;
        }

        // Registra una nueva sesion con tiempo de vida en milisegundos
        void login(String token, String username, String role, long ttlMs) {
            long expiracion = System.currentTimeMillis() + ttlMs;
            int indice = indiceDe(token);
            tabla[indice].agregar(new Session(token, username, role, expiracion));
            System.out.println("Login: " + username + " (token=" + token + ") en indice " + indice);
        }

        // Retorna la sesion si el token existe y no ha expirado; null en caso contrario
        Session validate(String token) {
            int indice = indiceDe(token);
            Session s = tabla[indice].buscarPorToken(token);
            if (s == null) return null; // token no encontrado
            long ahora = System.currentTimeMillis();
            if (s.expiresAt >= ahora) {
                return s; // sesion valida
            }
            return null; // existe pero ya expiro
        }

        // Elimina la sesion del cache (cierre de sesion explicito)
        void logout(String token) {
            int indice = indiceDe(token);
            boolean eliminado = tabla[indice].eliminarPorToken(token);
            if (eliminado) {
                System.out.println("Logout exitoso para token " + token);
            } else {
                System.out.println("Logout: token " + token + " no estaba activo");
            }
        }

        // Recorre toda la tabla y elimina las sesiones cuyo expiresAt sea menor al tiempo actual
        void cleanExpired() {
            long ahora = System.currentTimeMillis();
            int eliminadas = 0;
            for (int i = 0; i < TAMANO; i++) {
                eliminadas += tabla[i].eliminarExpiradas(ahora);
            }
            System.out.println("cleanExpired(): se eliminaron " + eliminadas + " sesion(es) expirada(s)");
        }

        // Cuenta cuantas sesiones siguen activas en total
        int sesionesActivas() {
            int total = 0;
            for (int i = 0; i < TAMANO; i++) {
                total += tabla[i].contar();
            }
            return total;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SessionCache cache = new SessionCache();

        // (1) Tres usuarios inician sesion con tokens y tiempos de expiracion distintos
        cache.login("abc123", "ana", "admin", 5000);   // expira en 5 segundos
        cache.login("xyz789", "luis", "user", 200);     // expira casi de inmediato (200 ms)
        cache.login("qwe456", "carla", "user", 5000);  // expira en 5 segundos

        // Esperamos un poco para que el token de "luis" expire
        Thread.sleep(400);

        // (2) Validamos los tokens (uno ya debe haber expirado)
        System.out.println("\nValidando tokens...");
        System.out.println("abc123 -> " + cache.validate("abc123"));
        System.out.println("xyz789 -> " + cache.validate("xyz789")); // null: ya expiro
        System.out.println("qwe456 -> " + cache.validate("qwe456"));

        // (3) Un usuario cierra sesion explicitamente
        System.out.println("\nCerrando sesion de ana...");
        cache.logout("abc123");

        // (4) Limpiamos sesiones expiradas y mostramos cuantas quedan activas
        System.out.println();
        cache.cleanExpired();
        System.out.println("Sesiones activas restantes: " + cache.sesionesActivas());

        // Reflexion pedida en el enunciado
        System.out.println("\nReflexion:");
        System.out.println("Usar una tabla hash es mas eficiente que recorrer una lista enlazada");
        System.out.println("de sesiones porque la busqueda de un token pasa de ser O(n), donde hay");
        System.out.println("que revisar sesion por sesion, a ser O(1) en promedio: la funcion hash");
        System.out.println("calcula directamente el indice donde buscar, sin recorrer el resto de");
        System.out.println("las sesiones almacenadas. Esto es clave cuando millones de usuarios");
        System.out.println("hacen peticiones por segundo, ya que el tiempo de validacion no crece");
        System.out.println("con la cantidad de sesiones activas.");
        System.out.println("La ventaja de HashMap de Java sobre esta implementacion manual es que");
        System.out.println("ya viene optimizada, maneja automaticamente el redimensionamiento y el");
        System.out.println("factor de carga, usa buenas funciones hash internas para distribuir las");
        System.out.println("claves, y a partir de cierto tamano convierte las listas de colision en");
        System.out.println("arboles balanceados para evitar el peor caso O(n) en una sola cadena.");
    }
}
