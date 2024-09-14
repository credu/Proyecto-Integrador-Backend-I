export const buildOdontologoRow = (odontologo) => {
  return (
        `<tr>
            <td>${odontologo.id}</td>
            <td>${odontologo.nombre}</td>
            <td>${odontologo.apellido}</td>
            <td>${odontologo.matricula}</td>
            <td>
                <button
                    type="button"
                    id="editBtn"
                    class="bg-[#04C9D8] p-3 rounded-[10px]"
                    onclick='openModalForEdit(event,${JSON.stringify(odontologo)})'
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
                            d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L6.832 19.82a4.5 4.5 0 0 1-1.897 1.13l-2.685.8.8-2.685a4.5 4.5 0 0 1 1.13-1.897L16.863 4.487Zm0 0L19.5 7.125"
                        />
                    </svg>
                </button>
                <button
                    type="button"
                    id="deleteBtn"
                    class="bg-[#F33709] p-3 rounded-[10px]"
                    onclick="handleDeleteButton(event,${odontologo.id})"
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
