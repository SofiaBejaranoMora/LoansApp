package org.una.programmingIII.loans.controllers;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.programmingIII.loans.dtos.UserDTO;
import org.una.programmingIII.loans.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@SecurityRequirement(name = "BearerAuth")
public class UserController {

   @Autowired
   private UserService userService;

   @Operation(summary = "Obtener todos los usuarios")
   @ApiResponse(responseCode = "200", description = "Lista de usuarios", 
       content = @Content(schema = @Schema(implementation = UserDTO.class)))
   @GetMapping
   public ResponseEntity<List<UserDTO>> getAllUsers() {
       List<UserDTO> users = userService.getAllUsers();
       return new ResponseEntity<>(users, HttpStatus.OK);
   }

   @Operation(summary = "Obtener usuario por email")
   @ApiResponse(responseCode = "200", description = "Usuario encontrado", 
       content = @Content(schema = @Schema(implementation = UserDTO.class)))
   @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
   @GetMapping("/findByEmail")
   public ResponseEntity<UserDTO> getUserByEmail(
       @Parameter(description = "Email del usuario", example = "usuario@correo.com") 
       @RequestParam String email) {
       Optional<UserDTO> userDTO = userService.getUserByEmail(email);
       return userDTO.map(ResponseEntity::ok)
               .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
   }

   @Operation(summary = "Crear usuario")
   @ApiResponse(responseCode = "201", description = "Usuario creado", 
       content = @Content(schema = @Schema(implementation = UserDTO.class)))
   @PostMapping
   public ResponseEntity<UserDTO> createUser(
       @RequestBody(description = "DTO con información del usuario", required = true) UserDTO userDTO) {
       UserDTO createdUser = userService.createUser(userDTO);
       return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
   }

   @Operation(summary = "Actualizar usuario")
   @ApiResponse(responseCode = "200", description = "Usuario actualizado", 
       content = @Content(schema = @Schema(implementation = UserDTO.class)))
   @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
   @PutMapping("/{id}")
   public ResponseEntity<UserDTO> updateUser(
       @Parameter(description = "ID del usuario", example = "1") @PathVariable Long id, 
       @RequestBody(description = "DTO con datos actualizados", required = true) UserDTO userDTO) {
       Optional<UserDTO> updatedUser = userService.updateUser(id, userDTO);
       return updatedUser.map(ResponseEntity::ok)
               .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
   }

   @Operation(summary = "Eliminar usuario")
   @ApiResponse(responseCode = "204", description = "Usuario eliminado")
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteUser(
       @Parameter(description = "ID del usuario", example = "1") @PathVariable Long id) {
       userService.deleteUser(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }

   @GetMapping("/page")
    public ResponseEntity<Page<UserDTO>> getUsersPage(@ParameterObject Pageable pageable) {
        Page<UserDTO> page = userService.getUsersPage(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}
