import { getOdontologoList } from './services/odontologo.service.js';
import { getPacienteList } from './services/paciente.service.js';
import { addTurno } from './services/turno.service.js';

const turnoForm = document.querySelector('#turnoForm');

const handleLoadData = async () => {
  const pacienteList = await getPacienteList();
  const odontologoList = await getOdontologoList();

  const odontologoSelect = turnoForm.elements.odontologoId;
  const pacienteSelect = turnoForm.elements.pacienteId;

  const pacienteOptions = pacienteList.map(paciente => `<option value="${paciente.id}">${paciente.nombre} ${paciente.apellido}</option>`).join('');
  const odontologoOptions = odontologoList.map(odontologo => `<option value="${odontologo.id}">${odontologo.nombre} ${odontologo.apellido}</option>`).join('');

  odontologoSelect.innerHTML += pacienteOptions;
  pacienteSelect.innerHTML += odontologoOptions;
};

const handleEvents = async () => {
  turnoForm.addEventListener('submit', async (event) => {
    event.preventDefault();

    const form = event.target;
    const dataForm = Object.fromEntries(new FormData(form));
    const turno = {
      paciente: {
        id: Number(dataForm.pacienteId)
      },
      odontologo: {
        id: Number(dataForm.pacienteId)
      },
      fecha: dataForm.fecha
    };

    const turnoScheduled = await addTurno(turno);
    window.alert(`Turno agendado con el odontologo ${turnoScheduled.odontologo.nombre} ${turnoScheduled.odontologo.apellido} con el paciente ${turnoScheduled.paciente.nombre} ${turnoScheduled.paciente.apellido}`);
    form.reset();
  });
};

handleLoadData();
handleEvents();
