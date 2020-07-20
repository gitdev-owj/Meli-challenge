# EJERCICIO TECNICO MERCADOLIBRE

Ejercicio que requiere realizar busqueda de secuencias de 4 letras consecutiva restringidas a :
> A , C , G , T

Las secuencias pueden ser en sentido _Horizontal_ , _Vertical_ , _Diangonal_  en una matriz cuadrada. 


# SOLUCION 

Para el desarrollo de esta solución se usaron las siguentes tecnologías: 
> *  SpringBoot
> *  JDK 1.8
> *  Maven
> *  JUnit

#   SERVICIOS 

Para validar las solucion se publico la solucion en aws. 

## Validación Secuencia  
Para validar la secuencia se debe hacer uso de la url 
````
http://demomelichallenge-env.eba-xq728sph.us-east-2.elasticbeanstalk.com/mutant
````
Enviando en request Body un  _JSON_ con la siguiente estructura: 
````
{
    "dna" :["ATGCGA","CAGTTC","TCATGT","AGcAGG","CACCTG","TCACTG"]
}
````
### Respuestas servicio 
````
HTTP :  200  / 403 / 400
````

## Estadisticas Secuencias 

Para la revisión de las estadisticas  de las secuencias validadas  se expuso el servicio en la siguiente url
````
http://demomelichallenge-env.eba-xq728sph.us-east-2.elasticbeanstalk.com/stats
````
Este servicio da una respuesta _JSON_  con la siguiente estructura.
````
{
    "count_mutant_dna": 3,
    "count_human_dna": 1,
    "ratio": 0.75
}
````

#  COMO USAR EL PROYECTO 

Para el poder hacer uso  del proyecto se deben tener en cuenta lo siguiente: 
 > *  Tener instalado un Ide (IntelliH / Eclipse / Netbeans) o el editor de preferencia 
 > *  Tener instalado el JDK 1.8 
 > *  Tener configuradas las variables de entorno _JAVA_HOME_
 > *  Descargar las  fuentes del proyecto mediante cliente GIT o descarga directa de _GITHUB_
 
 ## Ejecucion del proyecto Local
 
 Para validar el proyecto localmente se debe hacer uso del siguiente comando ubicandose en la ruta del proyecto y por la consola (eje windows:cmd )
 ````
 mvnw.cmd install
 ````
 Y para la ejecución del proyecto usar el siguiente comando por consola 
  ````
 rutaproyecto\target> java -jar challenge-meli-exercise-0.0.1-SNAPSHOT.jar
  ````


