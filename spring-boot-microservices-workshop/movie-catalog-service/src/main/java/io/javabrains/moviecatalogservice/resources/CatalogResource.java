package io.javabrains.moviecatalogservice.resources;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.ribbon.proxy.annotation.Http;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Employee;
import io.javabrains.moviecatalogservice.models.UserRating;
import io.javabrains.moviecatalogservice.services.MovieInfo;
import io.javabrains.moviecatalogservice.services.UserRatingInfo;

@RefreshScope
@RestController
@RequestMapping("/catalog")
public class CatalogResource {
    
    @Autowired
    private MovieInfo movieInfo;
    
    @Autowired
    private UserRatingInfo userRatingInfo;
    
    @Value("${catalog.service.users}")
    private List<String> usersList;
    
    @Value("#{${catalog.service.db.url}}")
    private Map<String, String> dbDetails;
    
    @Autowired
    private Employee employee;

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    @CacheEvict(value="thirty-second-cache", key="'CatalogCache'+#userId", condition="!#isCacheable", beforeInvocation = true)
    @Cacheable(value="thirty-second-cache", key="'CatalogCache'+#userId", condition="#isCacheable")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId, @RequestParam boolean isCacheable) throws InterruptedException {
    	Thread.sleep(5000);
        UserRating userRating = userRatingInfo.getUserRating(userId);

        return userRating.getRatings().stream()
        		.map(rating -> movieInfo.getCatalogItem(rating))
                .collect(Collectors.toList());

    } 
    
    @RequestMapping("/allUsers")
    public List<String> getAllUsers() {
    	return usersList;
    }
    
    @RequestMapping("/getDBString")
    public Map<String, String> getDBDetails() {
    	return dbDetails;
    }
    
    @RequestMapping("/getEmployee")
    public String getEmployee() {
    	return employee.toString();
    }
}