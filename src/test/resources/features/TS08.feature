Feature: Asignar agricultor a una parcela
  Para definir quién opera cada parcela
  Como desarrollador
  Quiero implementar el endpoint POST "/api/organizations/{orgId}/parcels/{parcelId}/assign"

  Scenario Outline: Asignación válida de responsable
    Given que el usuario "<userId>" pertenece a la organización
    When envío el userId "<userId>" como responsable
    Then se responde con estado 200
    And se vincula correctamente al responsable

    Examples:
      | userId |
      | 123    |
      | 456    |

  Scenario Outline: Error por usuario fuera de la organización
    Given que el usuario "<userId>" no es miembro de la organización
    When intento asignarlo como responsable
    Then se responde con estado 400
    And el mensaje es "responsable inválido"

    Examples:
      | userId |
      | 999    |
      | 888    |
