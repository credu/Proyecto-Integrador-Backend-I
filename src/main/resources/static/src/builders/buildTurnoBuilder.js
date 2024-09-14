export const buildTurnoBuilder = (turno) => {
  return (
        `<tr>
            <td>${turno.id}</td>
            <td>${turno.paciente.nombre} ${turno.paciente.apellido}</td>
            <td>${turno.odontologo.nombre} ${turno.odontologo.apellido}</td>
            <td>${turno.fecha}</td>
            <td>
                <button
                    type="button"
                    id="deleteBtn"
                    class="bg-[#F33709] p-3 rounded-[10px]"
                    onclick="handleDeleteButton(event,${turno.id})"
                >
                    <svg
                        xmlns="http://www.w3.org/2000/svg"
                        fill="none"
                        viewBox="0 0 24 24"
                        stroke-width="1.5"
                        stroke="#FFFFFF"
                        class="size-6"
                    >
                        <path
                            stroke-linecap="round"
                            stroke-linejoin="round"
                            d="M6 18 18 6M6 6l12 12"
                        />
                    </svg>
                </button>
            </td>
        </tr>`
  );
};
