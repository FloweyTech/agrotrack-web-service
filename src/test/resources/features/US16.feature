Feature: Crear tarea
  Para planificar el trabajo de campo
  Como Agrónomo
  Quiero crear una tarea (con responsable Agricultor, fecha, prioridad y checklist opcional)

  Scenario Outline: Creación exitosa
    Given el Agrónomo tiene una parcela con checklist opcional
    When crea una tarea con responsable "<responsableId>", fecha "<fecha>", y prioridad "<prioridad>"
    Then el resultado debe ser "tarea guardada y notificada"
    And el sistema guarda la tarea como pendiente
    And notifica al responsable

    Examples:
      | responsableId | fecha        | prioridad  |
      | 201           | 2026-03-15   | ALTA       |
      | 202           | 2025-12-20   | MEDIA      |

  Scenario Outline: Datos faltantes
    Given el Agrónomo crea una tarea
    When no indica responsable o fecha: responsable "<responsableId>", fecha "<fecha>"
    Then el resultado debe ser "creación rechazada"
    And el sistema rechaza la creación
    And muestra un mensaje de error

    Examples:
      | responsableId | fecha        |
      | ""            | 2026-01-01   |
      | 203           | ""           |