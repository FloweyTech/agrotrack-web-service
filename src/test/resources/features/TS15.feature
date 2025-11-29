Feature: Cambio de estado de tarea
  Para reflejar el avance del trabajo del agricultor
  Como desarrollador
  Quiero implementar el endpoint PATCH "/api/tasks/{taskId}/status"

  Scenario Outline: Iniciar tarea (responsable correcto)
    Given la tarea está Asignada al Agricultor
    And el taskId es "<taskId>"
    And el estado enviado es "<estado>"
    When envío la solicitud para cambiar el estado
    Then responde 200
    And registra startedAt

    Examples:
      | taskId | estado        |
      | 101    | in_progress   |
      | 202    | in_progress   |

  Scenario Outline: Completar con checklist pendiente
    Given la tarea tiene checklist con ítems sin marcar
    And el taskId es "<taskId>"
    And el estado enviado es "<estado>"
    When envío la solicitud para cambiar el estado
    Then responde 400
    And el mensaje es "completar checklist antes de cerrar"

    Examples:
      | taskId | estado     |
      | 303    | completed  |
      | 404    | completed  |

  Scenario Outline: Reabrir la tarea
    Given la tarea está completed
    And el taskId es "<taskId>"
    And el estado enviado es "<estado>"
    And se especifica un motivo "<motivo>"
    When envío la solicitud para cambiar el estado
    Then responde 200
    And registra auditoría del cambio

    Examples:
      | taskId | estado   | motivo                   |
      | 505    | reopened | Error en validación      |
      | 606    | reopened | Se encontró nueva falla  |
