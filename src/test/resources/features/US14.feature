Feature: Alertas por umbrales de cultivo
  Para generar alertas internas basadas en métricas del cultivo
  Como Agrónomo
  Quiero configurar umbrales (humedad/temperatura)

  Scenario Outline: Alerta por humedad baja
    Given el Agrónomo configuró un rango de humedad mínima de "<humedad_min>"
    When el Agricultor registra un valor de humedad de "<valor_registrado>" por debajo de ese rango
    Then el resultado debe ser "alerta de humedad baja generada"
    And el sistema genera una alerta de humedad baja

    Examples:
      | humedad_min | valor_registrado |
      | 45%         | 40%              |
      | 60%         | 55%              |

  Scenario Outline: Edición de umbrales
    Given ya existen umbrales configurados para la parcela
    When el Agrónomo actualiza los umbrales con nuevos valores
    Then el resultado debe ser "configuración de umbrales actualizada"
    And el sistema guarda la nueva configuración
    And usa esos valores para futuras alertas

    Examples:
      | placeholder |
      | actualizar  |