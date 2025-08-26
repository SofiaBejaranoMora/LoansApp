package org.una.programmingIII.loans.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.una.programmingIII.loans.dtos.ClientDTO;
import org.una.programmingIII.loans.dtos.UserDTO;
import org.una.programmingIII.loans.models.Client;
import org.una.programmingIII.loans.models.User;
import org.una.programmingIII.loans.repositories.UserRepository;
import org.una.programmingIII.loans.transformers.GenericMapper;
import org.una.programmingIII.loans.transformers.GenericMapperFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private GenericMapperFactory mapperFactory;

   @Override
   public List<UserDTO> getAllUsers() {
       GenericMapper<User, UserDTO> userMapper = mapperFactory.createMapper(User.class, UserDTO.class);
       return userRepository.findAll().stream()
               .map(userMapper::convertToDTO)
               .collect(Collectors.toList());
   }

   @Override
   public Optional<UserDTO> getUserByEmail(String email) {
       GenericMapper<User, UserDTO> userMapper = mapperFactory.createMapper(User.class, UserDTO.class);
       return Optional.ofNullable(userRepository.findByEmail(email))
               .map(userMapper::convertToDTO);
   }
   @Override
   public UserDTO createUser(UserDTO userDTO) {
    GenericMapper<User, UserDTO> userMapper = mapperFactory.createMapper(User.class, UserDTO.class);
    User user = userMapper.convertToEntity(userDTO);

    if(user.getPassword() != null && !user.getPassword().isEmpty()) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    User savedUser = userRepository.save(user);
    return userMapper.convertToDTO(savedUser);
   }

   @Override
   public Optional<UserDTO> updateUser(Long id, UserDTO userDTO) {
       GenericMapper<User, UserDTO> userMapper = mapperFactory.createMapper(User.class, UserDTO.class);
       return userRepository.findById(id)
               .map(existingUser -> {
                   User updatedUser = userMapper.convertToEntity(userDTO);
                   updatedUser.setId(id);
                   updatedUser.setCreatedAt(existingUser.getCreatedAt());
                   User savedUser = userRepository.save(updatedUser);
                   return userMapper.convertToDTO(savedUser);
               });
   }

   @Override
   public void deleteUser(Long id) {
       userRepository.deleteById(id);
   }
}
