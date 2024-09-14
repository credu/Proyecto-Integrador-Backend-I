import { buildOdontologoRow } from './builders/barrel.js';
import { addOdontologo, deleteOdontologoById, editOdontologoById, getOdontologoList } from './services/odontologo.service.js';

const tableBody = document.querySelector('#tableBody');
const modalText = document.querySelector('#modalText');
const modalContainer = document.querySelector('#modalContainer');
const modalForm = document.querySelector('#modalForm');
const modalCloseButton = document.querySelector('#modalCloseButton');

let dataList = [];

const buildRows = () => {
  const tableRows = dataList.map(buildOdontologoRow).join('');
  tableBody.innerHTML = tableRows;
};

const loadData = async () => {
  dataList = await getOdontologoList();
  buildRows();
};

const closeModal = () => {
  modalContainer.classList.add('hidden');
  modalForm.reset();
};

const handleGlobalVariables = () => {
  window.handleDeleteButton = async (event, id) => {
    const button = event.currentTarget;

    const isConfirmed = window.confirm(`Esta seguro de eliminar el odontologo con id ${id}`);
    if (isConfirmed) {
      const isOk = await deleteOdontologoById(id);
      const message = isOk ? 'Odontologo eliminado correcamente' : 'Ha ocurrido un error';
      window.alert(message);
      button.parentElement.parentElement.remove();
    }
  };

  window.openModalForCreate = (event) => {
    modalContainer.classList.remove('hidden');
    modalText.innerText = 'Nuevo odontologo';

    modalForm.elements.id.value = '';
    modalForm.elements.submitButton.innerText = 'Agregar odontologo';
  };

  window.openModalForEdit = (event, odontologo) => {
    modalContainer.classList.remove('hidden');
    modalText.innerText = 'Editar odontologo';

    modalForm.elements.id.value = odontologo.id;
    modalForm.elements.nombre.value = odontologo.nombre;
    modalForm.elements.apellido.value = odontologo.apellido;
    modalForm.elements.matricula.value = odontologo.matricula;
    modalForm.elements.submitButton.innerText = 'Actualizar odontologo';
  };
};

const handleEvents = () => {
  modalCloseButton.addEventListener('click', () => {
    closeModal();
  });

  modalForm.addEventListener('submit', async (event) => {
    event.preventDefault();
    const form = event.target;
    const formData = Object.fromEntries(new FormData(form));

    if (formData.id) {
      formData.id = Number(formData.id);
      const wasEdited = await editOdontologoById(formData);
      if (wasEdited) {
        dataList = dataList.map(data => (formData.id === data.id) ? formData : data);
      }
    } else {
      const odontologo = await addOdontologo(formData);
      dataList.push(odontologo);
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
