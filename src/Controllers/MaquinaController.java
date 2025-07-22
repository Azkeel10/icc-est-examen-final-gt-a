package Controllers;

import java.util.*;
import Models.Maquina;

public class MaquinaController {
    public Stack<Maquina> filtrarPorSubred(List<Maquina> maquinas, int umbral){
        LinkedList<Maquina> cola = new LinkedList<>();
        for (Maquina maquina : maquinas){
            if(maquina.getSubred() < umbral){
                cola.add(maquina);
            }
        }
        Stack<Maquina> pila = new Stack<>();
        for (Maquina maquina : cola){
            pila.add(maquina);
        }
        return pila;
    }

    public TreeSet<Maquina> ordenarPorSubred(Stack<Maquina> pila){
        TreeSet<Maquina> ordenadas = new TreeSet<>((a,b) ->{
            int compara = Integer.compare(a.getSubred(),b.getSubred());
            if(compara != 0){
                return compara;
            }
            return a.getNombre().compareTo(b.getNombre());
        });

        ordenadas.addAll(pila);
        return ordenadas;

    }

    public TreeMap<Integer, Queue<Maquina>> agruparPorRiesgo(List<Maquina> maquinas){
        TreeMap<Integer,Queue<Maquina>> mapa = new TreeMap<>();
        for(Maquina maquina : maquinas){
            int riesgo = maquina.getRiesgo();
            mapa.putIfAbsent(riesgo, new LinkedList<>());
            mapa.get(riesgo).add(maquina);
        }
        return mapa;
    }

    public Stack<Maquina> explotarGrupo(Map<Integer,Queue<Maquina>> mapa ){
        int maxCantidad = -1;
        int riesgoSeleccionado = -1;

        for (Map.Entry<Integer,Queue<Maquina>> entry : mapa.entrySet()){
            int riesgo = entry.getKey();
            int cantidad = entry.getValue().size();
            if(cantidad > maxCantidad || (cantidad == maxCantidad && riesgo == riesgoSeleccionado)){
                maxCantidad = cantidad;
                riesgoSeleccionado = riesgo;
            }
        }

        Queue<Maquina> grupo = mapa.get(riesgoSeleccionado);
        Stack<Maquina> resultado = new Stack<>();

        for(Maquina maquina : grupo){
            resultado.insertElementAt(maquina, 0);
        }
        
        return resultado;
    }
}
