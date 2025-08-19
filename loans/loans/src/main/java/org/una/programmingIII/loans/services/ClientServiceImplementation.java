package org.una.programmingIII.loans.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.loans.dtos.ClientDTO;
import org.una.programmingIII.loans.models.Client;
import org.una.programmingIII.loans.repositories.ClientRepository;
import org.una.programmingIII.loans.transformers.GenericMapper;
import org.una.programmingIII.loans.transformers.GenericMapperFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImplementation implements ClientService {

   @Autowired
   private ClientRepository clientRepository;

   @Autowired
   private GenericMapperFactory mapperFactory;

   @Override
   public List<ClientDTO> getAllClients() {
       GenericMapper<Client, ClientDTO> clientMapper = mapperFactory.createMapper(Client.class, ClientDTO.class);
       return clientRepository.findAll().stream()
               .map(clientMapper::convertToDTO)
               .collect(Collectors.toList());
   }

   @Override
   public Optional<ClientDTO> getClientByEmail(String email) {
       GenericMapper<Client, ClientDTO> clientMapper = mapperFactory.createMapper(Client.class, ClientDTO.class);
       return clientRepository.findByEmail(email)
               .map(clientMapper::convertToDTO);
   }

   @Override
   public ClientDTO createClient(ClientDTO clientDTO) {
       GenericMapper<Client, ClientDTO> clientMapper = mapperFactory.createMapper(Client.class, ClientDTO.class);
       Client client = clientMapper.convertToEntity(clientDTO);
       Client savedClient = clientRepository.save(client);
       return clientMapper.convertToDTO(savedClient);
   }

   @Override
   public Optional<ClientDTO> updateClient(Long id, ClientDTO clientDTO) {
       GenericMapper<Client, ClientDTO> clientMapper = mapperFactory.createMapper(Client.class, ClientDTO.class);
       return clientRepository.findById(id)
               .map(existingClient -> {
                   Client updatedClient = clientMapper.convertToEntity(clientDTO);
                   updatedClient.setId(id);
                   updatedClient.setCreatedAt(existingClient.getCreatedAt());
                   Client savedClient = clientRepository.save(updatedClient);
                   return clientMapper.convertToDTO(savedClient);
               });
   }

   @Override
   public void deleteClient(Long id) {
       clientRepository.deleteById(id);
   }
}
