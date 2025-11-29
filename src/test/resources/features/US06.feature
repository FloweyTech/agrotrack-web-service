Feature: Definir tipo de cultivo por parcela
  Para personalizar el monitoreo y recomendaciones
  Como Agrónomo de la organización
  Quiero escoger un tipo de cultivo

  Scenario Outline: Asignación exitosa
    Given el Agrónomo tiene acceso a la parcela "<parcelaId>"
    When selecciona el cultivo "<cultivo_valido>" de la lista de cultivos disponibles
    Then el resultado debe ser "asignación completada"
    And el sistema asigna el cultivo "<cultivo_valido>" a la parcela "<parcelaId>"
    And habilita configuraciones de monitoreo relacionadas

    Examples:
      | parcelaId | cultivo_valido |
      | 101       | Arándano       |
      | 102       | Maíz           |

  Scenario Outline: Cultivo no válido
    Given la lista de cultivos disponibles no incluye el cultivo "<cultivo_no_valido>"
    When el Agrónomo intenta asignarlo
    Then el resultado debe ser "acción rechazada"
    And el sistema rechaza la acción
    And muestra "El cultivo seleccionado no es válido"

    Examples:
      | cultivo_no_valido |
      | X                 |
      | CultivoFantasma   |

  Scenario Outline: Reasignación con historial
    Given la parcela "<parcelaId>" ya tiene el cultivo "<cultivo_anterior>" asignado
    When el Agrónomo cambia el cultivo a "<cultivo_nuevo>"
    Then el resultado debe ser "reasignación con historial guardado"
    And el sistema actualiza el cultivo a "<cultivo_nuevo>"
    And guarda un historial de cambios con fecha y usuario

    Examples:
      | parcelaId | cultivo_anterior | cultivo_nuevo |
      | 103       | Maíz             | Arándano      |
      | 104       | Trigo            | Cebada        |

  Scenario Outline: Registro de cultivo inexistente
    Given la lista de cultivos disponibles no incluye el cultivo "<cultivo_inexistente>"
    When el Agrónomo selecciona la opción "Agregar nuevo cultivo" e ingresa el nombre "<cultivo_inexistente>"
    Then el resultado debe ser "nuevo cultivo agregado y asignado"
    And el sistema registra "<cultivo_inexistente>" en el catálogo de cultivos
    And lo asigna inmediatamente a la parcela seleccionada
    And muestra "Nuevo cultivo agregado y asignado"

    Examples:
      | cultivo_inexistente |
      | Pitahaya            |
      | Aguaymanto          |