Feature: Solicitar reporte general de organización
  Para mostrar indicadores globales de la organización
  Como desarrollador
  Quiero implementar el endpoint GET "/api/organizations/{orgId}/report"

  Scenario Outline: Reporte general disponible
    Given la organización "<orgId>" tiene parcelas y tareas activas
    When solicito el reporte general
    Then se responde con estado 200 OK
    And se retorna la información con KPIs agregados

    Examples:
      | orgId |
      | 100   |
      | 200   |

  Scenario Outline: Error por falta de permisos (Sin permisos)
    Given el usuario no es miembro de la organización "<orgId>"
    When intenta solicitar el reporte
    Then se responde con estado 403 Forbidden
    And el mensaje es "acceso denegado"

    Examples:
      | orgId |
      | 300   |
      | 400   |