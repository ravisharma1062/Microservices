package io.javabrains.moviecatalogservice.resources;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.netty.handler.logging.LogLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Employee;
import io.javabrains.moviecatalogservice.models.UserRating;
import io.javabrains.moviecatalogservice.services.MovieInfo;
import io.javabrains.moviecatalogservice.services.UserRatingInfo;

@RefreshScope
@RestController
@Api(value = "catalog", description = "Operations pertaining to catalog details for various movies")
@RequestMapping("/catalog")
public class CatalogResource {

    private static final Logger log = LogManager.getLogger(CatalogResource.class);

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

    @GetMapping(path = "/{userId}")
    @ApiOperation(value = "getCatalog", notes = "Method to get movie catalog")
    @CacheEvict(value = "thirty-second-cache", key = "'CatalogCache'+#userId", condition = "!#isCacheable", beforeInvocation = true)
    @Cacheable(value = "thirty-second-cache", key = "'CatalogCache'+#userId", condition = "#isCacheable")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId, @RequestParam boolean isCacheable) throws InterruptedException {
        //Thread.sleep(5000);
        UserRating userRating = userRatingInfo.getUserRating(userId);

        return userRating.getRatings().stream()
                .map(rating -> movieInfo.getCatalogItem(rating))
                .collect(Collectors.toList());

    }

    @ApiOperation(value = "getAllUsers", notes = "Method to get all users")
    @GetMapping("/allUsers")
    public List<String> getAllUsers() {
        return usersList;
    }

    @ApiOperation(value = "getDBDetails", notes = "Method to get DataBase details")
    @GetMapping("/getDBString")
    public Map<String, String> getDBDetails() {
        return dbDetails;
    }

    @ApiOperation(value = "getEmployee", notes = "Method to get the Employee detail")
    @GetMapping("/getEmployee")
    public String getEmployee() {
        return employee.toString();
    }

    @ApiOperation(value = "printLogs", notes = "Prints the log based on log level set and return \"Logged\" in response", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully logged the message"),
            @ApiResponse(code = 401, message = "You are not authorized to log the message"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Log level not found")
    }
    )
    //Purposely made postmapping to demonstrate post request
    @PostMapping("/loggers")
    public ResponseEntity<String> printLogs(String loglevel) {
        loglevel = loglevel.toUpperCase();
        switch (loglevel) {
            case "DEBUG":
                log.debug("This is a debug message");
                break;
            case "INFO":
                log.info("This is an info message");
                break;
            case "WARN":
                log.warn("This is a warn message");
                break;
            case "ERROR":
                log.error("This is an error message");
                break;
            case "FATAL":
                log.fatal("This is a fatal message");
                break;
            case "TRACE":
                log.trace("This is trace message");
                break;
            default:
                log.info("Incorrect loglevel selected");
                return new ResponseEntity<>(loglevel + " logs not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loglevel + " log logged successfully", HttpStatus.OK);
    }
}