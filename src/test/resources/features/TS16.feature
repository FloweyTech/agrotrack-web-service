Feature: Registrar materiales usados en tarea
  Para controlar insumos utilizados
  Como desarrollador
  Quiero implementar el endpoint POST "/api/tasks/{taskId}/materials"

  Scenario Outline: Alta de material usado
    Given la tarea con id "<taskId>" está en progreso
    When envío el nombre "<nombre>", cantidad "<cantidad>" y unidad "<unidad>"
    Then responde 201 Created con materialId

    Examples:
      | taskId | nombre             | cantidad | unidad |
      | 101    | Fertilizante NPK   | 50       | kg     |
      | 202    | Pesticida B        | 1.5      | litro  |

  Scenario Outline: Error por material duplicado en la tarea
    Given la tarea con id "<taskId>" está en progreso
    And el material "<nombre>" ya está registrado en esa tarea
    When intento registrar otra vez el material "<nombre>"
    Then responde 409 Conflict
    And el mensaje es "material duplicado"

    Examples:
      | taskId | nombre             |
      | 101    | Fertilizante NPK   |
      | 202    | Pesticida B        |