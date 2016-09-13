package com.poc.neo4j;

import com.poc.neo4j.responses.D3Graph;
import com.poc.neo4j.services.GraphService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class Application {

    //private Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    private final static Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    public GraphService graphService;

    @RequestMapping(value = "/graph", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    D3Graph graph() {
        return graphService.graph(100);
    }


}
