package xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import security.PasswordStorage;
import xml.Constants;
import xml.stateStuff.State;
import xml.stateStuff.StateManager;
import xml.model.Korisnik;
import xml.repositories.IUserDAO;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;


@RestController
@RequestMapping("/korisnik")
public class UserController {

    @Autowired
    private IUserDAO userDao;

    @RequestMapping(value = "/svi" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Korisnik>> getAll() {
        try{
            List<Korisnik> korisnici = userDao.getAll();
            if(korisnici == null)
                return new ResponseEntity<List<Korisnik>>(HttpStatus.NO_CONTENT);

            return  new ResponseEntity<List<Korisnik>>(korisnici,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<List<Korisnik>>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/dodaj",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity post(@RequestBody Korisnik korisnik){
        //prvo bez validacije

        /*byte[] salt = new byte[0];
        try {
            salt = PasswordStorage.generateSalt();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }

        korisnik.setSalt(PasswordStorage.base64Encode(salt));


        byte[] hashedPassword = new byte[0];
        try {
            hashedPassword = PasswordStorage.hashPassword(korisnik.getPassword(), salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }

        korisnik.setPassword(PasswordStorage.base64Encode(hashedPassword));*/





        try {
            userDao.create(korisnik, Constants.User + korisnik.getId().toString(),Constants.UsersCollection);
            return new ResponseEntity(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (JAXBException e) {
            return new ResponseEntity<Korisnik>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> getById(@PathVariable("id")Long id){

        try {
            Korisnik user = null;
            try {
                user = userDao.get(id);
            } catch (JAXBException e) {
                return new ResponseEntity<Korisnik>(HttpStatus.NO_CONTENT);
            }
            if(user == null){
                return new ResponseEntity<Korisnik>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<Korisnik>(user,HttpStatus.OK);
            }
        } catch (IOException e) {
            return new ResponseEntity<Korisnik>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> getByLogin(@RequestBody Korisnik korisnik){
        if(korisnik == null)
            return new ResponseEntity<Korisnik>(HttpStatus.OK);
        try {
            Korisnik user = userDao.getByLogin(korisnik.getUsername(),korisnik.getPassword());
            if(user == null){
                return new ResponseEntity<Korisnik>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<Korisnik>(user,HttpStatus.OK);
            }
        } catch (IOException e) {
            return new ResponseEntity<Korisnik>(HttpStatus.BAD_REQUEST);
        } catch (JAXBException e) {
            return new ResponseEntity<Korisnik>(HttpStatus.NO_CONTENT);
        }
    }

    //for presidend
    @RequestMapping(value = "/state",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getState(){
        State state = StateManager.getState();
        if(state == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        return new ResponseEntity(state,HttpStatus.OK);
    }

    @RequestMapping(value = "/state",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity setState(@RequestBody State state){

        if(StateManager.setState(state.getState()))
            return new ResponseEntity(state,HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }




}
