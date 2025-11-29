Feature: Solicitar reporte de parcela
  Para entregar un resumen de desempeño de la parcela
  Como desarrollador
  Quiero implementar el endpoint GET "/api/parcels/{parcelaId}/report"

  Scenario Outline: Reporte JSON disponible
    Given la parcela "<parcelaId>" tiene datos suficientes
    When solicito GET "/api/parcels/<parcelaId>/report?format=json"
    Then se responde con estado 200 OK
    And se retorna métricas y series temporales en formato JSON

    Examples:
      | parcelaId |
      | 101       |
      | 202       |

  Scenario Outline: Reporte PDF disponible
    Given la parcela "<parcelaId>" tiene datos suficientes
    When solicito GET "/api/parcels/<parcelaId>/report?format=pdf"
    Then se responde con estado 200 OK
    And el Content-Type es "application/pdf"

    Examples:
      | parcelaId |
      | 303       |
      | 404       |

  Scenario Outline: Parcela sin datos
    Given la parcela "<parcelaId>" no tiene registros
    When solicito el reporte
    Then se responde con estado 404 Not Found
    And el mensaje es "no hay datos para reportar"

    Examples:
      | parcelaId |
      | 505       |
      | 606       |

  Scenario Outline: Control de acceso
    Given el usuario no pertenece a la organización dueña de la parcela "<parcelaId>"
    When intento descargar el reporte
    Then se responde con estado 403 Forbidden
    And el mensaje es "acceso denegado"

    Examples:
      | parcelaId |
      | 707       |
      | 808       |