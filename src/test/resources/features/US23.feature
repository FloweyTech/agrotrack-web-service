Feature: Generar/descargar reporte
  Para compartir el reporte de desempeño
  Como Agrónomo
  Quiero generar y descargar el reporte

  Scenario Outline: Descarga disponible
    Given el reporte con ID "<reporteId>" ya está generado
    When el Agrónomo lo descarga
    Then el resultado debe ser "archivo entregado y descarga registrada"
    And el sistema entrega el archivo
    And registra la descarga

    Examples:
      | reporteId |
      | 101       |
      | 202       |

  Scenario Outline: Reporte en proceso
    Given el reporte con ID "<reporteId>" aún está generándose
    When el Agrónomo intenta descargarlo
    Then el resultado debe ser "descarga bloqueada"
    And el sistema bloquea la acción
    And muestra un mensaje de espera

    Examples:
      | reporteId |
      | 303       |
      | 404       |