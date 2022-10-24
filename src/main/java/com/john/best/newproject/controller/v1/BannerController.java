package com.john.best.newproject.controller.v1;

import com.john.best.newproject.dto.PersonDTO;
import com.john.best.newproject.exception.http.ForbiddenException;
import com.john.best.newproject.exception.http.NotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.Map;

/**
 * @author john
 * @date 2022/10/23
 */
@RestController
@RequestMapping("/v1/banner")
public class BannerController {
    //v1/banner/test/2?name=john
    @GetMapping("/test/{id1}")
    public String get1(@PathVariable(name = "id1") Integer id, @RequestParam(name = "name") String name){
        throw new NotFoundException(10000);
    }

    //v1/banner/test/2?name=john
    @PostMapping("/test2/{id1}")
    public String post1(@PathVariable(name = "id1") Integer id,
                       @RequestParam(name = "name") String name,
                       @RequestBody Map<String,Object> person){
        throw new ForbiddenException(10001);
    }

    //v1/banner/test/2?name=john
    @PostMapping("/test2/{id2}")
    public PersonDTO post2(@PathVariable(name = "id2") Integer id,
                       @RequestParam(name = "name") String name,
                       @RequestBody PersonDTO person){
        //throw new ForbiddenException(1001001);
        return person;
    }
    @GetMapping("/id/{id}/simplify")
    public void getTest01(@PathVariable @Positive(message = "{id.positive}") Long id){

    }




}
