package xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xml.repositories.IUserDAO;


@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private IUserDAO korisnikDAO;

    @RequestMapping("/addOdbornik")
    public String addOdbornik(){
/*
        System.out.print("Usao u metodu addOdbornik");

        Korisnik korisnik = new Korisnik();
        korisnik.setUsername("nekiusername");
        korisnik.setPassword("najtezipassnasvet");
        korisnik.setIme("Branislav");
        korisnik.setPrezime("Cogic");
        korisnik.setUloga("Neradnik");
        korisnik.setId((long) 6);

        try {
            korisnikDAO.create(korisnik,"testing.xml","neka_kolekcija",DatabaseConfig.loadProperties());
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.print("Testing");

*/
        return "OK";
    }

}
