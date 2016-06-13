package xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.Constants;
import xml.model.PravniAkt;
import xml.repositories.IActDAO;

import javax.ws.rs.Path;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;


@RestController
public class ActController{

    @Autowired
    private IActDAO aktDao;

    @RequestMapping(value = "/akt" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PravniAkt>> getAll() {
        try{
            List<PravniAkt> akati = aktDao.getAll();
            if(akati.size() == 0)
                return new ResponseEntity<List<PravniAkt>>(HttpStatus.NO_CONTENT);

            return  new ResponseEntity<List<PravniAkt>>(akati,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<List<PravniAkt>>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/akt/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@PathVariable("id") Long id) {
        try{
            PravniAkt akt = aktDao.get(id);
            if(akt == null)
                return new ResponseEntity<List<PravniAkt>>(HttpStatus.NO_CONTENT);

            return new ResponseEntity(akt,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/akt", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity post(@RequestBody PravniAkt object) {
        try{
            aktDao.create(object,Constants.Act+object.getId().toString(), Constants.ActCollection);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/akt/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity put(@RequestBody PravniAkt object,@PathVariable("id") Long id) {
        try{
            aktDao.update(object,id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/akt/brisi/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public void delete(@PathVariable("id") Long id){
        System.out.print(id);
        try {
            aktDao.delete(id,Constants.Act);
            System.out.print("Successfully deleted from db");
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
