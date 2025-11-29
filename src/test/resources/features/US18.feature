Feature: Completar tarea
  Para cerrar la labor
  Como Agricultor
  Quiero marcar la tarea como completada

  Scenario Outline: Completar la tarea
    Given la tarea "<tareaId>" está en progreso
    When el Agricultor marca todos los ítems y completa la tarea
    Then el resultado debe ser "tarea completada"
    And el sistema cambia el estado a "Completada"

    Examples:
      | tareaId |
      | 101     |
      | 202     |

  Scenario Outline: Tarea incompleto
    Given la tarea "<tareaId>" está en progreso
    When no se completan todos los ítems
    Then el resultado debe ser "finalización impedida"
    And el sistema impide finalizar la tarea
    And muestra un mensaje de advertencia

    Examples:
      | tareaId |
      | 303     |
      | 404     |