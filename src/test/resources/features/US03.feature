Feature: Remover Agricultor de Organización
  Para mantener el equipo al día y gestionar la colaboración
  Como Agrónomo
  Quiero eliminar a un Agricultor de mi organización

  Scenario Outline: Remoción exitosa
    Given el Agrónomo con ID "<agronomoId>" es Propietario o Admin de la organización "<orgId>"
    And la organización "<orgId>" tiene al Agricultor con ID "<agricultorId>" registrado como miembro
    When el Agrónomo selecciona al Agricultor "<agricultorId>" y confirma la acción de eliminar
    Then el resultado debe ser "remoción completada"
    And el sistema remueve al Agricultor de la lista de miembros de la organización "<orgId>"
    And el sistema muestra el mensaje "Miembro eliminado"

    Examples:
      | agronomoId | orgId | agricultorId |
      | 101        | 50    | 201          |
      | 102        | 51    | 202          |

  Scenario Outline: Cancelación de la eliminación
    Given el Agrónomo con ID "<agronomoId>" está en el proceso de eliminación del Agricultor "<agricultorId>"
    When cancela la acción en el cuadro de confirmación
    Then el resultado debe ser "acción cancelada"
    And el sistema mantiene al Agricultor "<agricultorId>" en la lista de miembros de la organización
    And no muestra el mensaje de eliminación

    Examples:
      | agronomoId | agricultorId |
      | 103        | 203          |
      | 104        | 204          |