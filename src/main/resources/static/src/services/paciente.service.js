/**
 * @typedef {import("../interfaces/paciente.model.ts").Paciente} PacienteDetails
 */

/**
 * @returns {Promise<Array<PacienteDetails>>}
 */
export const getPacienteList = async () => {
  const res = await fetch('http://localhost:8080/paciente');

  if (!res.ok) {
    throw new Error('GET: /paciente throws ' + res.status + ' status code');
  }

  return await res.json();
};

/**
 * @returns {Promise<Array<PacienteDetails>>}
 */
export const addPaciente = async (paciente) => {
  const res = await fetch('http://localhost:8080/paciente', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(paciente)
  });

  if (!res.ok) {
    throw new Error('POST: /paciente throws ' + res.status + ' status code');
  }

  return await res.json();
};

/**
 * @param {PacienteDetails} paciente
 * @returns {Promise<Array<PacienteDetails>>}
 */
export const editPacienteById = async (paciente) => {
  const res = await fetch('http://localhost:8080/paciente', {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(paciente)
  });

  return res.ok;
};

/**
 * @param {Number} id
 * @returns {Promise<boolean>>}
 */
export const deletePacienteById = async (id) => {
  const res = await fetch(`http://localhost:8080/paciente/${id}`, {
    method: 'DELETE'
  });

  return res.ok;
};
