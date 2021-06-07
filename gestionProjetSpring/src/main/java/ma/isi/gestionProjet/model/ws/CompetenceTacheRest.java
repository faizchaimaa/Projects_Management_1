package ma.isi.gestionProjet.model.ws;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import ma.isi.gestionProjet.bean.CompetenceCollaborateur;
import ma.isi.gestionProjet.bean.CompetenceTache;
import ma.isi.gestionProjet.bean.Tache;
import ma.isi.gestionProjet.model.service.CompetenceTacheService;
import ma.isi.gestionProjet.model.ws.converter.AbstractConverter;
import ma.isi.gestionProjet.model.ws.vo.CollaborateurVo;
import ma.isi.gestionProjet.model.ws.vo.CompetenceCollaborateurVo;
import ma.isi.gestionProjet.model.ws.vo.CompetenceTacheVo;
import ma.isi.gestionProjet.model.ws.vo.CompetenceVo;
import ma.isi.gestionProjet.model.ws.vo.TacheVo;

@RestController
@CrossOrigin( origins = {"http://localhost:4200"})
@RequestMapping("/manageproject/competencetaches")
@Api(description="gérer les competencestache.")
public class CompetenceTacheRest {

 @Autowired
private CompetenceTacheService  competenceTacheService    ;
 @Autowired
 @Qualifier("competenceTacheConverter")
 private AbstractConverter<CompetenceTache, CompetenceTacheVo> competenceTacheConverter;
 @Autowired
 @Qualifier("competenceConverter")
 private AbstractConverter<Competence, CompetenceVo> competenceConverter;
 @Autowired
 @Qualifier("tacheConverter")
 private AbstractConverter<Tache, TacheVo> tacheConverter;
 
 @ApiOperation("Cette methode permet de sauvegarder une competence")
 @PostMapping("/")
public int save(@RequestBody CompetenceTacheVo competenceTacheVo) {
	 CompetenceTache ct=competenceTacheConverter.toItem(competenceTacheVo); 
	return competenceTacheService.save(ct);
 }
 @ApiOperation("Cette methode permet de supprimer une competenceTache avec la tache et la competence assosiés")
 @DeleteMapping("/del/{id}")
public void delete(@PathVariable Long id) {
	competenceTacheService.delete(id);
} 
 @ApiOperation("Cette methode permet de trouver une competenceTache à partir de son id")
public CompetenceTache findById(Long id) {
	return competenceTacheService.findById(id);
}

 @ApiOperation("Cette methode permet de trouver une competencetache à partir de sa description et son intitule")
 @GetMapping("/description/{description}/intitule/{intitule}")
public CompetenceTache findByCompetenceTitreAndTacheIntitule(@PathVariable String titre, @PathVariable String intitule) {
	return competenceTacheService.findByCompetenceTitreAndTacheIntitule(titre, intitule);
}


 
 @ApiOperation("Cette methode permet de trouver tous les taches competences")
 @GetMapping("/")
 public Collection<CompetenceTache> findAll() {
	return competenceTacheService.findAll();
   }


 @GetMapping("/findByTache/{id}")
 public Collection<CompetenceTache> findByTache(@PathVariable Long id) {
	return competenceTacheService.findByTache(id);
   }
 
public CompetenceTacheService getCompetenceTacheService() {
	return competenceTacheService;
}


public void setCompetenceTacheService(CompetenceTacheService competenceTacheService) {
	this.competenceTacheService = competenceTacheService;
}
public AbstractConverter<CompetenceTache, CompetenceTacheVo> getCompetenceTacheConverter() {
	return competenceTacheConverter;
}
public void setCompetenceTacheConverter(
		AbstractConverter<CompetenceTache, CompetenceTacheVo> competenceTacheConverter) {
	this.competenceTacheConverter = competenceTacheConverter;
}
public AbstractConverter<Competence, CompetenceVo> getCompetenceConverter() {
	return competenceConverter;
}
public void setCompetenceConverter(AbstractConverter<Competence, CompetenceVo> competenceConverter) {
	this.competenceConverter = competenceConverter;
}
public AbstractConverter<Tache, TacheVo> getTacheConverter() {
	return tacheConverter;
}
public void setTacheConverter(AbstractConverter<Tache, TacheVo> tacheConverter) {
	this.tacheConverter = tacheConverter;
}

 
 

 

 
 
 
}
