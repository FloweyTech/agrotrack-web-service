Feature: Editar/eliminar registro
  Para corregir errores en los datos registrados
  Como Agricultor
  Quiero editar o eliminar un registro

  Scenario Outline: Edición válida
    Given existe un registro reciente con id "<registroId>"
    When el Agricultor actualiza la información con nuevos valores
    Then el resultado debe ser "cambios guardados con historial"
    And el sistema guarda los cambios
    And conserva un historial de modificaciones

    Examples:
      | registroId |
      | 501        |
      | 502        |

  Scenario Outline: Eliminación válida
    Given existe un registro propio con id "<registroId>"
    When el Agricultor confirma su eliminación
    Then el resultado debe ser "eliminación exitosa"
    And el sistema lo elimina
    And muestra un mensaje de confirmación

    Examples:
      | registroId |
      | 601        |
      | 602        |