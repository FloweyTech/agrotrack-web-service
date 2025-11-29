Feature: Editar perfil
  Para mantener mis datos actualizados
  Como usuario
  Quiero editar mi perfil en cualquier momento

  Scenario Outline: Edición exitosa
    Given el perfil del usuario "<usuarioId>" ya existe
    When el usuario actualiza sus datos: "<campo_actualizado>" a "<nuevo_valor>"
    Then el resultado debe ser "cambios guardados exitosamente"
    And el sistema guarda los cambios
    And muestra un mensaje de confirmación

    Examples:
      | usuarioId | campo_actualizado | nuevo_valor       |
      | 101       | nombre            | Juan Pérez Jr.    |
      | 102       | contacto          | +51999888777      |

  Scenario Outline: Datos inválidos
    Given el perfil del usuario "<usuarioId>" ya existe
    When el usuario ingresa información "<info_invalida>" en formato inválido
    Then el resultado debe ser "edición rechazada"
    And el sistema rechaza la acción
    And muestra un mensaje de error

    Examples:
      | usuarioId | info_invalida |
      | 103       | email         |
      | 104       | teléfono      |