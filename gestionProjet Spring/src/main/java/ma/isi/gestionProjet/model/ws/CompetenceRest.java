package ma.isi.gestionProjet.model.ws;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.isi.gestionProjet.bean.Collaborateur;
import ma.isi.gestionProjet.bean.Competence;
import ma.isi.gestionProjet.model.service.CompetenceService;

@RestController
@CrossOrigin( origins = {"http://localhost:4200"})
@RequestMapping("/manageproject/competences")
@Api(description="gérer les competences.")
public class CompetenceRest {

 @Autowired
private CompetenceService  competenceService  ;

  
 @ApiOperation("Cette methode permet de sauvegarder une competence")
 @PostMapping("/")
 public int save(@RequestBody Competence competence) {
	return competenceService.save(competence);
    }
 
 @ApiOperation("Cette methode permet de mettre à jour une competence")
 @PostMapping("/up")
 public int update(@RequestBody Competence competence) {
     return competenceService.update(competence);
 }
   
 @ApiOperation("Cette methode permet de supprimer une competence à partir de son titre")
 @DeleteMapping("/del/{titre}")
public int delete(@PathVariable  String titre) {
	return  competenceService.delete(titre);
}

 @ApiOperation("Cette methode permet de trouver une competence à partir de son titre")
 @GetMapping("/titre/{titre}")
public Competence findByTitre(@PathVariable  String titre) {
	return competenceService.findByTitre(titre);
    }

 @ApiOperation("Cette methode permet de trouver tous les competences")
 @GetMapping("/")
 public Collection<Competence> findAll() {
	return competenceService.findAll();
   }


public CompetenceService getCompetenceService() {
	return competenceService;
}

public void setCompetenceService(CompetenceService competenceService) {
	this.competenceService = competenceService;
}

 
 
 
}
