Feature: Creación de parcelas
  Para gestionar cultivos y su monitoreo
  Como desarrollador
  Quiero implementar el endpoint POST "/api/organizations/{orgId}/parcels"

  Scenario Outline: Creación válida de parcela
    Given soy Agrónomo autenticado con suscripción vigente y cupo disponible
    And envío un cuerpo válido con nombre "<nombre>", ubicación "<ubicacion>" y área "<area>"
    When realizo una solicitud POST a "/api/organizations/<orgId>/parcels"
    Then la API responde 201
    And retorna el parcelaId

    Examples:
      | orgId | nombre   | ubicacion        | area |
      | 1     | Lote Sur | Sector 3 - Valle | 2.5  |
      | 1     | Campo 12 | Zona Norte       | 1.8  |

  Scenario Outline: Límite de parcelas alcanzado
    Given la organización "<orgId>" alcanzó el máximo de parcelas permitidas por el plan
    When intento crear una nueva parcela con nombre "<nombre>"
    Then la API responde 403
    And el mensaje es "límite de parcelas alcanzado"

    Examples:
      | orgId | nombre     |
      | 2     | Parcela X  |
      | 2     | Campo Final|

  Scenario Outline: Nombre duplicado en la misma organización
    Given ya existe una parcela llamada "<nombre>" en la organización "<orgId>"
    When intento crear nuevamente una parcela con el nombre "<nombre>"
    Then la API responde 409
    And el mensaje describe el conflicto

    Examples:
      | orgId | nombre  |
      | 3     | Lote A  |
      | 3     | Lote A  |
