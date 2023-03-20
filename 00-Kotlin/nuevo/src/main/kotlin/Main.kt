//es interoperable con JAVA
import java.util.*

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    //TENEMOS DOS TIPOS DE VARIABLES
    //1.- INMUTABLES las que no se puede reasginar o cambiar su valor (cte)
    val inmutable: String = "Kevin";
    //inmutable = "Fernando"

    //2.- Mutables son aquellas que si se pueden reasignar, es decir, puede cambiar su valor (variables)
    var mutable: String = "Kevin";
    mutable = "Fernando";

    //Sintaxis Duck Type
    //No es necesario definir el tipo de variable que es (int, char, String, etc) en su lugar podemos utilizar
    //funciones de Kotlin

    val ejemploVariable = "Ejemplo" //Este no está definido, el ide mismo lo detecta
    val edadEjemplo: Int = 12
    ejemploVariable.trim()

    //variables primitivas
    val nombreProfesor: String = "Adrian"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'S'
    val mayorEdad: Boolean = true

    //clases JAVA
    val fechaNacimiento: Date = Date();

    //CONDICIONALES
    if (true){
    } else{

    }

    //switch no existe en su lugar se tiene el when
    val estadoCivilWhen = "S"
    when(estadoCivilWhen){
        ("S") -> {
            println("acercarse")
        }
        "C" -> {
            println("alejarse")
        }
        "UN" -> println("Hablar")
        else -> println("No reconocido")
    }
    //if en una sola linea
    val coqueto = if(estadoCivilWhen == "S") "SI" else "NO"
}

//funciones van despues de main
//Unit == Void
fun imprimirNombre(nombre: String): Unit{

}

fun calcularSueldo(
    sueldo: Double,
    tasa: Double = 12.00,
    bonoEspecial: Double? = null,
):Double {
    //String -> String?
    //Int -> Int?
    //Date -> Date?
    //Este tipo de sintaxis nos ayuda a dar una mejor comprension del programa y vitar exception

    if (bonoEspecial == null) {
        return sueldo * (100 / tasa)
    } else {
        return sueldo * (100 / tasa) + bonoEspecial
    }
}



abstract class numerosJava{
    protected val numeroUno: Int
    protected val numeroDos: Int

    constructor(
        uno: Int,
        dos: Int
    ){ //Bloque Codigo Constructor
        this.numeroUno = uno
        this.numeroDos = dos
    }
}

abstract class numeros( //constructores Primarios
    //public var uno: Int, //parámetro / la palabra public es opcional
    //var uno1: Int, //tambien es publico

    protected val numeroUno: Int,
    protected val numeroDos: Int

){
    init {
        //this.uno
        this.numeroUno //no es necesario usar el this para usar el atributo (this.numeroUno = numeroUNo
        this.numeroDos
        //por lo tanto no es necesario es inicializador
        println("Inicializado")


    }
}


class Suma(
  uno: Int,
  dos: Int
): numeros(uno, dos){
    init {
        this.numeroUno
        this.numeroDos
    }
    constructor(
        uno: Int?,
        dos: Int,
    ):this( //llamada al constructor primario
        if(uno == null) 0 else uno, dos
    ){

    }

    //
    constructor(
        uno: Int,
        dos: Int?,
    ):this( //llamada al constructor primario
        uno,
        if(dos == null) 0 else dos
    ){
    }

    //
    constructor(
        uno: Int?,
        dos: Int?,
    ):this( //llamada al constructor primario
        if(uno == null) 0 else uno,
        if(dos == null) 0 else dos
    )

    fun sumar(): Int{
        val total = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }
    companion object{ //Atributos y métodos "Compartidos entre las instancias
        val pi = 3.14
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma)
        }
    }

    //Arreglo Estático
    val arregloEstatico: Array<Int> = arrayOf<Int>(1, 2, 3)
    println(arregloEstatico)

    //ArregloDinámico
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1,2, 3,4, 5,6, 7,8, 9, 10)
    println(arregloDinamico)
    //AGREGANDO MÁS DATOS
    arregloDinamico.add(11)
    arregloDinamico.add(12)




    //OPERADORES
    //FUNCIONAN EN LOS TIPOS DE ARREGLOS TANTO ESTÁTICOS COMO DINÁMICOS
    //FOREACH SOLO DEVUELVE VALORES
    //FOREACHINDEXED DEVUELVE VALORES Y LOS INDICES
    //la respuesta del foreach es un unit, es decir, es un void

    //foreach devuelve un unit
    //Sirve para iterar arreglos
    val respuestaForEach: Unit = arregloDinamico
        .forEach{
            valorActual: Int ->
            println("Valor Actual: ${valorActual}")
        }
    //foreachIndexed
    arregloEstatico
        .forEachIndexed{indice: Int, ValorActual:Int ->
            println("Valor ${ValorActual} Indice ${indice}")
        }
    println(respuestaForEach)




    //var arregloDinamico = IntArray(20){(Math.random())*11.toInt()}

    //FILTER
    //1) Devleolver una expresion (trute o False)
    //2) Nuevo Arreglo filtrado
    val respuestaFilter: List<Int> = arregloDinámico
        .filter { valorActual: Int ->
            val mayoresACinco: Boolean = valorActual > 5// Expresión COndición
            return@filter mayoresACinco
        }
    val respuestraFilterDos = arregloDinamico.filter{it <=5}
    println(respuestaFilter)
    println(respuestaFilterDos)


    //OR AND
    //OR -> ANY(CUMPLE ALGUNO?)
    //AND -> ALL(TODOS CUMPLE?)

    val respuestaAny: Boolean = arregloDinamico
        .any{valorActual: Int ->
            return@any(valorActual>5)
        }
    println(respuestaAny) //true

    val respuestaAll : Boolean = arregloDinamico
        .all { valorActual ->
            return@all (valorActual >5)
        }
    println(respuestaAll) // false

    // REDUCE
    //AYUDA A ACUMULAR NUMEROS, EN KOTLIN ININCIA EN CERO
    //VALOR ACUMULADO = 0  (Siempre en cero en Kotlin)
    /*
    [1, 2, 3, 4, 5] -> SUMER TODOS LOS VALORES DEL ARREGLO
    valorIteracion1 = 0 (Siempre en cero en Kotlin)
    valorIteracion2 = valorEmpieza + 1 = 0 +1 = 2 -> Iteracion 1
    valorIteracion3 = valorIteracion2 + 2 = 0 +1 = 2 -> Iteracion 2
    valorIteracion4 = valorIteracion3 + 3 = 0 +1 = 2 -> Iteracion 3
    valorIteracion5 = valorIteracion4 + 4 = 0 +1 = 2 -> Iteracion 4
    valorIteracion = valorIteracion5 + 5 = 0 +1 = 2 -> Iteracion 5
     */

    val respuestaReduce: Int = arregloDinamico
        .reduce{
            acumulado: Int, valorActual: Int ->
            return@reduce(acumulado + valorActual)
        }
    println(respuestaReduce)


}