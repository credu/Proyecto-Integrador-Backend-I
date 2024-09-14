import { buildPacienteBuilder } from './builders/barrel.js';
import { addPaciente, deletePacienteById, editPacienteById, getPacienteList } from './services/paciente.service.js';

const tableBody = document.querySelector('#tableBody');
const modalText = document.querySelector('#modalText');
const modalContainer = document.querySelector('#modalContainer');
const modalForm = document.querySelector('#modalForm');
const modalCloseButton = document.querySelector('#modalCloseButton');

let dataList = [];

const buildRows = () => {
  const tableRows = dataList.map(buildPacienteBuilder).join('');
  tableBody.innerHTML = tableRows;
};

const loadData = async () => {
  dataList = await getPacienteList();
  buildRows();
};

const closeModal = () => {
  modalContainer.classList.add('hidden');
  modalForm.reset();
};

const handleGlobalVariables = () => {
  window.handleDeleteButton = async (event, id) => {
    const button = event.currentTarget;

    const isConfirmed = window.confirm(`Esta seguro de eliminar el paciente con id ${id}`);
    if (isConfirmed) {
      const isOk = await deletePacienteById(id);
      const message = isOk ? 'Paciente eliminado correcamente' : 'Ha ocurrido un error';
      window.alert(message);
      button.parentElement.parentElement.remove();
    }
  };

  window.openModalForCreate = (event) => {
    modalContainer.classList.remove('hidden');
    modalText.innerText = 'Nuevo paciente';

    modalForm.elements.id.value = '';
    modalForm.elements.submitButton.innerText = 'Agregar paciente';
  };

  window.openModalForEdit = (event, paciente) => {
    modalContainer.classList.remove('hidden');
    modalText.innerText = 'Editar paciente';

    modalForm.elements.id.value = paciente.id;
    modalForm.elements.nombre.value = paciente.nombre;
    modalForm.elements.apellido.value = paciente.apellido;
    modalForm.elements.cedula.value = paciente.cedula;
    modalForm.elements.fechaIngreso.value = paciente.fechaIngreso;
    modalForm.elements.email.value = paciente.email;
    modalForm.elements.domicilioId.value = paciente.domicilio.id;
    modalForm.elements.domicilioCalle.value = paciente.domicilio.calle;
    modalForm.elements.domicilioNumero.value = paciente.domicilio.numero;
    modalForm.elements.domicilioLocalidad.value = paciente.domicilio.localidad;
    modalForm.elements.domicilioProvincia.value = paciente.domicilio.provincia;
    modalForm.elements.submitButton.innerText = 'Actualizar paciente';
  };
};

const convertFormToPaciente = (form) => {
  const data = Object.fromEntries(new FormData(form));

  return {
    id: data.id,
    nombre: data.nombre,
    apellido: data.apellido,
    cedula: data.cedula,
    fechaIngreso: data.fechaIngreso,
    email: data.email,
    domicilio: {
      id: data.domicilioId,
      calle: data.domicilioCalle,
      numero: data.domicilioNumero,
      localidad: data.domicilioLocalidad,
      provincia: data.domicilioProvincia
    }
  };
};

const handleEvents = () => {
  modalCloseButton.addEventListener('click', () => {
    closeModal();
  });

  modalForm.addEventListener('submit', async (event) => {
    event.preventDefault();
    const form = event.target;
    const formData = convertFormToPaciente(form);

    if (formData.id && formData.domicilio.id) {
      formData.id = Number(formData.id);
      formData.domicilio.id = Number(formData.domicilio.id);
      const wasEdited = await editPacienteById(formData);
      if (wasEdited) {
        dataList = dataList.map(data => (formData.id === data.id) ? formData : data);
      }
    } else {
      const paciente = await addPaciente(formData);
      dataList.push(paciente);
    }
    console.log(dataList);

    buildRows();
    modalForm.reset();
    closeModal();
  });
};

handleGlobalVariables();
loadData();
handleEvents();
