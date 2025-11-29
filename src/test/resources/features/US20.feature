Feature: Registrar materiales de la tarea
  Para el control de insumos utilizados
  Como Agricultor
  Quiero registrar/editar/eliminar materiales usados

  Scenario Outline: Registro de material
    Given la tarea "<tareaId>" está en progreso
    When el Agricultor agrega un material con nombre "<material>" y cantidad "<cantidad>"
    Then el resultado debe ser "material guardado"
    And el sistema guarda el material en la tarea

    Examples:
      | tareaId | material           | cantidad |
      | 101     | Fertilizante NPK   | 50       |
      | 202     | Agua              | 20       |

  Scenario Outline: Edición y eliminación
    Given ya existe un material registrado con id "<materialId>" en la tarea "<tareaId>"
    When el Agricultor actualiza su cantidad o lo elimina
    Then el resultado debe ser "material actualizado"
    And el sistema guarda los cambios
    And actualiza la lista de materiales

    Examples:
      | tareaId | materialId |
      | 303     | 1          |
      | 404     | 2          |