Feature: Alertas climáticas automáticas
  Para anticipar riesgos en el cultivo
  Como Agrónomo
  Quiero recibir alertas de clima

  Scenario Outline: Alerta por evento climático
    Given existe un pronóstico de un cambio climático "<evento>" que puede afectar el cultivo
    When el sistema procesa la información meteorológica
    Then el resultado debe ser "alerta enviada a responsables"
    And envía una alerta de riesgo al Agrónomo y al Agricultor responsable

    Examples:
      | evento       |
      | Lluvia intensa |
      | Helada       |
      | Ola de calor |

  Scenario Outline: Silenciar alertas
    Given el Agrónomo ya recibió una alerta
    When decide silenciar notificaciones por un periodo de tiempo "<duracion>"
    Then el resultado debe ser "alerta silenciada temporalmente"
    And el sistema respeta esa configuración y no envía nuevas alertas en ese lapso

    Examples:
      | duracion     |
      | 1 hora       |
      | 1 día        |