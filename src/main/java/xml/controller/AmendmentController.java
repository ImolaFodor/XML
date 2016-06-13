package xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xml.Constants;
import xml.model.Amandman;
import xml.repositories.IAmendmentDAO;

import java.util.List;


@RestController
public class AmendmentController{

    @Autowired
    private IAmendmentDAO amendmentDao;

    @RequestMapping(value = "/amandman/{aktId}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Amandman>> getAllAmendmentsFromAct(Long aktId) {
        try{
            List<Amandman> amendments = null;//amendmentDao.getAllFromAct(aktId);
            if(amendments.size() == 0)
                return new ResponseEntity(HttpStatus.NO_CONTENT);

            return new ResponseEntity<List<Amandman>>(amendments,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/amandman/{aktId}" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Amandman> post(@RequestBody Amandman amendment) {
        try{
            amendmentDao.create(amendment, Constants.Amendment+amendment.getId().toString(), Constants.ProposedAmendmentCollection);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
