package org.una.programmingIII.loans.services;

import java.util.List;
import java.util.Optional;
import org.una.programmingIII.loans.dtos.ClientDTO;

public interface ClientService {
   List<ClientDTO> getAllClients();
   Optional<ClientDTO> getClientByEmail(String email);
   ClientDTO createClient(ClientDTO clientDTO);
   Optional<ClientDTO> updateClient(Long id, ClientDTO clientDTO);
   void deleteClient(Long id);
}
