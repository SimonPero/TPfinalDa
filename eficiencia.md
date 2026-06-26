mostrarPersonaje()
Esta funcion tiene una eficiencia O(n), principalmente porque dentro de ella, utilza la funcion buscarPersonaje, la cual recorre el arreglo de personajes existentes, arreglo que puede variar entre 0 y 999. Por lo que al buscarPersonaje utilizar un For Loop para recorrer personajes, podemos decir que lo recorre 'n' cantidad de veces, entonces obviando los calculos de tiempos, podemos ver como de la funcion principal MostrarPersonaje() que tiene un if interno, cuya primera parte del if contiene el Lopp y la segunda un SOUT, podemos asegurar, que minimo va a correr n veces. Por eso concluimos, que es O(n)


agregarPersonaje()
Esta funcion posee dos loops principales, el primero es while loop que se ejecuta n veces, siendo estas n veces la cantidad de veces que el usuario ingresa un codigo invalido, y luego cuando ingresa un codigo valido, se usa la funcion de buscarPersonaje que tiene un For Loop que recorre el array de personajes, por lo que termina siendo el While loop n iteraciones, y que internamente tiene n iteraciones por un loop interno. el segundo loop es un While loop que busca un espacio nulo dentro del arreglo de personajes para cargar el nuevo personaje. Pero esto a lo sumo hace que sea 2n. Por lo que podemos concluir que la funcion es O(n^2)


mostrarDueloMagia()
Esta funcion tiene un loop adentro de otro loop, por que recorre la matriz, entonces como son dos loops aninados, podemos rapidamente concluir que es O(n^2)
