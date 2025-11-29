Feature: Aceptar e iniciar tarea
  Para registrar que empecé a ejecutar la tarea
  Como Agricultor asignado
  Quiero aceptar e iniciar una tarea

  Scenario Outline: Aceptación e inicio
    Given existe una tarea "<tareaId>" pendiente asignada al Agricultor
    When la acepta e inicia desde la app
    Then el resultado debe ser "tarea en progreso"
    And el sistema cambia el estado a "En progreso"

    Examples:
      | tareaId |
      | 101     |
      | 202     |

  Scenario Outline: Rechazo de tarea
    Given existe una tarea "<tareaId>" pendiente
    When el Agricultor la rechaza e indica un motivo "<motivo>"
    Then el resultado debe ser "tarea rechazada y notificado"
    And el sistema cambia el estado a "Rechazada"
    And notifica al Agrónomo

    Examples:
      | tareaId | motivo                |
      | 303     | Falta de insumos      |
      | 404     | Conflicto de horario |