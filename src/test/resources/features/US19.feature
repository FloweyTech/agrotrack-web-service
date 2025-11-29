Feature: Modificar/cancelar tarea
  Para replanificar el trabajo
  Como Agrónomo
  Quiero modificar fecha, prioridad, responsable o cancelar una tarea

  Scenario Outline: Modificación de fecha y prioridad
    Given la tarea "<tareaId>" está pendiente
    When el Agrónomo actualiza la fecha a "<fecha_nueva>" y la prioridad a "<prioridad_nueva>"
    Then el resultado debe ser "cambios guardados"
    And el sistema guarda los cambios

    Examples:
      | tareaId | fecha_nueva | prioridad_nueva |
      | 101     | 2026-05-15  | ALTA            |
      | 202     | 2026-01-20  | MEDIA           |

  Scenario Outline: Cambio de responsable
    Given la tarea "<tareaId>" está pendiente
    When el Agrónomo asigna a otro agricultor con ID "<responsable_nuevo>"
    Then el resultado debe ser "responsable actualizado y notificado"
    And el sistema actualiza la asignación
    And notifica al nuevo responsable

    Examples:
      | tareaId | responsable_nuevo |
      | 303     | 400               |
      | 404     | 500               |

  Scenario Outline: Cancelación
    Given la tarea "<tareaId>" está pendiente
    When el Agrónomo la cancela
    Then el resultado debe ser "tarea cancelada"
    And el sistema cambia el estado a "Cancelada"

    Examples:
      | tareaId |
      | 505     |
      | 606     |