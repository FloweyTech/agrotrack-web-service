Feature: Registro de condiciones ambientales
  Para monitorear el ambiente de la parcela
  Como desarrollador
  Quiero implementar el endpoint POST "/api/parcels/{parcelaId}/environment-records"

  Scenario Outline: Registro válido
    Given monitoreo activo en la parcela
    And la humedad "<humedad>" y la temperatura "<temperatura>" están dentro del rango permitido
    When envío el registro ambiental
    Then se responde con estado 201
    And se retorna el registroId

    Examples:
      | humedad | temperatura |
      | 45      | 22          |
      | 80      | 18          |

  Scenario Outline: Humedad fuera de rango
    Given ingreso humedad "<humedad>"
    When envío el registro
    Then se responde con estado 400
    And el mensaje es "humedad fuera de 0–100%"

    Examples:
      | humedad |
      | 120     |
      | -5      |

  Scenario Outline: Parcela sin monitoreo activo
    Given la parcela no tiene monitoreo iniciado
    When intento registrar condiciones ambientales
    Then se responde con estado 409
    And el mensaje es "activar monitoreo primero"

    Examples:
      | parcelaId |
      | 10        |
      | 25        |
