package FC;

import javax.swing.*;
import java.util.*;

public class CandidatosList {

    int contador;

    private ArrayList<Candidato> candidatosList = new ArrayList<>();

    public ArrayList<Candidato> getCandidatosList() {
        return candidatosList;
    }

    public void setCandidatosList(ArrayList<Candidato> candidatosList) {
        this.candidatosList = candidatosList;
    }

    public ArrayList<Candidato> filtrar(String ciudad) {

        ArrayList<Candidato> candidatosCiudad = new ArrayList<>();
        for (Candidato candidato : candidatosList) {
            if (Objects.equals(ciudad, candidato.getCiudad())) {
                candidatosCiudad.add(candidato);
            }
        }
        return candidatosCiudad;
    }

    //--------------------------------------------------//

    public ArrayList<Candidato> filtrar(boolean tipoTraslado) {

        ArrayList<Candidato> candidatosTraslado = new ArrayList<>();

        for (Candidato candidato : candidatosList) {
            if (tipoTraslado == candidato.getTipoTraslado()) {
                candidatosTraslado.add(candidato);
            }
        }
        return candidatosTraslado;
    }

    //--------------------------------------------------//


    public ArrayList<Candidato> filtrar(Presencialidad tipoPresencialidad) {

        ArrayList<Candidato> candidatosPresencialidad = new ArrayList<>();

        for (Candidato candidato : candidatosList) {
            if (tipoPresencialidad == candidato.getPresencialidad()) {
                candidatosPresencialidad.add(candidato);
            }
        }
        return candidatosPresencialidad;
    }

    //--------------------------------------------------//

    public Candidato buscarEmail(String email) {

        Candidato candidatoEmail = new Candidato();

        for (Candidato candidato : candidatosList) {
            if (Objects.equals(email, candidato.getEmail())) {
                candidatoEmail = candidato;
            }
        }
        return candidatoEmail;

    }

    //--------------------------------------------------//

    public Candidato buscarTelefono(String telefono) {

        Candidato candidatoTelefono = new Candidato();

        for (Candidato candidato : candidatosList) {
            if (Objects.equals(telefono, candidato.getTelefono())) {
                candidatoTelefono = candidato;
            }
        }
        return candidatoTelefono;
    }

    //--------------------------------------------------//

    public ArrayList<Candidato> filtrar(String ciudad, Presencialidad tipoPresencialidad, boolean tipoTraslado) {

        ArrayList<Candidato> candidatosCPT = new ArrayList<>();

        for (Candidato candidato : candidatosList) {
            if (
                    Objects.equals(ciudad, candidato.getCiudad())
                            && tipoPresencialidad == candidato.getPresencialidad()
                            && tipoTraslado == candidato.getTipoTraslado()) {

                candidatosCPT.add(candidato);
            }
        }
        return candidatosCPT;
    }

    //--------------------------------------------------//

    public void eliminar(int candidato) {
        candidatosList.remove(candidato);
    }


    public void imprimeCiudadesTop() {

        Map<String, Integer> cRepetidas = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        for (Candidato i : candidatosList) {
            cRepetidas.merge(i.getCiudad(), 1, Integer::sum);
        }
        List<Map.Entry<String, Integer>> pOrdenados = new ArrayList<>(cRepetidas.entrySet());
        pOrdenados.sort(Map.Entry.comparingByValue());
        Collections.reverse(pOrdenados);
        sb.append("Candidatos ordenados por cantidad en ciudades\nen forma descendiente\n");
        for (Map.Entry<String, Integer> pOrdenado : pOrdenados) {
            sb.append("CIUDAD: ").append(pOrdenado.getKey().toUpperCase(Locale.ROOT)).append("- CANTIDAD: ").append(pOrdenado.getValue()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb);


    }

    public void imprimePaisesTop() {

        Map<String, Integer> pRepetidas = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        for (Candidato i : candidatosList) {
            pRepetidas.merge(i.getPais(), 1, Integer::sum);
        }
        List<Map.Entry<String, Integer>> pOrdenados = new ArrayList<>(pRepetidas.entrySet());
        pOrdenados.sort(Map.Entry.comparingByValue());
        Collections.reverse(pOrdenados);
        sb.append("Candidatos ordenados por cantidad en paises\nen forma descendiente\n");
        for (Map.Entry<String, Integer> pOrdenado : pOrdenados) {
            sb.append("PAIS: ").append(pOrdenado.getKey().toUpperCase(Locale.ROOT)).append("- CANTIDAD: ").append(pOrdenado.getValue()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb);

    }

    public int totalRemotos() {
        contador = 0;
        for (Candidato candidato : candidatosList) {
            if (candidato.getPresencialidad() == Presencialidad.REMOTO) {
                contador++;
            }
        }
        return contador;
    }

    public int totalPosibilidadTraslado() {
        contador = 0;
        for (Candidato candidato : candidatosList) {
            if (candidato.getTipoTraslado()) {
                contador++;
            }
        }
        return contador;
    }

    public int totalPresencialSinTraslado() {
        contador = 0;
        for (Candidato candidato : candidatosList) {
            if (!candidato.getTipoTraslado() && candidato.getPresencialidad() == Presencialidad.PRESENCIAL) {
                contador++;
            }
        }
        return contador;
    }
}