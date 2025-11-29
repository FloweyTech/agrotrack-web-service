Feature: Crear tarea
  Para planificar el trabajo del agricultor
  Como desarrollador
  Quiero implementar el endpoint POST "/api/parcels/{parcelaId}/tasks"

  Scenario Outline: Creación válida
    Given soy Agrónomo y el responsable pertenece a la organización
    When envío título "<titulo>", fecha futura "<fecha>" y responsableId "<responsableId>"
    Then responde 201 y notifica al responsable

    Examples:
      | titulo           | fecha        | responsableId |
      | Riego inicial    | 2030-05-10   | 45            |
      | Aplicar abono    | 2031-01-15   | 88            |

  Scenario Outline: Responsable inválido
    Given el responsableId "<responsableId>" no es miembro de la organización
    When intento crear la tarea con ese responsable
    Then responde 400 "responsable inválido"

    Examples:
      | responsableId |
      | 999           |
      | 1234          |

  Scenario Outline: Fecha en el pasado
    Given la fecha indicada "<fecha>" es menor a hoy
    When envío la solicitud de creación de tarea
    Then responde 400 "fecha no válida"

    Examples:
      | fecha        |
      | 2020-01-01   |
      | 2023-12-31   |
