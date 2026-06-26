package cz.ferdo.equiflow.service;

import cz.ferdo.equiflow.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDTO create(UserDTO user);
    UserDTO findById(Long id);
    List<UserDTO> findAll();
    UserDTO update(UserDTO userDTO, Long id);
    void delete(Long id);

}
