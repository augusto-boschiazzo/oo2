# Refactoring

### Catálogo de Refactoring de la materia

---

1. Composición de métodos

    Permiten distribuir el código adecuadamente. Los métodos largos son problemáticos, contienen mucha información y lógica compleja.

    - _Extract Method_: Se consideran los métodos largos, y sus fragmentos que requieren de comentarios para saber qué hacen. Estos fragmentos que se pueden agrupar se transforman en un método cuyo nombre explique qué es lo que hace. Fowler remarca la importancia de darle un buen nombre, tal que indique claramente qué es lo que hace.

        **Mecánica**

        - Crear un nuevo método, y nombrarlo según la intención del método.
        - Copiar el código extraído del método de orígen al nuevo método.
        - Escanear el código extraído por referencias a variables temporales que son locales al alcance del método orígen. Estas son parámetros para el nuevo método.
        - Ver si alguna de las variables sólo son usadas en el código extraído. De ser así, declararlas en el método extraído como variables temporales.
        - Revisar si alguna de las variables locales son modificadas por el código extraído. Si sólo hay una, tratar como query y asignar el resultado. Si hay más de una, la extracción no se puede realizar.
        - Pasar al método extraído como parámetros las variables locales que se leen en el código extraído.
        - Compilar cuando se resuelven las variables locales.
        - Reemplazar el código extraído del método orígen con el llamado al nuevo método.
        - Compilar y testear.

    - _Replace Temp with Query_: Se usa una variable temporal para mantener el resultado de una expresión. El problema con estas variables es que son temporales y locales. Como sólo se pueden ver en el contexto del método en las que se usan, tienden a llevar a métodos más largos, ya que se requiere para poder alcanzar el valor de esta variable. Extrayendo una query, cualquier método puede acceder a esta información.

        Replace Temp with Query es un paso previo vital antes de Extract Method.

        **Mecánica**

        - Buscar una variable temporal que se asigna una única vez.
        - Extraer el lado derecho de la asignación en un método.
        - Reemplazar todas las referencias a la variable temporal por el nuevo método.
        - Eliminar la declaración de la variable temporal y las asignaciones.
        - Compilar y testear.

---

2. Mover aspectos entre objetos

    Ayuda a mejorar la asignación de responsabilidades.

    - _Move Method_: Está relacionado a la Feature Envy. Un método usa o usará muchas características de otro objeto. Para eso, se mueve el método a la clase donde están dichas características. El método original pasa a ser simplemente una delegación o directamente se elimina.

        **Mecánica**

        - Examinar todas las características usadas por un método origen que están definidas en la clse orígen. Considerar si deben ser movidas también.
        - Chequear la sub y súper clases de la clase orígen para ver otras declaraciones del método. Si hay otras declaraciones, no se puede realizar el movimiento, de no ser que haya un polimorfismo expresado en la clase destino.
        - Declarar el método en la clase destino, puede ser necesario cambiar el nombre a uno que tenga más sentido en el contexto de la nueva clase.
        - Copiar el código del método origen al destino. Ajustar el método para que funcione en la nueva clase. Si tiene manejadores de excepciones, comprobar qué clase debería manejarlas.
        - Compilar.
        - Determinar cómo referenciar el método desde el orígen.
        - Cambiar el método orígen a una delegación.
        - Compilar y testear.
        - Comprobar si eliminar el método orígen o dejarlo como una delegación.
        - Si se elimina, cambiar todas las referencias al método orígen con referencias al método destino.
        - Compilar y testear.

---

3. Manipulación de la generalización

    Ayuda a mejorar la jerarquía de clases

    - _Pull up Method_: Hay métodos con resultados idénticos en las subclases. Si ocurre que el método llama a métodos de la subclase que la superclase no contiene, se puede o generalizar ese método, o crear un método abstracto en la superclase. Podría ser necesario cambiar la firma del método o create uno que delegue para que funcione. Si hay dos métodos que son similares pero no idénticos, podría usarse Form Template Method.

        **Mecánica**

        - Inspeccionar los métodos para comprobar que sean idénticos. Si no lo son, se puede hacer una sustitución de algoritmo en uno de ellos para hacerlos iguales.
        - Si los métodos tienen firmas diferentes, elegir una que se usará en la superclase.
        - Si el método llama a algunos métodos que están presentes en las subclases pero no en la superclase, declarar métodos abstractos en la superclase.
        - Si llama a un atributo declarado en las subclases, usar Pull up Field o Self Encapsulate Field y declarar los getters abstractos en la superclase.
        - Crear un nuevo método en la superclase, copiar el cuerpo de uno de los métodos, ajustar y compilar.
        - Borrar el método de una de las subclases.
        - Compilar y testear.
        - Repetir los dos pasos anteriores hasta que no quede ninguna subclase restante.

---

4. Organización de datos

    Facilita la organización de atributos

    - _Self Encapsulate Field_:

---

5. Simplificación de expresiones condicionales

    Ayuda a simplificar condicionales para que sea más fácil cambiar o agregar funcionalidad.

    - _Replace Conditional with Polymorphism_: Existe un condicional que elije comportamiento según el tipo del objeto. La ventaja que tiene el polimorfismo sobre esta forma de programar, es que facilita cambiar o agregar funcionalidad a una nueva clase con el comportamiento correspondiente, ya que si se quisiera agregar comportamiento a un código que contiene switch statements basados en el tipo, se deberá agregar a cada uno de los métodos que contienen el switch sobre el tipo.

        **Mecánica**

        Para poder realizar el refactoring, se necesita crear la estructura de clases, ya sea con Replace Type Code with Subclasses o Replace Type Code with State/Strategy. La forma más simple es con subclases, pero si se modifica el tipo del objeto luego de su creación, se deberá considerar State/Strategy, según corresponda.

        - Si el condicional es parte de un método más grande, hacer Extract Method.
        - Si es necesario, usar Move Method para ubicar el condicional en la parte más alta de la estructura de herencia.
        - Elegir una de las subclases, sobreescribir el método del condicional. Copiar la parte del condicional correspondiente a esta subclase. Podría ser necesario cambiar algunos miembros de la superclase de privados a protegidos.
        - Compilar y testear.
        - Remover la porción de código del condicional copiada.
        - Compilar y testear.
        - Repetir con cada una de las partes del condicional hasta que todas ellas sean convertidas en métodos de las subclases.
        - Hacer abstracto el método de la subclase.

---

6. Simplificación de invocación de métodos

    Ayuda a mejorar la interfaz de una clase.

    - _Rename Method_: El nombre de un método es importante, ya que permite entender claramente qué es lo que hace sin necesidad de mirar al código.

        **Mecánica**

        - Comprobar si el método se encuentra implementado en superclases o subclases. Si lo es, realizar los pasos por cada implementación.
        - Declarar un nuevo método con el nuevo nombre. Copiar el cuerpo del codigo anterior al nuevo método y hacer las alteraciones necesarias para que encaje.
        - Compilar.
        - Cambiar el cuerpo del método anterior para que llame al nuevo.
        - Compilar y testear.
        - Encontrar todas las referencias al método viejo y cambiarlas al nuevo. Compilar y testear por cada cambio.
        - Eliminar el método anterior.
        - Compilar y testear.

---

### Refactoring to patterns

-   _Form Template Method_:

    **Mecánica**

    -   Encontrar el método que es similar en todas las subclases y extraer sus partes en métodos idénticos (misma signatura y cuerpo en las subclases) o métodos únicos (distinta signatura y cuerpo)
    -   Aplicar “Pull Up Method” para los métodos idénticos.
    -   Aplicar “Rename Method” sobre los métodos únicos hasta que el método similar quede con cuerpo idéntico en las subclases.
    -   Compilar y testear después de cada “rename”.
    -   Aplicar “Rename Method” sobre los métodos similares de las subclases (esqueleto).
    -   Aplicar “Pull Up Method” sobre los métodos similares.
    -   Definir métodos abstractos en la superclase por cada método único de las subclases.
    -   Compilar y testear
