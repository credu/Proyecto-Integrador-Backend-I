export interface Paciente {
    id: Number;
    nombre: string;
    apellido: string;
    cedula: string;
    fechaDeIngreso: string;
    domicilio: import("../models/domicilio.model.ts").Domicilio;
    email: string;
};