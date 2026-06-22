package cz.ferdo.equiflow.mapper;

import cz.ferdo.equiflow.dto.UserDTO;
import cz.ferdo.equiflow.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDTO(UserEntity source) {
        UserDTO newUser = new UserDTO(
                source.getUserName(),
                source.getRole()
        );
        newUser.setId(source.getId());
        return newUser;
    }

    public UserEntity toEntity(UserDTO source) {

        UserEntity entity = new UserEntity();

        entity.setUserName(source.getUserName());
        entity.setRole(source.getRole());

        return entity;
    }

    public UserEntity updateEntity(UserDTO source, UserEntity target) {

        target.setUserName(source.getUserName());
        target.setRole(source.getRole());

        return target;
    }
}
