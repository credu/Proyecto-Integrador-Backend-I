/**
 * @typedef {import("../interfaces/turno.model.ts").Turno} TurnoDetails
 */

/**
 * @returns {Promise<Array<TurnoDetails>>}
 */
export const getTurnoList = async () => {
  const res = await fetch('http://localhost:8080/turno');

  if (!res.ok) {
    throw new Error('GET: /turno throws ' + res.status + ' status code');
  }

  return await res.json();
};

/**
 * @returns {Promise<Array<TurnoDetails>>}
 */
export const addTurno = async (turno) => {
  console.log(turno);
  const res = await fetch('http://localhost:8080/turno', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(turno)
  });

  if (!res.ok) {
    throw new Error('POST: /turno throws ' + res.status + ' status code');
  }

  return await res.json();
};

/**
 * @param {TurnoDetails} turno
 * @returns {Promise<Array<TurnoDetails>>}
 */
export const editTurnoById = async (turno) => {
  const res = await fetch('http://localhost:8080/turno', {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(turno)
  });

  return res.ok;
};

/**
 * @param {Number} id
 * @returns {Promise<boolean>>}
 */
export const deleteTurnoById = async (id) => {
  const res = await fetch(`http://localhost:8080/turno/${id}`, {
    method: 'DELETE'
  });

  return res.ok;
};
