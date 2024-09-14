import { buildTurnoBuilder } from './builders/barrel.js';
import { deleteTurnoById, getTurnoList } from './services/turno.service.js';

const tableBody = document.querySelector('#tableBody');

let dataList = [];

const buildRows = () => {
  const tableRows = dataList.map(buildTurnoBuilder).join('');
  tableBody.innerHTML = tableRows;
};

const loadData = async () => {
  dataList = await getTurnoList();
  buildRows();
};

const handleGlobalVariables = () => {
  window.handleDeleteButton = async (event, id) => {
    const button = event.currentTarget;

    const isConfirmed = window.confirm(`Esta seguro de eliminar el turno con id ${id}`);
    if (isConfirmed) {
      const isOk = await deleteTurnoById(id);
      const message = isOk ? 'Odontologo eliminado correcamente' : 'Ha ocurrido un error';
      window.alert(message);
      button.parentElement.parentElement.remove();
    }
  };
};

handleGlobalVariables();
loadData();
