/**
 * @typedef {import("../interfaces/odontologo.model.ts").Odontologo} OdontologoDetails
 */

/**
 * @returns {Promise<Array<OdontologoDetails>>}
 */
export const getOdontologoList = async () => {
  const res = await fetch('http://localhost:8080/odontologo');

  if (!res.ok) {
    throw new Error('GET: /odontologo throws ' + res.status + ' status code');
  }

  return await res.json();
};

/**
 * @returns {Promise<Array<OdontologoDetails>>}
 */
export const addOdontologo = async (odontologo) => {
  const res = await fetch('http://localhost:8080/odontologo', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(odontologo)
  });

  if (!res.ok) {
    throw new Error('POST: /odontologo throws ' + res.status + ' status code');
  }

  return await res.json();
};

/**
 * @param {OdontologoDetails} odontologo
 * @returns {Promise<Array<OdontologoDetails>>}
 */
export const editOdontologoById = async (odontologo) => {
  const res = await fetch('http://localhost:8080/odontologo', {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(odontologo)
  });

  return res.ok;
};

/**
 * @param {Number} id
 * @returns {Promise<boolean>>}
 */
export const deleteOdontologoById = async (id) => {
  const res = await fetch(`http://localhost:8080/odontologo/${id}`, {
    method: 'DELETE'
  });

  return res.ok;
};
