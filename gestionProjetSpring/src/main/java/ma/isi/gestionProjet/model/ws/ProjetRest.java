package ma.isi.gestionProjet.model.ws;

import java.util.Collection;

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
import ma.isi.gestionProjet.bean.Projet;
import ma.isi.gestionProjet.model.service.ProjetService;

@RestController
@CrossOrigin( origins = {"http://localhost:4200"})
@RequestMapping("/manageproject/projets")
@Api(description="gérer les projet.")
public class ProjetRest {

 @Autowired
private ProjetService  projetService ;

// @PostMapping("/")
//public void save(@RequestBody Projet projet) {
	//projetService.save(projet);
//}
 @ApiOperation("Cette methode permet de sauvegarder un projet")
 @PostMapping("/")
 public int save(@RequestBody Projet projet) {
	return projetService.save(projet);
}
 
 @ApiOperation("Cette methode permet de mettre à jour un projet")
 @PostMapping("/up")
 public int update(@RequestBody Projet projet) {
     return projetService.update(projet);
 }
 

 @ApiOperation("Cette methode permet de supprimer un projet")
 @GetMapping("/numprojet/{numprojet}")
 public Projet findByNumprojet(@PathVariable int numprojet) {
	return projetService.findByNumprojet(numprojet);
 }
 @ApiOperation("Cette methode permet de trouver tous les projets")
@GetMapping("/")
public Collection<Projet> findAll() {
	return projetService.findAll();
}

 @ApiOperation("Cette methode permet de trouver un projet à partir de son intitulé")
 @GetMapping("/intitule/{intitule}")
public Projet findByIntitule(@PathVariable String intitule) {
	return projetService.findByIntitule(intitule);
}
 
 @ApiOperation("Cette methode permet de supprimer un projet à partir du numprojet")
 @DeleteMapping("/delete/{numprojet}")
public int DeleteByNumprojet(@PathVariable int numprojet) {
	return projetService.deleteByNumprojet(numprojet);
}

public ProjetService getProjetService() {
	return projetService;
}

public void setProjetService(ProjetService projetService) {
	this.projetService = projetService;
}
 
 
}
