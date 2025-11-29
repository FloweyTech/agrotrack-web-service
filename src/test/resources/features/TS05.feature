Feature: Crear organización mediante la API
  Para centralizar miembros y parcelas por empresa
  Como desarrollador
  Quiero implementar el endpoint POST "/api/organizations"

  Scenario Outline: Creación válida de organización
    Given usuario autenticado
    And el cuerpo contiene nombre "<nombre>" y descripción "<descripcion>" válidos
    When se envía una solicitud POST a "/api/organizations"
    Then se responde con estado 201
    And se retorna el organizationId

    Examples:
      | nombre               | descripcion                        |
      | AgroTech Solutions   | Empresa dedicada a cultivos smart  |
      | CampoVerde S.A.      | Organización agrícola regional     |

  Scenario Outline: Error por nombre duplicado
    Given existe una organización registrada con el nombre "<nombre>"
    When se intenta crear otra organización con ese mismo nombre
    Then se responde con estado 409
    And el mensaje es "conflicto: nombre de organización duplicado"

    Examples:
      | nombre             |
      | AgroTech Solutions |
      | MiFinca Perú       |
