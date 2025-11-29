Feature: Registro de datos de planta
  Para llevar el estado fenológico del cultivo
  Como desarrollador
  Quiero implementar el endpoint POST "/api/parcels/{parcelaId}/plant-records"

  Scenario Outline: Registro válido de datos de planta
    Given que soy el Agricultor responsable de la parcela
    And envío hojas "<hojas>", botones "<botones>" y un timestamp válido "<timestamp>"
    When envío la solicitud POST para registrar los datos de planta
    Then se responde con estado 201
    And se guarda el registro

    Examples:
      | hojas | botones | timestamp              |
      | 5     | 2       | 2025-01-10T10:00:00Z   |
      | 0     | 0       | 2025-03-15T14:30:00Z   |

  Scenario Outline: Error por valores negativos
    Given que ingreso hojas "<hojas>"
    When envío el registro
    Then se responde con estado 400
    And el mensaje es "valores no pueden ser negativos"

    Examples:
      | hojas |
      | -1    |
      | -5    |

  Scenario Outline: Error por usuario sin permiso
    Given que no soy responsable de esa parcela
    When intento registrar datos de planta
    Then se responde con estado 403
    And el mensaje es "acceso denegado"

    Examples:
      | placeholder |
      | x           |
