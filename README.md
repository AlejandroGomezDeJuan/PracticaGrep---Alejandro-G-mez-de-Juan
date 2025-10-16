# Practica GREP Alejandro Gómez de Juan

Esta práctica consiste en implementar un filtrado de texto similar al comando `grep` de Linux.  
El programa recibe un bloque de texto y muestra únicamente las líneas que contienen la palabra **PSP**.


## ComandoGrep.java

### Constantes

- Contiene el comando que se va a ejecutar .
```java
private static final String[] COMANDO = { "grep", "PSP" };
```

- Se guarda el texto que se le va a enviar al comando grep.
```java
private static final String TEXTO =
            "Me gusta PSP y java\n" +
            "PSP se programa en java\n" +
            "es un módulo de DAM\n" +
            "y se programa de forma concurrente en PSP\n" +
            "PSP es programación.\n";
```

- Es un mensaje que se imprime antes de mostrar la salida filtrada.
```java
private static final String SALIDA_TITULO = "Salida de grep:";
```

-Este mensaje se muestra si ocurre algún problema al ejecutar el comando, por ejemplo, si grep falla por alguna razón.
```java
private static final String ERROR_GREP = "Error";
```

### Main

```java
public static void main(String[] args) {
    try {

        Process proceso = Runtime.getRuntime().exec(COMANDO);
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(proceso.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

        writer.print(TEXTO);
        writer.close();

        System.out.println(SALIDA_TITULO);
        String linea;
        while ((linea = reader.readLine()) != null) {
            System.out.println(linea);
        }
        reader.close();

        int exitVal = proceso.waitFor();
        if (exitVal != 0) {
            System.err.println(ERROR_GREP);
        }

    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
    }
}
```
El programa ejecuta un proceso externo desde Java, en este caso el comando grep, envía un texto para que lo procese y muestra en pantalla únicamente las líneas que coinciden con el patrón buscado. Primero se lanza el proceso con Runtime.getRuntime().exec(COMANDO) y se preparan los flujos de entrada y salida: PrintWriter permite enviar el texto al proceso y BufferedReader permite leer lo que devuelve. A continuación, se envía todo el contenido de TEXTO y se cierra el flujo de escritura, indicando que no habrá más datos. Después, se imprime un título y se recorren línea por línea los resultados, mostrando solo las coincidencias. Por último, se espera a que el proceso termine con waitFor() y se comprueba si terminó correctamente; en caso de error, se muestra un mensaje. Todo el bloque está dentro de un try-catch para manejar posibles problemas de entrada/salida o interrupciones, mostrando la información del error si ocurre.

### Enlace a github
 - 


