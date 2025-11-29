Feature: Iniciar monitoreo en parcela
  Para habilitar el registro de datos en una parcela
  Como desarrollador
  Quiero implementar el endpoint POST "/api/parcels/{parcelaId}/monitoring/start"

  Scenario Outline: Inicio válido de monitoreo
    Given parcela "<parcelaId>" con tipo de planta definido
    When inicio el monitoreo para la parcela "<parcelaId>"
    Then responde 201 y cambia el estado a "Monitoreo activo"

    Examples:
      | parcelaId |
      | 101       |
      | 202       |

  Scenario Outline: Error por parcela sin tipo de planta definido
    Given parcela "<parcelaId>" sin cultivo definido
    When intento iniciar el monitoreo para la parcela "<parcelaId>"
    Then responde 400 "definir tipo de planta primero"

    Examples:
      | parcelaId |
      | 303       |
      | 404       |
