package net.engineeringdigest.journalApp.Cache;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;
import net.engineeringdigest.journalApp.repository.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class APPCache {

    public enum keys{
        WEATHER_API;
    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String , String> appCache;

    @PostConstruct
    public void init(){
        appCache=new HashMap<>();
        List<ConfigJournalAppEntity> all =configJournalAppRepository.findAll();
        for(ConfigJournalAppEntity configJournalAppEntity: all){
            appCache.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
        }
    }

}
