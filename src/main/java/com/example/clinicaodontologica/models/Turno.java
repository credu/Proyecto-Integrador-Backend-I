package com.example.clinicaodontologica.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    @NonNull
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "odontologo_id")
    @NonNull
    private Odontologo odontologo;
    @NonNull
    private LocalDate fecha;
}
