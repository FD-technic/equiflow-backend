package cz.ferdo.equiflow.service;

import cz.ferdo.equiflow.dto.UserDTO;
import cz.ferdo.equiflow.entity.UserEntity;
import cz.ferdo.equiflow.mapper.UserMapper;
import cz.ferdo.equiflow.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    @Override
    public UserDTO addNew(UserDTO user) {

        UserEntity entity = userMapper.toEntity(user);
        UserEntity saved = userRepository.save(entity);

        return userMapper.toDTO(saved);
    }

    @Override
    public UserDTO getById(Long id) {
        UserEntity user = findUser(id);

        return userMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @Transactional
    @Override
    public void remove(Long id) {

        UserEntity user = findUser(id);
        userRepository.delete(user);
    }

    @Transactional
    @Override
    public UserDTO edit(UserDTO userDTO, Long id) {
        UserEntity oldEntity = findUser(id);
        UserEntity newEntity = userMapper.updateEntity(userDTO, oldEntity);

        UserEntity saved = userRepository.save(newEntity);

        return userMapper.toDTO(saved);
    }

    // === PRIVATE ===

    private UserEntity findUser(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
