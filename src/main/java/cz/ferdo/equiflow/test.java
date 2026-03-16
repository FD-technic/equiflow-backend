package cz.ferdo.equiflow;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {

    @GetMapping("/")
    public String hello() {
        return "Backend běží";
    }
    @GetMapping("/cislo")
    public int cislo() {
        return 42;
    }
    @GetMapping("/person")
    public Person person() {
        return new Person("Ferdo", 32);
    }


}
