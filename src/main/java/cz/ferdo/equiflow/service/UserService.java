package cz.ferdo.equiflow.service;

import cz.ferdo.equiflow.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDTO addNew(UserDTO user);
    UserDTO getById(Long id);
    List<UserDTO> getAll();
    void remove(Long id);
    UserDTO edit(UserDTO userDTO, Long id);

}
