package com.example.sis414recetas.Controllers;

import com.example.sis414recetas.Models.PacienteModel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private static List<PacienteModel> pacientes = new ArrayList<>();

    static {
        pacientes.add(new PacienteModel(
                "Juan Perez",
                "1234567",
                25,
                "Dolor de cabeza"
        ));
    }

    @GetMapping
    public List<PacienteModel> obtenerPacientes() {
        return pacientes;
    }

    @PostMapping
    public PacienteModel guardarPaciente(@RequestBody PacienteModel paciente) {
        pacientes.add(paciente);
        return paciente;
    }

    @PatchMapping
    public String actualizarParcialPaciente(
            @RequestBody PacienteModel pacienteConCambios) {

        for (PacienteModel p : pacientes) {

            if (p.getCi().equalsIgnoreCase(pacienteConCambios.getCi())) {

                if (pacienteConCambios.getNombre() != null) {
                    p.setNombre(pacienteConCambios.getNombre());
                }

                if (pacienteConCambios.getEdad() != 0) {
                    p.setEdad(pacienteConCambios.getEdad());
                }

                if (pacienteConCambios.getMotivoConsulta() != null) {
                    p.setMotivoConsulta(
                            pacienteConCambios.getMotivoConsulta()
                    );
                }

                return "Paciente actualizado parcialmente con éxito";
            }
        }

        return "No se encontró el paciente para actualizar";
    }

    @DeleteMapping
    public String eliminarPaciente(
            @RequestBody PacienteModel pacienteABorrar) {

        boolean eliminado = pacientes.removeIf(p ->
                p.getCi().equalsIgnoreCase(pacienteABorrar.getCi())
        );

        if (eliminado) {
            return "Paciente eliminado con éxito";
        }

        return "No se encontró el paciente especificado";
    }
}