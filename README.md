Proyecto Practico Vida Artificial, presentado por Sebastian Narvaez R. (1035605).
Automata Celular de 1 Dimension, con Celulas de 2 Estados.

- Propiedades de la Implementacion:

	* Lenguaje de Programacion: Java
	* Version del JDK: 1.7
	* Librerias externas utilizadas: Ninguna
	
- Instrucciones para Compilar y Ejecutar:
	* Compilacion: Ubiquese en la carpeta src, y ejecute los siguientes comandos:
		mkdir clases #Si ya existe un directorio clases en src, omita este comando
		javac -d clases/ CA/*.java
		
	* Ejecucion: Ubiquese en la carpeta clases creada en el paso anterior, y ejecute el siguiente comando:
		java CA/FramePrincipal
	
	ATENCION: Intentar compilar o ejecutar desde otro directorio del indicado puede producir errores, ya que los archivos
	pertenecen al paquete CA, y java asocia los paquetes con las carpetas.
	
- Instrucciones de Uso:

	* Configure la poblacion inicial de Celulas (El tamano de esta es fijo: 100 Celulas) haciendo click en cada una de las
	  Celulas de la primera fila.
		- Oprima Reset para volver a la configuracion inicial (Todas las Celulas Muertas).
		- Oprima Poblacion Aleatoria para generar pseudoaleatoriamente una poblacion inicial.
		
	* Digite la regla bajo la cual se rige el Automata Celular, introduciendo el numero decimal que representa dicha regla en
	  el campo "Regla" (Los valores validos son aquellos entre 0 y 2^(2^(Cant. de vecinos))).
		- Puede cambiar el numero de vecinos que se tendran en cuenta para aplicar la regla, introduciendolo en el campo "Cant.
		  Vecinos" (3 por defecto).
		  
	* Oprima Procesar Automata para aplicar las reglas, teniendo en cuenta la cant. de vecinos definida, a partir de la pobla-
	  ción inicial hasta un tiempo fijo = 100 (Cada fila representa la población inicial en determinado tiempo). Este software
	  construye un Automata Celular de Frontera Fría.
