package Models;

import java.util.*;

public class Maquina {
//-----------------------------------------------------------------------------
    private String nombre;
    private String ip;
    private List<Integer> codigos;
    private int subred;
    private int riesgo;
//-----------------------------------------------------------------------------
    public Maquina(String nombre, String ip, List<Integer> codigos) {
        this.nombre = nombre;
        this.ip = ip;
        this.codigos = codigos;
    }
//-----------------------------------------------------------------------------
    public String getNombre() {
        return nombre;
    }

    public String getIp() {
        return ip;
    }

    public List<Integer> getCodigos() {
        return codigos;
    }

    public int getSubred() {
        subred = calcularSubred();
        return subred;
    }
    public int getRiesgo() {
        riesgo = calcularRiesgo();
        return riesgo;
    }
//-----------------------------------------------------------------------------
    private int calcularSubred(){
        String[] partes = ip.split("\\.");

        return Integer.parseInt(partes[2]);
    }

    private int calcularRiesgo(){
        int suma = 0;
        for (int codigo : codigos){
            if(codigo % 5 == 0){
                suma += codigo;
            }
        }
        String nombreSinEspacios = nombre.replaceAll("\\s+"," ");
        Set<Character> caracteresUnicos = new HashSet<>();
        for (char caracter : nombreSinEspacios.toCharArray()){
            caracteresUnicos.add(caracter);
        }

        return suma * caracteresUnicos.size();
    }
//-----------------------------------------------------------------------------
    @Override
    public int hashCode() {
        return nombre.hashCode() * 31 + calcularSubred();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Maquina other = (Maquina) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (ip == null) {
            if (other.ip != null)
                return false;
        } else if (!ip.equals(other.ip))
            return false;
        if (codigos == null) {
            if (other.codigos != null)
                return false;
        } else if (!codigos.equals(other.codigos))
            return false;
        if (subred != other.subred)
            return false;
        if (riesgo != other.riesgo)
            return false;
        return true;
    }
 //-----------------------------------------------------------------------------   
}
