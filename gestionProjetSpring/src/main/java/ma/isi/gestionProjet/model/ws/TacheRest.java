package ma.isi.gestionProjet.model.ws;


import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.isi.gestionProjet.bean.Collaborateur;
import ma.isi.gestionProjet.bean.Projet;
import ma.isi.gestionProjet.bean.Tache;
import ma.isi.gestionProjet.model.service.TacheService;

 

@RestController
@CrossOrigin( origins = {"http://localhost:4200"})
@RequestMapping("/manageproject/tache")
@Api(description="gérer les taches.")
public class TacheRest {

 @Autowired
private TacheService tacheService ;

 @ApiOperation("Cette methode permet de sauvegarder une tache")
 @PostMapping("/")
 public int saveTache(@RequestBody Tache tache) {
	return tacheService.saveTache(tache);
}
 //update(not working)
 
 @PostMapping("/up")
 public int update(@RequestBody Tache tache) {
	return tacheService.update(tache);
}
 //delete
 @ApiOperation("Cette methode permet de  supprimer une tache")
 @DeleteMapping("/del/{intitule}")
public int delete(@PathVariable String intitule) {
	return tacheService.delete(intitule);
}
 @ApiOperation("Cette methode permet de  supprimer une tache a partir du numProjet")
 @DeleteMapping("/delnumprojet/{numprojet}")
 public int deleteByProjetNumprojet(@PathVariable int numprojet) {
	return tacheService.deleteByProjetNumprojet(numprojet);
}
 @ApiOperation("Cette methode permet de trouver une tache a partir du numProjet")
 @GetMapping("/delnumprojet/{numprojet}")
public Tache findByProjetNumprojet(@PathVariable int numprojet) {
	return tacheService.findByProjetNumprojet(numprojet);
}

@ApiOperation("Cette methode permet de trouver tache")
@GetMapping("/")
public Collection<Tache> findAll() {
	return tacheService.findAll();
}
 
/*public int save(Projet projet, Collection<Tache> taches) {
	return tacheService.save(projet, taches);
}*/

 
public Collection<Tache> findByProjet(Projet projet) {
	return tacheService.findByProjet(projet);
}

@ApiOperation("Cette methode permet de trouver tache à partir de son intitulé")
@GetMapping("/intitule/{intitule}")
public Tache findByIntitule(@PathVariable String intitule) {
	return tacheService.findByIntitule(intitule);
}

@ApiOperation("Cette methode permet de trouver la liste des collabs à affecter")
@GetMapping("/CollabsAaffecterById/{id}")
public Collection<Collaborateur> CollabAaffecter(@PathVariable Long id) {
	return tacheService.CollabAaffecter(id);
}


public TacheService getTacheService() {
	return tacheService;
}


public void setTacheService(TacheService tacheService) {
	this.tacheService = tacheService;
}
 
 
}
