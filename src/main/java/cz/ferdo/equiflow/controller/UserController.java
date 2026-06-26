package cz.ferdo.equiflow.controller;

import cz.ferdo.equiflow.dto.UserDTO;
import cz.ferdo.equiflow.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Vytvoří nového uživatele a uloží jej do databáze.
     *
     * @param user data nového uživatele
     * @return uložený uživatel včetně přiděleného ID
     */
    @PostMapping()
    public UserDTO create(@RequestBody UserDTO user) {
        return userService.create(user);
    }

    /**
     * Vrátí všechny registrované uživatele.
     *
     * @return seznam uživatelů
     */
    @GetMapping()
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    /**
     * Vyhledá uživatele podle jeho identifikátoru.
     *
     * @param id identifikátor uživatele
     * @return nalezený uživatel
     */
    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    /**
     * Aktualizuje údaje existujícího uživatele.
     *
     * @param id identifikátor upravovaného uživatele
     * @param userDTO nové hodnoty uživatele
     * @return aktualizovaný uživatel
     */
    @PutMapping("/{id}")
    public UserDTO update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userService.update(userDTO, id);
    }

    /**
     * Odstraní uživatele z databáze.
     *
     * @param id identifikátor uživatele
     * @return potvrzení o úspěšném smazání
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}
