package vtttp.nusiss.MarchMockCalc.controllers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vtttp.nusiss.MarchMockCalc.services.CalculateService;

@RestController
@RequestMapping(path="/calculate",produces=MediaType.APPLICATION_JSON_VALUE)
public class CalculatorController {

    @Autowired
    private CalculateService calcSvc;
    
    @PostMapping
    public ResponseEntity<String>postResult(
        @RequestBody String payload
    ){
        JsonObject body;
        try (InputStream is = new ByteArrayInputStream(payload.getBytes())) {
            
            JsonReader reader = Json.createReader(is);
            body = reader.readObject();
            System.out.println(body.getInt("oper1"));
            Integer oper1 =  body.getInt("oper1");
            Integer oper2 = body.getInt("oper2");
            String ops = body.getString("ops");
            System.out.println("This is first num : " + oper1);
            System.out.println("This is the operation : " + ops);
            System.out.println("This is second num : " + oper2);
            String result = calcSvc.calculateResult(oper1, ops, oper2);
            System.out.println("This is the result : " + result);

            Date date = new Date();
            String dateString = date.toString();
            JsonObject jsonResult = Json.createObjectBuilder()
            .add("result",Integer.parseInt(result))
            .add("timestamp",dateString)
            .add("userAgent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.51 Safari/537.36")
            .build();
            System.out.println(jsonResult.toString());
            return ResponseEntity.ok(jsonResult.toString());

            } catch(Exception ex) {
            body = Json.createObjectBuilder()
            .add("error", ex.getMessage()).build();
            return ResponseEntity.internalServerError().body(body.toString());
            }
    
    
    
        }




}
